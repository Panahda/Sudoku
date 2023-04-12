package Sudoku.bin.ExtraFeatures;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Sudoku.bin.GameLogic.*;
import Sudoku.bin.HighScore.*;
import Sudoku.bin.Utility.*;

public class GameBoardPanel extends JPanel {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   // Define named constants for the game board properties
   public static final int GRID_SIZE = 9;    // Size of the board
   public static final int SUBGRID_SIZE = 3; // Size of the sub-grid
   // Define named constants for UI sizes
   public static final int CELL_SIZE = 60;   // Cell width/height in pixels
   public static final int BOARD_WIDTH  = CELL_SIZE * GRID_SIZE;
   public static final int BOARD_HEIGHT = CELL_SIZE * GRID_SIZE;
                                             // Board width/height in pixels
   public int difficulty;
   // Define properties
   /** The game board composes of 9x9 Cells (customized JTextFields) */
   private Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];
   /** It also contains a Puzzle with array numbers and isGiven */
   private Puzzle puzzle = Puzzle.getInstance();
   private GameClock timer = GameClock.getInstance();

   /** Constructor */
   public GameBoardPanel() {
	   super(new GridLayout(GRID_SIZE / SUBGRID_SIZE, GRID_SIZE / SUBGRID_SIZE));  // JPanel
      super.setBackground(Colours.FG_GIVEN);
      
	   // Allocate the 2D array of Cell, and add to sub-grids
	   for (int subRow = 0; subRow < SUBGRID_SIZE; ++subRow) {
	      for (int subCol = 0; subCol < SUBGRID_SIZE; ++subCol) {
	         JPanel subGrid = new JPanel(new GridLayout(SUBGRID_SIZE, SUBGRID_SIZE));
	         subGrid.setBorder(BorderFactory.createLineBorder(Colours.FG_NOT_GIVEN, 1));
	         super.add(subGrid);   // add sub-grid to main panel

	         for (int row = 0; row < SUBGRID_SIZE; ++row) {
	            for (int col = 0; col < SUBGRID_SIZE; ++col) {
	               int absRow = subRow * SUBGRID_SIZE + row;
	               int absCol = subCol * SUBGRID_SIZE + col;
	               cells[absRow][absCol] = new Cell(absRow, absCol);
	               subGrid.add(cells[absRow][absCol]);   // add cell to sub-grid
	            }
	         }
	      }
	   }

	   //  Cells (JTextFields)
	   CellInputListener listener = new CellInputListener();

	   for (int row = 0; row < GRID_SIZE; ++row) {
	      for (int col = 0; col < GRID_SIZE; ++col) {
	         if (cells[row][col].isEditable()) {
	            cells[row][col].addActionListener(listener);   // For all editable rows and cols
	         }
	      }
	   }

	   super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
	}
               
   /**
    * Generate a new puzzle; and reset the gameboard of cells based on the puzzle.
    * You can call this method to start a new game.
    */
   public void newGame() {
      // Generate a new puzzle
      System.out.println("newGame Method Called");
      Puzzle.getInstance().newBoard(difficulty);

      // Initialize all the 9x9 cells, based on the puzzle.
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            cells[row][col].newGame(puzzle.numbers[row][col], puzzle.isGiven[row][col]);
         }
      }
   }
   
   /**
    * Return true if the puzzle is solved
    * i.e., none of the cell have status of TO_GUESS or WRONG_GUESS
    */
   public boolean isSolved() {
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            if (cells[row][col].status == CellStatus.TO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
               return false;
            }
         }
      }
      return true;
   }

   //  Define a Listener Inner Class for all the editable Cells
   private class CellInputListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
       // Get a reference of the JTextField that triggers this action event
       Cell sourceCell = (Cell)e.getSource();
    
       // Retrieve the int entered
       int numberIn = Integer.parseInt(sourceCell.getText());
       // For debugging
       System.out.println("You entered " + numberIn);

       /*
          * Check the numberIn against sourceCell.number.
          * Update the cell status sourceCell.status,
          * and re-paint the cell via sourceCell.paint().
          */

        if (numberIn == sourceCell.number){
            sourceCell.status = CellStatus.CORRECT_GUESS;
            sourceCell.paint();
        } else { 
            sourceCell.status = CellStatus.WRONG_GUESS;
            sourceCell.paint();
        }
        
         if (numberIn == sourceCell.number) {
            JOptionPane.showMessageDialog(null, "Congratulation!");
         } else {
            JOptionPane.showMessageDialog(null, "Try again!");
         }


         //Once puzzle completed, timer stops and add to highscore
         if(isSolved()){
            timer.stop();
            HighScoreDatabase.getInstance().addNewScore(timer.elapsedTime);
            HighScore.getInstance().updateScore();
         }
      }
   }
   
   public void setDifficulty(int difficulty) {
	   this.difficulty = difficulty;
   }
}