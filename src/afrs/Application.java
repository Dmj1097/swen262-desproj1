package afrs;

import afrs.appcontroller.StorageCenter;
import afrs.appcontroller.Clients;
import afrs.uicontroller.RequestGenerator;
import afrs.uicontroller.RequestHandler;
import afrs.uiview.ResponseHandler;
import java.util.Scanner;

public class Application {

  private static RequestGenerator requestGenerator;
  private static ResponseHandler responseHandler;
  private static RequestHandler requestHandler;
  private static StorageCenter storageCenter;

  public static void main(String[] args) {
    System.out.println("Welcome to AFRS!");
    storageCenter = new StorageCenter();
    responseHandler = new ResponseHandler();
    requestHandler = new RequestHandler(responseHandler);
    requestGenerator = new RequestGenerator(storageCenter, requestHandler);

    //System.out.println(PassengerStorage.class.getProtectionDomain().getCodeSource().getLocation());

    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    while (!input.equals("quit")) {
      requestGenerator.parseRequest(input);
      input = in.nextLine();
    }

    storageCenter.close();

  }
}
