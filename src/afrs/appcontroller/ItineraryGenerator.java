package afrs.appcontroller;

import afrs.appmodel.Flight;
import afrs.appmodel.Itinerary;
import afrs.appmodel.Journey;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ItineraryGenerator {

    private static final int FLIGHT_LIMIT = 3;
    private StorageCenter storageCenter;

    public ItineraryGenerator(StorageCenter storageCenter){
        this.storageCenter = storageCenter;
    }

    public ArrayList<Itinerary> generateItineraries(String origin, String destination, int connections){

        ArrayList<Itinerary> itineraries = new ArrayList<>();      // Collection of Itineraries
        ArrayList<Flight> availableFlights = new ArrayList<>();    // Any flight that can be taken within the flight limit
        ArrayList<Flight> flightPath = new ArrayList<>();
        Itinerary it;


        for( int i = 1; i <= FLIGHT_LIMIT; i++ ){
            for (Flight flight : availableFlights) {

                flightPath.set(i-1, flight);

                if (flight.getDestination() == destination){
                    it = new Itinerary();
                    for (Flight validFlight : flightPath) {
                        it.addFlight(validFlight);
                    }
                }
                availableFlights.addAll( this.storageCenter.getFlightsFromOrigin(flight.getDestination()) );
            }
        }

        storageCenter.setLatestItineraries(itineraries);
        return itineraries;
    }
}