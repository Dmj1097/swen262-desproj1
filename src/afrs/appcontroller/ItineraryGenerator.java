package afrs.appcontroller;

import afrs.appmodel.Flight;
import afrs.appmodel.Itinerary;
import afrs.appmodel.Journey;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ItineraryGenerator {

    private static final int FLIGHT_LIMIT = 3;
    private StorageCenter storageCenter;

    public ItineraryGenerator(StorageCenter storageCenter){
        this.storageCenter = storageCenter;
    }

    public List<Journey> generateItineraries(String origin, String destination, int connections){

        List<Journey> itineraries = new ArrayList<>();      // Collection of Itineraries
        List<Flight> availableFlights = new ArrayList<>();    // Any flight that can be taken within the flight limit
        List<Flight> flightPath = new ArrayList<>();
        Itinerary it;


        for( int i = 1; i <= FLIGHT_LIMIT; i++ ){
            for (Flight flight : availableFlights) {

                flightPath.set(i-1, flight);

                if (flight.getDestination().equals(destination)){
                    it = new Itinerary();
                    for (Flight validFlight : flightPath) {
                        it.addFlight(validFlight);
                    }
                }
                availableFlights.addAll( this.storageCenter.getFlightsFromOrigin(flight.getDestination()) );
            }
        }

        return itineraries;
    }
}