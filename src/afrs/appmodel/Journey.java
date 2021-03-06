package afrs.appmodel;

/**
 * Journey
 * An interface for flights and collections of flights
 *
 * @author Alex Piazza - 03/01/2018
 */
public interface Journey {

  /**
   * @return the origin airport's name
   */
  String getOrigin();

  /**
   * @return the destination airport's name
   */
  String getDestination();

  /**
   * @return the cost of the journey
   */
  int getCost();

  /**
   * @return a CSV friendly string representing this journey
   */
  String toStringForFile();

  /**
   * @return the string representation of this itinerary with labels
   */
  String toString();

  /**
   * @return the string representing the flight information
   */
  String flightInfo();

  /**
   * @return the departure time
   */
  Time getDepart();

  /**
   * @return the arrival time
   */
  Time getArrive();

}
