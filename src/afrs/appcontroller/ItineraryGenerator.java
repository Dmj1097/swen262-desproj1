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

    public ItineraryGenerator(StorageCenter storageCenter){
        this.storageCenter = storageCenter;
    }

    public List<Journey> generateJourneys(String origin, String destination, int connections){

        List<String> flightPath = new LinkedList<>();
        List<Journey> journeys = new ArrayList<>();      // Collection of Itineraries
        List<Journey> availableFlights = new ArrayList<>();    // Any flight that can be taken within the flight limit

        availableFlights.addAll( this.storageCenter.getFlightsFromOrigin(origin) );

        // Add all single-flights that go from the origin to the destination
        for (Journey flight : availableFlights) {
            if (flight.getDestination().equals(destination)){
                journeys.add(flight);
            }
        }

        return journeys;
    }
}