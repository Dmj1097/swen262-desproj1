package afrs;

import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.RequestGenerator;
import afrs.uiview.Response;
import afrs.uiview.TerminalClient;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;

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
	private BorderPane outputBox;


	public static void main(String[] args) {
		Application.launch( args );
	}

	public void start(Stage stage) {
    this.storageCenter = new StorageCenter();
    this.requestGenerator = new RequestGenerator(storageCenter);

    this.connectedUsers = new ArrayList<>();
    this.tabPane = new TabPane();
    this.outputBox = new BorderPane();
    switchTab(newClient());

		stage.setTitle(WINDOW_NAME);
		stage.setScene(new Scene(getView()));
		stage.setMinWidth(600);
		stage.setMinHeight(400);
		stage.show();
	}

  private TerminalClient newClient() {
    TerminalClient terminalClient = new TerminalClient(storageCenter, requestGenerator);
    connectedUsers.add(terminalClient);
    terminalClient.getTab().setOnCloseRequest(event -> {
      terminalClient.disconnect();
      connectedUsers.remove(terminalClient);
      tabPane.getTabs().remove(terminalClient.getTab());
    });
    tabPane.getTabs().add(terminalClient.getTab());
    return terminalClient;
  }

	private void switchTab(TerminalClient terminalClient) {
	  this.currentClient = terminalClient;
	  tabPane.getSelectionModel().select(currentClient.getTab());
	  outputBox.setCenter(currentClient.getOutput());
  }

  private void changeService(String mode) {
    currentClient.clearPartial();
    currentClient.doRequestGUI("server," + mode + ";");
  }

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
	private BorderPane getView(){

		this.outputBox = new BorderPane();
		outputBox.setTop(getTabBar());
		outputBox.setCenter( currentClient.getOutput() );
		outputBox.setBottom(getBottom());

		return outputBox;
	}

	/**
	 * Return the top part of the window
	 */
	private Node getTabBar(){
		BorderPane borderPane = new BorderPane();
		HBox hbox = new HBox();

		Button newClientButton = new Button(NEW_CLIENT_BUTTON);
		newClientButton.setPadding(new Insets(6, 10, 6, 10));

		// New client button indicates a new client has connected, and switches to that tab
		newClientButton.setOnAction(event -> switchTab(newClient()));

		// Changing tabs changes which client is the focus
		tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> switchTab(getClientFromTab(newTab)));

		hbox.getChildren().addAll(tabPane);
		borderPane.setLeft(newClientButton);
		borderPane.setCenter(hbox);
		return borderPane;
	}

	/**
	 * Return the bottom part of the window
	 */
	private Node getBottom(){
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		HBox bottom = new HBox();
		bottom.setSpacing(5);

		// Create input menu
		TextField input = new TextField();
		input.setPromptText("Input commands here");
		Button submitButton = new Button(SUBMIT_BUTTON_NAME);

		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				submit( input.getText() );
				input.setText("");
			}
		});

		bottom.setHgrow(input, Priority.ALWAYS);


		// Create services menu
		VBox serviceMenu = new VBox();
		serviceMenu.setPrefWidth(60);
		Label serviceLabel = new Label(SERVICES_LABEL_NAME);
		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton faaServiceButton = new RadioButton(FAA_RADIO_NAME);
		RadioButton localServiceButton = new RadioButton(LOCAL_RADIO_NAME);

		faaServiceButton.setToggleGroup(toggleGroup);
		localServiceButton.setToggleGroup(toggleGroup);

		// Set actions of changing the service radio button
		serviceMenu.getChildren().addAll(serviceLabel, faaServiceButton, localServiceButton);


		localServiceButton.setOnAction(event -> changeService("local"));
		faaServiceButton.setOnAction(event -> changeService("faa"));

		//input.setText("airport,airport");
		ButtonBar macros = this.getMacroButtons(input);

		bottom.getChildren().addAll(input,submitButton,serviceMenu);

		vbox.getChildren().addAll(bottom, macros);
		return vbox;
	}

	/**
	 * Accept a string from the input text field
	 * Called by the submit button when pressed
	 */
	private void submit(String input){
		this.currentClient.doRequestGUI(input);
	}

	/**
	 * Put the text from a response on the current output window
	 */
	private void putResponse(Response response){
		TextArea textArea = this.currentClient.getOutput();
		textArea.appendText(response.getText());
	}

	/**
	 * Additional Buttons to autofill input text boxes with well-formed request strings
	 * @param input The textfield to autofill
	 * @return a ButtonBar for the GUI
	 */
	private ButtonBar getMacroButtons(TextField input){

		ButtonBar buttonBar = new ButtonBar();

    Button airportInfo = new Button("Airport Info");
    airportInfo.setOnAction(event -> input.setText("airport,airport;")
    );
    ButtonBar.setButtonData(airportInfo, ButtonBar.ButtonData.YES);

		Button flightInfo = new Button("Flight Info");
		flightInfo.setOnAction(event -> input.setText("info,origin,destination[,connections[,sort-order]]; ")
    );
		ButtonBar.setButtonData(flightInfo, ButtonBar.ButtonData.YES);

		Button makeReservation = new Button("Make Reservation");
		makeReservation.setOnAction(event -> input.setText("reserve,id,passenger; ")
    );
		ButtonBar.setButtonData(makeReservation, ButtonBar.ButtonData.YES);

		Button retrieveReservation = new Button("Retrieve Reservation");
		retrieveReservation.setOnAction(event -> input.setText("retrieve,passenger[,origin[,destination]];")
    );
		ButtonBar.setButtonData(retrieveReservation, ButtonBar.ButtonData.YES);

		Button deleteReservation = new Button("Delete Reservation");
		deleteReservation.setOnAction(event -> input.setText("delete,passenger,origin,destination;")
    );
		ButtonBar.setButtonData(deleteReservation, ButtonBar.ButtonData.YES);

		buttonBar.getButtons().addAll(flightInfo,makeReservation,retrieveReservation,deleteReservation,airportInfo);
		return buttonBar;

	}
}
