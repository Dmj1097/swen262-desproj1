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

  public abstract void add(Request request);
  public abstract void undo();
  public abstract void redo();
}
