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

  public void setDelayTime(int delayTime){
    this.delayTime = delayTime;
  }

  public void setLayoverTime(int layoverTime){
    this.layoverTime = layoverTime;
  }

  public String getName() {
    return name;
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

  public int getDelayTime() {
    return delayTime;
  }

  public int getLayoverTime() {
    return layoverTime;
  }
}



