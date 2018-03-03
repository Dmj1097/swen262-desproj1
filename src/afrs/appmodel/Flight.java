package afrs.appmodel;

/**
 * Flight
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Flight implements Journey {


  private int ID;

  private String origin;

  private String destination;

  private int cost;

  private Time depart;

  private Time arrive;


  /**
   * Create a new Flight object
   */
  public Flight(int ID, String origin, String destination, int cost,Time depart,Time arrive){
    this.ID = ID;
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

  public Time getDepart() {
    return depart;
  }

  public Time getArrive() {
    return arrive;
  }

  public int getFlightID(){
    return ID;
  }

  @Override
  public String toString(){
    return "[," + ID + ","+  origin + "," + depart + "," + destination + "," + arrive +"]\n";
  }
}
