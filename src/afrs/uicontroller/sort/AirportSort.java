package afrs.uicontroller.sort;

import afrs.appmodel.Journey;
import java.util.Comparator;

/**
 * AirportSort
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class AirportSort implements Comparator<Journey> {

  /**
   * Compares two Journeys for sorting based on Airport
   */
  @Override
  public int compare(Journey o1, Journey o2) {
    return o1.getOrigin().compareTo(o2.getOrigin());
  }
}
