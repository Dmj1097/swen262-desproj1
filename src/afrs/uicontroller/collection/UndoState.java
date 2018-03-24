package afrs.uicontroller.collection;

import afrs.uicontroller.requests.Request;

public class UndoState extends RequestState {

  UndoState(final RequestCollection instance) {
    super(instance);
  }


  @Override
  public void add(Request request) {
    instance.addRequest(request);
  }

  @Override
  public void undo() {
    instance.undoRequest();
    if (instance.canUndo()) {
      instance.changeState(instance.getUndoRedoState());
    } else {
      instance.changeState(instance.getRedoState());
    }
  }

  @Override
  public void redo() {}
}
