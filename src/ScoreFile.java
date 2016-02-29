/*************************************************************** 
* file: ScoreFile.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 2 
* date last modified: 2/27/2016
* 
* purpose: create, modify score file
* 
****************************************************************/ 

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreFile {
    ArrayList<Score> highScore;  
    ObjectOutputStream output;
    ObjectInputStream input;
    
    // Initialize
    public ScoreFile() {
        highScore = new ArrayList<Score>();
    }
    
    // Returns ArrayList of sorted scores
    public ArrayList<Score> getHighScore() {
        load();
        sortScores();
        return highScore;
    }
    
    // Load existing .dat file
    public void load() {
        try {
            input = new ObjectInputStream(new FileInputStream("HighScores.dat"));
            highScore = (ArrayList<Score>) input.readObject();
        } catch (Exception e) {
             e.printStackTrace();
        } finally {
        try {
            if (output != null) {
                output.flush();
                output.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    }
    
    // Update existing .dat file
    public void update() {
        try {
            output = new ObjectOutputStream(new FileOutputStream("HighScores.dat"));
            output.writeObject(highScore);
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
        try {
            if (output != null) {
                output.flush();
                output.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       
    }
    
    // Returns string that contains highest string info
    public String printHighestFive() {
        
        ArrayList<Score> temp = getHighScore();
        String highest = "";
        int i = 0;
        int size = temp.size();
        
        if (size == 0)
        {
            highest += "No Scores in the database";
        }
        else if (size == 1) {
            highest += (i+1) + " - " + temp.get(i).getName() + "\t" + temp.get(i).getScore() + "\n";
        }
        else if (size == 2) {
            while (i < 2)
            {
                highest += (i+1) + " - " + temp.get(i).getName() + "\t" + temp.get(i).getScore() + "\n";
                i++;
            }
        }
        else if (size == 3) {
            while (i < 3)
            {
                highest += (i+1) + " - " + temp.get(i).getName() + "\t" + temp.get(i).getScore() + "\n";
                i++;
            }          
        }
        else if (size == 4) {
            while (i < 4)
            {
                highest += (i+1) + " - " + temp.get(i).getName() + "\t" + temp.get(i).getScore() + "\n";
                i++;
            }   
        } 
        else
        {
            while (i < 5)
            {
                highest += (i+1) + " - " + temp.get(i).getName() + "\t" + temp.get(i).getScore() + "\n";
                i++;
            }   
        }

        return highest;
    }
    
    // Sort scores
    public void sortScores() {
        CompareScore comparator = new CompareScore();
        Collections.sort(highScore, comparator);
    }
    
    // Add score into the .dat file
    public void addScore(int score, String name) {
        load();
        highScore.add(new Score(score, name));
        update();
    }
}

/*
Comparator Class
*/
class CompareScore implements Comparator<Score> {
    
    public int compare(Score score1, Score score2) {
        int compare1 = score1.getScore();
        int compare2 = score2.getScore();
        
        if (compare1 > compare2){
            return -1;
        }
        else if (compare1 < compare2){
            return 1;
        } else {
            return 0;
        }
        
    }

}