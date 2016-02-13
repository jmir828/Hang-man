/*************************************************************** 
* file: Hangman.java 
* authors: Josue Miramontes, Sang Choi
* class: CS 245 – Programming Graphical User Interface 
* 
* assignment: Quarter Project program 2 
* date last modified: 
* 
* purpose: This class implements the hangman game
* 
****************************************************************/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Hangman extends JComponent {
    Draw draw = null;
    JButton skipButton    = null;
    JButton newGameButton = null;
    JLabel    wordArea    = null;
    JLabel    messageArea = null;
    java.util.List alphaButtonList = new ArrayList();
    Iterator alphaIterator = null;
    
    boolean dontWrap     = false;
    boolean wrap         = true;
    boolean headDrawn    = false;
    boolean bodyDrawn    = false;
    boolean leftArmDrawn = false;
    boolean rightArmDrawn= false;
    boolean leftLegDrawn = false;
    boolean rightLegDrawn= false;

    // Target words
    String[] targetWords = {"abstract", "cemetery", "nurse", "pharmacy", "climbing"};
    String currentGuess;
    String targetWord;
    JLabel space ;
    
    int numberWrong       = 0;
    int numberOfBodyParts = 6;
    public static int score = 0;
    int next              = 0;
    
    
    // method: setUpNewGame()
    // purpose: Sets up a new game 
        public void setUpNewGame() {
            numberWrong = 0;
            score =100;
            messageArea.setText("Current Score: " + score);

            //Enable alphabet buttons
            Iterator alphaIterator = alphaButtonList.iterator();
            while( alphaIterator.hasNext() ) {
                ( (JButton)alphaIterator.next() ).setEnabled( true );
            }

            //Disable new game button
            newGameButton.setEnabled( false );

            //Color the word area
            wordArea.setBackground(Color.black);

            //Present the new word
            double numb = Math.random();
            next = (int)( numb * targetWords.length );
            targetWord  = targetWords[next];

            //Fill the word-to-guess with ???
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
            else {
                nextGuess = nextGuess.concat(String.valueOf( currentGuess.charAt(i) ));
            }
        }//for each character
        currentGuess = nextGuess;
        wordArea.setText( currentGuess );
        
        if( !foundAMatch ) {
                numberWrong++;
                score -= 10;
                switch (numberWrong){
                    case 1: { headDrawn     = true; break; }
                    case 2: { bodyDrawn     = true; break; }
                    case 3: { leftArmDrawn  = true; break; }
                    case 4: { rightArmDrawn = true; break; }
                    case 5: { leftLegDrawn  = true; break; }
                    case 6: { rightLegDrawn = true; break; }
                    default: System.out.println("You should be dead!");
                }
                // Repaint the gallows area JPanel
                draw.repaint();
        }

        messageArea.setText("Current Score: " + score);
        // We have a winner
        if( currentGuess.equals( targetWord ) ) {
        	
            //Disable the buttons
            Iterator alphaIterator = alphaButtonList.iterator();
            while( alphaIterator.hasNext() ) {
                ( (JButton)alphaIterator.next() ).setEnabled( false );
            }
            messageArea.setText( "Game over! Total score is  " + score);
            skipButton.setEnabled( true );
            Colors c = new Colors();
            c.run();
            Main.dispose();
        }
        // Wrong Answer
        //   Set out a new body part to be drawn by repaint()
            
            // Is the game over?
            if( numberWrong >= numberOfBodyParts )  {
                //Disable the buttons
                Iterator alphaIterator = alphaButtonList.iterator();
                while( alphaIterator.hasNext() ) {
                    ( (JButton)alphaIterator.next() ).setEnabled( false );
                }
                messageArea.setText( "Total score is " + score);
                Colors c = new Colors();
                c.run();
                Main.dispose();
                skipButton.setEnabled( true );
            }
//        }//if else
    }//processAnswer


 //method: createNorthPane() 
 //Purpose: Create the North pane of the the game
 //where the word prompts will be displayed.

    public Component createNorthPane() {
        JPanel pane = new JPanel();
        pane.setLayout( new BoxLayout( pane, BoxLayout.X_AXIS ) );
        pane.setBorder( BorderFactory.createEmptyBorder(0, 10, 10, 10) );
        pane.add(Box.createHorizontalGlue() );
        wordArea = new JLabel("Press New Game");
        wordArea.setFont( new Font("Helvetica", Font.PLAIN, 24) );
        wordArea.setBackground(Color.lightGray);
        wordArea.setForeground(Color.RED);
        pane.add(wordArea);
        pane.add(Box.createHorizontalGlue() );
        return pane;
    }

    //method: createWestPane() 
    //Purpose: Create the West pane of the the game
    //where the keyboard will be displayed.
    public Component createWestPane() {
        ActionListener alphabetButtonAction = new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                JButton buttonPushed = (JButton)e.getSource();
                buttonPushed.setEnabled( false );
                try {
                    processAnswer( buttonPushed.getText() );
                } catch (IOException ex) {
                    Logger.getLogger(Hangman.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createLoweredBevelBorder());
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c  = new GridBagConstraints();
        pane.setLayout( gridbag );
        c.fill = GridBagConstraints.CENTER;

        JButton button = new JButton( "a" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 0;
       // c.gridheight = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "b" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 0;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "c" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 0;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "d" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 3;
        c.gridy      = 0;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "e" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "f" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );


        button = new JButton( "g" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "h" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 3;
        c.gridy      = 1;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "i" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 2;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "j" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 2;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "k" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 2;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "l" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 3;
        c.gridy      = 2;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "m" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 3;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "n" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 3;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "o" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 3;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "p" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 3;
        c.gridy      = 3;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "q" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 4;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "r" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 4;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "s" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 4;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "t" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 3;
        c.gridy      = 4;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "u" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 0;
        c.gridy      = 5;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "v" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 5;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "w" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 5;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "x" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 3;
        c.gridy      = 5;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "y" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 1;
        c.gridy      = 6;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        button = new JButton( "z" );
        c.weightx    = 0.5;
        c.weighty    = 0.5;
        c.gridx      = 2;
        c.gridy      = 6;
        gridbag.setConstraints( button, c );
        button.addActionListener( alphabetButtonAction );
        pane.add( button );
        alphaButtonList.add( button );

        alphaIterator = alphaButtonList.iterator();
        while( alphaIterator.hasNext() ) {
            ( (JButton)alphaIterator.next() ).setEnabled( false );
        }
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
         messageArea.setBackground( Color.lightGray );
         messageArea.setForeground( Color.red );
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
    public Component createEastPane() 
    {
        ActionListener controlButtonListener = new ActionListener() {
            public void actionPerformed( ActionEvent e )
            {
                JButton buttonPushed = (JButton)e.getSource();
                if( buttonPushed.getText().equals("New Game") ) {
                    setUpNewGame();
                }
                else // User triggers 'Skip' 
                {   
                    Colors c = new Colors();
                        c.run();
 
                        Main.dispose();
                }
            }//actionPerformed
        };//controlButtonListener
     

        JPanel pane = new JPanel();
        JPanel clkPane = new JPanel();
        pane.setBorder(BorderFactory.createLoweredBevelBorder());
        pane.setLayout( new BoxLayout( pane, BoxLayout.Y_AXIS ) );
        pane.setAlignmentX(CENTER_ALIGNMENT);
        Clock clock = new Clock();
        clkPane.add(clock.time);
        pane.add(clkPane);
        
        pane.setVisible( true );
        clock.start();
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        
        newGameButton = new JButton( "New Game" );
        newGameButton.setFont( new Font("Helvetica", Font.PLAIN, 18) );
        newGameButton.setBounds(75,100,300,200);
        newGameButton.setToolTipText("Press to Start Game");
        newGameButton.addActionListener( controlButtonListener );
        buttons.add( newGameButton );
        skipButton = new JButton( "Skip" );
        skipButton.setFont( new Font("Helvetica", Font.PLAIN, 18) );
        skipButton.setBounds(75,160,300,200);
        skipButton.setToolTipText("Skip and go to next Game");
        skipButton.addActionListener( controlButtonListener );
        buttons.add( skipButton);
        pane.add(buttons);
        return pane;
    }

    //method: createComponenets() 
    //Purpose: Create the GUI for the game.
    public Component createComponents() {
        JPanel pane = new JPanel();
        Component alphabet = createWestPane();
        alphabet.setBounds(new Rectangle(100,75));
        JPanel eastPane = new JPanel();
        eastPane.setLayout( new BoxLayout( eastPane, BoxLayout.Y_AXIS ) );
        eastPane.add(createEastPane());
        eastPane.add(alphabet);
        
        pane.setBorder(BorderFactory.createLoweredBevelBorder());
        pane.setLayout(new BorderLayout() );
        pane.add( createNorthPane(),  BorderLayout.NORTH );
	// pane.add( createWestPane(),   BorderLayout.WEST );
        pane.add( createSouthPane(),  BorderLayout.SOUTH );
        pane.add( createCenterPane(), BorderLayout.CENTER );
        pane.add( eastPane,   BorderLayout.EAST );

        return pane;
    }

}