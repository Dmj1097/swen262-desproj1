package afrs.uicontroller.collection;

import afrs.uicontroller.requests.Request;
import java.util.Stack;

/**
 * A collection of Requests, ordered such that they may be undone and redone.
 *
 * @author Brian Taylor
 */
public class RequestCollection {

	private Stack<Request> undoStack;
	private Stack<Request> redoStack;


  private RequestState c_state;
  private EmptyState emptyState;
	private UndoState undoState;
	private RedoState redoState;
	private UndoRedoState undoRedoState;

  public EmptyState getEmptyState() {
    return emptyState;
  }
  public UndoState getUndoState() {
    return undoState;
  }
  public RedoState getRedoState() {
    return redoState;
  }
  public UndoRedoState getUndoRedoState() {
    return undoRedoState;
  }

  public RequestCollection() {
		this.emptyState = new EmptyState(this);
		this.undoState = new UndoState(this);
		this.redoState = new RedoState(this);
		this.undoRedoState = new UndoRedoState(this);
		this.c_state = emptyState;

		this.undoStack = new Stack<>();
		this.redoStack = new Stack<>();
	}

  // Public functions
  public void add(Request request) {
    c_state.add(request);
  }
  public void undo() {
    c_state.undo();
  }
  public void redo() {
    c_state.redo();
  }

  // Internal functions used by states
  void changeState(RequestState state) {
    this.c_state = state;
  }
	boolean canUndo() {
	  return !undoStack.isEmpty();
  }
  boolean canRedo() {
	  return !redoStack.isEmpty();
  }

	void addRequest(Request request) {
		undoStack.push(request);
		redoStack.clear();
	}
	void undoRequest() {
		Request r = undoStack.pop();
		//r.undo();
		redoStack.push(r);
	}
	void redoRequest() {
		Request r = redoStack.pop();
		//r.redo();
		undoStack.push(r);
	}
}
