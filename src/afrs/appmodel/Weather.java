package afrs.appmodel;

/**
 * The weather at an airport
 */
public class Weather {

  private String temperature;

  private String forecast;

  public Weather(String condition, String temp) {
    temperature = temp;
    forecast = condition;
  }

  /**
   * Get a human-readable string from this object
   */
  @Override
  public String toString() {
    return forecast + "," + temperature;
  }
}
