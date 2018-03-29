package afrs.uicontroller.collection;

import afrs.uicontroller.requests.Request;

/**
 * state when an undoredorequest is made
 */
public class UndoRedoState extends RequestState {

  UndoRedoState(final RequestCollection instance) {
    super(instance);
  }


  @Override
  public void add(Request request) {
    instance.addRequest(request);
    instance.changeState(instance.getUndoState());
  }

  @Override
  public boolean undo() {
    instance.undoRequest();
    if (!instance.canUndo()) {
      instance.changeState(instance.getRedoState());
    }
    return true;
  }

  @Override
  public boolean redo() {
    instance.redoRequest();
    if (!instance.canRedo()) {
      instance.changeState(instance.getUndoState());
    }
    return true;
  }
}
