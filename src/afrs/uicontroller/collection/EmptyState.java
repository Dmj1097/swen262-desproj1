package afrs.uicontroller.collection;

import afrs.uicontroller.requests.Request;

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
  public void undo() {}

  @Override
  public void redo() {}
}
