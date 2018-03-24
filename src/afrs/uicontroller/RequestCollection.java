package afrs.uicontroller;

import java.util.Iterator;

/**
 * A collection of Requests, ordered such that they may be undone and redone.
 *
 * @author Alex Piazza
 */
public class RequestCollection implements RequestAggregator {

	private RequestIterator iterator;
	private RequestState state;

	public RequestIterator getIterator(){ return null; }
	public void add( Request request ){}
	public void undo(){}
	public void redo(){}

}
