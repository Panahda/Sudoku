package Sudoku.bin;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Sudoku.bin.ExtraFeatures.GameBoardPanel;
import Sudoku.bin.ExtraFeatures.GameClock;
import Sudoku.bin.HighScore.HighScore;

/**
 * The main Sudoku program
 */
public class SudokuGame extends JFrame {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   // private variables
   GameBoardPanel board = new GameBoardPanel();
   JButton btnNewGame = new JButton("New Game");
   GameClock timer = GameClock.getInstance();
   HighScore highscore = HighScore.getInstance();
   public static int x;
   
   public void setSudokuDiff(int x) {
	   SudokuGame.x=x;
   }
   // Constructor
   public SudokuGame(int x) {
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());

      cp.add(board, BorderLayout.CENTER);

      cp.add(highscore, BorderLayout.WEST);

      //Add timer on top of gameboard
      cp.add(timer, BorderLayout.NORTH);

      // Add a button to the south to re-start the game via board.newGame()
      cp.add(btnNewGame, BorderLayout.SOUTH);
      BtnInputListener listener = new BtnInputListener();
      btnNewGame.addActionListener(listener);
      board.setX(x);
      // Initialize the game board to start the game
      board.newGame();

      pack();     // Pack the UI components, instead of using setSize()
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // to handle window-closing
      setTitle("Sudoku");
      setVisible(true);
   }

   // /** The entry main() entry method */
   // public static void main(String[] args) {
   //    SwingUtilities.invokeLater(new Runnable() {
   //       @Override
   //       public void run() {
   //          new SudokuGame(x); // Let the constructor does the job
   //       }
   //    });
   // }
    

    //When button is pressed, gameboard and timer resets
    private class BtnInputListener implements ActionListener {
    	@Override
    	public void actionPerformed(ActionEvent evt){
    		board.newGame();
    		timer.reset();
    	}
   }

}
