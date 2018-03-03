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

  public Flight getFlight(int ID){
    return flights.getFlight(ID);
  }

  public ArrayList<Flight> getFlightsFromOrigin(String ID){ return flights.getFlightsFromOrigin(ID);}

  public Passenger getPassenger(String ID){
    return passengers.getPassenger(ID);
  }

  public void addPassenger(Passenger passenger){
    //TODO change parameters
    passengers.addPassenger(passenger.toString(),new Reservation());
  }

}
