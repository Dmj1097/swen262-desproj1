package afrs.uicontroller;

import afrs.uiview.Response;

/**
 * InvalidRequest
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class InvalidRequest extends Request {

  /**
   * Create a new Request object
   */
  public InvalidRequest() {
    super(null, null);
  }

  /**
   * Nothing happens, invalid request
   *
   * @return the command's response
   */
  @Override
  public Response execute() {
    complete = true;
    return new Response("error,unknown request");
  }
}
