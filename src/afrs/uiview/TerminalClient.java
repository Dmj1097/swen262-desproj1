package afrs.uiview;

import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.RequestGenerator;
import afrs.uicontroller.requests.Request;
import java.util.UUID;
import javafx.scene.CacheHint;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Class that represents the GUI used for having one or multiple clients connected to the system at once
 * Brian Taylor
 */
public class TerminalClient {

  private String clientID;
  private StorageCenter storageCenter;
  private RequestGenerator requestGenerator;

  private TextField input;
  private TextArea output;
  private Tab tab;

  public String getID() {
    return clientID;
  }

  public TextField getInput() {
    return input;
  }

  public TextArea getOutput() {
    return output;
  }

  public Tab getTab() {
    return tab;
  }

  public TerminalClient(final StorageCenter storageCenter, final RequestGenerator requestGenerator,
      boolean gui) {
    this.storageCenter = storageCenter;
    this.requestGenerator = requestGenerator;

    this.clientID = UUID.randomUUID().toString().substring(0, 5);
    while (storageCenter.getClient(clientID) != null) {
      this.clientID = UUID.randomUUID().toString().substring(0, 5);
    }
    storageCenter.connectClient(clientID);

    if (gui) {
      this.input = new TextField();
      input.setPromptText("Input commands here!");
      HBox.setHgrow(input, Priority.ALWAYS);
      input.setOnAction(event -> submit());
      input.setCache(true);
      input.setCacheHint(CacheHint.SPEED);

      this.output = new TextArea("Welcome [" + clientID + "] to AFRS!\n");
      output.setEditable(false);
      output.setCache(true);
      output.setCacheHint(CacheHint.SPEED);

      this.tab = new Tab(String.format("Client: %s", clientID));
    }
  }

  /**
   * submits the current stored input
   */
  public void submit() {
    doRequestGUI(input.getText());
    input.setText("");
  }

  /**
   * takes an input string from the input line, creates a request,executes it, and puts it into the output area
   *
   * @param input input request string
   */
  private void doRequestGUI(String input) {
    Request request = requestGenerator.parseRequest(clientID, input);
    output.appendText(request.execute().getText() + "\n");
    //output.setSc
  }

  /**
   * takes a request string, creates a request object, and exectues it
   *
   * @param input input string
   * @return response object in String form
   */
  public String doRequest(String input) {
    Request request = requestGenerator.parseRequest(clientID, input);
    return request.execute().getText();
  }

  /**
   * deletes current partial request
   */
  public void clearPartial() {
    storageCenter.getClient(clientID).clearPartial();
  }

  /**
   * disconnects client from their client services
   */
  public void disconnect() {
    storageCenter.disconnectClient(clientID);
  }
}
