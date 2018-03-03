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
  public Passenger(String name, ArrayList<Reservation> reservations){
    this.name = name;
    this.reservations = reservations;
  }

  /*
   * Add reservations to the passengers list of reservations
   */
  public void AddReservation(Reservation r){
    reservations.add(r);
  }

}
