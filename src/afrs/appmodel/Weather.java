package afrs.appmodel;

public class Weather {


  private String temperature;

  private String forecast;

  public Weather(String condition, String temp) {
    temperature = temp;
    forecast = condition;
  }

  @Override
  public String toString() {
    return forecast + "," + temperature;
  }
}
