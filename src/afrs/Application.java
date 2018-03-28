package afrs;

import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.RequestGenerator;
import afrs.uicontroller.RequestHandler;
import afrs.uiview.ResponseHandler;

public class Application {

  private static RequestGenerator requestGenerator;
  private static ResponseHandler responseHandler;
  private static RequestHandler requestHandler;
  private static StorageCenter storageCenter;

  public static void main(String[] args) {
    System.out.println("Welcome to AFRS!");
    storageCenter = new StorageCenter();
    responseHandler = new ResponseHandler();
    requestHandler = new RequestHandler(storageCenter, responseHandler);
    requestGenerator = new RequestGenerator(storageCenter, requestHandler);

    //System.out.println(PassengerStorage.class.getProtectionDomain().getCodeSource().getLocation());

    new Terminal(requestGenerator, storageCenter).run();
    new Terminal(requestGenerator, storageCenter).run();
    storageCenter.close();
  }
}
