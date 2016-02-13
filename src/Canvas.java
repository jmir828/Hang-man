/*************************************************************** 
* file: Canvas.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 2 
* date last modified: 
* 
* purpose: This class changes the way the Canvas frame would look
* like
* 
****************************************************************/
import java.awt.*;
import javax.swing.JComponent;

public class Canvas extends JComponent {
    // method: paintComponent
    // purpose: changes the way the title menu looks like
    public void paintComponent(Graphics g) {
        if(g instanceof Graphics2D){
            Graphics2D g2 = (Graphics2D)g;
            Font font = new Font("Arial", Font.BOLD, 42);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.setFont(font);
            g2.drawString("CS245 Quarter Project",80,150); 
            Font font2 = new Font("Arial", Font.BOLD, 40);
            g2.setFont(font2);
            g2.setColor(Color.RED);
            g2.drawString("Team GUI",205,220);
        }
    }
}