package afrs.uicontroller;

import afrs.uiview.Response;
import afrs.appcontroller.StorageCenter;

/**
 * Created by Apiazza on 3/3/18.
 */
public class CreateReservationReciever {

	private StorageCenter center;

	public CreateReservationReciever( StorageCenter center ){
		this.center = center;
	}

	public Response execute(){
	//	this.center.createReservation(  );

		Response response = new Response();
		return response;
	}
}
