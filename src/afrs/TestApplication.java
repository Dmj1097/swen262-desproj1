package afrs;

import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.RequestGenerator;
import afrs.uiview.TerminalClient;
import java.util.Scanner;

/**
 * application class that uses the input-output service through command line
 * Brian Taylor
 */
public class TestApplication {

  public static void main(String[] args) {
    StorageCenter storageCenter = new StorageCenter();
    RequestGenerator requestGenerator = new RequestGenerator(storageCenter);
    new TestApplication(storageCenter, requestGenerator);
  }

  public TestApplication(final StorageCenter storageCenter,
      final RequestGenerator requestGenerator) {
    TerminalClient client = new TerminalClient(storageCenter, requestGenerator, false);
    System.out.println("Welcome [" + client.getID() + "] to AFRS!");

    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    while (!input.equals("quit;")) {
      System.out.println(client.doRequest(input));
      input = in.nextLine();
    }
  }
}
