package afrs;

import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.RequestGenerator;
import java.util.Scanner;
import java.util.UUID;

public class Terminal {
  private String clientID;
  private RequestGenerator requestGenerator;
  private StorageCenter storageCenter;

  public Terminal(final RequestGenerator requestGenerator, final StorageCenter storageCenter) {
    this.requestGenerator = requestGenerator;
    this.storageCenter = storageCenter;

    this.clientID = UUID.randomUUID().toString().substring(0, 5);
    while (storageCenter.getClientServices(clientID) != null) {
      this.clientID = UUID.randomUUID().toString().substring(0, 5);
    }
    storageCenter.connectClient(clientID);
  }

  public void run() {
    System.out.println("Welcome to AFRS!");

    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    while (!input.equals("quit")) {
      requestGenerator.parseRequest(clientID, input);
      input = in.nextLine();
    }

    storageCenter.disconnectClient(clientID);
  }
}
