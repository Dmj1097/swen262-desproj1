package afrs.uicontroller.sort;

import afrs.appmodel.Journey;
import java.util.Comparator;

/**
 * CostSort
 *
 * Created By Brian Taylor - 03/03/2018
 */
public class CostSort implements Comparator<Journey> {

  /**
   * Compares two Journeys for sorting based on airfare
   */
  @Override
  public int compare(Journey o1, Journey o2) {
    return Integer.compare(o1.getCost(), o2.getCost());
  }
}
