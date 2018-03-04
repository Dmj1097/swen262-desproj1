package afrs.appmodel;

import java.util.LinkedList;
import javax.print.attribute.standard.JobHoldUntil;
import java.util.ArrayList;

/**
 * Itinerary
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Itinerary implements Journey{

  /** The flights associated with this itinerary, in order from origin to destination */
  private LinkedList<Journey> flights;
  /** The total airfare of each flight in the itinerary */
  private int cost;

  /**
   * Create a new Itinerary object
   */
  public Itinerary(){
    this.flights = new LinkedList<>();
  }

  /**
   * Adds a flight and adds to the connection count
   * Adjusts cost
   */
  public void addFlight(Journey flight){
    flights.add(flight);
  }

  /**
   * Removes a flight and adds to the connection count.
   * Adjusts cost
   */
  public void removeFlight(Journey flight){
    flights.remove(flight);
  }

  /**
   * @return a string representing the flight info
   */
  @Override
  public String flightInfo() {
    StringBuilder all = new StringBuilder();
    all.append(getCost()).append(",").append(flights.size()).append(",");
    for(Journey flight: flights){
      all.append(flight.flightInfo()).append(",");
    }
    all.deleteCharAt(all.toString().length() - 1);
    return all.toString();
  }

    /**
     * @return the string representation of this itinerary
     */
  @Override
  public String toString(){
    return flightInfo();
  }

  @Override
  public Time getDepart() {
    return flights.getFirst().getDepart();
  }

  @Override
  public Time getArrive() {
    return flights.getLast().getArrive();
  }



  /**
   * @return the origin airport's name
   */
  @Override
  public String getOrigin() {
    return this.flights.getFirst().getOrigin();
  }

  /**
   * @return the destination airport's name
   */
  @Override
  public String getDestination() {
    return this.flights.getLast().getDestination();
  }

  /**
   * Returns the cost of the entire journey
   */
  @Override
  public int getCost() {
    int cost = 0;
    for (Journey flight : this.flights){
      cost += flight.getCost();
    }
    return cost;
  }

  /**
   * @return number of flights in itinerary
   */
  @Override
  public int getFlights() {
    return flights.size();
  }

  /**
   * @return a CSV friendly string representing this itinerary
   */
  @Override
  public String toStringForFile(){
    StringBuilder all = new StringBuilder();
    for(Journey flight: flights){
      all.append(flight.toStringForFile()).append("/");
    }
    all.deleteCharAt(all.toString().length() - 1);
    return all.toString();
  }

  /**
   * Calculate and return the number of connections
   * @return the number of connections
   */
  public int getConnections(){
    int connections = -1;
    for (Journey flight : this.flights ) {
      connections++;
    }
    return connections;
  }
}
