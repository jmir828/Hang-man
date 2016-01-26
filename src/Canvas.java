/*************************************************************** 
* file: Canvas.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 1 
* date last modified: 1/23/2016
* purpose: This class changes the way the Canvas frame would look
*          like
* 
****************************************************************/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

public class Canvas extends JComponent {
    // method: paintComponent
    // purpose: changes the way the title menu looks
    public void paintComponent(Graphics g) {
        if(g instanceof Graphics2D){
            Graphics2D g2 = (Graphics2D)g;
            Font font = new Font("Arial", Font.BOLD, 42);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.setFont(font);
            g2.drawString("CS 245 Quarter Project",60,80); 
            Font font2 = new Font("Arial", Font.BOLD, 32);
            g2.setFont(font2);
            g2.setColor(Color.WHITE);
            g2.drawString("<Team GUI>",190,300);
        }
    }  
}