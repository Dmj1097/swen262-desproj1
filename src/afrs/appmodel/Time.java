package afrs.appmodel;

/**
 * Time
 *
 * Create By Alex Piazza - 03/01/2018
 */
public class Time implements Comparable<Time> {


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
      new_timestamp = timestamp.replace("a","");
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
    String dayHalf;
    if(half.equals(Half.AM)){
      dayHalf = "a";
    }else {
      dayHalf = "p";
    }
    return String.format("%d:%02d%s", hour, minute, dayHalf);
  }

  /**
   * For comparing two objects
   * @param t - any Time object
   * @return a negative integer, 0, or a positive integer indicating whether o is less than, equal to, or greater than this
   */
  public int compareTo(Time t){
    if (t.half == this.half){
      if(this.hour == t.hour){ return this.minute - t.minute; }
      else{ return this.hour - t.hour; }

    } else if (t.half == Half.AM && this.half == Half.PM){
      return 1;
    } else if (t.half == Half.PM && this.half == Half.AM){
      return -1;
    } else { return 0; }


  }

  public int getInBetweenTime(Time time){
    int inBetTime = 0;
    if(this.half == time.half){
      if (this.hour >= time.hour){
        inBetTime += (this.hour-time.hour)*60;
      }else{
        inBetTime += (this.hour+24 - time.hour)*60;

      }
    } else{
      if (this.half == Half.AM && time.half == Half.PM){
        inBetTime += (time.hour - this.hour + 12)*60;
      } else {
        inBetTime += (this.hour - time.hour + 12)*60;
      }
    }
    if (this.minute >= time.minute){
      inBetTime += (this.minute - time.minute);
    }else{
      inBetTime -= 60;
      inBetTime += (this.minute + 60 - time.minute);
    }
    return inBetTime;
  }

}
