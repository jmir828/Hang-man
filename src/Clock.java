/*************************************************************** 
* file: Clock.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 1 
* date last modified: 1/23/2016
* 
* purpose: This class implements the display clock
* 
****************************************************************/
import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.text.SimpleDateFormat;

class Clock {
    final JLabel time = new JLabel();
    private final SimpleDateFormat sdf  = new SimpleDateFormat("hh:mm");
    private int   currentSecond;
    private Calendar calendar;

    // method: reset
    //Purpose: Resets the time
    private void reset(){
        calendar = Calendar.getInstance();
        currentSecond = calendar.get(Calendar.SECOND);
    }
    // method: start
    //Purpose: Starts the timer
    public void start(){
        reset();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            public void run(){
                if( currentSecond == 60 ) {
                    reset();
                }
                time.setText( String.format("%s:%02d", sdf.format(calendar.getTime()), currentSecond ));
                currentSecond++;
            }
        }, 0, 1000 );
    }
}