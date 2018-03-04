package afrs.appmodel;

public class Weather {


  private int temperature;

  private String forecast;

  private boolean checked;


  public Weather(String condition, int temp) {
    temperature = temp;
    forecast = condition;
  }

  public boolean getChecked() {
    return checked;
  }

  public void setChecked() {
    if (checked) {
      checked = false;
    } else {
      checked = true;
    }
  }

  @Override
  public String toString() {
    return forecast + "," + temperature;
  }
}
