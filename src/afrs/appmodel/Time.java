package afrs.appmodel;

/**
 * Time
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Time {



  public enum Half{
    AM, PM
  }

  private final Half half;
  private int hour;
  private int minute;

  /**
   * Create a new Time object
   */
  public Time(int hour, int minute, Half half){
    this.hour = hour;
    this.minute = minute;
    this.half = half;
  }

  public String toString(){
    return "Time: " + hour + ":" + minute + half;
  }
}
