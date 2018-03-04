package afrs.appcontroller;

import afrs.appmodel.Flight;
import afrs.appmodel.Itinerary;
import afrs.appmodel.Journey;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ItineraryGenerator {

    private static final int FLIGHT_LIMIT = 3;
    private StorageCenter storageCenter;
    private List<Journey> latestItineraries;

    public ItineraryGenerator(StorageCenter storageCenter){
        this.storageCenter = storageCenter;
    }

    /**
     * Create a collection of valid journeys from an origin and destination
     * @param origin - the name of the origin airport
     * @param destination - the name of the destination airport
     * @return a collection of journeys
     */
    public List<Journey> generateJourneys(String origin, String destination, int connections){

        LinkedList<Flight> flightPath = new LinkedList<>();
        List<Journey> journeys = new ArrayList<>();      // Collection of Itineraries

      List<Journey> availableFlights = new ArrayList<>(this.storageCenter.getFlightsFromOrigin(origin));
        Itinerary it;

        for (Journey firstLeg : availableFlights){
            Flight firstFlight = (Flight) firstLeg;

            // Test each possible second flight
            for(Journey secondLeg : this.storageCenter.getFlightsFromOrigin(firstFlight.getDestination())){
                Flight secondFlight = (Flight) secondLeg;
                if( isValidNextFlight( firstFlight, secondFlight ) ){
                    if(secondFlight.getDestination().equals(destination)){
                        it = new Itinerary();
                        it.addFlight(firstFlight);
                        it.addFlight(secondFlight);
                        journeys.add( it );
                    }

                    // Test each possible third flight
                    for(Journey thirdLeg : this.storageCenter.getFlightsFromOrigin(secondFlight.getDestination())){
                        Flight thirdFlight = (Flight) thirdLeg;
                        if( isValidNextFlight(secondFlight, thirdFlight) && thirdFlight.getDestination().equals(destination)){
                            it = new Itinerary();
                            it.addFlight(firstFlight);
                            it.addFlight(secondFlight);
                            it.addFlight(thirdFlight);
                            journeys.add( it );
                        }
                    }
                }
            }
        }

        // Add all single-flights that go from the origin to the destination
        for (Journey flight : availableFlights) {
            if (flight.getDestination().equals(destination)){
                journeys.add(flight);
            }
        }
        setLatestItineraries(journeys);
        return journeys;
    }

    /**
     * Determine if a flight can be a valid subsequent flight
     * Itineraries cannot have a second flight which has an earlier time than the first
     * @param firstFlight - the first flight of a flight sequence
     * @param secondFlight - the second flight of a flight sequence
     * @return true if second flight can be followed by the second
     */
    private boolean isValidNextFlight(Flight firstFlight, Flight secondFlight){

       return firstFlight.getArrivalTime().compareTo( secondFlight.getArrivalTime() ) >= 0;
    }

    /**
     * takes the query of itineraries and sets it as the current list of itineraries that can be called back for
     * reservation
     * @param itineraries list of itineraries
     */
    private void setLatestItineraries(List<Journey> itineraries){
        latestItineraries = itineraries;
    }

    /**
     * gets specfic itinerary from the list based on given index
     * @param idx index being looked at
     * @return specfic itinerary
     */
    public Journey getItinerary(int idx){
        return latestItineraries.get(idx - 1);
    }
}