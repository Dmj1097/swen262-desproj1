package afrs.uicontroller.requests;

import afrs.appcontroller.Client;
import afrs.appcontroller.StorageCenter;
import afrs.appmodel.Journey;
import afrs.appmodel.Passenger;
import afrs.uiview.Response;

/**
 * UndoRedoRequest
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class UndoRedoRequest extends Request {

  private boolean undo;

  /**
   * Create a new UndoRedoRequest object
   *
   */
  public UndoRedoRequest(String clientID, StorageCenter storageCenter, boolean undo) {
    super(storageCenter, null);
    this.clientID = clientID;
    this.undo = undo;
  }

  /**
   * Executes the command
   *
   * @return the command's response
   */
  @Override
  public Response execute() {
    Client client = storageCenter.getClientServices(clientID);

    String mode;
    String type;
    Request r;
    Passenger passenger;
    Journey journey;
    if (undo) {
      mode = "undo";
      r = client.getUndo();
      if (r == null) {
        return new Response(clientID + ",error,no request available");
      }
    } else {
      mode = "redo";
      r = client.getRedo();
      if (r == null) {
        return new Response(clientID + ",error,no request available");
      }
    }

    if (r instanceof CreateReservationRequest) {
      CreateReservationRequest create = (CreateReservationRequest) r;
      type = "reserve";
      passenger = create.getCreated().getPassenger();
      journey = create.getCreated().getJourney();
    } else {
      DeleteReservationRequest del = (DeleteReservationRequest) r;
      type = "delete";
      passenger = del.getDeleted().getPassenger();
      journey = del.getDeleted().getJourney();
    }

    // If successful undo
    boolean check = (undo) ? client.undoRequest() : client.redoRequest();
    if (check) {
      return new Response(clientID + "," + mode + "," + type + "," + passenger.getName() + "," + journey);
    } else {
      return new Response(clientID + ",error,no request available");
    }
  }
}
