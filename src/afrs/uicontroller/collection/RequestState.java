package afrs.uicontroller.collection;

import afrs.uicontroller.requests.Request;

/**
 * State for determining which operations can be performed after undoing/redoing
 *
 * @author Brian Taylor
 */
public abstract class RequestState {
  protected RequestCollection instance;

  RequestState(final RequestCollection instance) {
    this.instance = instance;
  }

  /** Add a new request to the collection */
  public abstract void add(Request request);

  /** Undo the most recently done request, if there is one */
  public abstract boolean undo();

  /** Redo the most recently undone request, if there is one */
  public abstract boolean redo();
}
