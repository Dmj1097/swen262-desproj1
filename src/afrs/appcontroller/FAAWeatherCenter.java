package afrs.appcontroller;

import afrs.appmodel.Airport;
import afrs.appmodel.Weather;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.ws.ProtocolException;

/**
 * Class that represents the FAA airport query service that gets airport info when a uses switches to FAA mode
 *
 * @author Dylan Johnston
 */
public class FAAWeatherCenter {

  /**
   * generates the json object of the airport data from the specific URL
   *
   * @param code airport ID
   * @return JSONObject representing the airport information
   * @throws IOException in case URL is invalid
   */
  private static JsonObject getAirport(String code) throws IOException {
    String url =
        "https://soa.smext.faa.gov/asws/api/airport/status/";
    URL FAAURL = new URL(url + code); //specifies the airport that is being looked up
    HttpURLConnection urlConnection =
        (HttpURLConnection) FAAURL.openConnection(); //inits connection

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

    while ((inputLine = in.readLine()) != null) { //builds json response
      response.append(inputLine);
    }
    in.close();

    // response is in JSON format
    JsonParser parser = new JsonParser();
    return parser.parse(response.toString()).getAsJsonObject(); //parses jsonstring into jsonobject
  }

  /**
   * generates airport object based on information from website in json format
   *
   * @param ID airport ID
   * @return airport object with info from website
   */
  private static Airport generateAirport(String ID) {
    Airport airport;
    try {

      //get json object of airport info
      JsonObject JSONairport = getAirport(ID);
      String condition = JSONairport.
          get("Weather").getAsJsonObject().
          get("Weather").getAsJsonArray().get(0).getAsJsonObject().
          get("Temp").getAsJsonArray().get(0).
          toString().replace("\"", "");
      String temp = JSONairport.
          get("Weather").getAsJsonObject().
          get("Temp").getAsJsonArray().get(0).
          toString().replace("\"", "");
      Weather weather = new Weather(condition, temp);
      ArrayList<Weather> weatherlist = new ArrayList<>(); //setup weather object for airport
      weatherlist.add(weather);
      airport = new Airport(JSONairport.get("IATA").toString(),
          JSONairport.get("Name").toString().replace("\"", ""));
      airport.setWeather(weatherlist);
      airport.setDelayTime(Integer.parseInt(JSONairport.get("DelayCount").toString()));
      return airport;
    } catch (FileNotFoundException e) { //exceptions for all possible errors that could occur
      System.out.print("URL not found: ");
      System.out.println(e.getMessage());
    } catch (MalformedURLException e) {
      System.out.print("Malformed URL: ");
      System.out.println(e.getMessage());
    } catch (ProtocolException e) {
      System.out.print("Unsupported protocol: ");
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }


  /**
   * gets an airport from the website based on provided airport ID
   *
   * @param ID airport ID
   * @return airport object
   */
  public static Airport getInstance(String ID) {
    return generateAirport(ID);

  }

  /**
   * gets delay time of specifc airport from FAA website
   *
   * @param ID airport ID
   * @return delaytime in minutes
   */
  public static int getAirportDelay(String ID) {
    return generateAirport(ID).getDelayTime();

  }


}
