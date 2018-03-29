package afrs.uiview;

import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.RequestGenerator;
import afrs.uicontroller.requests.Request;
import java.util.UUID;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

public class TerminalClient {

  private String clientID;
  private StorageCenter storageCenter;
  private RequestGenerator requestGenerator;

  private TextArea output;
  private Tab tab;

  public String getID() {
    return clientID;
  }
  public TextArea getOutput() {
    return output;
  }
  public Tab getTab() {
    return tab;
  }

  public TerminalClient(final StorageCenter storageCenter, final RequestGenerator requestGenerator) {
    this.storageCenter = storageCenter;
    this.requestGenerator = requestGenerator;

    this.clientID = UUID.randomUUID().toString().substring(0, 5);
    while (storageCenter.getClientServices(clientID) != null) {
      this.clientID = UUID.randomUUID().toString().substring(0, 5);
    }
    storageCenter.connectClient(clientID);

    this.output = new TextArea("Welcome [" + clientID + "] to AFRS!\n");
    output.setEditable(false);
    this.tab = new Tab(String.format("Client: %s", clientID ));
  }

  public void doRequestGUI(String input) {
      Request request = requestGenerator.parseRequest(clientID, input);
      output.appendText(request.execute().getText() + "\n");
  }

  public String doRequest(String input) {
    Request request = requestGenerator.parseRequest(clientID, input);
    return request.execute().getText();
  }

  public void clearPartial() {
    storageCenter.getClientServices(clientID).clearPartial();
  }

  public void disconnect() {
    storageCenter.disconnectClient(clientID);
  }
}
