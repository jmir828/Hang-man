/*************************************************************** 
* file: Sudoku.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 1 
* date last modified: 3/5/2016
* 
* purpose: This class implements Sudoku game
* 
****************************************************************/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Sudoku extends JPanel{
    public void run() throws IOException{
        r.setSize(30, 30);
        newGame();
    }
    
    JPanel panel;
    JPanel mainPanel;
    JPanel clockPanel;
    JPanel scorePanel;
    JButton checkSolution;
    JButton end;
    public static int score = 0;
    JButton quit;
    JFrame frame = new JFrame("Sudoku!"); 
    int numberOfRounds;
    String ans;
    JLabel scores = new JLabel("Total Score");
    JLabel totalScore = null;
    String scoreString;
    Clock c = new Clock();
    Rectangle r = new Rectangle();
    int count = 0;
    JFormattedTextField[] textFields = new JFormattedTextField[81]; //array of textfields
    int[] solution = {8, 3, 5, 4, 1, 6, 9, 2, 7, 2, 9, 6, 8, 5, 7, 4, 3, 1, 4, 
                     1, 7, 2, 9, 3, 6, 5, 8, 5, 6, 9, 1, 3, 4, 7, 8, 2, 1, 2, 
                     3, 6, 7, 8, 5, 4, 9, 7, 4, 8, 5, 2, 9, 1, 6, 3, 6, 5, 2, 
                     7, 8, 1, 3, 9, 4, 9, 8, 1, 3, 4, 5, 2, 7, 6, 3, 7, 4, 9, 
                     6, 2, 8, 1, 5}; //answers of the board

    public void newGame() throws IOException{
        score = Colors.score;
        Font s = new Font("Serif", Font.BOLD, 14);
        UIManager.put("Button.font", s);
        numberOfRounds = 0;
        panel = new JPanel();
        mainPanel = new JPanel(new BorderLayout(3,3));
        clockPanel = new JPanel();
        scorePanel = new JPanel();
        JButton submitButton= new JButton("Submit");
        checkSolution = submitButton;
        quit = new JButton("Quit");
        end = new JButton("End");
        checkSolution.setToolTipText("Click To Submit Solution");
        quit.setToolTipText("Continue To Next Screen..");
        end.setToolTipText("End Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setVisible(true);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 400);
        frame.getContentPane().setBackground(Color.WHITE);
        scorePanel.setLayout(null);
        scorePanel.setBackground(Color.lightGray);
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
        panel.setBackground(Color.lightGray);
        panel.add(quit);
        panel.add(checkSolution);
        
        checkSolution.setVisible(true);
        panel.repaint();
        quit.setBounds(10,300,130,30);
        
        quit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            panel.setVisible(false);
                   scorePanel.setVisible(true);
                   mainPanel.add(scorePanel, BorderLayout.CENTER);
                   scoreString = String.valueOf(score);
                   totalScore = new JLabel (scoreString);
                   totalScore.setFont( new Font("Helvetica", Font.PLAIN, 28) );
                   totalScore.setBounds(220,50,300,100);
                   totalScore.setForeground(Color.blue);
                   scorePanel.add(totalScore);
                   end.setBounds(10,300,130,30);
                   scorePanel.add(end);
                   end.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent e){
                           repaint();
                           revalidate();
                           frame.dispose();  
                           displayScore();
                       }
                   });
        }
        
    });
    checkSolution.setBounds(10,270,130,30);
    checkSolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfRounds++;
                System.out.println("SCORE_SUDOKU: " + score);
                if(checkBoard() || numberOfRounds <= 5){
                   panel.setVisible(false);
                   scorePanel.setVisible(true);
                   mainPanel.add(scorePanel, BorderLayout.CENTER);
                   scoreString = String.valueOf(score);
                   totalScore = new JLabel (scoreString);
                   totalScore.setFont( new Font("Helvetica", Font.PLAIN, 28) );
                   totalScore.setBounds(220,50,300,100);
                   totalScore.setForeground(Color.blue);
                   scorePanel.add(totalScore);
                   end.setBounds(10,300,130,30);
                   scorePanel.add(end);
                   end.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent e){
                           repaint();
                           revalidate();
                           frame.dispose();  
                           displayScore();
                       }
                   });
                   
                    repaint();
                    revalidate();
                }else{
                    System.out.println("Error");
                }
            }
        });
    
    
    clockPanel.add(c.time);
    c.start();
    textArrayInitializor();
    boardPainter();
    
    panel.setVisible(true);
    clockPanel.setVisible(true);
    mainPanel.add(panel,BorderLayout.CENTER);
    mainPanel.add(clockPanel, BorderLayout.NORTH);
    frame.add(mainPanel);
    
         //Key Bindings
        
        JFrame frame = new JFrame("Info");
        frame.setPreferredSize(new Dimension(300,200));
        frame.pack();
        JLabel label = new JLabel("InfoLabel");
        label.setText("<html>Sang Choi 009327396<br>Josue Miramontes 008239023<br>CS245 Quarter Project<br>Winter 2016</html>");
        frame.add(label);
        
        Action showInfo = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
            }
        };
        
        Action close = new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), "showInfo");
        panel.getActionMap().put("showInfo", showInfo);
        
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "close");
        panel.getActionMap().put("close", close);
        
    }
    public void textArrayInitializor(){ 
        NumberFormat f = NumberFormat.getNumberInstance();
         f.setMaximumIntegerDigits(1);
        for(int i = 0; i < 81; i++){
            JFormattedTextField textTemp = new JFormattedTextField(f); 
            textTemp.setBounds(r);
            textFields[i] = textTemp;
        }
    }

    public void boardPainter(){
        int x = 170; int y = 60;
        int counter = 0;
        int[] presetInts = {8, 4, 6, 7, 4, 1, 6, 5, 5, 9, 3, 7, 8, 
                            7, 4, 8, 2, 1, 3, 5, 2, 9, 1, 3, 9, 2, 5};
        int[] presetLoc = {0, 3, 5, 8, 15, 19, 24, 25, 27, 29, 31, 33, 34, 40, 
                          46, 47, 49, 51, 53, 55, 56, 61, 65, 72, 75, 77, 80};
        int presetCounter = 0;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(counter == presetLoc[presetCounter]){
                    textFields[counter].setLocation(x, y);
                    textFields[counter].setText(Integer.toString(presetInts[presetCounter]));
                    textFields[counter].setEditable(false);
                    panel.add(textFields[counter]);
                    textFields[counter].setVisible(true);
                    x+=30;
                    presetCounter++;
                    counter++;
                }else{
                    textFields[counter].setLocation(x, y);
                    panel.add(textFields[counter]);
                    textFields[counter].setVisible(true);
                    x+=30;
                    counter++;
                }
            }
            y+=30;
            x = 170;
        }

    }
    
    public boolean checkBoard(){
        int incorrect = 0;
        int totalPossible = 540;
        boolean result = true;
        
        for(int i = 0; i < textFields.length; i++){
            if(textFields[i].getText().equals("")){
                
                if (incorrect < 540)
                    incorrect +=10;
                result = false;
            } 
            else if (solution[i] == Integer.parseInt(textFields[i].getText())) {
                continue;
            }
            else{
                if (incorrect < 540)
                    incorrect+= 10;
                result = false;
            }
        }
        
        if(result){
            score+= 540;
            return result;
        }else{
            totalPossible-=incorrect;
            score+=totalPossible;
            if(score < 0){ 
                score = 0;
            }
            return result; }
    }
    
    // method: displayScore
    // purpose: this method displays player score when 'Colors' game is over
    public void displayScore(){
        ScoreDisplay s = new ScoreDisplay();
            try {
                s.run();
            } catch (IOException ex) {
                Logger.getLogger(Colors.class.getName()).log(Level.SEVERE, null, ex);
            }
           frame.dispose();
    }
}