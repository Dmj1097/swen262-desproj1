package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;
import java.util.List;

public class InvalidRequest extends Request {

  /**
   * Create a new Request object
   */
  public InvalidRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
  }

  @Override
  public Response execute() {
    complete = true;
    return new Response("error,unknown request");
  }
}
