package afrs.appcontroller;

import afrs.appmodel.Weather;

import java.util.List;
import java.lang.Iterable;

/**
 * Aggregation of Weather objects
 * Represents a the sequence of weathers for an airport
 *
 * @author Alex Piazza
 */
public class WeatherCollection implements Iterable<Weather> {

	/** The underlying collection of weather objects */
	private List<Weather> weatherList;

	public WeatherCollection(List<Weather> weather){
		this.weatherList = weather;
	}

	/**
	 * creates a new weather iterator for an object
	 * @return
	 */
	@Override
	public WeatherIterator iterator(){
		return new WeatherIterator(this);
	}

	/**
	 * gets size of weather list
	 * @return size
	 */
	protected int size(){ return this.weatherList.size();}

	/**
	 * gets weather object at specific index
	 * @param index
	 * @return weather object at specified index
	 */
	protected Weather getWeather(int index){
		return this.weatherList.get(index);
	}
}
