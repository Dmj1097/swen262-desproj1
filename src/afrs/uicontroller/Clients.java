package afrs.uicontroller;

import java.util.HashMap;
import java.util.Map;

/**
 * A collection of clients accessable by their client ids
 * Glorified hashmap
 *
 * @author Alex Piazza
 */
public class Clients {
	private Map<String, ClientServices> services;

	public Clients(){ this.services = new HashMap<>(); }

	public ClientServices getClientServices(String id){
		return this.services.get(id);
	}

	public void connectClient(String id){
		this.services.put(id, new ClientServices());
	}

	public void disconnectClient(String id){
		this.services.get(id).disconnect();
		this.services.remove(id);
	}
}
