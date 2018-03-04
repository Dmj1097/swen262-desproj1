package afrs.uicontroller;

import afrs.appmodel.Journey;
import java.util.Comparator;

public class AirportSort implements Comparator<Journey> {

  @Override
  public int compare(Journey o1, Journey o2) {
    return o1.getOrigin().compareTo(o2.getOrigin());
  }
}
