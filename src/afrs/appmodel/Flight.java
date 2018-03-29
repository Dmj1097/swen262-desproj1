package afrs.appmodel;

/**
 * Flight
 *
 * @author Alex Piazza - 03/01/2018
 */
public class Flight implements Journey {

  /**
   * The flight's numerical id
   */
  private int ID;
  /**
   * The origin airport's name, corresponds with airport name
   */
  private String origin;
  /**
   * The destination airport's name, corresponds with airport name
   */
  private String destination;
  /**
   * The airfare, cost of the flight
   */
  private int cost;
  /**
   * The departure time
   */
  private Time depart;
  /**
   * The arrival time
   */
  private Time arrive;


  /**
   * Create a new Flight object
   */
  public Flight(int ID, String origin, String destination, int cost, Time depart, Time arrive) {
    this.ID = ID;
    this.origin = origin;
    this.destination = destination;
    this.cost = cost;
    this.depart = depart;
    this.arrive = arrive;
  }

  /**
   * @return the origin airport's name
   */
  @Override
  public String getOrigin() {
    return origin;
  }

  /**
   * @return the destination airport's name
   */
  @Override
  public String getDestination() {
    return destination;
  }

  /**
   * @return the cost of the flight, airfare
   */
  @Override
  public int getCost() {
    return cost;
  }

  /**
   * @return the departure time
   */
  @Override
  public Time getDepart() {
    return depart;
  }

  /**
   * @return the arrival time
   */
  @Override
  public Time getArrive() {
    return arrive;
  }

  /**
   * @return the flight's id
   */
  public int getFlightID() {
    return ID;
  }

  /**
   * @return a string representing the flight info
   */
  @Override
  public String flightInfo() {
    return ID + "," + origin + "," + depart + "," + destination + "," + arrive;
  }

  /**
   * @return a string representing this flight instance
   */
  @Override
  public String toString() {
    return String.valueOf(getCost()) + "," + 1 + "," + flightInfo();
  }

  /**
   * @return a CSV friendly string representing this flight
   */
  @Override
  public String toStringForFile() {
    return ID + "," + cost + "," + origin + "," + depart + "," + destination + "," + arrive;
  }

  /** Get the arrival time of a flight */
  public Time getArrivalTime() {
    return this.arrive;
  }

  /** Get the departure time of a flight */
  public Time getDepartureTime() {
    return this.depart;
  }
}
