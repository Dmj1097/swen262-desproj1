package afrs.uicontroller;

import afrs.appcontroller.StorageCenter;
import afrs.uiview.Response;

public abstract class Receiver {
  protected StorageCenter storageCenter;

  protected Receiver(StorageCenter storageCenter) {
    this.storageCenter = storageCenter;
  }

  public abstract Response execute();
}
