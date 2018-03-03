package afrs.appcontroller;
import afrs.appmodel.Airport;
import afrs.appmodel.Weather;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * AirportStorage
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class AirportStorage {


  private Map<String, Airport> airports;

  /**
   * Create a new AirportStorage object
   */
  public AirportStorage() {
    airports = new HashMap<>();
    setupAirportMap();
    setupAirportWeather();
  }


  public Airport getAirport(String ID) {
    return airports.get(ID);
  }

  private void setupAirportMap() {
    try {
      File file = new File("src/afrs/appcontroller/data/cities.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] line_info = line.split(",");
        Airport airport = new Airport(line_info[0], line_info[1]);
        airports.put(line_info[0], airport);
      }
      reader.close();
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read cities.txt");
      e.printStackTrace();
    }
    try {
      File file = new File("src/afrs/appcontroller/data/connectTime.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] line_info = line.split(",");
        airports.get(line_info[0]).setLayoverTime(Integer.parseInt(line_info[1]));
      }
      reader.close();
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read connectTime.txt");
      e.printStackTrace();
    }
    try {
      File file = new File("src/afrs/appcontroller/data/delayTime.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] line_info = line.split(",");
        airports.get(line_info[0]).setDelayTime(Integer.parseInt(line_info[1]));
      }
      reader.close();
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read delayTime.txt");
      e.printStackTrace();
    }
  }

  private void setupAirportWeather(){
    try {
      File file = new File("src/afrs/appcontroller/data/weather.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] line_info = line.split(",");
        ArrayList<Weather> weatherList = new ArrayList<>();
        for (int i = 1; i <= line_info.length-1; i += 2){
          weatherList.add(new Weather(line_info[i],Integer.parseInt(line_info[i + 1])));
        }
        airports.get(line_info[0]).setWeather(weatherList);
      }
      reader.close();
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read weather.txt");
      e.printStackTrace();
    }
  }
}