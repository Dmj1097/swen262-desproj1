package afrs.uicontroller;

import afrs.appmodel.Journey;
import java.util.Comparator;

public class CostSort implements Comparator<Journey> {

  @Override
  public int compare(Journey o1, Journey o2) {
    return Integer.compare(o1.getCost(), o2.getCost());
  }
}
