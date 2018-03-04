package afrs.appcontroller;

import afrs.appmodel.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * PassengerStorage class that stores all passengers with their respective reservations into a map. Also writes storage
 * changes to file when program is quit
 *
 * @author Dylan Johnston
 */
public class PassengerStorage {



    private Map<String,Passenger> passengers; //map of name of passenger to their respective object
  /**
   * Create a new PassengerStorage object
   */
  public PassengerStorage(){
    passengers = new HashMap<>();
    setupPassngerMap();
  }

  /**
   * setup for the passenger map. reads line by line of passenger.txt and parses it down to individual flights,then creates
   * itineraries, which turns it into reservations which is placed in passenger object
   */
  private void setupPassngerMap() {
      try {
        File file = new File(System.getProperty("user.home") + "\\passengers.txt");
        file.createNewFile();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
          String[] line_info = line.split("^"); //splits line on name and itineraries
          String name = line_info[0];
          String[] split_itineraries = line_info[1].split("|"); //splits line on each itinerary
          Itinerary it = new Itinerary();
          for(String str: split_itineraries){
              String[] it_line = str.split("-"); //splits line on each flight
              int total_cost = Integer.parseInt(it_line[0]);
              String[] flights_line = it_line[1].split("/");

              for(String flight:flights_line){ // splits line on each paramter of flight
                String[] flight_info = flight.split(",");
                int ID = Integer.parseInt(flight_info[0]);
                int cost = Integer.parseInt(flight_info[1]);
                String origin = flight_info[2];
                String depart = flight_info[3];
                String destination = flight_info[4];
                String arrive = flight_info[5];
                Flight newFlight = new Flight(ID,origin,destination,cost,new Time(depart),new Time(arrive));
                it.addFlight(newFlight); //addition of flight into itinerary
              }
          }
          Reservation reservation = new Reservation(new Passenger(name),it); //create reservation object
          addPassengerOrReservation(name,reservation); //function that creates passenger and/or adds reservation to said
                                                      // passenger object
        }
        reader.close();
      } catch (Exception e) {
        System.err.format("Exception occurred trying to read passengers.txt");
        e.printStackTrace();
      }
    }


  /**
   * gets specific passenger based on name
   * @param ID name of passenger
   * @return passenger with name
   */
    public Passenger getPassenger(String ID){
      return passengers.get(ID);
    }

  /**
   * takes a name of passenger and a reservation object. checks if passenger is present, then if reservation is present in
   * passenger, otherwise it creates a passenger and adds a resservation to it
   * @param name name of passenger
   * @param res reservation object
   * @return true if reservation exists for passenger, false otherwise
   */
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

  /**
   * checks if passenger is in storage and then tries to remove it from that passengers reservations
   * @param name name of passenger
   * @param origin name of origin airport
   * @param destination name of destination aiport
   * @return true if removed, false otherwise
   */
    public boolean removeReservation(String name, String origin, String destination){
      if (passengers.containsKey(name)){
        return passengers.get(name).removeReservation(origin, destination);
      }else{
        return false;
      }
    }

  /**
   * Takes all information from passenger storage and writes it to a file in a certain format.
   */
  public void writePassengersFile(){

      try {

        //Creating a file
        Writer fileWriter = new FileWriter(System.getProperty("user.home") + "\\passengers.txt");
        Writer bufferedWriter = new BufferedWriter(fileWriter);

        // Writing the content
        for(String key : passengers.keySet()) {
          String line = "";
          line += key + "^";
          for(Journey res: passengers.get(key).getReservations()){ //creates String of all journey objects in passenger's
            line += res.toStringForFile();                         // reservations
          }
          bufferedWriter.write(line);
        }
        bufferedWriter.close();
      } catch (IOException e) {
        System.out.println("Problem occurs when creating file " + "");
        e.printStackTrace();
       }
    }

  /**
   * gets all journey objects associated with a passenger, with possible specification of origin and destination airports
   * @param name name of passenger
   * @param origin name of origin airport
   * @param desitination name of destination airport
   * @return list of journey objects
   */
    public ArrayList<Journey> getReservations(String name, String origin, String desitination){
      ArrayList<Journey> reservationArrayList = new ArrayList<>();
      if(origin.equals("") && desitination.equals("")){ //checks if client provided origin and destination names
        return passengers.get(name).getReservations();
      } else if(!origin.equals("") && (desitination.equals(""))){
        for(Journey reservation: passengers.get(name).getReservations()){ //goes through to find matching journeys
          if (reservation.getOrigin().equals(origin)){
            reservationArrayList.add(reservation);
          }
        }
        return reservationArrayList;
      } else if (origin.equals("") && !desitination.equals("")){
        for(Journey reservation: passengers.get(name).getReservations()){
          if (reservation.getDestination().equals(desitination)){
            reservationArrayList.add(reservation);
          }
        }
        return reservationArrayList;

      }else{
        for(Journey reservation: passengers.get(name).getReservations()){
          if (reservation.getOrigin().equals(origin) && reservation.getDestination().equals(desitination)){
            reservationArrayList.add(reservation);
          }
        }
        return reservationArrayList;
      }
    }
}
