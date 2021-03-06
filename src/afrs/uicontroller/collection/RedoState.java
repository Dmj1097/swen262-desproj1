package afrs.uicontroller.collection;

import afrs.uicontroller.requests.Request;

/**
 * state when a redo is called for
 * Brian Taylor
 */
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
  public boolean undo() {
    return false;
  }

  @Override
  public boolean redo() {
    instance.redoRequest();
    if (instance.canRedo()) {
      instance.changeState(instance.getUndoRedoState());
    } else {
      instance.changeState(instance.getUndoState());
    }
    return true;
  }
}
