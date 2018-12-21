/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Emoji Match");
        frame.setLocation(500, 500);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Status: Running  ");
        status_panel.add(status);
        final JLabel score = new JLabel("Score:");
        status_panel.add(score);

        // Player's name
        String name = JOptionPane.showInputDialog("What's your name?");
        final JLabel PlayerName = new JLabel(name);
        status_panel.add(PlayerName);
        
        // game description
        JOptionPane.showMessageDialog(frame, 
        		"Instructions\n" +
        		"The objective of the game is to combine 2 of the same emojis until you create " +
                "ANGRY REACCS!\n" +
                "Shift the tiles using the arrow keys\n" +
                "With each shift, another tile is generated\n" +
                "You win if you get the angry reaccs emoji\n" + 
                "You lose if the board fills up before that");
        
        // Main playing area
        final Board board = new Board(status, name, score);
        frame.add(board, BorderLayout.CENTER);

        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Undo button
        final JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.undo();
            }
        });
        control_panel.add(undo);
        
        // Restart button
        final JButton restart = new JButton("Restart");
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.restart();
            }
        });
        control_panel.add(restart);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        // Start game
        board.restart();
        
        // display all players
        JOptionPane.showMessageDialog(frame, HighScores.getHighScores(), "Leaderboard", 
				JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}