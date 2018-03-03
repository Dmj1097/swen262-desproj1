package afrs.appmodel;

import java.util.ArrayList;

/**
 * Passenger
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Passenger {

  private String name;
  private ArrayList<Reservation> reservations;

  /**
   * Create a new Passenger object
   */
  public Passenger(String name){
    this.name = name;
    reservations = new ArrayList<>();
  }

  /*
   * Add reservations to the passengers list of reservations
   */
  public void addReservation(Reservation r){
    reservations.add(r);
  }

  public String getName(){return name;}

  public boolean alreadyContains(String origin, String destination){
    for(Reservation res: reservations) {
        return res.equalsTo(origin,destination);
    }
    return false;
  }

}
