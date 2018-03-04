package afrs.uicontroller;

import afrs.appmodel.Journey;
import java.util.Comparator;

/**
 * DepartSort
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class DepartSort implements Comparator<Journey> {

  /**
   * Compares two Journeys for sorting based on departure time
   */
  @Override
  public int compare(Journey o1, Journey o2) {
    return o1.getDepart().compareTo(o2.getDepart());
  }

}
