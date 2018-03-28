package afrs.appcontroller;

import afrs.appmodel.Flight;
import afrs.appmodel.Itinerary;
import afrs.appmodel.Journey;

import java.util.*;

public class ItineraryGenerator {

  private static final int FLIGHT_LIMIT = 3;
  private StorageCenter storageCenter;
  private FAAWeatherCenter faaWeatherCenter;
  private Map<String,Integer> delayMap = new HashMap<>();


  public ItineraryGenerator(StorageCenter storageCenter, FAAWeatherCenter faaWeatherCenter) {
    this.storageCenter = storageCenter;
    this.faaWeatherCenter = faaWeatherCenter;
  }

  /**
   * Create a collection of valid journeys from an origin and destination
   *
   * @param origin - the name of the origin airport
   * @param destination - the name of the destination airport
   * @return a collection of journeys
   */
  public List<Journey> generateJourneys(String origin, String destination, int connections, boolean FAAMode) {
    Set<String> keys = storageCenter.getAirportKeys();
    if(FAAMode){
      for(String ID: keys){
        delayMap.put(ID,faaWeatherCenter.getAirportDelay(ID));
      }
    }else {
      for (String ID : keys) {
        delayMap.put(ID, storageCenter.getAirport(ID).getDelayTime());
      }
    }
    LinkedList<Flight> flightPath = new LinkedList<>();
    List<Journey> journeys = new ArrayList<>();      // Collection of Itineraries

    List<Journey> availableFlights = new ArrayList<>(
        this.storageCenter.getFlightsFromOrigin(origin));
    Itinerary it;

    if (connections == 0) {
      // Add all single-flights that go from the origin to the destination
      for (Journey flight : availableFlights) {
        if (flight.getDestination().equals(destination)) {
          journeys.add(flight);
        }
      }
    } else if (connections == 1) {
      for (Journey firstLeg : availableFlights) {
        Flight firstFlight = (Flight) firstLeg;

        // Test each possible second flight
        for (Journey secondLeg : this.storageCenter
            .getFlightsFromOrigin(firstFlight.getDestination())) {
          Flight secondFlight = (Flight) secondLeg;
          if (isValidNextFlight(firstFlight, secondFlight)) {
            if (secondFlight.getDestination().equals(destination)) {
              it = new Itinerary();
              it.addFlight(firstFlight);
              it.addFlight(secondFlight);
              journeys.add(it);
            }

          }
        }
      }
    } else {
      for (Journey firstLeg : availableFlights) {
        Flight firstFlight = (Flight) firstLeg;

        // Test each possible second flight
        for (Journey secondLeg : this.storageCenter
            .getFlightsFromOrigin(firstFlight.getDestination())) {
          Flight secondFlight = (Flight) secondLeg;
          if (isValidNextFlight(firstFlight, secondFlight)) {
            // Test each possible third flight
            for (Journey thirdLeg : this.storageCenter
                .getFlightsFromOrigin(secondFlight.getDestination())) {
              Flight thirdFlight = (Flight) thirdLeg;
              if (isValidNextFlight(secondFlight, thirdFlight) && thirdFlight.getDestination()
                  .equals(destination)) {
                it = new Itinerary();
                it.addFlight(firstFlight);
                it.addFlight(secondFlight);
                it.addFlight(thirdFlight);
                journeys.add(it);
              }
            }
          }
        }
      }
    }
    return journeys;
  }

  /**
   * Determine if a flight can be a valid subsequent flight
   * Itineraries cannot have a second flight which has an earlier time than the first
   *
   * @param firstFlight - the first flight of a flight sequence
   * @param secondFlight - the second flight of a flight sequence
   * @return true if second flight can be followed by the second
   */
  private boolean isValidNextFlight(Flight firstFlight, Flight secondFlight) {
    int inBetTime = firstFlight.getArrivalTime().getInBetweenTime(secondFlight.getDepartureTime());
    return (delayMap.get(storageCenter.getAirport(secondFlight.getOrigin()).getAbbreviation()) + storageCenter
        .getAirport(secondFlight.getOrigin()).getLayoverTime()) <= inBetTime;
  }
}