package afrs.appmodel;

/**
 * Reservation
 *
 * @author Alex Piazza - 03/01/2018
 */
public class Reservation {

  private Passenger passenger;
  private Journey journey;

  /**
   * Create a new Reservation object
   */
  public Reservation(Passenger passenger, Journey journey) {
    this.passenger = passenger;
    this.journey = journey;
  }

  /**
   * compares two reservations to see if they match
   *
   * @param origin origin airport name being used for compare
   * @param destination destination aiport name being used for compare
   * @return true if equal, false otherwise
   */
  public boolean equalsTo(String origin, String destination) {
    return (journey.getOrigin().equals(origin) && journey.getDestination().equals(destination));
  }

  /**
   * @return the passenger associated with this reservation
   */
  public Passenger getPassenger() {
    return passenger;
  }

  /**
   * @return origin airport name from journey
   */
  public String getOrigin() {
    return journey.getOrigin();
  }

  /**
   * @return destination airport name from journey
   */
  public String getDestination() {
    return journey.getDestination();
  }

  /**
   * @return gets journey object tied to reservation
   */
  public Journey getJourney() {
    return journey;
  }


}
