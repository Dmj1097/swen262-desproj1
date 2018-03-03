package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.List;

public class InvalidRequest extends Request {

  /**
   * Create a new Request object
   */
  protected InvalidRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
  }

  @Override
  public Response execute() {
    Response response = new Response();
    return response;
  }
}
