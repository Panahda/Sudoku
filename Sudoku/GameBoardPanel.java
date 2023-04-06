package Sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameBoardPanel extends JPanel{
    //initialise constant grid size
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // Define named constants for the game board properties
    public static final int GRID_SIZE = 9;    // Size of the board
    public static final int SUBGRID_SIZE = 3; // Size of the sub-grid
    
    // Define named constants for UI sizes
    public static final int CELL_SIZE = 60;   // Cell width/height in pixels
    public static final int BOARD_WIDTH  = CELL_SIZE * GRID_SIZE;
    public static final int BOARD_HEIGHT = CELL_SIZE * GRID_SIZE;


}
