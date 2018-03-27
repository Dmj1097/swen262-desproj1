package afrs.uicontroller.requests;

import afrs.uicontroller.RequestHandler;
import afrs.uiview.Response;

/**
 * AirportInfoRequest
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class UndoRedoRequest extends Request {

  private RequestHandler requestHandler;
  private boolean undo;

  /**
   * Create a new AirportInfoRequest object
   *
   * @param requestHandler the RequestHandler instance
   */
  public UndoRedoRequest(RequestHandler requestHandler, boolean undo) {
    super(null, null);
    this.requestHandler = requestHandler;
    this.undo = undo;
  }

  /**
   * Executes the command
   *
   * @return the command's response
   */
  @Override
  public Response execute() {
    // If successful undo
    boolean check = (undo) ? requestHandler.undo() : requestHandler.redo();
    if (check) {
      return new Response("");
    } else {
      return new Response("error,no request available");
    }
  }
}
