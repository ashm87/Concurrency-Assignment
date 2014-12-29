package Concurrency;

import java.util.Calendar;


/**
 * Created by Ashley Morris on 21/12/2014.
 */
public class Clock extends Thread {

    private static Calendar time;
    private static int hours, mins, secs;

    public Clock() {
        //Constructs a clock and sets the time to 12pm.
        time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, 12);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
    }


    @Override
    public void run() {
        while (hours <= 13) {

            //Increase the time on the clock object by 5 seconds every 'tick'.
            try {
                time.add(Calendar.SECOND, 5);

                hours = time.get(Calendar.HOUR_OF_DAY);
                mins = time.get(Calendar.MINUTE);
                secs = time.get(Calendar.SECOND);

                System.out.println("time is " + hours+ ":" + mins + ":" + secs);

                sleep(1000);//Pause of one second or else the time will update too fast.

            }
            catch (InterruptedException iex) {

            }
        }
    }


    /**
     * Checks to see if the duration has elapsed between the time stamp and now.
     *
     * @param timeStamp The time in milliseconds to measure from.
     * @param duration The difference in seconds between
     * @return True if the difference is 0. False if not.
     */
    public synchronized boolean timerCompleted(Long timeStamp, int duration){

        Long difference = time.getTimeInMillis() - timeStamp; //Calculate the difference between the two time periods.
        int differenceSecs = (int) (difference / 1000); //Difference in seconds. 1 second = 1000 milliseconds.

        //If seconds difference is equal to the duration then the timer is completed.
        if(differenceSecs == duration){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Gets a timestamp of the current clock time in milliseconds.
     *
     * @return A long that represents the current time on the clock in milliseconds.
     */
    public synchronized long getTimeStamp(){

        long timeStamp = time.getTimeInMillis();

        return timeStamp;
    }



}
