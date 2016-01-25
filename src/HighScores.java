/*************************************************************** 
* file: HighScores.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 1 
* date last modified: 1/23/2016
*  
* purpose: This class changes the way the Highscores frame would look
*          like
* 
****************************************************************/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighScores extends JComponent{
    // method: paintComponent
    // purpose: changes the way the highScore menu looks like
    public void paintComponent(Graphics g) {
        if(g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D)g;
            Font font = new Font("Arial", Font.BOLD, 32);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.setFont(font);
            g2.drawString("High Scores",210,100); 
            
            //Place Holders for "High Scores" window
            JLabel h1,h2,h3,h4,h5 = new JLabel("AAA ----------");
            
        }
    }
}