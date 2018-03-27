package afrs.appcontroller;

import afrs.appmodel.Airport;
import afrs.appmodel.Weather;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.xml.ws.ProtocolException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FAAWeatherCenter {


    private static JsonObject getAirport(String code) throws IOException {
        String url =
                "https://soa.smext.faa.gov/asws/api/airport/status/";
        URL FAAURL = new URL(url + code);
        HttpURLConnection urlConnection =
                (HttpURLConnection) FAAURL.openConnection();

        // Set the paramters for the HTTP Request
        urlConnection.setRequestMethod("GET");
        urlConnection.setConnectTimeout(10000);
        urlConnection.setReadTimeout(10000);
        urlConnection.setRequestProperty("Accept", "application/json");

        // Create an input stream and wrap in a BufferedReader to read the
        // response.
        BufferedReader in =
                new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        // MAKE SURE TO CLOSE YOUR CONNECTION!
        in.close();

        // response is in JSON format
        JsonParser parser = new JsonParser();
        return parser.parse(response.toString()).getAsJsonObject();
    }

    private Airport generateAirport(String ID){
        Airport airport;
        try {
                JsonObject JSONairport = getAirport(ID);
                String condition = JSONairport.get("Weather").getAsJsonObject().get("Weather").getAsJsonObject().toString();
                int temp = Integer.parseInt(JSONairport.get("Weather").getAsJsonObject().get("Weather").getAsJsonArray().get(0).getAsJsonObject().get("Temp").toString());
                Weather weather = new Weather(condition,temp);
                ArrayList<Weather> weatherlist  = new ArrayList<>();
                weatherlist.add(weather);
                airport = new Airport(JSONairport.get("IATA").toString(),JSONairport.get("City").toString());
                airport.setWeather(weatherlist);
                airport.setDelayTime(Integer.parseInt(JSONairport.get("DelayCount").toString()));
                return airport;


        }
        catch (FileNotFoundException e) {
            System.out.print("URL not found: ");
            System.out.println(e.getMessage());
        }
        catch (MalformedURLException e) {
            System.out.print("Malformed URL: ");
            System.out.println(e.getMessage());
        }
        catch (ProtocolException e) {
            System.out.print("Unsupported protocol: ");
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }




    public Airport getInstance(String ID){
        return generateAirport(ID);

    }





}
