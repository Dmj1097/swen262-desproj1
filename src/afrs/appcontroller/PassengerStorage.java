package afrs.appcontroller;

import afrs.appmodel.Flight;
import afrs.appmodel.Itinerary;
import afrs.appmodel.Journey;
import afrs.appmodel.Passenger;
import afrs.appmodel.Reservation;
import afrs.appmodel.Time;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PassengerStorage class that stores all passengers with their respective reservations into a map. Also writes storage
 * changes to file when program is quit
 *
 * @author Dylan Johnston
 */
public class PassengerStorage implements Storage {

  //private static String location = PassengerStorage.class.getProtectionDomain().getCodeSource()
  private static String location = System.getProperty("user.home");
  private Map<String, Passenger> passengers; //map of name of passenger to their respective object

  /**
   * Create a new PassengerStorage object
   */
  public PassengerStorage() {
    System.out.println(location);
    passengers = new HashMap<>();
    setupMap();
  }

  /**
   * setup for the passenger map. reads line by line of passenger.txt and parses it down to individual flights,then creates
   * itineraries, which turns it into reservations which is placed in passenger object
   */
  public void setupMap() {
    try {
      File file = new File(location + "\\passengers.txt");
      file.createNewFile();
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line;
      while ((line = reader.readLine()) != null) {
        String[] line_info = line.split("\\^"); //splits line on name and itineraries
        String name = line_info[0];
        String[] split_itineraries = line_info[1].split("\\|"); //splits line on each itinerary
        for (String str : split_itineraries) {
          Itinerary it = new Itinerary();
          String[] flights_line = str.split("/");
          for (String flight : flights_line) { // splits line on each paramter of flight
            String[] flight_info = flight.split(",");
            int ID = Integer.parseInt(flight_info[0]);
            int cost = Integer.parseInt(flight_info[1]);
            String origin = flight_info[2];
            String depart = flight_info[3];
            String destination = flight_info[4];
            String arrive = flight_info[5];
            Flight newFlight = new Flight(ID, origin, destination, cost, new Time(depart),
                new Time(arrive));
            it.addFlight(newFlight); //addition of flight into itinerary
          }
          Reservation reservation = new Reservation(new Passenger(name),
              it); //create reservation object
          addPassengerOrReservation(name,
              reservation); //function that creates passenger and/or adds reservation to said passenger object
        }
      }
      reader.close();
    } catch (Exception e) {
      System.err.format("Exception occurred trying to read passengers.txt");
      e.printStackTrace();
    }
  }


  /**
   * gets specific passenger based on name
   *
   * @param ID name of passenger
   * @return passenger with name
   */
  public Object getInstance(Object ID) {
    return passengers.get(ID);
  }

  /**
   * takes a name of passenger and a reservation object. checks if passenger is present, then if reservation is present in
   * passenger, otherwise it creates a passenger and adds a resservation to it
   *
   * @param name name of passenger
   * @param res reservation object
   * @return true if reservation exists for passenger, false otherwise
   */
  public boolean addPassengerOrReservation(String name, Reservation res) {
    if (passengers.containsKey(name)) {
      if (passengers.get(name).alreadyContains(res.getOrigin(), res.getDestination())) {
        return false;
      } else {
        passengers.get(name).addReservation(res);
        return true;
      }
    } else {
      Passenger passenger = new Passenger(name);
      passenger.addReservation(res);
      passengers.put(name, passenger);
      return true;
    }
  }

  /**
   * checks if passenger is in storage and then tries to remove it from that passengers reservations
   *
   * @param name name of passenger
   * @param origin name of origin airport
   * @param destination name of destination aiport
   * @return true if removed, false otherwise
   */
  public boolean removeReservation(String name, String origin, String destination) {
    boolean result = passengers.containsKey(name) && passengers.get(name)
        .removeReservation(origin, destination);
    if (passengers.containsKey(name) && passengers.get(name).getReservations().isEmpty()) {
      passengers.remove(name);
    }
    return result;
  }

  /**
   * Takes all information from passenger storage and writes it to a file in a certain format.
   */
  public void writePassengersFile() {

    try {

      //Creating a file writer
      Writer fileWriter = new FileWriter(location + "\\passengers.txt");
      Writer bufferedWriter = new BufferedWriter(fileWriter);

      // Writing the content
      for (String key : passengers.keySet()) {
        StringBuilder line = new StringBuilder();
        line.append(key).append("^");
        for (Journey res : passengers.get(key)
            .getReservations()) { //creates String of all journey objects in passenger's
          line.append(res.toStringForFile()).append("|");        // reservations
        }
        line.deleteCharAt(line.toString().length() - 1);
        bufferedWriter.write(line.toString() + "\n");
      }
      bufferedWriter.close();
    } catch (IOException e) {
      System.out.println("Problem occurs when creating file " + "passengers.txt");
      e.printStackTrace();
    }
  }

  /**
   * gets all journey objects associated with a passenger, with possible specification of origin and destination airports
   *
   * @param name name of passenger
   * @param origin name of origin airport
   * @param desitination name of destination airport
   * @return list of journey objects
   */
  public ArrayList<Journey> getReservations(String name, String origin, String desitination) {
    ArrayList<Journey> reservationArrayList = new ArrayList<>();
    if (origin.equals("") && desitination
        .equals("")) { //checks if client provided origin and destination names
      return passengers.get(name).getReservations();
    } else if (!origin.equals("") && (desitination.equals(""))) {
      for (Journey reservation : passengers.get(name)
          .getReservations()) { //goes through to find matching journeys
        if (reservation.getOrigin().equals(origin)) {
          reservationArrayList.add(reservation);
        }
      }
      return reservationArrayList;
    } else if (origin.equals("") && !desitination.equals("")) {
      for (Journey reservation : passengers.get(name).getReservations()) {
        if (reservation.getDestination().equals(desitination)) {
          reservationArrayList.add(reservation);
        }
      }
      return reservationArrayList;

    } else {
      for (Journey reservation : passengers.get(name).getReservations()) {
        if (reservation.getOrigin().equals(origin) && reservation.getDestination()
            .equals(desitination)) {
          reservationArrayList.add(reservation);
        }
      }
      return reservationArrayList;
    }
  }

  /**
   * gets reservation based on supplied name, origin, and destination
   *
   * @param name passenger name
   * @param origin origin airport abbreviation
   * @param destination destination airport abbreviation
   * @return specified reservation
   */
  public Reservation getReservation(String name, String origin, String destination) {
    Passenger p = passengers.get(name);
    if (p != null) {
      List<Journey> reservations = p.getReservations();
      for (Journey j : reservations) {
        if (j.getOrigin().equals(origin) && j.getDestination().equals(destination)) {
          return new Reservation(p, j);
        }
      }
    }
    return null;
  }
}
