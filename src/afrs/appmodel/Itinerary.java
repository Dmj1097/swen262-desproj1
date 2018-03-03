package afrs.appmodel;

import javax.print.attribute.standard.JobHoldUntil;
import java.util.ArrayList;

/**
 * Itinerary
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Itinerary implements Journey{

  
  private ArrayList<Journey> flights;
  private int cost;

  /**
   * Create a new Itinerary object
   */
  public Itinerary(){
    this.flights = new ArrayList<>();
  }

  /*
   * Adds a flight and adds to the connection count
   * Adjusts cost
   */
  public void addFlight(Journey flight){
    flights.add(flight);
  }

  /*
   * Removes a flight and adds to the connection count.
   * Adjusts cost
   */
  public void removeFlight(Journey flight){
    flights.remove(flight);
  }

  @Override
  public String toString(){
    String all = "";
    all += (cost + ", " + this.getConnections());
    for(Journey flight: flights){
      all += (flight.toString());
    }
    return all;
  }

  public String toStringForFile(){
    String all = "";
    all += cost + "-" + this.getConnections();
    for(Journey flight: flights){
      all += (flight.toString());
    }
    return all;
  }

  /*
   * Updates origin and returns it
   */
  @Override
  public String getOrigin() {
    return this.flights.get(0).toString();
  }

  /*
   * Updates destination and returns it
   */
  @Override
  public String getDestination() {
    if(this.flights.size() == 0){
      return "no destination exists in this itinerary";
    } else {
      return this.flights.get(this.getConnections()).toString();
    }
  }

  /*
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
   * Calculate and return the number of conenctions
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
