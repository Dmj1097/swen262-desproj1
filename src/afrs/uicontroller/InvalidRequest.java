package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import java.util.List;

public class InvalidRequest extends Request {

  /**
   * Create a new Request object
   */
  public InvalidRequest(StorageCenter storageCenter, List<String> parameters) {
    super(storageCenter, parameters);
  }

  @Override
  public String execute() {
    complete = true;
    return "error,unknown request";
  }
}
