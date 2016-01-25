/*************************************************************** 
* file: Main.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 1 
* date last modified: 1/23/2016
* purpose: This class implements the games
* 
****************************************************************/ 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Main {
    //method: main
    //purpose: this main method runs the program
    static JFrame frame = null;
    
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
        frame = new JFrame("HangMan");
        final JPanel panel = new JPanel();
        final JPanel cpanel = new JPanel();
        System.out.println("CS 245: " + SwingUtilities.isEventDispatchThread());
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 400);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);
        
        UIManager.put("Button.background", Color.GRAY);
        UIManager.put("Button.foreground", Color.white);
        Font s = new Font("Serif", Font.BOLD, 14);
        UIManager.put("Button.font", s);
        final Container container = frame.getContentPane();
        final Canvas canvas = new Canvas();
        container.add(canvas);
        
        panel.getInputMap().put(KeyStroke.getKeyStroke("F1"),"doSomething");
        panel.getActionMap().put("doSomething",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame,
                    "Josue Miramontes - Bronco ID: 008239023\nSang Choi - Bronco ID:00???????\n" +
                    "Point and Clicker\nWinter 2016 ",
                    "Info",JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        panel.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),"Escape");
        panel.getActionMap().put("Escape",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        ActionListener timer = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // "New Game" button in Main Menu
                canvas.setVisible(false);
                panel.setLayout(null);
                JButton play = new JButton("New Game");
                play.setBounds(230,200,140,30);
                play.setToolTipText("Start New Game");  
                panel.add(play);
                //  Action Listener for "New Game" button
                ActionListener listener = new ActionListener(){
                    final Container cp = frame.getContentPane();
                    final HangMan game = new HangMan();
                    final Component contents = game.createComponents();
                    // Event Listener for "New Game" button
                    public void actionPerformed(ActionEvent arg0)  {
            		cp.add(game);
            		frame.getContentPane().add(contents);
                        game.setVisible(true);
                        panel.setVisible(false);
                    }
                };
                // Apply listener to "New Game" button
                play.addActionListener(listener);
                
                // "High Scores" button in Main Menu
                JButton highScores = new JButton("High Scores");
                highScores.setBounds(230,240,140,30);
                highScores.setToolTipText("View High Scores");
                // Action Listener for "High Scores" button
                highScores.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        final Container cp = frame.getContentPane();
                        final HighScores c = new HighScores();
                        panel.setVisible(false);
                        cpanel.setVisible(true);
                        cpanel.setLayout(null);
                        cp.add(c);
                        // "Back" button inside "High Scores" window
                        JButton back = new JButton("Back");
                        back.setBounds(30,325,110,30);
                        back.setToolTipText("Go to Main Menu");  
                        c.add(back);
                        // Action Listener for "Back" button in "High Scores"
                        back.addActionListener(new ActionListener(){ 
                            public void actionPerformed(ActionEvent e) {
                    		panel.setVisible(true);
                                c.setVisible(false);
                            }
                        });
                    }
                });
 
                panel.add(highScores);
                // "Credits" button in Main Menu
                JButton credits = new JButton("Credits");
                credits.setBounds(230,280,140,30);
                credits.setToolTipText("View game creators");  
                panel.add(credits);
                
                // Image contained in Main Menu
                // Does not appear in Main Menu....needs fixing
                ImageIcon ii = new ImageIcon("src\\hangman6.gif");
                JLabel label = new JLabel(ii);
                label.setVisible(true);
                label.setBounds(230,100,300,200);
                
                panel.add(label);
                frame.add(panel);
                panel.setBackground(Color.BLACK);
                // Action Listener for "Credits" button
                credits.addActionListener(new ActionListener() { 
                // Event Handler for "Credits" button
                    public void actionPerformed(ActionEvent e) {
                        final Container cp = frame.getContentPane();
                        final Credits c = new Credits();
                     
                        panel.setVisible(false);
                        cpanel.setVisible(true);
                        cpanel.setLayout(null);
                        cp.add(c);
                        // "Back" button within "Credits" window
                        JButton back = new JButton("Back");
                        back.setBounds(30,325,110,30);
                        back.setToolTipText("Go to Main Menu");
                        c.add(back);
                        // Action Listener for "Back" button in "Credits"
                        back.addActionListener(new ActionListener() {
                            // Event Handler for "Back" button
                            public void actionPerformed(ActionEvent e) {
                                panel.setVisible(true);
                                c.setVisible(false);
                            }
                        });
                    }
                });
            }
        };
        // This timer is created at beginning of run
        // Timer allows 'Splash' to view for 3 seconds before entering Main Menu
        new Timer(3000, timer).start();     
    }
    
    // This is called after complete run through. if possible, replace it and 
    // force program to restart or copy code from initial hangman window (main menu)
    public static void menu() {
        frame = new JFrame("CS 245!!!!");
        final JPanel panel = new JPanel();
        final JPanel cpanel = new JPanel();
        System.out.println("CS 245!!!lkjs"+ SwingUtilities.isEventDispatchThread());
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);
        UIManager.put("Button.background", Color.black);
        UIManager.put("Button.foreground", Color.white);
        Font s = new Font("Serif", Font.BOLD, 14);
        UIManager.put("Button.font", s);
        final Container cp = frame.getContentPane();
        final Canvas t = new Canvas();
        cp.add(t);

        t.setVisible(false);
        panel.setLayout(null);
             
        JButton play = new JButton("Play");
        play.setBounds(470,200,110,30);
        panel.add(play);
        ActionListener listener = new ActionListener() {
            
            final Container cp = frame.getContentPane();
     	    final HangMan b = new HangMan();
     	    final Component contents = b.createComponents();
            	 
            public void actionPerformed(ActionEvent arg0)  {
                cp.add(b);
                frame.getContentPane().add(contents);
            	b.setVisible(true);
            	panel.setVisible(false);
            }
        };

        play.addActionListener(listener);
        JButton highScores = new JButton("High Scores");
        highScores.setBounds(470,240,110,30);
        highScores.addActionListener(new ActionListener(){
          
            public void actionPerformed(ActionEvent e) {
                
                final Container cp = frame.getContentPane();
                final HighScores c = new HighScores();
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
        credits.setBounds(470,280,110,30);
        panel.add(credits);
        ImageIcon ii = new ImageIcon("src\\hangman6.gif");
        JLabel label = new JLabel(ii);
        label.setBounds(75,100,300,200);
        panel.add(label);
        frame.add(panel);
        panel.setBackground(Color.BLACK);
        
        credits.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                final Container cp = frame.getContentPane();
                final Credits c = new Credits();
                     
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
    public static void dispose() { frame.dispose(); }
}//end of main 