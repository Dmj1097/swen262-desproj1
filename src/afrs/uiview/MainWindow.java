package afrs.uiview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {

	private static final String WINDOW_NAME = "Airline Flight Reservation System";
	private static final String SUBMIT_BUTTON_NAME = "Submit";
	private static final String FAA_RADIO_NAME = "FAA";
	private static final String LOCAL_RADIO_NAME = "Local";


	public void start(Stage stage){

		stage.setTitle(WINDOW_NAME);
		Scene scene = new Scene( this.getView() );
		stage.setScene( scene );
		stage.show();
	}

	/**
	 * Create and return the window with javafx elements
	 */
	private BorderPane getView(){

		BorderPane view = new BorderPane();
		view.setTop( this.getTabBar() );
		view.setCenter( new TextArea("Welcome to AFRS!") );
		view.setBottom( this.getBottom() );

		return view;
	}

	private HBox getTabBar(){
		HBox hbox = new HBox();

		// TODO
		// Hbox
		//		TabPane
		//			Tab: client1
		//			Tab: client2
		// 			...
		//		Button - NewClient

		return hbox;
	}

	private VBox getBottom(){
		VBox vbox = new VBox();

		// TODO
		// VBox
		// 		Hbox
		//			TextField - Input
		//			Button - "Submit"
		//			Vbox
		//				Label - Services
		//				RadioButton - "FAA"
		//				RadioButton - "Local"
		//		ButtonBar
		//			Button - Undo
		//			Button - Redo
		//			...

		return vbox;
	}


	/* Additional Buttons to autofill input text boxes with well-formed request strings */

	private Button getAirportInfoButton(){ return null; }
	private Button getFlightInfoButton(){ return null; }
	private Button getMakeReservationButton(){ return null; }
	private Button getReservationInfoButton(){ return null; }
	private Button getDeleteReservationButton(){ return null; }
	private Button getUndoButton(){ return null; }
	private Button getRedoButton(){ return null; }

}
