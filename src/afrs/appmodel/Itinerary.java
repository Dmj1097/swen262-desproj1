package afrs.appmodel;

import javax.print.attribute.standard.JobHoldUntil;
import java.util.ArrayList;

/**
 * Itinerary
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Itinerary implements Journey{

  /** The flights associated with this itinerary, in order from origin to destination */
  private ArrayList<Journey> flights;
  /** The total airfare of each flight in the itinerary */
  private int cost;

  /**
   * Create a new Itinerary object
   */
  public Itinerary(){
    this.flights = new ArrayList<>();
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
   * @return the string representation of this itinerary
   */
  @Override
  public String toString(){
    StringBuilder all = new StringBuilder();
    all.append(cost).append(",").append(this.getConnections());
    for(Journey flight: flights){
      all.append(flight.toString());
    }
    return all.toString();
  }

  /**
   * @return a CSV friendly string representing this itinerary
   */
  public String toStringForFile(){
    StringBuilder all = new StringBuilder();
    all.append(cost).append("-").append(this.getConnections());
    for(Journey flight: flights){
      all.append(flight.toString());
    }
    return all.toString();
  }

  /**
   * @return the origin airport's name
   */
  @Override
  public String getOrigin() {
    return this.flights.get(0).toString();
  }

  /**
   * @return the destination airport's name
   */
  @Override
  public String getDestination() {
    if(this.flights.size() == 0){
      return "no destination exists in this itinerary";
    } else {
      return this.flights.get(this.getConnections()).toString();
    }
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
