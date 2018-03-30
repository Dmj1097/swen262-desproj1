package afrs.appcontroller;

import afrs.appmodel.Airport;
import afrs.appmodel.Flight;
import afrs.appmodel.Journey;
import afrs.appmodel.Passenger;
import afrs.appmodel.Reservation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * StorageCenter class that handles the different types of storages and the itinerary generator
 *
 * @author Dylan Johnston
 */
public class StorageCenter implements Storage {


  private FlightStorage flights;

  private AirportStorage airports;

  private PassengerStorage passengers;

  private ItineraryGenerator itineraryGenerator;

  private ClientServices clientServices;

  /**
   * Create a new StorageCenter object
   */
  public StorageCenter() {
    setupMap();
  }

  @Override
  public void setupMap() {
    flights = new FlightStorage();
    airports = new AirportStorage();
    passengers = new PassengerStorage();
    itineraryGenerator = new ItineraryGenerator(this);
    clientServices = new ClientServices(this);

  }

  @Override
  public Object getInstance(Object ID) {
    return null;
  }

  /**
   * calls the airportStorage class's getAirport method
   *
   * @param ID name of airport
   * @return airport with given name
   */
  public Airport getAirport(String ID) {
    return (Airport) airports.getInstance(ID);
  }

  /**
   * calls the flightStorage class's getFlight method
   *
   * @param ID flight ID number
   * @return Journey object associated with ID
   */
  public Journey getFlight(int ID) {
    return (Journey) flights.getInstance(ID);
  }

  /**
   * calls airportStorage class's getFlightFromOrigin method
   *
   * @param ID airport ID name
   * @return list of flights with found airport as origin
   */
  public ArrayList<Flight> getFlightsFromOrigin(String ID) {
    return flights.getFlightsFromOrigin(ID);
  }

  /**
   * calls passengerStorage's getPassenger method
   *
   * @param ID name of passenger
   * @return Passenger object associated with given name
   */
  public Passenger getPassenger(String ID) {
    return (Passenger) passengers.getInstance(ID);
  }

  /**
   * calls passengerStorage's addPassengerOrReservation method
   *
   * @param passenger passenger name
   * @param res reservation object
   * @return true if already present, false otherwise
   */
  public boolean addPassengerOrReservation(String passenger, Reservation res) {
    boolean result = passengers.addPassengerOrReservation(passenger, res);
    save();
    return result;
  }

  /**
   * called when system is given quit command
   */
  private void save() {
    passengers.writePassengersFile();
  }

  /**
   * calls itineraryGenerator's getLatestJourneys method
   */
  public List<Journey> getLatestJourneys(String origin, String destination, int connections,
      boolean FAAMode) {
    return itineraryGenerator.generateJourneys(origin, destination, connections, FAAMode);
  }

  /**
   * calls passengerStorage's removeReservation method
   */
  public boolean removeReservation(String name, String origin, String destination) {
    boolean result = passengers.removeReservation(name, origin, destination);
    save();
    return result;
  }

  /**
   * calls passengerStorage's getReservations method
   */
  public ArrayList<Journey> getReservations(String name, String origin, String destination) {
    return passengers.getReservations(name, origin, destination);
  }

  public Reservation getReservation(String name, String origin, String destination) {
    return passengers.getReservation(name, origin, destination);
  }

  /**
   * Return a client given a client id
   */
  public Client getClient(String id) {
    return this.clientServices.getClient(id);
  }

  /**
   * Add a new client given by specifying an id
   */
  public void connectClient(String ID) {
    this.clientServices.connectClient(ID);
  }

  /**
   * Remove a client by specifying their id
   */
  public void disconnectClient(String ID) {
    this.clientServices.disconnectClient(ID);
  }

  /**
   * Get the set of local airports
   */
  public Set<String> getAirportKeys() {
    return airports.getKeys();
  }
}
