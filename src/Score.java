/*************************************************************** 
* file: Socre.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 2 
* date last modified: 2/27/2016
* 
* purpose: Score class to store score and name
* 
****************************************************************/ 
import java.io.Serializable;

public class Score implements Serializable {
    private int score;
    private String name;
    
    public Score(int score, String name)
    {
        this.score = score;
        this.name = name;
    }
    
    public Score(int score)
    {
        this.score = score;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public String getName()
    {
        return name;
    }
    
}
