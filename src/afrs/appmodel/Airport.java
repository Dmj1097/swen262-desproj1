package afrs.appmodel;

import java.util.ArrayList;


/**
 * Airport
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Airport {

  /** The name of the city in which the airport is located (e.g. LasVegas, Atlanta, LosAngelos)  */
  private String name;
  /** The airport code (e.g. LAS, ATL, LAX, ... etc.) */
  private String abbreviation;
  /** The airport's weather states (e.g rainy, cloudy, sunny), alternating per query  */
  private ArrayList<Weather> weatherlist;
  /** The average delay that the airport expects */
  private int delayTime;
  /** The avery layover time which the airport expects */
  private int layoverTime;


  /**
   * Create a new Airport object
   */
  public Airport(String abbreviation, String name){

    this.abbreviation = abbreviation;
    this.name = name;

  }

  /**
   * Set the airport's weather states
   * @param weather - the collection of weather states
   */
  public void setWeather(ArrayList<Weather> weather){
    this.weatherlist = weather;
  }

  /**
   * Set the airport's expected delay time
   * @param delayTime - the expected delay time
   */
  public void setDelayTime(int delayTime){
    this.delayTime = delayTime;
  }

  /**
   * Set the airport's expected layover time
   * @param layoverTime - the expected layover time
   */
  public void setLayoverTime(int layoverTime){
    this.layoverTime = layoverTime;
  }

  /**
   * @return the airport abbreviation
   */
  public String getAbbreviation() {
    return abbreviation;
  }

  /**
   * @return current weather, and update the current weather state
   */
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

  /**
   * @return the expected delay time
   */
  public int getDelayTime() {
    return delayTime;
  }

  /**
   * @return the expected layover time
   */
  public int getLayoverTime() {
    return layoverTime;
  }

  @Override
  public String toString() {
    return (name + "," + getWeather() + "," + delayTime);
  }
}



