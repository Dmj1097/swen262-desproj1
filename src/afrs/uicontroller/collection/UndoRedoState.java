package afrs.uicontroller.collection;

import afrs.uicontroller.requests.Request;

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
  public void undo() {
    instance.undoRequest();
    if (!instance.canUndo()) {
      instance.changeState(instance.getRedoState());
    }
  }

  @Override
  public void redo() {
    instance.redoRequest();
    if (!instance.canRedo()) {
      instance.changeState(instance.getUndoState());
    }
  }
}
