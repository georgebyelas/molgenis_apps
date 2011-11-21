/* Date:        November 21, 2011
 * Template:	MapperDecoratorGen.java.ftl
 * generator:   org.molgenis.generators.db.MapperDecoratorGen 4.0.0-testing
 *
 * THIS FILE IS A TEMPLATE. PLEASE EDIT :-)
 */

package org.molgenis.scrum.decorators;

import java.sql.SQLException;
import java.util.List;

import org.molgenis.framework.db.DatabaseException;

import org.molgenis.framework.db.jdbc.MappingDecorator;

import org.molgenis.framework.db.Mapper;


public class TaskDecorator<E extends org.molgenis.scrum.Task> extends MappingDecorator<E>
{
	//Mapper is the generate thing
	public TaskDecorator(Mapper generatedMapper)
	{
		super(generatedMapper);
	}

	@Override
	public int add(List<E> entities) throws DatabaseException
	{
		// add your pre-processing here, e.g.
		// for (org.molgenis.scrum.Task e : entities)
		// {
		//  	e.setTriggeredField("Before add called!!!");
		// }

		// here we call the standard 'add'
		int count = super.add(entities);

		// add your post-processing here
		// if you throw and exception the previous add will be rolled back

		return count;
	}

	@Override
	public int update(List<E> entities) throws DatabaseException
	{

		// add your pre-processing here, e.g.
		// for (org.molgenis.scrum.Task e : entities)
		// {
		// 		e.setTriggeredField("Before update called!!!");
		// }

		// here we call the standard 'update'
		int count = super.update(entities);

		// add your post-processing here
		// if you throw and exception the previous add will be rolled back

		return count;
	}

	@Override
	public int remove(List<E> entities) throws DatabaseException
	{
		// add your pre-processing here

		// here we call the standard 'remove'
		int count = super.remove(entities);

		// add your post-processing here, e.g.
		// if(true) throw new SQLException("Because of a post trigger the remove is cancelled.");

		return count;
	}
}
