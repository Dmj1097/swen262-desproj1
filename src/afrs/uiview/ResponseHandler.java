package afrs.uiview;

import java.io.OutputStream;

/**
 * Created by Apiazza on 3/1/18.
 */
public class ResponseHandler {
	private OutputStream out;

	public ResponseHandler(){
		this.out = System.out;	// stdout
	}

	public void writeResponse(Response response){
		System.out.println( response.getText() );
	}
}
