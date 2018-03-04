package afrs.appmodel;

/**
 * Journey
 *
 * Create By Alex Piazza - 03/01/2018
 */
public interface Journey {

    String getOrigin();

    String getDestination();

    int getCost();

    String toStringForFile();

}
