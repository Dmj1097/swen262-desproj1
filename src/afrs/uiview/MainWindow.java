package afrs.uiview;

import afrs.appcontroller.StorageCenter;
import afrs.uicontroller.RequestGenerator;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {

	private static final String WINDOW_NAME = "Airline Flight Reservation System";
	private static final String SUBMIT_BUTTON_NAME = "Submit";
	private static final String FAA_RADIO_NAME = "FAA";
	private static final String LOCAL_RADIO_NAME = "Local";
	private static final String SERVICES_LABEL_NAME = "Services";
	private static final String NEW_CLIENT_BUTTON = "NewClient";

	// The string id of the client - corresponds to tab name
	private TerminalClient currentClient;
	private TabPane tabPane;
	private BorderPane outputBox;
	private Scene windowScene;

  private StorageCenter storageCenter;
  private RequestGenerator requestGenerator;

  private List<TerminalClient> connectedUsers;

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
		Scene scene = new Scene( this.getView() );
		windowScene = scene;
		stage.setScene( scene );
		stage.show();
	}

	private void switchTab(TerminalClient terminalClient) {
	  this.currentClient = terminalClient;
	  tabPane.getSelectionModel().select(currentClient.getTab());
	  outputBox.setCenter(currentClient.getOutput());
  }

	/**
	 * Create and return the window with javafx elements
	 */
	private BorderPane getView(){

		this.outputBox = new BorderPane();
		outputBox.setTop( this.getTabBar() );
		outputBox.setCenter( currentClient.getOutput() );
		outputBox.setBottom( this.getBottom() );

		return outputBox;
	}

	/**
	 * Return the top part of the window
	 */
	private HBox getTabBar(){
		HBox hbox = new HBox();

		Button newClientButton = new Button(NEW_CLIENT_BUTTON);

		// New client button indicates a new client has connected, and switches to that tab
		newClientButton.setOnAction(event -> switchTab(newClient()));
		tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> switchTab(getClientFromTab(newTab)));

		hbox.getChildren().addAll(tabPane, newClientButton);
		return hbox;
	}

	private TerminalClient newClient() {
    TerminalClient terminalClient = new TerminalClient(storageCenter, requestGenerator);
    connectedUsers.add(terminalClient);
    tabPane.getTabs().add(terminalClient.getTab());
    return terminalClient;
  }

	/**
	 * Return the bottom part of the window
	 */
	private VBox getBottom(){
		VBox vbox = new VBox();

		HBox bottom = new HBox();

		// Create input menu
		TextField input = new TextField();
		input.setPromptText("Input commands here");
		Button submitButton = new Button(SUBMIT_BUTTON_NAME);
		bottom.setHgrow(input, Priority.ALWAYS);


		// Create services menu
		VBox serviceMenu = new VBox();
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

	// TODO make this able to reference clients by ID
	private void changeService(String mode) {
	  currentClient.clearPartial();
	  currentClient.doRequestGUI("server," + mode + ";");
	}

	/* Additional Buttons to autofill input text boxes with well-formed request strings */

	// TODO make this work
	private ButtonBar getMacroButtons(TextField input){


		ButtonBar buttonBar = new ButtonBar();
		Button flightInfo = new Button("FlightInfo");
		flightInfo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				input.setText("info,origin,destination[,connections[,sort-order]]; ");
			}
			}
		);
		ButtonBar.setButtonData(flightInfo, ButtonBar.ButtonData.YES); //implement
		Button airportInfo = new Button("AirportInfo");
		airportInfo.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
			   input.setText("airport,airport");
		    }
			}
		);
		ButtonBar.setButtonData(airportInfo, ButtonBar.ButtonData.YES); //implement
		buttonBar.getButtons().addAll(flightInfo,airportInfo);
		return buttonBar;

		//return new ButtonBar();
	}

	/**
	 * Put the string onto the output window
	 */
	protected void output(String output){
		//this.windowScene.
	}

	private TerminalClient getClientFromTab(Tab tab) {
	  for (TerminalClient client : connectedUsers) {
	    if (client.getTab().equals(tab)) {
	      return client;
      }
    }
	  return null;
  }
}
