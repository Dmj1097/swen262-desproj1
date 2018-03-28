package afrs.uiview;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {

	private static final String WINDOW_NAME = "Airline Flight Reservation System";
	private static final String SUBMIT_BUTTON_NAME = "Submit";
	private static final String FAA_RADIO_NAME = "FAA";
	private static final String LOCAL_RADIO_NAME = "Local";
	private static final String SERVICES_LABEL_NAME = "Services";
	private static final String NEW_CLIENT_BUTTON = "NewClient";

	// The string id of the client - corresponds to tab name
	private static String currentClient = "";

	private static Scene windowScene;

	public static void main(String[] args) {
		Application.launch( args );
	}

	public void start(Stage stage){

		stage.setTitle(WINDOW_NAME);
		Scene scene = new Scene( this.getView() );
		windowScene = scene;
		stage.setScene( scene );
		stage.show();
	}

	// TODO Implement this properly
	/**
	 * Create a new client and return their ID
	 */
	protected static String newClient(){ return new String(); }

	/**
	 * Create and return the window with javafx elements
	 */
	private BorderPane getView(){

		BorderPane view = new BorderPane();
		view.setTop( this.getTabBar() );
		TextArea ta = new TextArea("Welcome to AFRS!");
		ta.setEditable(false);
		view.setCenter( ta );

		view.setBottom( this.getBottom() );

		return view;
	}

	/**
	 * Return the top part of the window
	 */
	private HBox getTabBar(){
		HBox hbox = new HBox();

		TabPane tabPane = new TabPane();
		Button newClientButton = new Button(NEW_CLIENT_BUTTON);

		// New client button indicates a new client has connected, and switches to that tab
		newClientButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Tab tab = new Tab(String.format("Client: %s", MainWindow.newClient() ));
				tabPane.getTabs().add(tab);
				tabPane.getSelectionModel().select(tab);
			}
		});

		hbox.getChildren().addAll(tabPane, newClientButton);
		return hbox;
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


		localServiceButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainWindow.changeService(currentClient);
			}
		});
		faaServiceButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 MainWindow.changeService(currentClient);

			 }
		 }
		);

		ButtonBar macros = this.getMacroButtons();

		bottom.getChildren().addAll(input,submitButton,serviceMenu);

		vbox.getChildren().addAll(bottom, macros);
		return vbox;
	}

	// TODO make this able to reference clients by ID
	private static void changeService(String id){
		return;
	}

	/* Additional Buttons to autofill input text boxes with well-formed request strings */

	// TODO make this work
	private ButtonBar getMacroButtons(){
		return new ButtonBar();
	}

	/**
	 * Put the string onto the output window
	 */
	protected void output(String output){
		//this.windowScene.
	}
}
