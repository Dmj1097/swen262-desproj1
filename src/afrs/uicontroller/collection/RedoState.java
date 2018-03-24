package afrs.uicontroller.collection;

import afrs.uicontroller.requests.Request;

public class RedoState extends RequestState {

  RedoState(final RequestCollection instance) {
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
  public void redo() {
    instance.redoRequest();
    if (instance.canRedo()) {
      instance.changeState(instance.getUndoRedoState());
    } else {
      instance.changeState(instance.getUndoState());
    }
  }
}
