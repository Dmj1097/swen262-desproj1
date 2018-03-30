package afrs;

import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.RequestGenerator;
import afrs.uiview.TerminalClient;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * application class that launches the input output service through a GUI
 * Alex Piazza, Brian Taylor
 */
public class GuiApplication extends Application {

  private static final String WINDOW_NAME = "Airline Flight Reservation System";
  private static final String SUBMIT_BUTTON_NAME = "Submit";
  private static final String FAA_RADIO_NAME = "FAA";
  private static final String LOCAL_RADIO_NAME = "Local";
  private static final String SERVICES_LABEL_NAME = "Services";
  private static final String NEW_CLIENT_BUTTON = "Connect";

  private StorageCenter storageCenter;
  private RequestGenerator requestGenerator;

  private List<TerminalClient> connectedUsers;
  private TerminalClient currentClient;

  private TabPane tabPane;
  private HBox inputBar;
  private BorderPane outputBox;
  private Button submitButton;


  public static void main(String[] args) {
    Application.launch(args);
  }

  public GuiApplication() {
    this.storageCenter = new StorageCenter();
    this.requestGenerator = new RequestGenerator(storageCenter);
    new Thread(() -> new TestApplication(storageCenter, requestGenerator)).start();
  }

  /**
   * starts the GUI
   *
   * @param stage stage that GUI will be setup on
   */
  public void start(Stage stage) {
    this.connectedUsers = new ArrayList<>();

    stage.setTitle(WINDOW_NAME);
    stage.setScene(new Scene(getView()));
    stage.setMinWidth(800);
    stage.setMinHeight(480);

    switchTab(newClient());
    stage.show();
    currentClient.getInput().requestFocus();
  }

  /**
   * creates a new client in the terminal
   * this happens when the "Connect" button is clicked
   *
   * @return new TerminalClient object with clients information
   */
  private TerminalClient newClient() {
    TerminalClient terminalClient = new TerminalClient(storageCenter, requestGenerator, true);
    connectedUsers.add(terminalClient);
    terminalClient.getTab().setOnCloseRequest(event -> {
      terminalClient.disconnect();
      connectedUsers.remove(terminalClient);
      tabPane.getTabs().remove(terminalClient.getTab());
    });
    tabPane.getTabs().add(terminalClient.getTab());
    return terminalClient;
  }

  /**
   * switches the clicked on tab to be the displayed tab
   *
   * @param terminalClient the client to switch to
   */
  private void switchTab(TerminalClient terminalClient) {
    this.currentClient = terminalClient;
    tabPane.getSelectionModel().select(currentClient.getTab());
    inputBar.getChildren().clear();
    inputBar.getChildren().addAll(currentClient.getInput(), submitButton);
    outputBox.setCenter(currentClient.getOutput());
  }

  /**
   * Called by the submit button when pressed
   */
  private void submit() {
    if (currentClient != null) {
      currentClient.submit();
    }
  }

  /**
   * gets the client that is present in a specific tab
   *
   * @param tab tab request is made from
   * @return client as the new TerminalClient
   */
  private TerminalClient getClientFromTab(Tab tab) {
    for (TerminalClient client : connectedUsers) {
      if (client.getTab().equals(tab)) {
        return client;
      }
    }
    return null;
  }

  /**
   * Create and return the window with javafx elements
   */
  private BorderPane getView() {
    this.outputBox = new BorderPane();
    outputBox.setCache(true);
    outputBox.setCacheHint(CacheHint.SPEED);
    outputBox.setTop(getTabBar());
    outputBox.setBottom(getBottom());
    return outputBox;
  }

