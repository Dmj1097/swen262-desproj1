package afrs.appcontroller;

import afrs.appcontroller.AirportStorage;
import afrs.appmodel.Airport;
import afrs.appmodel.Journey;
import afrs.appcontroller.WeatherIterator;
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

	private static List<String> airports;

	private RequestCollection requests;
	private boolean connectionStatus;
	private Service service;
	private String partialString;
	private List<Journey> latestJourneys;
	private Map<String, WeatherIterator> weather;

	public ClientServices(){
		this(airports);
	}

	public ClientServices(List<String> aiports){
		this.requests = new RequestCollection();
		this.connectionStatus = true;
		this.service = Service.Local;
		this.latestJourneys = null;
		this.partialString = "";

		this.weather = new HashMap<>();
		for (String airport : airports ){
			//this.weather.put(airport, );
		}

		airports = airports;
	}

	/**
	 * Change the service which this client is using
	 */
	public void switchService(){
		if (this.service.equals(Service.Local)){
			this.service = Service.FAA;
		} else {
			this.service = Service.Local;
		}
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
		return this.weather.get(airportCode);
	}

	/**
	 * Create the airportCode-WeatherIterator map given the list of airport codes
	 */
	public void populateWeatherIterators(List<String> airportCode, AirportStorage airports){
		for (String code : airportCode){
			Airport airport = (Airport)airports.getInstance(code);
			this.weather.put(code, airport.getWeatherIterator());
		}
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
	public void undoRequest(){
		this.requests.undo();
	}

	/**
	 * Attempt to redo the most recent, redoable request in this request collection
	 */
	public void redoRequest(){
		this.requests.redo();
	}

	/**
	 * Add a given request to this client's Request Collection
	 */
	public void makeRequest(Request request){
		this.requests.add(request);
	}

	/**
	 * Get the most recent journeys that this client has requested
	 */
	public List<Journey> getLatestJourneys(){
		return this.latestJourneys;
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
