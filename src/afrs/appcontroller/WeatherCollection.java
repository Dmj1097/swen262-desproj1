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

	private List<Weather> weatherList;

	public WeatherCollection(List<Weather> weather){
		this.weatherList = weather;
	}

	@Override
	public WeatherIterator iterator(){
		return new WeatherIterator(this);
	}

	protected int size(){ return this.weatherList.size();}

	protected Weather getWeather(int index){
		return this.weatherList.get(index);
	}
}
