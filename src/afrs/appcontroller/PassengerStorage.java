package afrs.appcontroller;

import afrs.appmodel.Passenger;
import afrs.appmodel.Reservation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * PassengerStorage
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class PassengerStorage {



    private Map<String,Passenger> passengers;
  /**
   * Create a new PassengerStorage object
   */
  public PassengerStorage(){
    passengers = new HashMap<>();
    setupPassngerMap();
  }


    private void setupPassngerMap() {
      try {
        File file = new File("src/afrs/appcontroller/data/passengers.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
          String[] line_info = line.split("^");
          String name = line_info[0];
          String[] split_itineraries = line_info[1].split("|");
          for(String str: split_itineraries){
              String[] it_line = str.split(",");

          }
        }
        reader.close();
      } catch (Exception e) {
        System.err.format("Exception occurred trying to read cities.txt");
        e.printStackTrace();
      }
    }



    public Passenger getPassenger(String ID){
      return passengers.get(ID);
    }


    public void addPassenger(String name, Reservation res){
      //TODO add parameters to Passenger creation to have proper Passenger object be stored
      Passenger passenger = new Passenger();
      passengers.put(name,passenger);
    }

    public void writePassengersFile(){

    }
}
