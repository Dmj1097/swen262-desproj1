package afrs.uicontroller.collection;

import afrs.uicontroller.requests.Request;

/**
 * state when an undo is in process
 */
public class UndoState extends RequestState {

  UndoState(final RequestCollection instance) {
    super(instance);
  }

  @Override
  public void add(Request request) {
    instance.addRequest(request);
  }

  @Override
  public boolean undo() {
    instance.undoRequest();
    if (instance.canUndo()) {
      instance.changeState(instance.getUndoRedoState());
    } else {
      instance.changeState(instance.getRedoState());
    }
    return true;
  }

  @Override
  public boolean redo() {
    return false;
  }
}
