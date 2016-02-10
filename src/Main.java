/*************************************************************** 
* file: Main.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 2 
* date last modified: 
* 
* purpose: This class implements the games
* 
****************************************************************/ 
import java.awt.*;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {
    //method: main
    //purpose: this main method runs the program
    static JFrame f = null;
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowGUI(); 
            }
        });
    }
    // method: createAndShowGUI
    // purpose: this method creates the title menu and the game menu 
    public static void createAndShowGUI() {
        f = new JFrame("Welcome to Hangman");
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
       
        
        ActionListener timer = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                t.setVisible(false);
                panel.setLayout(null);
                JButton play = new JButton("Play");
                play.setBounds(245,245,110,30);
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
                play.addActionListener(listener);
                JButton highScores = new JButton("High Scores");
                highScores.setBounds(245,285,110,30);
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
                panel.add(highScores);
                JButton credits = new JButton("Credits");
                credits.setBounds(245,325,110,30);
                credits.setToolTipText("View who made this Game");  
                panel.add(credits);
                ImageIcon ii = new ImageIcon("src/hangman.png");
                JLabel label = new JLabel(ii);
                label.setBounds(150,30,300,200);
                panel.add(label);
                f.add(panel);
                panel.setBackground(Color.BLACK);
            
                credits.addActionListener(new ActionListener() { 
                    public void actionPerformed(ActionEvent e) {
                         Container cp = f.getContentPane();
                         Credits c = new Credits();

                         panel.setVisible(false);
                         cpanel.setVisible(true);
                         cpanel.setLayout(null);
                         cp.add(c);
                         JButton back = new JButton("Back");
                         back.setBounds(30,325,110,30);
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
    
    public static void menu() {
        Main.dispose();
        f = new JFrame("Hangman - Main Menu");
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

        t.setVisible(false);
        panel.setLayout(null);

        JButton play = new JButton("Play");
        play.setBounds(245,245,110,30);
        panel.add(play);
        
        ActionListener listener = new ActionListener() {
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
        
        play.addActionListener(listener);
        JButton highScores = new JButton("High Scores");
        highScores.setBounds(245,285,110,30);

        highScores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Container cp = f.getContentPane();
                HighScores c = new HighScores();
                panel.setVisible(false);
                cpanel.setVisible(true);
                cpanel.setLayout(null);
                cp.add(c);
                JButton back = new JButton("Back");
                back.setBounds(30,325,110,30);
                c.add(back);

                back.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        panel.setVisible(true);
                        c.setVisible(false);                             
                    }
                });
            }
        });

        panel.add(highScores);
        JButton credits = new JButton("Credits");
        credits.setBounds(245,325,110,30);
        panel.add(credits);
        ImageIcon i = new ImageIcon("src/hangman.png");
        JLabel label = new JLabel(i);
        label.setBounds(150,30,300,200);
        panel.add(label);             f.add(panel);
        panel.setBackground(Color.BLACK);

        credits.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                Container cp = f.getContentPane();
                Credits c = new Credits();

                panel.setVisible(false);
                cpanel.setVisible(true);
                cpanel.setLayout(null);
                cp.add(c);
                JButton back = new JButton("Back");
                back.setBounds(30,325,110,30);
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
    
    public static void dispose(){
        f.dispose();
    }    
}//end of main 