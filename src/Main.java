/*************************************************************** 
* file: Main.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 2 
* date last modified: 2/14/2016
* 
* purpose: This class acts as driver for Hangman game.
* 
****************************************************************/ 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {
    // method: createAndShowGUI
    // purpose: this method creates the title menu and the game menu 
    public static void menu() {
        f = new JFrame("CS245 Quarter Project");
        JPanel panel = new JPanel();
        JPanel cpanel = new JPanel();
        System.out.println("Initialization Successful: "+ SwingUtilities.isEventDispatchThread());
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,400);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        f.setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 400);
        f.setVisible(true);
        f.getContentPane().setBackground(Color.BLACK);
        UIManager.put("Button.background", Color.black);
        UIManager.put("Button.foreground", Color.white);
        Font s = new Font("Serif", Font.BOLD, 14);
        UIManager.put("Button.font", s);
        Container cp = f.getContentPane();
        Canvas t = new Canvas();
        cp.add(t);
       
        // Main Menu Buttons
        
        // Play
        ActionListener timer = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                t.setVisible(false);
                panel.setLayout(null);
                JButton play = new JButton("Play");
                play.setBounds(235,245,135,30);
                play.setToolTipText("Start Game");  
                panel.add(play);
                ActionListener listener = new ActionListener(){
                    Container cp = f.getContentPane();
                    Hangman b = new Hangman();
                    Component contents = b.createComponents();
                    public void actionPerformed(ActionEvent arg) {     
            		cp.add(b);
            		f.getContentPane().add(contents);
                        b.setVisible(true);
                        panel.setVisible(false);
                    }
                };
                
                // High Scores Button
                play.addActionListener(listener);
                JButton highScores = new JButton("High Scores");
                highScores.setBounds(235,285,135,30);
                highScores.setToolTipText("View High Scores");  
                highScores.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        Container cp = f.getContentPane();
                        HighScores c = new HighScores();
                        panel.setVisible(false);
                        cpanel.setVisible(true);
                        cpanel.setLayout(null);
                        cp.add(c);
                        JButton back = new JButton("Back");
                        back.setBounds(30,325,110,30);
                        back.setToolTipText("Go Back to menu");  
                        c.add(back);
                        back.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                panel.setVisible(true);
                                c.setVisible(false);
                            }
                        });
                    }
                });
                
                // Credits Button
                panel.add(highScores);
                JButton credits = new JButton("Credits");
                credits.setBounds(235,325,135,30);
                credits.setToolTipText("Authors");  
                panel.add(credits);
                
                // Change string in next line to 'src//hangman.png'
                // for Windows Systems
                ImageIcon ii = new ImageIcon("src/hangman.png");
                JLabel label = new JLabel(ii);
                label.setBounds(150,30,300,200);
                panel.add(label);
                f.add(panel);
                panel.setBackground(Color.black);
            
                credits.addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent e) {
                         Container cp = f.getContentPane();
                         Credits c = new Credits();

                         panel.setVisible(false);
                         cpanel.setVisible(true);
                         cpanel.setLayout(null);
                         cp.add(c);
                         JButton back = new JButton("Back");
                         back.setBounds(30,325,135,30);
                         back.setToolTipText("Go Back to menu");
                         c.add(back);

                         back.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                panel.setVisible(true);
                                c.setVisible(false);
                            }
                        });
                    }
                });
            }
        };
        new Timer(3000, timer).start(); 
    }
    
    // method: dispose
    // purpose: This method terminates an active frame.
    //          used for moving from window to window
    public static void dispose(){
        f.dispose();
    }    
    
    //method: main
    //purpose: this main method runs the program
    static JFrame f = null;
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                menu(); 
            }
        });
    }
}//end of main 