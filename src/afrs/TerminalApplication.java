package afrs;

import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.RequestGenerator;
import afrs.uiview.TerminalClient;
import java.util.Scanner;

/**
 * application class that uses the input-output service through command line
 * Brian Taylor
 */
public class TerminalApplication {

  public static void main(String[] args) {
    StorageCenter storageCenter = new StorageCenter();
    RequestGenerator requestGenerator = new RequestGenerator(storageCenter);
    new TerminalApplication(storageCenter, requestGenerator);
  }

  public TerminalApplication(final StorageCenter storageCenter,
                             final RequestGenerator requestGenerator) {
    TerminalClient client = new TerminalClient(storageCenter, requestGenerator, false);
    System.out.println("Welcome [" + client.getID() + "] to AFRS!");

    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    while (!input.equals("quit;")) {
      if (input.equals("connect;")) {
        client.disconnect();
        client = new TerminalClient(storageCenter, requestGenerator, false);
        System.out.println("Welcome [" + client.getID() + "] to AFRS!");
      } else {
        System.out.println(client.doRequest(input));
      }
      input = in.nextLine();
    }
  }
}
