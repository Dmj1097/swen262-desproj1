package afrs.appcontroller;

import afrs.appmodel.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * StorageCenter
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class StorageCenter {


  private FlightStorage flights;

  private AirportStorage airports;

  private PassengerStorage passengers;

  private ItineraryGenerator itineraryGenerator;

  private List<Journey> latestItineraries;


  /**
   * Create a new StorageCenter object
   */
  public StorageCenter() {
    flights = new FlightStorage();
    airports = new AirportStorage();
    passengers = new PassengerStorage();
    latestItineraries = new ArrayList<>();
    this.itineraryGenerator = new ItineraryGenerator(this);
  }


  public Airport getAirport(String ID){
    return airports.getAirport(ID);
  }

  public Journey getFlight(int ID){
    return flights.getFlight(ID);
  }

  public List<Flight> getFlightsFromOrigin(String ID){ return flights.getFlightsFromOrigin(ID);}

  public Passenger getPassenger(String ID){
    return passengers.getPassenger(ID);
  }

  public boolean addPassengerOrReservation(String passenger, Reservation res){
    return passengers.addPassengerOrReservation(passenger, res);
  }

  public void close(){
    passengers.writePassengersFile();
  }

  public List<Journey> getLatestItineraries(String origin, String destination, int connections){
    return (latestItineraries = itineraryGenerator.generateItineraries(origin, destination, connections));
  }

  public Journey getItinerary(int idx){
    return latestItineraries.get(idx);
  }

  public boolean removeReservation(String name, String origin, String destination){
    return passengers.removeReservation(name,origin,destination);
  }
  public List<Journey> getReservations(String name, String origin, String destination){
    return passengers.getReservations(name, origin, destination);
  }

}
