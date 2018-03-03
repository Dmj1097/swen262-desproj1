package afrs.appmodel;

/**
 * Flight
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Flight {



  private String origin;

  private String destination;

  private int cost;

  private String depart;

  private String arrive;



  /**
   * Create a new Flight object
   */
  public Flight(String origin, String destination, int cost,String depart,String arrive){
    this.origin = origin;
    this.destination = destination;
    this.cost = cost;
    this.depart = depart;
    this.arrive = arrive;
  }

  public String getOrigin() {
    return origin;
  }

  public String getDestination() {
    return destination;
  }

  public int getCost() {
    return cost;
  }

  public String getDepart() {
    return depart;
  }

  public String getArrive() {
    return arrive;
  }
}
