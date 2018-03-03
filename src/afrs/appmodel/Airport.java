package afrs.appmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Airport
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Airport {


  private String name;

  private String abbreviation;

  private ArrayList<Weather> weatherlist;

  private double temperature;

  private int delayTime;

  private int layoverTime;


  /**
   * Create a new Airport object
   */
  public Airport(String abbreviation, String name){

    this.abbreviation = abbreviation;
    this.name = name;

  }

  public void setWeather(ArrayList<Weather> weather){
    this.weatherlist = weather;
  }

  public void setTemperature(double temp){
    this.temperature = temp;
  }

  public void setDelayTime(int delayTime){
    this.delayTime = delayTime;
  }

  public void setLayoverTime(int layoverTime){
    this.layoverTime = layoverTime;
  }

  public String getName() {
    return name;
  }

  public String getWeatherList(){
    return weatherlist.toString();
  }
  public String getWeather() {
    for(Weather weather: weatherlist){
      if(!weather.getChecked()){
        weather.setChecked();
        return weather.toString();
      }
    }for (Weather weather: weatherlist){
      weather.setChecked();
    }
    weatherlist.get(0).setChecked();
    return weatherlist.get(0).toString();
  }

  public double getTemperature() {
    return temperature;
  }

  public int getDelayTime() {
    return delayTime;
  }

  public int getLayoverTime() {
    return layoverTime;
  }
}
