package afrs.appmodel;

/**
 * Journey
 *
 * Create By Alex Piazza - 03/01/2018
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

}
