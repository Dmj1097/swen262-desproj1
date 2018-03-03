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
  public Time(String timestamp){

    String new_timestamp;
    if(timestamp.contains("p")){
      half = Half.PM;
      new_timestamp = timestamp.replace("p","");
    }else{
      half = Half.AM;
      new_timestamp = timestamp.replace("c","");
    }
    String[] time_line = new_timestamp.split(":");
    hour = Integer.parseInt(time_line[0]);
    minute = Integer.parseInt(time_line[1]);
  }

  public Time(int hour, int minute, Half half){
    this.hour = hour;
    this.minute = minute;
    this.half = half;
  }

  public String toString(){
    return "Time: " + hour + ":" + minute + half;
  }
}
