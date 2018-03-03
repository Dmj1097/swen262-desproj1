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
        ArrayList<Itinerary> itineraries = new ArrayList<>();

        ArrayList<Flight> availableFlights = new ArrayList<>();
        availableFlights.addAll(storageCenter.getFlightsFromOrigin(origin));

        String current = origin; // the airport whos connections are being checked.
        Itinerary it;

        // For each possible flight
        for( int i = 1; i <= FLIGHT_LIMIT; i++ ){

            //
            for(Journey flight : availableFlights ){
                if( flight.getDestination() == destination ){
                    it = new Itinerary();
                    it.addFlight(flight);



                    itineraries.add( it );
                }
            }
        }


        storageCenter.setLatestItineraries(itineraries);
        return itineraries;
    }


}
