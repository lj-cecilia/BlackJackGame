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

import javax.swing.JFrame;
import java.awt.*;

public class Main {
    //count the win times of player and dealer, used in blackjackFrame
    static int winPlayer = 0;
    static int winDealer = 0;

    //main method
    public static void main(String[] args) {
        //instantiate an instance of BlackjackFrame
        BlackjackFrame blackjackFrame = new BlackjackFrame();

        //parameterized the blackJackFrame, set the size, change the color
        blackjackFrame.setBackground(new Color(250,50,80));
        blackjackFrame.setOpacity(1);
        blackjackFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        blackjackFrame.pack();
        blackjackFrame.setVisible(true);
    }
}