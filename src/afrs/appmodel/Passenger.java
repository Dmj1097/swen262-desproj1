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

  /**
   * Add reservations to the passengers list of reservations
   */
  public void addReservation(Reservation r){
    reservations.add(r);
  }

  /**
   * @return name of passenger
   */
  public String getName(){return name;}

  /**
   * checks if a given origin airport name and a given destination airport is already in this passengers reservations
   * @param origin origin airport name
   * @param destination destination airport name
   * @return true if it contains it, false otherwise
   */
  public boolean alreadyContains(String origin, String destination){
    for(Reservation res: reservations) {
      if (res.equalsTo(origin,destination)) {
        return true;
      }
    }
    return false;
  }

  /**
   * checks if reservation is already present in this passenger's reservations, deletes if it is
   * @param origin origin airport name
   * @param destination destination airport name
   * @return true if reservation was present and removed, false otherwise
   */
  public boolean removeReservation(String origin, String destination){
    if(alreadyContains(origin, destination)){
      for(Reservation res: reservations){
        if (res.equalsTo(origin,destination)){
          reservations.remove(res);
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @return all reservations tied to this passenger object
   */
  public ArrayList<Journey> getReservations() {
    ArrayList<Journey> journies = new ArrayList<>();
    for(Reservation res: reservations){
      journies.add(res.getJourney());
    }
    return journies;
  }
}
