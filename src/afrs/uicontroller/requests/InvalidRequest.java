package afrs.uicontroller.requests;

import afrs.uiview.Response;

/**
 * InvalidRequest
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class InvalidRequest extends Request {

  private String message;

  /**
   * Create a new Request object
   */
  public InvalidRequest(String message) {
    super(null, null);
    this.message = message;
  }

  /**
   * Nothing happens, invalid request
   *
   * @return the command's response
   */
  @Override
  public Response execute() {
    return new Response(message);
  }
}
