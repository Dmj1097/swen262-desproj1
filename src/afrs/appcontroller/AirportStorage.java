package afrs.appcontroller;

import afrs.appmodel.Airport;
import afrs.appmodel.Weather;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for storing all information of each airport provided to us
 *
 * @author Dylan Johnston
 */
public class AirportStorage implements Storage {


  private Map<String, Airport> airports; //map of all airport objects to their abbreviation strings

  /**
   * Create a new AirportStorage object
   */
  public AirportStorage() {
    airports = new HashMap<>();
    setupMap();
    setupAirportWeather();
  }

  /**
   * gets an airport from the map based on provided ID
   *
   * @param ID ID for airport
   * @return Airport asscoiated with abbreviation
   */
  public Object getInstance(Object ID) {
    return airports.get(ID);
  }


  /**
   * initialization class that reads from cities.txt, connectTime.txt, delayTime.txt, and weather.txt to create, put
   * into storage, and fill in information for each airport object
   */
  public void setupMap() {
    try { //starts by reading cities.txt
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(getClass().getResourceAsStream("/cities.txt")));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] line_info = line
            .split(","); //splits input line for reading each parameter of an aiport
        Airport airport = new Airport(line_info[0],
            line_info[1]); //takes abbreviation and name and creates new airport to place into map
        airports.put(line_info[0], airport);
      }
      reader.close();
    } catch (Exception e) { //in case cities.txt disappears
      System.err.format("Exception occurred trying to read cities.txt");
      e.printStackTrace();
    }
    try { //next sets up the layover time of each airport
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(getClass().getResourceAsStream("/connectTime.txt")));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] line_info = line.split(",");
        airports.get(line_info[0])
            .setLayoverTime(Integer.parseInt(line_info[1])); //finds airport and sets layover time
      }
      reader.close();
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read connectTime.txt");
      e.printStackTrace();
    }
    try { // next sets up delay time due to current condition of airport
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(getClass().getResourceAsStream("/delayTime.txt")));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] line_info = line.split(",");
        airports.get(line_info[0]).setDelayTime(
            Integer.parseInt(line_info[1])); //finds and sets delay time like previously
      }
      reader.close();
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read delayTime.txt");
      e.printStackTrace();
    }
  }

  /**
   * Sets up weather object in each airport. Is a separate method because added parameter isn't string like before
   */
  private void setupAirportWeather() {
    try {
      BufferedReader reader = new BufferedReader(
          new InputStreamReader(getClass().getResourceAsStream("/weather.txt")));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] line_info = line.split(",");
        ArrayList<Weather> weatherList = new ArrayList<>();
        for (int i = 1; i <= line_info.length - 1; i += 2) {
          weatherList.add(new Weather(line_info[i],
              Integer.parseInt(line_info[i + 1]))); //takes each weather pair in line
        }                                                                               // and turns it into weather object
        airports.get(line_info[0]).setWeather(
            weatherList); //takes list of weather objects and adds it to corresponding airport
      }
      reader.close();
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read weather.txt");
      e.printStackTrace();
    }
  }

  public List<String> getAirportCodes(){
    List<String> result = new ArrayList<>();
    result.addAll( this.airports.keySet());
    return result;
  }
}