/*Cecilia Liu
CSC112 Fall 2022
Programming Assignment 6 – BlackJackFrame
December 2, 2022,

This program implement JFrame and other built-in methods to construct GUI projects.
Among which JLabel, JTextField, JButton, JTextArea, and multiple methods are used to achieve the desired effect.
Dealer methods implements while loop and recursion
All these work together to do a pseudo blackJack game
player needs to control their score below 21, and then as close as possible
TODO to see the full text on screen, remember to enlarge the window~
TODO do the same thing when restart the game~

~~~procedures to follow~~~
Run the program, fill in player name
Click start button to shuffle the cards
Click hit me as the player as many times as desire, but automatically lose if score gets greater than 21
Click stand when decide to let dealer play
Click play again to restart
Click exit to exit the game
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.Random;

public class BlackjackFrame extends JFrame implements ActionListener {
    //array of numbers to calculate the score
    Integer[] cardsNumbers = {
            2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 10, 10, 10,
            2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 10, 10, 10,
            2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 10, 10, 10,
            2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 10, 10, 10
    };

    //array of string cards to show what's the pseudo card dealt
    String[] cardsLetters = {
            "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "TC", "AC", "JC", "QC", "KC",//C
            "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "TD", "AD", "JD", "QD", "KD",//D
            "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "TH", "AH", "JH", "QH", "KH",//H
            "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "TS", "AS", "JS", "QS", "KS" //S
    };

    Integer[] winnerCount = {0, 0};

    //arraylist to get the index of cards
    ArrayList<Integer> arraylist = new ArrayList<Integer>(0);

    //The following block of code are just declare public variables
    //so that they can be accessed in the methods other than the constructors

    //score of player and dealer
    int scoreplayer = 0;
    int scoredealer = 0;

    //fundamental game name
    JLabel gameName;

    //dealer and player
    JLabel dealerLabel;
    JLabel playerLabel;

    //display scores
    JTextField scorePlayer;
    JTextField score1;
    JTextField scoreDealer;
    JTextField score2;

    //when player is done
    JButton StandButton;
    JButton QuitButton;
    JButton startButton;
    JButton playAgainButton;
    JButton hitButton;

    //display cards drawn
    JTextArea outputArea;

    //count the winning times
    /*
    int winPlayer = 0;
    int winDealer = 0;

     */

    //Create variables and classes necessary for the deck
    public BlackjackFrame() {
        //prepare the basics so that later J variables can use
        setLayout(new GridBagLayout());
        GridBagConstraints positionConst = new GridBagConstraints();
        Border Line = BorderFactory.createLineBorder(Color.white, 3);

        //The following 13 huge block of codes creates the instances of the J variables
        //block #1
        setTitle("Blackjack");
        gameName = new JLabel("Let's Play Blackjack");
        gameName.setBorder(Line);
        gameName.setForeground(Color.pink);
        gameName.setBackground(Color.lightGray);
        gameName.setOpaque(true);
        gameName.setFont(new Font("MV Boli", Font.PLAIN, 50));
        positionConst.gridx = 2;
        positionConst.gridy = 0;
        positionConst.insets = new Insets(20, 20, 20, 20);
        add(gameName, positionConst);

        //block #2
        scorePlayer = new JTextField("Score of player: ");
        scorePlayer.setForeground(Color.darkGray);
        scorePlayer.setFont(new Font("MV Boli", Font.PLAIN, 18));
        scorePlayer.setEditable(false);
        scorePlayer.setHorizontalAlignment(JTextField.CENTER);
        positionConst.gridx = 0;
        positionConst.gridy = 1;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(scorePlayer, positionConst);

        //block #3
        score1 = new JTextField("Score");
        score1.setForeground(Color.darkGray);
        score1.setFont(new Font("MV Boli", Font.PLAIN, 18));
        score1.setEditable(false);
        score1.setHorizontalAlignment(JTextField.CENTER);
        positionConst.gridx = 1;
        positionConst.gridy = 1;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(score1, positionConst);

        //block #4
        scoreDealer = new JTextField("Score of dealer: ");
        scoreDealer.setForeground(Color.darkGray);
        scoreDealer.setFont(new Font("MV Boli", Font.PLAIN, 18));
        scoreDealer.setEditable(false);
        scoreDealer.setHorizontalAlignment(JTextField.CENTER);
        positionConst.gridx = 2;
        positionConst.gridy = 1;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(scoreDealer, positionConst);

        //block #5
        score2 = new JTextField("Score");
        score2.setForeground(Color.darkGray);
        score2.setFont(new Font("MV Boli", Font.PLAIN, 18));
        score2.setEditable(false);
        score2.setHorizontalAlignment(JTextField.CENTER);
        positionConst.gridx = 3;
        positionConst.gridy = 1;
        positionConst.insets = new Insets(10, 10, 10, 10);
        add(score2, positionConst);

        //block #6
        playerLabel = new JLabel("Player");
        playerLabel.setForeground(Color.darkGray);
        playerLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        positionConst.gridx = 0;
        positionConst.gridy = 2;
        positionConst.insets = new Insets(0, 0, 10, 10);
        add(playerLabel, positionConst);

        //block #7
        dealerLabel = new JLabel("Dealer");
        dealerLabel.setForeground(Color.darkGray);
        dealerLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
        positionConst.gridx = 3;
        positionConst.gridy = 2;
        positionConst.insets = new Insets(0, 0, 10, 10);
        add(dealerLabel, positionConst);

        //block #8
        hitButton = new JButton("Hit me!");
        hitButton.setForeground(Color.darkGray);
        hitButton.setFont(new Font("MV Boli", Font.PLAIN, 18));
        positionConst.gridx = 1;
        positionConst.gridy = 3;
        positionConst.insets = new Insets(0, 0, 10, 10);
        hitButton.addActionListener(this);
        add(hitButton, positionConst);

        //block #9
        StandButton = new JButton("Stand");
        StandButton.setForeground(Color.darkGray);
        StandButton.setFont(new Font("MV Boli", Font.PLAIN, 18));
        positionConst.gridx = 2;
        positionConst.gridy = 3;
        positionConst.insets = new Insets(0, 0, 10, 10);
        StandButton.addActionListener(this);
        add(StandButton, positionConst);

        //block #10
        startButton = new JButton("START");
        positionConst.gridx = 1;
        positionConst.gridy = 5;
        positionConst.insets = new Insets(20, 0, 0, 0);
        startButton.setFont(new Font("MV Boli", Font.PLAIN, 30));
        startButton.setForeground(Color.pink);
        startButton.setBackground(Color.gray);
        startButton.setOpaque(true);
        startButton.setBorder(Line);
        startButton.setPreferredSize(new Dimension(100, 60));
        startButton.addActionListener(this);
        add(startButton, positionConst);

        //block #11
        playAgainButton = new JButton("PLAY AGAIN");
        positionConst.gridx = 2;
        positionConst.gridy = 5;
        positionConst.insets = new Insets(20, 0, 0, 0);
        playAgainButton.setFont(new Font("MV Boli", Font.PLAIN, 30));
        playAgainButton.setForeground(Color.pink);
        playAgainButton.setBackground(Color.gray);
        playAgainButton.setOpaque(true);
        playAgainButton.setBorder(Line);
        playAgainButton.setPreferredSize(new Dimension(200, 60));
        playAgainButton.addActionListener(this);
        add(playAgainButton, positionConst);

        //block #12
        QuitButton = new JButton("QUIT");
        positionConst.gridx = 3;
        positionConst.gridy = 5;
        positionConst.insets = new Insets(20, 0, 0, 0);
        QuitButton.setFont(new Font("MV Boli", Font.PLAIN, 30));
        QuitButton.setForeground(Color.pink);
        QuitButton.setBackground(Color.gray);
        QuitButton.setOpaque(true);
        QuitButton.setBorder(Line);
        QuitButton.setPreferredSize(new Dimension(100, 60));
        QuitButton.addActionListener(this);
        add(QuitButton, positionConst);

        //block #13
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        positionConst.gridx = 2;
        positionConst.gridy = 4;
        outputArea.setFont(new Font("MV Boli", Font.PLAIN, 16));
        positionConst.insets = new Insets(0, 0, 20, 0);
        outputArea.setForeground(Color.gray);
        outputArea.setBackground(new Color(255, 250, 200));
        outputArea.setText("\n");
        outputArea.setEditable(false);
        add(outputArea, positionConst);

        //Prompt the user for the name
        String playerName = (String) JOptionPane.showInputDialog(new JFrame(), "Player 1, what is your name?");
        playerLabel.setText(playerName);

        //organize the J variables
        pack();
        repaint();
    }

    //actionPerformed method is used to capture all the mouse click, aka the userInput
    //for each button pressed, the corresponding method will be called
    public void actionPerformed(ActionEvent action) {

        if (action.getSource() == startButton) {
            System.out.println("startButton pressed");
            startToPlay();
        }

        if (action.getSource() == hitButton) {
            System.out.println("hitButton pressed");
            play();
        }

        if (action.getSource() == StandButton) {
            System.out.println("StandButton pressed");
            dealerTurn();
        }

        if (action.getSource() == QuitButton) {
            countWinners();
            System.exit(1);
        }

        if (action.getSource() == playAgainButton) {
            System.out.println("playAgainButton pressed");
            playAgain();
        }

    }

    //start to play method prepares the user to play the game
    public void startToPlay() {
        score1.setText("");
        score2.setText("");

        //shuffle cards
        //arraylist to get the index of cards
        //random number generator to shuffle cards
        Random rand = new Random();

        for (int i = 0; i < 52; i++) {
            int index = rand.nextInt(52);
            if (!arraylist.contains(index)) {
                arraylist.add(index);
            } else {
                i--;
            }
        }
        //disable it so user can't click on start multiple times
        startButton.setEnabled(false);
    }

    //playAgain method allows the user to restart the game
    public void playAgain() {

        BlackjackFrame blackjackFrame = new BlackjackFrame();
        blackjackFrame.setBackground(new Color(250, 50, 80));
        blackjackFrame.setOpacity(1);
        blackjackFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        blackjackFrame.pack();
        blackjackFrame.setVisible(true);

        //enable all buttons to be used by user
        startButton.setEnabled(true);
        hitButton.setEnabled(true);
        StandButton.setEnabled(true);

    }

    //play method allows the user the play as many times as desired
    //the first two times play is clicked, the dealer also get dealt cards
    int dealer = 0;

    public void play() {

        //check to see if consider Ace is 11 or 1
        if (arraylist.get(0) == 11) {
            if (scoreplayer + 11 <= 21) scoreplayer = scoreplayer + 11;
            else scoreplayer = scoreplayer + 1;
        }
        //add directly if the card is not an ace
        else if (arraylist.get(0) != 11) {
            scoreplayer = scoreplayer + cardsNumbers[arraylist.get(0)];
        }

        //display the card to outputArea
        score1.setText(scoreplayer + "");
        outputArea.append("   Player is dealt with " + cardsLetters[arraylist.get(0)] + "   \n");
        arraylist.remove(0);

        if (dealer == 0) {
            scoredealer = cardsNumbers[arraylist.get(0)];
            score2.setText(scoredealer + "");

            //display the card to outputArea
            outputArea.append("   Dealer is dealt with " + cardsLetters[arraylist.get(0)] + "   \n");
            arraylist.remove(0);
            dealer++;
        } else if (dealer == 1) {
            int temp = scoredealer;
            score2.setText(temp + " + ?");
            scoredealer = scoredealer + cardsNumbers[arraylist.get(0)];

            //display the card to outputArea
            outputArea.append("   Dealer is dealt with ?   \n");
            arraylist.remove(0);
            dealer++;
        }

        //scoreplayer automatically loses if scoreplayer is greater than 21
        if (scoreplayer > 21) {
            outputArea.append("   Player loses because Player score goes beyond 21" + "   \n");
            resultMethod();
        }

    }

    //dealerTurn method is a recursive method continues to call itself until scoredealer is greater than 17
    public void dealerTurn() {

        //only continues if scoredealer is less than 17
        while (scoredealer < 17) {
            //check to see if consider Ace is 11 or 1
            if (arraylist.get(0) == 11) {
                if (scoredealer + 11 >= 17 && scoredealer + 11 < 21) {
                    scoredealer = scoredealer + 11;
                    resultMethod();
                } else {
                    scoredealer = scoredealer + 1;
                }
            }
            //add directly if the card is not an ace
            else if (arraylist.get(0) != 11) {
                scoredealer = scoredealer + cardsNumbers[arraylist.get(0)];
            }
            score2.setText(scoredealer + "");
            System.out.println("Score of dealer: " + scoredealer);

            //display the card to outputArea
            outputArea.append("   Dealer is dealt with " + cardsLetters[arraylist.get(0)] + "   \n");

            arraylist.remove(0);
            dealerTurn();
        }
        //after dealer is done playing, call the result method to compare the result
        resultMethod();
    }

    //method that calls the resultMethod is recursive
    //an integer is set to prevent the resultMethod from being called multiple times
    //only gets called one time to show the result of the game
    int a = 0;

    public void resultMethod() {
        if (a > 0) {
            return;
        }
        else {
            //prepare for output JPane message
            JFrame f = new JFrame();

            //if scoredealer > 21, the scoreplayer automatically wins
            if (scoredealer > 21) {
                Main.winPlayer++;
                JOptionPane.showMessageDialog(f,
                        "Score of player :" + scoreplayer
                                + "\nScore of dealer :" + scoredealer
                                + "\nPlayer wins!!!");
                a++;
            }

            //if scoreplayer > 21, the scoredealer automatically wins
            else if (scoreplayer > 21) {
                Main.winDealer++;
                JOptionPane.showMessageDialog(f,
                        "Score of player :" + scoreplayer
                                + "\nScore of dealer :" + scoredealer
                                + "\nDealer wins!!!");
                a++;
            }
            else {
                //if both of them are smaller than 21, we then further compare
                int differencePlayer = 0;
                int differenceDealer = 0;
                differencePlayer = Math.abs(21 - scoreplayer);
                differenceDealer = Math.abs(21 - scoredealer);

                if (differencePlayer < differenceDealer) {
                    Main.winPlayer++;
                    JOptionPane.showMessageDialog(f,
                            "Score of player :" + scoreplayer
                                    + "\nScore of dealer :" + scoredealer
                                    + "\nPlayer wins!!!");
                } else if (differencePlayer > differenceDealer) {
                    Main.winDealer++;
                    JOptionPane.showMessageDialog(f,
                            "Score of player :" + scoreplayer
                                    + "\nScore of dealer :" + scoredealer
                                    + "\nDealer wins!!!");
                } else if (differencePlayer == differenceDealer) {
                    JOptionPane.showMessageDialog(f,
                            "Score of player :" + scoreplayer
                                    + "\nScore of dealer :" + scoredealer
                                    + "\nIt's a tie");
                }
                a++;
            }
            //disable it so user can't click on hitButtom multiple times
            hitButton.setEnabled(false);
            //disable it so user can't click on StandButton multiple times
            StandButton.setEnabled(false);
        }
    }

    //countWinner method executes before exit the game
    public void countWinners() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f,
                "Player wins " + Main.winPlayer + " times"
                        + "\nDealer wins " + Main.winDealer + " times"
                        + "\n");
    }
}