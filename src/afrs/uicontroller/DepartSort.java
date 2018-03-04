package afrs.uicontroller;

import afrs.appmodel.Journey;
import java.util.Comparator;

public class DepartSort implements Comparator<Journey> {

  @Override
  public int compare(Journey o1, Journey o2) {
    return o1.getDepart().compareTo(o2.getDepart());
  }
}
