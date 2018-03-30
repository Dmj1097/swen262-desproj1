package afrs.uicontroller.collection;

import afrs.uicontroller.requests.Request;

/**
 * state when both redo and undo stacks are empty
 * Brian Taylor
 */
public class EmptyState extends RequestState {

  EmptyState(final RequestCollection instance) {
    super(instance);
  }


  @Override
  public void add(Request request) {
    instance.addRequest(request);
    instance.changeState(instance.getUndoState());
  }

  @Override
  public boolean undo() {
    return false;
  }

  @Override
  public boolean redo() {
    return false;
  }
}
