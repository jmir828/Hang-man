/*************************************************************** 
* file: HighScores.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 2
* date last modified: 2/14/2016
*  
* purpose: This class displays the 'HighScore' window to player
* 
****************************************************************/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JComponent;

public class HighScores extends JComponent {
    // method: paintComponent
    // purpose: changes the way the highScore menu is displayed
    public void paintComponent(Graphics g) {
        ScoreFile sf = new ScoreFile();
        ArrayList<Score> scores = sf.getHighScore();
        
        if(g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D)g;
            Font font = new Font("Arial", Font.BOLD, 72);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.setFont(font);
            g2.drawString("High Scores",90,60); 
            
            Font scoresFont = new Font("Arial", Font.PLAIN, 32);
            Graphics2D score1 = (Graphics2D)g;
            score1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            score1.setColor(Color.WHITE);
            score1.setFont(scoresFont);
            score1.drawString("1 " + sf.highScore.get(0).getName() + " " + sf.highScore.get(0).getScore(),120,120); 
            
            Graphics2D score2 = (Graphics2D)g;
            score2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            score2.setColor(Color.WHITE);
            score2.setFont(scoresFont);
            score2.drawString("2 " + sf.highScore.get(1).getName() + " " + sf.highScore.get(1).getScore(),120,160);
            
            Graphics2D score3 = (Graphics2D)g;
            score3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            score3.setColor(Color.WHITE);
            score3.setFont(scoresFont);
            score3.drawString("3 " + sf.highScore.get(2).getName() + " " + sf.highScore.get(2).getScore(),120,200);
            
            Graphics2D score4 = (Graphics2D)g;
            score4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            score4.setColor(Color.WHITE);
            score4.setFont(scoresFont);
            score4.drawString("4 " + sf.highScore.get(3).getName() + " " + sf.highScore.get(3).getScore(),120,240);
            
//            Graphics2D score5 = (Graphics2D)g;
//            score5.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            score5.setColor(Color.WHITE);
//            score5.setFont(scoresFont);
//            score5.drawString("5 " + sf.highScore.get(4).getName() + " " + sf.highScore.get(4).getScore(),120,280);
        }
    }
}