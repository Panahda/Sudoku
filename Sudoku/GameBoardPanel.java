package Sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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

   // Define properties
   /** The game board composes of 9x9 Cells (customized JTextFields) */
   private Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];
   /** It also contains a Puzzle with array numbers and isGiven */
   private Puzzle puzzle = Puzzle.getInstance();

   /** Constructor */
   public GameBoardPanel() {
      super.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));  // JPanel

      // Allocate the 2D array of Cell, and added into JPanel.
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            cells[row][col] = new Cell(row, col);
            super.add(cells[row][col]);   // JPanel
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
      Puzzle.getInstance().newBoard();

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

        // [TODO 6]
        if (numberIn == sourceCell.number) {
        	JOptionPane.showMessageDialog(null, "Congratulation!");
        } else {
        	JOptionPane.showMessageDialog(null, "Try again!");
        }
        }
    }
}