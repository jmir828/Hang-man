/*************************************************************** 
* file: ScoreDisplay.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 2 
* date last modified: 2/14/2016
* 
* purpose: This class presents Player Score
* 
****************************************************************/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ScoreDisplay extends JPanel {
    // method: run
    // purpose: calls run and initializes frame for Score display
    public void run() throws IOException{
        playerScore();
    }
    
    private JPanel panel;
    private JPanel mainPanel;
    private JPanel clockPanel;
    private JPanel scorePanel;
    private JButton menuButton;
    private JButton viewScoreButton;
    private int score = Sudoku.score;
    private JButton quit;
    private JFrame frame = new JFrame("Player Score");
    private JLabel scores = new JLabel("Total Score");
    private JLabel totalScore = null;
    private String scoreString;
    private Clock c = new Clock();

    // method: playerScore
    // purpose: finish a game. Player will be sent to Score display with
    //          values provided by this method
    public void playerScore() throws IOException{
        Font s = new Font("Serif", Font.BOLD, 14);
        UIManager.put("Button.font", s);
        ImageIcon gameOverIcon = new ImageIcon("src/gameOver.png");
        JLabel gameOverLabel = new JLabel(gameOverIcon);
        gameOverLabel.setBounds(190,50,220,150);
        gameOverLabel.setVisible(true);
        panel = new JPanel();
        mainPanel = new JPanel(new BorderLayout(3,3));
        clockPanel = new JPanel();
        scorePanel = new JPanel();
        viewScoreButton = new JButton("End Game");
        viewScoreButton.setToolTipText("Return to Main Menu");
        menuButton = new JButton("Main Menu");
        menuButton.setToolTipText("Go Back to menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setVisible(true);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 400);
        frame.getContentPane().setBackground(Color.WHITE);
        scorePanel.setLayout(null);
        scorePanel.setBackground(Color.WHITE);
        scorePanel.add(scores);
        scores.setFont( new Font("Helvetica", Font.PLAIN, 28) );
        scores.setBounds(220,5,300,100);
        scores.setForeground(Color.blue);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBorder(new EmptyBorder(1,1,1,1));
        panel.setPreferredSize(new Dimension(600,380));
        clockPanel.setPreferredSize(new Dimension(600,20));
        panel.setLayout(null);
        clockPanel.setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);
        viewScoreButton.setBounds(10,300,130,30);
        
        panel.add(gameOverLabel);
        panel.add(viewScoreButton);
        viewScoreButton.setVisible(true);
        
        // Saves current score into the file
        ScoreFile sf = new ScoreFile();
        String ans = JOptionPane.showInputDialog("See if you made the Leader Board!", "Enter Name");
        sf.addScore(score, ans);
        
        viewScoreButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
//                panel.setVisible(false);
//                //scorePanel.setVisible(true);
//                mainPanel.add(scorePanel, BorderLayout.CENTER);
//                scoreString = String.valueOf(score);
////                totalScore = new JLabel (scoreString);
////                totalScore.setFont( new Font("Helvetica", Font.PLAIN, 28) );
////                totalScore.setBounds(220,50,300,100);
////                totalScore.setForeground(Color.blue);
////                scorePanel.add(totalScore);
//                scorePanel.add(menuButton);
//                menuButton.setBounds(10,300,130,30);
//                
//                menuButton.addActionListener(new ActionListener(){
//                    public void actionPerformed(ActionEvent e){
                        repaint();
                        revalidate();
                        frame.dispose();  
                        Main.menu();
//                    }
//                });
            }
        
        });   
    
        clockPanel.add(c.time);
        c.start();
       
        panel.setVisible(true);
        clockPanel.setVisible(true);
        mainPanel.add(panel,BorderLayout.CENTER);
        mainPanel.add(clockPanel, BorderLayout.NORTH);
        frame.add(mainPanel);
    }
}