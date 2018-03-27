package afrs.appcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A collection of clients accessable by their client ids
 * Glorified hashmap
 *
 * @author Alex Piazza
 */
public class Clients {

	/** Map client IDs to client related information */
	private Map<String, ClientServices> services;

	/** Save aiport codes so they may be used  */
	private List<String> airportCodes;

	public Clients(List<String> airportCodes){
		this.airportCodes = airportCodes;
		this.services = new HashMap<>();
	}

	/** Get the clientservices object mapped to this client ID */
	public ClientServices getClientServices(String id){
		return this.services.get(id);
	}

	/** Add a new client upon connection */
	public void connectClient(String id){
		this.services.put(id, new ClientServices(this.airportCodes));
	}

	/** Disconnect a client */
	public void disconnectClient(String id){
		this.services.get(id).disconnect();
		this.services.remove(id);
	}
}
