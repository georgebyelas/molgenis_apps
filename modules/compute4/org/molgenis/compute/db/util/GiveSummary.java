package org.molgenis.compute.db.util;

import app.DatabaseFactory;
import org.molgenis.compute.runtime.ComputeTask;
import org.molgenis.framework.db.Database;
import org.molgenis.framework.db.DatabaseException;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: georgebyelas Date: 23/08/2012 Time: 12:04
 * To change this template use File | Settings | File Templates.
 */
public class GiveSummary
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

            System.out.println("JOBS SUMMARY");
            int intGen = 0, intReady = 0, intRun = 0, intFail = 0, intDone = 0;

			for (ComputeTask task : tasks)
			{
                if(task.getStatusCode().equalsIgnoreCase("failed"))
                    intFail++;
                else if(task.getStatusCode().equalsIgnoreCase("generated"))
                    intGen++;
                else if(task.getStatusCode().equalsIgnoreCase("done"))
                    intDone++;
                else if(task.getStatusCode().equalsIgnoreCase("running"))
                    intRun++;
                else if(task.getStatusCode().equalsIgnoreCase("ready"))
                    intReady++;
			}

			db.commitTx();
            System.out.println("GENERATED:  " + intGen);
            System.out.println("READY:      " + intReady);
            System.out.println("RUNNING:    " + intRun);
            System.out.println("FAILED:     " + intFail);
            System.out.println("DONE:       " + intDone);

		}
		catch (DatabaseException e)
		{
			e.printStackTrace();
		}

	}
}
