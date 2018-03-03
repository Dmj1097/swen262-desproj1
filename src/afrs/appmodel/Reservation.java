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


  public boolean equalsTo(String origin, String destination){
      return (journey.getOrigin().equals(origin) && journey.getDestination().equals(destination));
  }

  public String getOrigin(){
    return journey.getOrigin();
  }

  public String getDestination(){
    return journey.getDestination();
  }


}
