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
    public String getOrigin();

    /**
     * @return the destination airport's name
     */
    public String getDestination();

    /**
     * @return the cost of the journey
     */
    public int getCost();

    /**
     * @return a CSV friendly string representing this journey
     */
    public String toStringForFile();


}
