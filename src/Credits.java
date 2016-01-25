/*************************************************************** 
* file: Credits.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 1 
* date last modified: 1/23/2016
* 
* purpose: This class changes the way the credits frame would look
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
import javax.swing.JPanel;

public class Credits extends JComponent{
    // method: Credits
    // purpose: changes the way the credits menu looks like
    public void paintComponent(Graphics g) {
        if(g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D)g;
            Font font = new Font("Arial", Font.BOLD, 72);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.setFont(font);
            g2.drawString("Credits",160,60); 
            Font font2 = new Font("Arial", Font.BOLD, 20);
            g2.setFont(font2);
            g2.setColor(Color.GREEN);
            g2.drawString("Josue Miramontes",220,200);
            g2.drawString("Sang Choi",228,250);
        }
    }
}