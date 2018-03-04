package afrs.appcontroller;

import afrs.appmodel.*;

import java.util.ArrayList;
import java.util.List;

/**
 * StorageCenter class that handles the different types of storages and the itinerary generator
 *
 * @author Dylan Johnston
 */
public class StorageCenter {


  private FlightStorage flights;

  private AirportStorage airports;

  private PassengerStorage passengers;

  private ItineraryGenerator itineraryGenerator;

  /**
   * Create a new StorageCenter object
   */
  public StorageCenter(){
    flights = new FlightStorage();
    airports = new AirportStorage();
    passengers = new PassengerStorage();
    itineraryGenerator = new ItineraryGenerator(this);
  }

  /**
   * calls the airportStorage class's getAirport method
   * @param ID name of airport
   * @return airport with given name
   */
  public Airport getAirport(String ID){
    return airports.getAirport(ID);
  }

  /**
   * calls the flightStorage class's getFlight method
   * @param ID flight ID number
   * @return Journey object associated with ID
   */
  public Journey getFlight(int ID){
    return flights.getFlight(ID);
  }

  /**
   * calls airportStorage class's getFlightFromOrigin method
   * @param ID airport ID name
   * @return list of flights with found airport as origin
   */
  public ArrayList<Flight> getFlightsFromOrigin(String ID){ return flights.getFlightsFromOrigin(ID);}

  /**
   * calls passengerStorage's getPassenger method
   * @param ID name of passenger
   * @return Passenger object associated with given name
   */
  public Passenger getPassenger(String ID){
    return passengers.getPassenger(ID);
  }

  /**
   * calls passengerStorage's addPassengerOrReservation method
   * @param passenger passenger name
   * @param res reservation object
   * @return true if already present, false otherwise
   */
  public boolean addPassengerOrReservation(String passenger, Reservation res){
    return passengers.addPassengerOrReservation(passenger, res);
  }

  /**
   * called when system is given quit command
   */
  public void close(){
    passengers.writePassengersFile();
  }

  /**
   *calls itineraryGenerqator's getLatestJourneys method
   */
  public List<Journey> getLatestJourneys(String origin, String destination, int connections ){
    return this.itineraryGenerator.generateJourneys(origin, destination, connections);
  }

  /**
   * calls itineraryGenerator's getItinerary method
   */
  public Itinerary getItinerary(int idx){
    return itineraryGenerator.getItinerary(idx);
  }

  /**
   * calls passengerStorage's removeReservation method
   */
  public boolean removeReservation(String name, String origin, String destination){
    return passengers.removeReservation(name,origin,destination);
  }

  /**
   * calls passengerStorage's getReservations method
   */
  public ArrayList<Journey> getReservations(String name, String origin, String destination){
    return passengers.getReservations(name, origin, destination);
  }

}
