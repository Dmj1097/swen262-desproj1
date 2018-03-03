package afrs.appcontroller;

import afrs.appmodel.Itinerary;

import java.util.ArrayList;

public class ItineraryGenerator {


    private StorageCenter storageCenter;

    public ItineraryGenerator(StorageCenter storageCenter){
        this.storageCenter = storageCenter;
    }


    public ArrayList<Itinerary> generateItineraries(){
        ArrayList<Itinerary> itineraries = new ArrayList<>();
        //TODO itinerary algorithm




        storageCenter.setLatestItineraries(itineraries);
        return itineraries;
    }


}
