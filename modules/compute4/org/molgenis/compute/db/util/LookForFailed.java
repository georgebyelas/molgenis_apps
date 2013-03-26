package org.molgenis.compute.db.util;

import app.DatabaseFactory;
import org.molgenis.compute.runtime.ComputeHistory;
import org.molgenis.compute.runtime.ComputeTask;
import org.molgenis.framework.db.Database;
import org.molgenis.framework.db.DatabaseException;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: georgebyelas Date: 23/08/2012 Time: 12:04
 * To change this template use File | Settings | File Templates.
 */
public class LookForFailed
{
	public static void main(String[] args)
	{
		Database db = null;
		List<ComputeTask> tasks = null;

		try
		{
			db = DatabaseFactory.create();
			db.beginTx();

			tasks = db.query(ComputeTask.class).find();

            System.out.println("Change statuses of failed jobs");

			for (ComputeTask task : tasks)
			{
                if(task.getStatusCode().equalsIgnoreCase("failed"))
                {
                    //create history
                    ComputeHistory history = new ComputeHistory();
                    history.setComputeTask(task);
                    history.setOutputFile(task.getRunLog());
                    Date date = new Date();
                    history.setStatusTime(date);
                    history.setStatusCode(task.getStatusCode());
                    db.add(history);

                    //mark job as generated
				    task.setStatusCode("generated");
                    task.setRunLog("");
                    System.out.println(task.getName() + " >>> changed from failed to generated");
                }
			}

			db.commitTx();
            System.out.println("... statuses submitted");

		}
		catch (DatabaseException e)
		{
			e.printStackTrace();
		}

	}
}
