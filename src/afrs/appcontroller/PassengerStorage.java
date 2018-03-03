package afrs.appcontroller;

import afrs.appmodel.*;

import java.io.*;
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
        File file = new File(System.getProperty("user.home") + "\\passengers.txt");
        file.createNewFile();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
          String[] line_info = line.split("^");
          String name = line_info[0];
          String[] split_itineraries = line_info[1].split("|");
          Itinerary it = new Itinerary();
          for(String str: split_itineraries){
              String[] it_line = str.split("-");
              int total_cost = Integer.parseInt(it_line[0]);
              String[] flights_line = it_line[1].split("/");

              for(String flight:flights_line){
                String[] flight_info = flight.split(",");
                int ID = Integer.parseInt(flight_info[0]);
                int cost = Integer.parseInt(flight_info[1]);
                String origin = flight_info[2];
                String depart = flight_info[3];
                String destination = flight_info[4];
                String arrive = flight_info[5];
                Flight newFlight = new Flight(ID,origin,destination,cost,new Time(depart),new Time(arrive));
                it.addFlight(newFlight);
              }
          }
          Reservation reservation = new Reservation(new Passenger(name),it);
          addPassengerOrReservation(name,reservation);
        }
        reader.close();
      } catch (Exception e) {
        System.err.format("Exception occurred trying to read passengers.txt");
        e.printStackTrace();
      }
    }



    public Passenger getPassenger(String ID){
      return passengers.get(ID);
    }


    public boolean addPassengerOrReservation(String name, Reservation res) {
      if (passengers.containsKey(name) && passengers.get(name).alreadyContains(res.getOrigin(), res.getDestination())){
        return true;
      }else if(passengers.containsKey(name) && !passengers.get(name).alreadyContains(res.getOrigin(),res.getDestination())){
        passengers.get(name).addReservation(res);
        return false;
      }else {
        Passenger passenger = new Passenger(name);
        passenger.addReservation(res);
        passengers.put(name, passenger);
        return false;
      }
    }

    public boolean removeReservation(String name, String origin, String destination){
      if (passengers.containsKey(name)){
        return passengers.get(name).removeReservation(origin, destination);
      }else{
        return false;
      }
    }

    public void writePassengersFile(){

      try {

        //Creating a file
        Writer fileWriter = new FileWriter(System.getProperty("user.home") + "\\passengers.txt");
        Writer bufferedWriter = new BufferedWriter(fileWriter);

        // Writing the content
        for(String key : passengers.keySet()) {
          String line = "";
          line += key + "^";
          for(Reservation res: passengers.get(key).getReservations()){
            line += res.getJourney().toStringForFile();
          }
          bufferedWriter.write(line);
        }
        bufferedWriter.close();
      } catch (IOException e) {
        System.out.println("Problem occurs when creating file " + "");
        e.printStackTrace();
       }
    }
}
