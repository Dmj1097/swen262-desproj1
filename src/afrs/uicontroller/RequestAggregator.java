package afrs.uicontroller;

import java.util.Iterator;

/**
 * Defines an interface of a collection of requests
 * Has an iterator
 *
 * @author Alex Piazza
 */
public interface RequestAggregator {

	public Iterator getIterator();
	public void add( Request request );
	public void undo();
	public void redo();

}