  /**
   * Return the top part of the window
   */
  private Node getTabBar() {
    BorderPane borderPane = new BorderPane();
    HBox hbox = new HBox();

    Button newClientButton = new Button(NEW_CLIENT_BUTTON);
    newClientButton.setPadding(new Insets(6, 10, 6, 10));

    // New client button indicates a new client has connected, and switches to that tab
    newClientButton.setOnAction(event -> switchTab(newClient()));

    // Changing tabs changes which client is the focus
    this.tabPane = new TabPane();
    tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
      if (!connectedUsers.isEmpty()) {
        switchTab(getClientFromTab(newTab));
      } else {
        currentClient = null;
      }
    });

    hbox.getChildren().addAll(tabPane);
    borderPane.setLeft(newClientButton);
    borderPane.setCenter(hbox);
    return borderPane;
  }

  /**
   * Return the bottom part of the window
   */
  private Node getBottom() {
    VBox vbox = new VBox();
    vbox.setPadding(new Insets(3, 0, 0, 3));
    HBox.setHgrow(vbox, Priority.ALWAYS);

    this.inputBar = new HBox();
    inputBar.setSpacing(5);

    // Create submit button
    this.submitButton = new Button(SUBMIT_BUTTON_NAME);
    //submitButton.setPadding(new Insets(0, 2, 0, 0));
    submitButton.setOnAction(event -> submit());

    vbox.getChildren().addAll(inputBar, getMacroButtons());
    return vbox;
  }

  /**
   * Additional Buttons to autofill input text boxes with well-formed request strings
   *
   * @return a ButtonBar for the GUI
   */
  private Node getMacroButtons() {

    VBox vbox = new VBox();
    vbox.setSpacing(2);

    ButtonBar buttonBar1 = new ButtonBar();
    buttonBar1.setButtonMinWidth(130);
    buttonBar1.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    buttonBar1.setPadding(new Insets(2, 1, 0, 0));

    Button airportInfo = new Button("Airport Info");
    airportInfo.setOnAction(event -> {
      if (currentClient != null) {
        currentClient.getInput().setText("airport,airport;");
      }
    });
    ButtonBar.setButtonData(airportInfo, ButtonBar.ButtonData.YES);

    Button flightInfo = new Button("Flight Info");
    flightInfo.setOnAction(event -> {
      if (currentClient != null) {
        currentClient.getInput().setText("info,origin,destination[,connections[,sort-order]];");
      }
    });
    ButtonBar.setButtonData(flightInfo, ButtonBar.ButtonData.YES);

    Button retrieveReservation = new Button("Retrieve Reservations");
    retrieveReservation.setOnAction(event -> {
      if (currentClient != null) {
        currentClient.getInput().setText("retrieve,passenger[,origin[,destination]];");
      }
    });
    ButtonBar.setButtonData(retrieveReservation, ButtonBar.ButtonData.YES);

    Button airportMode = new Button("FAA Mode");
    airportMode.setOnAction(event -> {
      if (currentClient != null) {
        currentClient.getInput().setText("server,server;");
      }
    });
    ButtonBar.setButtonData(airportMode, ButtonBar.ButtonData.YES);

    buttonBar1.getButtons().addAll(airportMode, retrieveReservation, flightInfo, airportInfo);

    ButtonBar buttonBar2 = new ButtonBar();
    buttonBar2.setButtonMinWidth(130);
    buttonBar2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    buttonBar2.setPadding(new Insets(0, 1, 2, 0));

    Button makeReservation = new Button("Make Reservation");
    makeReservation.setOnAction(event -> {
      if (currentClient != null) {
        currentClient.getInput().setText("reserve,id,passenger;");
      }
    });
    ButtonBar.setButtonData(makeReservation, ButtonBar.ButtonData.YES);

    Button deleteReservation = new Button("Delete Reservation");
    deleteReservation.setOnAction(event -> {
      if (currentClient != null) {
        currentClient.getInput().setText("delete,passenger,origin,destination;");
      }
    });
    ButtonBar.setButtonData(deleteReservation, ButtonBar.ButtonData.YES);

    Button undoButton = new Button("Undo");
    undoButton.setOnAction(event -> {
      if (currentClient != null) {
        currentClient.getInput().setText("undo;");
      }
    });
    ButtonBar.setButtonData(undoButton, ButtonBar.ButtonData.YES);

    Button redoButton = new Button("Redo");
    redoButton.setOnAction(event -> {
      if (currentClient != null) {
        currentClient.getInput().setText("redo;");
      }
    });
    ButtonBar.setButtonData(redoButton, ButtonBar.ButtonData.YES);

    buttonBar2.getButtons().addAll(redoButton, undoButton, deleteReservation, makeReservation);

    vbox.getChildren().addAll(buttonBar1, buttonBar2);

    return vbox;
  }
}
