package afrs.uicontroller.sort;

import afrs.appmodel.Journey;
import java.util.Comparator;

/**
 * ArriveSort
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class ArriveSort implements Comparator<Journey> {

  /**
   * Compares two Journeys for sorting based on arrival time
   */
  @Override
  public int compare(Journey o1, Journey o2) {
    return o1.getArrive().compareTo(o2.getArrive());
  }
}
