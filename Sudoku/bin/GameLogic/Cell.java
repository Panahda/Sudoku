package Sudoku.bin.GameLogic;

import java.awt.Font;
import javax.swing.JTextField;

import Sudoku.bin.Utility.*;

/**
 * The Cell class model the cells of the Sudoku puzzle, by customizing (subclass)
 * the javax.swing.JTextField to include row/column, puzzle number and status.
 */


public class Cell extends JTextField {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   public static final Font FONT_NUMBERS = new Font("Comic Sans", Font.PLAIN, 28);

   // Define properties (package-visible)
   /** The row and column number [0-8] of this cell */
   int row, col;
   /** The puzzle number [1-9] for this cell */
   public int number;
   /** The status of this cell defined in enum CellStatus */
   public CellStatus status;

   /** Constructor */
   public Cell(int row, int col) {
      super();   // JTextField
      this.row = row;
      this.col = col;
      // Inherited from JTextField: Beautify all the cells once for all
      super.setHorizontalAlignment(JTextField.CENTER);
      super.setFont(FONT_NUMBERS);
   }

   /** Reset this cell for a new game, given the puzzle number and isGiven */
   public void newGame(int number, boolean isGiven) {
      this.number = number;
      status = isGiven ? CellStatus.GIVEN : CellStatus.TO_GUESS;
      paint();    // paint itself
   }

   /** This Cell (JTextField) paints itself based on its status */
   public void paint() {
      if (status == CellStatus.GIVEN) {
         // Inherited from JTextField: Set display properties
         super.setText(number + "");
         super.setEditable(false);
         super.setBackground(Colours.BG_GIVEN);
         super.setForeground(Colours.FG_GIVEN);
      } else if (status == CellStatus.TO_GUESS) {
         // Inherited from JTextField: Set display properties
         super.setText("");
         super.setEditable(true);
         super.setBackground(Colours.BG_TO_GUESS);
         super.setForeground(Colours.FG_NOT_GIVEN);
      } else if (status == CellStatus.CORRECT_GUESS) {  // from TO_GUESS
         super.setBackground(Colours.BG_CORRECT_GUESS);
      } else if (status == CellStatus.WRONG_GUESS) {    // from TO_GUESS
         super.setBackground(Colours.BG_WRONG_GUESS);
      }
   }
}