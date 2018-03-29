package afrs;

import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.RequestGenerator;
import afrs.uiview.TerminalClient;
import java.util.Scanner;

/**
 * application class that uses the input-output service through command line
 */
public class TestApplication {
  private StorageCenter storageCenter;
  private RequestGenerator requestGenerator;

  public static void main(String[] args) {
    new TestApplication();
  }

  public TestApplication() {
    this.storageCenter = new StorageCenter();
    this.requestGenerator = new RequestGenerator(storageCenter);

    TerminalClient client = new TerminalClient(storageCenter, requestGenerator);
    System.out.println("Welcome [" + client.getID() + "] to AFRS!");

    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    while (!input.equals("quit;")) {
      if (input.equals("connect;")) {
        client.disconnect();
        client = new TerminalClient(storageCenter, requestGenerator);
        System.out.println("Welcome [" + client.getID() + "] to AFRS!");
      } else {
        System.out.println(client.doRequest(input));
      }
      input = in.nextLine();
    }
  }
}
