/*************************************************************** 
* file: Clock.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 2 
* date last modified: 2/14/2016
* 
* purpose: This class implements the displayed clock
* 
****************************************************************/
import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.text.SimpleDateFormat;

class Clock {
    public JLabel time = new JLabel();
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
        timer.scheduleAtFixedRate( new TimerTask(){
            public void run(){
                if( currentSecond == 60 ) {
                    reset();
                }
                
                String month = "";
                
                switch (calendar.get(Calendar.MONTH)+1) {
                    case 1: month = "January";
                        break;
                    case 2: month = "February";
                        break;
                    case 3: month = "March";
                        break;
                    case 4: month = "April";
                        break;
                    case 5: month = "May";
                        break;
                    case 6: month = "June";
                        break;
                    case 7: month = "July";
                        break;
                    case 8: month = "August";
                        break;
                    case 9: month = "September";
                        break;
                    case 10: month = "October";
                        break;
                    case 11: month = "November";
                        break;
                    case 12: month = "December";
                        break;
                    default: System.out.println("Invalid Month");
                }
                time.setText(month + " " + calendar.get(Calendar.DAY_OF_MONTH) + ", " +
                        calendar.get(Calendar.YEAR) + String.format(" %s:%02d", sdf.format(calendar.getTime()), currentSecond));
                currentSecond++;
            }
        }, 0, 1000 );
    }
}