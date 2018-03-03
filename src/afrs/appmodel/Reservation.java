package afrs.appmodel;

/**
 * Reservation
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Reservation {

  private Passenger passenger;
  private Journey journey;

  /**
   * Create a new Reservation object
   */
  public Reservation(Passenger passenger, Journey journey){
    this.passenger = passenger;
    this.journey = journey;
  }


}
