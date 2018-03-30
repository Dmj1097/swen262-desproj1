package afrs.appcontroller;

import afrs.appmodel.Weather;
import java.util.Iterator;

/**
 * Iterator for a WeatherCollection
 * Cycles through weather objects on loop
 *
 * @author Alex Piazza
 */
public class WeatherIterator implements Iterator {

  /**
   * The weather collection that this iterator is iterating over
   */
  private WeatherCollection collection;

  /**
   * The current index in the weather collection
   */
  private int index;

  public WeatherIterator(WeatherCollection collection) {
    this.collection = collection;
    this.index = 0;
  }

  /**
   * Getting weather is cyclic, so hasNext will always return true
   */
  public boolean hasNext() {
    return true;
  }

  /**
   * Get the next valid weather object
   */
  public Weather next() {
    Weather result = this.collection.getWeather(index);
    index++;
    if (this.index >= this.collection.size()) {
      this.index = 0;
    }
    return result;
  }
}
