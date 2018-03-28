package afrs.appcontroller;

import afrs.appmodel.Airport;
import afrs.appmodel.Journey;
import afrs.uicontroller.collection.RequestCollection;
import afrs.uicontroller.requests.Request;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Collection of client-related data
 *
 * @author Alex Piazza
 */
public class ClientServices {

	public enum Service { FAA, Local }

	private RequestCollection requests;
	private boolean connectionStatus;
	private Service service;
	private String partialString;
	private List<Journey> latestJourneys;
	private StorageCenter storageCenter;
	private Map<String, WeatherIterator> weather;

	public ClientServices(StorageCenter storageCenter){
		this.requests = new RequestCollection();
		this.connectionStatus = true;
		this.service = Service.Local;
		this.latestJourneys = null;
		this.partialString = "";
    this.storageCenter = storageCenter;
		this.weather = new HashMap<>();
	}

	/**
	 * Change the service which this client is using
	 */
	public void switchService(){
		this.service = (this.service == Service.Local) ? Service.FAA : Service.Local;
	}

	/**
	 * Determine weather or not this client is 'connected' to the AFRS
	 */
	public boolean isConnected(){
		return this.connectionStatus;
	}

	/**
	 * Get the airport iterator, given an airport code
	 */
	public WeatherIterator getAirportWeatherIterator(String airportCode){
		WeatherIterator iterator = weather.get(airportCode);
		if (iterator == null && service != Service.FAA) {
      Airport airport = storageCenter.getAirport(airportCode);
      iterator = airport.getWeatherIterator();
      weather.put(airportCode, iterator);
    } else if (service == Service.FAA) {
      Airport airport = new FAAWeatherCenter().getInstance(airportCode);
      iterator = airport.getWeatherIterator();
      weather.put(airportCode, iterator);
    }
	  return iterator;
	}

	/**
	 * Get the service which this client is using
	 */
	public Service getService(){ return this.service; }

	/**
	 * Mark this client as 'disconnected' from the AFRS
	 */
	public void disconnect(){ this.connectionStatus = false;}

	/**
	 * Attempt to undo the most recent, undoable request in this request collection
	 */
	public boolean undoRequest(){
		return requests.undo();
	}

	/**
	 * Attempt to redo the most recent, redoable request in this request collection
	 */
	public boolean redoRequest(){
		return requests.redo();
	}

	/**
	 * Add a given request to this client's Request Collection
	 */
	public void makeRequest(Request request){
		requests.add(request);
	}

	public Request getUndo() {
	  return requests.getUndo();
  }

  public Request getRedo() {
	  return requests.getRedo();
  }

	/**
	 * Get the most recent journeys that this client has requested
	 */
	public List<Journey> getLatestJourneys(){
		return this.latestJourneys;
	}

	public void setLatestJourneys(List<Journey> journeys) {
	  this.latestJourneys = journeys;
  }

	/**
	 * Add a request substring to this client
	 */
	public void makePartialRequest(String str){
		this.partialString = String.format("%s%s", this.partialString, str );
	}

	/**
	 * Return the partial substring, assuming it is complete, forget current string
	 */
	public String getCompleteRequest(){
		String result = this.partialString;
		this.partialString = "";
		return result;
	}

}
