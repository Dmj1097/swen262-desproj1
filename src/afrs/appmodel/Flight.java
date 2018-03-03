package afrs.appmodel;

/**
 * Flight
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Flight implements Journey {


  private int ID;

  private Airport origin;

  private Airport destination;

  private double cost;

  private Time depart;

  private Time arrive;


  /**
   * Create a new Flight object
   */
  public Flight(int ID, Airport origin, Airport destination, double cost,Time depart,Time arrive){
    this.ID = ID;
    this.origin = origin;
    this.destination = destination;
    this.cost = cost;
    this.depart = depart;
    this.arrive = arrive;
  }

  public Airport getOrigin() {
    return origin;
  }

  public Airport getDestination() {
    return destination;
  }

  public double getCost() {
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
    return "[," + ID + ","+  origin.getName() + "," + depart + "," + destination.getName() + "," + arrive +"]\n";
  }
}
