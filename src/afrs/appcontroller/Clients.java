package afrs.appcontroller;

import java.util.HashMap;
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

	private StorageCenter storageCenter;

	public Clients(StorageCenter storageCenter){
		this.storageCenter = storageCenter;
		this.services = new HashMap<>();
	}

	/** Get the clientservices object mapped to this client ID */
	public ClientServices getClientServices(String id){
		return this.services.get(id);
	}

	/** Add a new client upon connection */
	public void connectClient(String id){
		this.services.put(id, new ClientServices(storageCenter));
	}

	/** Disconnect a client */
	public void disconnectClient(String id){
		this.services.get(id).disconnect();
		this.services.remove(id);
	}
}
