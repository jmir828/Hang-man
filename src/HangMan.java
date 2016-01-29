/*************************************************************** 
* file: HangMan.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 1 
* date last modified: 1/23/2016
* 
* purpose: This class implements the hangman game
* 
****************************************************************/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HangMan extends JComponent {
    Draw draw = null;
    JButton skipButton    = null;
    JButton newGameButton = null;
    JLabel    wordArea    = null;
    JLabel    messageArea = null;
    java.util.List alphaButtonList = new ArrayList();
    Iterator alphaIterator = null;

    boolean reset         = true;
    boolean disable       = false;
    boolean dontWrap      = false;
    boolean wrap          = true;
    boolean headDrawn     = false;
    boolean bodyDrawn     = false;
    boolean leftArmDrawn  = false;
    boolean rightArmDrawn = false;
    boolean leftLegDrawn  = false;
    boolean rightLegDrawn = false;
    boolean gameActive    = false;
   // boolean isVisible = false;
    
    Component eastPane   = null;
    Component northPane  = null;
    Component southPane  = null;
    Component centerPane = null;
    JPanel alphaButtonPanel = null;
    JPanel mainPanel = null;
    JPanel scorePanel = null;

    // Target words
    String[] targetWords = {"abstract", "cemetery", "nurse", "pharmacy", "climbing"};
    String currentGuess;
    String targetWord;
    JLabel space ;
    
    int numberWrong       = 0;
    int numberOfBodyParts = 6;
    public int score = 100;
    int next              = 0;
    
    // method: setUpNewGame()
    // purpose: Sets up a new game 
    public void setUpNewGame() {
        numberWrong = 0;
        
        messageArea.setText("Current score: " + score);
        mainPanel.add(southPane, BorderLayout.SOUTH);
       
        //Enable alphabet buttons
        Iterator alphaIterator = alphaButtonList.iterator();
        while( alphaIterator.hasNext() ) {
            ( (JButton)alphaIterator.next() ).setEnabled( reset );
        }

        //Disable new game button
        newGameButton.setEnabled( disable );

        //Color the word area
        wordArea.setBackground(Color.black);

        //Present the new word
        next = new Random().nextInt(targetWords.length-1); 
        targetWord  = targetWords[next];

        //Fill the mystery word with '?'s
        currentGuess = "?";
        for( int i=0; i<targetWord.length()-1; i++) {
            currentGuess = currentGuess.concat("?");
        }
        wordArea.setText( currentGuess );

        //Nothing is drawn yet
        headDrawn    = false;
        bodyDrawn    = false;
        leftArmDrawn = false;
        rightArmDrawn= false;
        leftLegDrawn = false;
        rightLegDrawn= false;
        
        draw.setVisible(true);
        alphaButtonPanel.setVisible(true);
        alphaButtonPanel.repaint();
        gameActive = true;
        southPane.setVisible(true);
        southPane.repaint();
        eastPane.repaint();
        draw.repaint();
    }//setUpNewGame
        
    // method: processAnswer()
    // pupose: it determines if the input is correct or not 
    public void processAnswer(String answer) throws IOException {    
        char newCharacter = answer.charAt(0);
        // Look thru the target word.
        // If the character matches the target, concat the new character.
        // If the character doesn't match the target, concat the character
        //    from the current guess.
        String nextGuess    = "";
        boolean foundAMatch = false;
        for( int i=0; i<targetWord.length(); i++ ) {
            char characterToMatch = targetWord.charAt(i);
            if( characterToMatch == newCharacter ) {
                nextGuess = nextGuess.concat( String.valueOf(newCharacter) );
                foundAMatch = true;
            }
            else 
                nextGuess = nextGuess.concat(String.valueOf( currentGuess.charAt(i) ));
            
        }//for each character
        
        currentGuess = nextGuess;
        wordArea.setText( currentGuess );
        
        if( !foundAMatch ) {
            numberWrong++;
            
            if (score == 0) 
                score = 0;
            else 
                score = score-10;
            
            switch (numberWrong){
                case 1: { headDrawn     = true; break; }
                case 2: { bodyDrawn     = true; break; }
                case 3: { leftArmDrawn  = true; break; }
                case 4: { rightArmDrawn = true; break; }
                case 5: { leftLegDrawn  = true; break; }
                case 6: { rightLegDrawn = true; break; }
                default: System.out.println("You should be dead by now!");
            }
            // Repaint the gallows area JPanel
            messageArea.setText("Current score: " + score);
            
            alphaButtonPanel.setVisible(true);
            alphaButtonPanel.repaint();
            gameActive = true;
            eastPane.repaint();
            draw.repaint();
        }

        // We have a winner
        if( currentGuess.equals( targetWord ) ) {
            //Disable the buttons
            Iterator alphaIterator = alphaButtonList.iterator();
            while( alphaIterator.hasNext() ) {
                ( (JButton)alphaIterator.next() ).setEnabled( disable );
            }
            
            messageArea.setText("Good Job! Total: " + score);
            skipButton.setEnabled( reset );
            newGameButton.setEnabled( reset );
            alphaButtonPanel.setVisible(false);
            
            gameActive = false;
            wordArea.setText("High Scores");
            // insert fixed coder here
            newGameButton.setVisible(true);
            skipButton.setVisible(true);
            score = 100;
            displayPlayerScore();
        }
        // Wrong Answer
        //   Set out a new body part to be drawn by repaint()
//        else {
            
            // Is the game over?
        if( numberWrong >= numberOfBodyParts ) {
            //Disable the buttons
            Iterator alphaIterator = alphaButtonList.iterator();
            while( alphaIterator.hasNext() ) {
                ( (JButton)alphaIterator.next() ).setEnabled( disable );
            }
            
            messageArea.setText( "You Lost! Total: " + score);
            
            newGameButton.setEnabled( reset );
            skipButton.setEnabled( reset );
            draw.setVisible(false);
            alphaButtonPanel.setVisible(false);
            alphaButtonPanel.repaint();
            gameActive = false;
            wordArea.setText("High Scores");
            // This needs to be completed...
            numberWrong = 0;
            newGameButton.setVisible(true);
            skipButton.setVisible(true);
            score = 100;
            displayPlayerScore();
        }
        else {
            alphaButtonPanel.setVisible(true);
            alphaButtonPanel.repaint();
            gameActive = true;
        }
    }//processAnswer

    //method: createNorthPane() 
    //Purpose: Create the North pane of the the game
    //where the word prompts will be displayed.

    public Component createNorthPane() {
        JPanel pane = new JPanel();
        pane.setAlignmentX(pane.CENTER_ALIGNMENT);
        pane.setLayout( new BoxLayout( pane, BoxLayout.X_AXIS ) );
        pane.setBorder( BorderFactory.createEmptyBorder(0, 10, 10, 10) );
        pane.setBackground(Color.black);
        pane.add(Box.createHorizontalGlue() );
        wordArea = new JLabel("Press New Game");
        wordArea.setFont( new Font("Helvetica", Font.PLAIN, 24) );
        wordArea.setBackground(Color.white);
        wordArea.setForeground(Color.white);
        pane.add(wordArea, BorderLayout.WEST);
        
        pane.add(Box.createHorizontalStrut(150));
        
        Clock clock = new Clock();
        pane.add( clock.time, BorderLayout.EAST );
        pane.setVisible( true );
        clock.start();
        
        pane.add(Box.createHorizontalGlue() );
        return pane;
    }
    
    //method: createSouthPane() 
    //Purpose: Create the South pane of the the game
    //where the status prompts will be displayed.
    public Component createSouthPane() {
        JPanel pane = new JPanel();
        pane.setLayout( new BoxLayout( pane, BoxLayout.X_AXIS ) );
        pane.setBorder( BorderFactory.createEmptyBorder(10, 10, 10, 10) );
        pane.add(Box.createHorizontalGlue() );
    	 messageArea = new JLabel("Hangman");
         messageArea.setFont( new Font("Helvetica", Font.PLAIN, 28) );
         messageArea.setBackground( Color.black );
         messageArea.setForeground( Color.red );
         messageArea.setVisible(true);
         pane.add(messageArea);
         pane.add(Box.createHorizontalGlue() );
         return pane;
    }
    //method: createCenterPane() 
    //Purpose: Create the center pane of the the game
    //where the picture prompts will be displayed.
    public Component createCenterPane() {
        // Pass the reference to this instance of the game so that
        //   the repaint() method can find out what to draw
        draw = new Draw(this);
        return draw;
    }

    //method: createEastPane() 
    //Purpose: Create the East pane of the the game
    //where the buttons prompts will be displayed.
    public Component createEastPane() {
        ActionListener controlButtonListener = new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                JButton buttonPushed = (JButton)e.getSource();
                if( buttonPushed.getText().equals("New Game") ) {
                    skipButton.setVisible(false);
                    newGameButton.setVisible(false);
                    setUpNewGame();
                    
                    
                }
                else if (buttonPushed.getText().equals("End")) {  
                    
                    Main.frame.dispose();
                    Main.createAndShowGUI();
                    // insert code for what to do when player does not want to 
                    // return to main menu
                    
                }
                else alphaButtonPanel.setVisible(true);
                alphaButtonPanel.repaint();
            }//actionPerformed
        };//controlButtonListener
     

        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createCompoundBorder());
        pane.setLayout( new BoxLayout( pane, BoxLayout.Y_AXIS ) );
        
        JPanel northInnerPane = new JPanel();
        northInnerPane.setBorder(new EmptyBorder(5,5,5,5));
        northInnerPane.setLayout( new BoxLayout(northInnerPane,BoxLayout.X_AXIS));  
        
        newGameButton = new JButton( "New Game" );
        newGameButton.setFont( new Font("Helvetica", Font.PLAIN, 18) );
        newGameButton.setBounds(1,1,1,1);
        newGameButton.setToolTipText("Press to Start Game");
        newGameButton.addActionListener( controlButtonListener );
        northInnerPane.add( newGameButton );
        
        northInnerPane.add(Box.createHorizontalStrut(5));
        
        skipButton = new JButton( "End" );
        skipButton.setFont( new Font("Helvetica", Font.PLAIN, 18) );
        skipButton.setBounds(1,1,1,1);
        skipButton.setToolTipText("Go to Main Menu");
        skipButton.addActionListener( controlButtonListener );
        northInnerPane.add( skipButton);
        
        pane.add(northInnerPane, BorderLayout.NORTH);
        
        // Alphabet Buttons
        alphaButtonPanel = new JPanel();
        alphaButtonPanel.setMaximumSize(new Dimension(250, 50));
        
        alphaButtonPanel.setBorder(BorderFactory.createCompoundBorder());
        
         ActionListener alphabetButtonAction = new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                JButton buttonPushed = (JButton)e.getSource();
                buttonPushed.setEnabled( disable );
                try {
                    processAnswer( buttonPushed.getText() );
                    if (gameActive == false)
                        alphaButtonPanel.setVisible(false);
                     
                } catch (IOException ex) {
                    Logger.getLogger(HangMan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c  = new GridBagConstraints();
        alphaButtonPanel.setLayout( gridbag );
        c.fill = GridBagConstraints.BOTH;

        JButton button = new JButton( "a" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 0;
        c.gridheight = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "b" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 0;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "c" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 0;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "d" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 3;
        c.gridy      = 0;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "e" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "f" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "g" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "h" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 3;
        c.gridy      = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "i" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 2;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "j" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 2;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "k" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 2;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "l" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 3;
        c.gridy      = 2;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "m" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 3;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "n" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 3;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "o" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 3;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "p" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 3;
        c.gridy      = 3;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "q" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 4;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "r" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 4;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "s" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 4;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "t" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 3;
        c.gridy      = 4;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "u" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 5;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "v" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 5;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "w" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 5;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "x" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 3;
        c.gridy      = 5;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "y" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 6;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        button = new JButton( "z" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 6;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        alphaButtonPanel.add( button );
        alphaButtonList.add( button );

        alphaIterator = alphaButtonList.iterator();
        while( alphaIterator.hasNext() ) {
            ( (JButton)alphaIterator.next() ).setEnabled( disable );
        }
        alphaButtonPanel.setVisible(false);
        pane.add(alphaButtonPanel, BorderLayout.CENTER);
        
        return pane;
    }
    
    public void displayPlayerScore() {
        // Construct Player's Score window, disable previous components first
        northPane.setVisible(true);
        southPane.setVisible(true);
        eastPane.setVisible(true);
        centerPane.setVisible(false);
        scorePanel = new JPanel();
        
        JPanel centerPanel = new JPanel();
        
        // Panel Layout
        centerPanel.setLayout( new BoxLayout( centerPanel, BoxLayout.Y_AXIS ) );
        centerPanel.add(Box.createHorizontalGlue() );
        centerPanel.setAlignmentX(centerPanel.CENTER_ALIGNMENT);
        centerPanel.setBorder( BorderFactory.createEmptyBorder(20, 70, 0, 0) );
                
        centerPanel.add( new JLabel("   <Insert High Score>"));
        centerPanel.add( new JLabel("   <Insert High Score>"));
        centerPanel.add( new JLabel("   <Insert High Score>"));
        centerPanel.add( new JLabel("   <Insert High Score>"));
        centerPanel.add( new JLabel("   <Insert High Score>"));
        
        scorePanel.setBorder(BorderFactory.createLoweredBevelBorder());
        scorePanel.setLayout(new BorderLayout() );
        // original southPane will be reused for Player's Score page, moved up
        scorePanel.add(southPane, BorderLayout.SOUTH);
        southPane.setVisible(true);
        scorePanel.add(centerPanel, BorderLayout.CENTER);
        scorePanel.repaint();
        mainPanel.add(scorePanel);

        mainPanel.repaint();
    }

    public void reconstructComponents() {
        this.createComponents();
    }
    //method: createComponenets() 
    //Purpose: Create the GUI for the game.
    public Component createComponents() {
        mainPanel = new JPanel();
        eastPane = createEastPane();
        northPane = createNorthPane();
        southPane = createSouthPane();
        centerPane = createCenterPane();
        
        mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        mainPanel.setLayout(new BorderLayout() );
        mainPanel.add( northPane,  BorderLayout.NORTH );
        mainPanel.add( southPane,  BorderLayout.SOUTH );
        mainPanel.add( centerPane, BorderLayout.CENTER );
        mainPanel.add( eastPane,   BorderLayout.EAST );
        
        centerPane.setVisible(false);
        northPane.setVisible(true);
        // true because this is where skip, new game, and clock are located
        // these buttons are needed to guide player into new game.
        // if visiblility set to false, game will get stuck
        eastPane.setVisible(true);
        southPane.setVisible(true);

        return mainPanel;
    }
}