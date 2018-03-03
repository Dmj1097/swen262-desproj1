package afrs.appcontroller;

import afrs.appmodel.*;

import java.util.ArrayList;

/**
 * StorageCenter
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class StorageCenter {


  private FlightStorage flights;

  private AirportStorage airports;

  private PassengerStorage passengers;

  private ArrayList<Itinerary> latestItineraries;


  /**
   * Create a new StorageCenter object
   */
  public StorageCenter(){
    flights = new FlightStorage();
    airports = new AirportStorage();
    passengers = new PassengerStorage();
  }


  public Airport getAirport(String ID){
    return airports.getAirport(ID);
  }

  public Journey getFlight(int ID){
    return flights.getFlight(ID);
  }

  public ArrayList<Flight> getFlightsFromOrigin(String ID){ return flights.getFlightsFromOrigin(ID);}

  public Passenger getPassenger(String ID){
    return passengers.getPassenger(ID);
  }

  public void addPassengerOrReservation(Passenger passenger, Reservation res){
    passengers.addPassengerOrReservation(passenger.getName(), res);
  }

  public void close(){
    passengers.writePassengersFile();
  }

  public void setLatestItineraries(ArrayList<Itinerary> itineraries){
    latestItineraries = itineraries;
  }

  public Itinerary getItinerary(int idx){
    return latestItineraries.get(idx);
  }

}
