package Sudoku;
import java.util.Random;

public final class Puzzle {

    //initialise 2D arrays and variables
    int numbers[][] = new int [GameBoardPanel.GRID_SIZE][GameBoardPanel.GRID_SIZE];
    boolean isGiven[][] = new boolean [GameBoardPanel.GRID_SIZE][GameBoardPanel.GRID_SIZE];
    final int maxPuzzleDifficulty = 3;
    int puzzleCurrentDifficultyLevel = 1;
    int puzzleMissingDigitArray[] = new int[3];


    //puzzle object
    private static final Puzzle newPuzzle = new Puzzle();

    //Puzzle constructor
    Puzzle(){
        //initialise missing digit array
        puzzleMissingDigitArray[0] = 18;
        puzzleMissingDigitArray[1] = 36;
        puzzleMissingDigitArray[2] = 54;

        fillGameGrid();
        createMissingDigits2DArray(maxPuzzleDifficulty);
        //fillDiagonalSubgrid();

    }

    //Set random false in 2D array
    private void createMissingDigits2DArray(int digitsMissing){
        setAllTrue();
        for(int count = 0; count < digitsMissing; count ++){
            removeDigit();
        }
    }

    //create all true 2D isGiven array
    private void setAllTrue(){
        for(int row = 0; row < GameBoardPanel.GRID_SIZE; row++){
            for(int col = 0; col < GameBoardPanel.GRID_SIZE; col ++){
                isGiven[row][col] = true;
            }
        }
    }

    //randomly changing 1 to false in isGiven 2D array IF not already false
    private void removeDigit(){
        int deleteRow;
        int deleteCol;
        do{
            deleteRow = randomNumberGenerator()-1;
            deleteCol = randomNumberGenerator()-1;
        }while(!isGiven[deleteRow][deleteCol]);
        
        for(int row = 0; row < GameBoardPanel.GRID_SIZE; row++){
            for(int col = 0; col < GameBoardPanel.GRID_SIZE; col ++){
                if(row == deleteRow && col == deleteCol){
                    isGiven[row][col] = false;
                }
            }
        }
        
    }

    //filling 3by3 subgrid that is diagonal
    private void fillDiagonalSubgrid(){
        for(int rowAndCol = 0; rowAndCol < GameBoardPanel.GRID_SIZE; rowAndCol = rowAndCol + GameBoardPanel.SUBGRID_SIZE){
            fillSubgrid(rowAndCol, rowAndCol);
        }
    }
    
    //generating random numbers from 1-9
    private int randomNumberGenerator(){
        Random randomNumberGenerated = new Random();
        int puzzleNumberGenerated = randomNumberGenerated.nextInt(GameBoardPanel.GRID_SIZE);
        return (puzzleNumberGenerated+1);
    }
    
    //return true is number not repeated in subgrid
    private boolean unusedInSubgrid(int gridRowStart, int gridColStart, int newNumber){
        for(int row = 0; row < GameBoardPanel.SUBGRID_SIZE; row++){
            for(int col = 0; col < GameBoardPanel.SUBGRID_SIZE; col++){
                if(numbers[gridRowStart + row][gridColStart + col] == newNumber){
                    return false;
                }
            }
        }
        return true;
    }

    //Filling subgrid with random numbers if not repeated in subgrid
    private void fillSubgrid(int gridRow, int gridCol){
        int newNumber;
        for(int row = 0; row < GameBoardPanel.SUBGRID_SIZE; row++){
            for(int col = 0; col < GameBoardPanel.SUBGRID_SIZE; col++){
                do{
                    newNumber = randomNumberGenerator();
                }while(!unusedInSubgrid(gridRow, gridCol, newNumber));

                numbers[gridRow + row][gridCol + col] = newNumber;
            }
        }
    }

    //return true if not used in current row
    private boolean unusedInRow(int gridRow, int newNumber){
        for(int col = 0; col < GameBoardPanel.GRID_SIZE; col++){
            if(numbers[gridRow][col] == newNumber){
                return false;
            }
        }
        return true;
    }

    //return true if not used in current col
    private boolean unusedInCol(int gridCol, int newNumber){
        for(int row = 0; row < GameBoardPanel.GRID_SIZE; row++){
            if(numbers[row][gridCol] == newNumber){
                return false;
            }
        }
        return true;
    }

    //combine all 3 conditions: 1) unused in row, 2) unused in col 3) unused in subgrid
    private boolean ifNumberNotRepeated(int gridRow, int gridCol, int newNumber){
        if(unusedInRow(gridRow, newNumber) && unusedInCol(gridCol, newNumber) && unusedInSubgrid(gridRow-gridRow%GameBoardPanel.SUBGRID_SIZE, gridCol-gridCol%GameBoardPanel.SUBGRID_SIZE, newNumber)){
            return true;
        }else{
            return false;
        }
    }

    //fills the rest of the puzzle fill going row by row recursively
    private boolean fillNonDiagonalSubgrids(int gridRow, int gridCol){
        //System.out.println("fillNonDiagonalSubgrids(" + gridRow + ", " + gridCol + ")");

        //If reach last col AND not last row, move to next row
        if(gridCol >= GameBoardPanel.GRID_SIZE && gridRow < GameBoardPanel.GRID_SIZE-1){
            gridRow ++;
            gridCol = 0;
        }

        //If reach last row and col
        if(gridRow >= GameBoardPanel.GRID_SIZE && gridCol >= GameBoardPanel.GRID_SIZE){
            return true;
        }

        //If reaches the diagonal grids, skip them
        if(gridRow < GameBoardPanel.SUBGRID_SIZE){
            if(gridCol < GameBoardPanel.SUBGRID_SIZE){
                gridCol = GameBoardPanel.SUBGRID_SIZE;
            }
        }else if(gridRow < GameBoardPanel.GRID_SIZE - GameBoardPanel.SUBGRID_SIZE){
            if(gridCol< GameBoardPanel.GRID_SIZE - GameBoardPanel.SUBGRID_SIZE && gridCol >= GameBoardPanel.SUBGRID_SIZE ){
                gridCol += GameBoardPanel.SUBGRID_SIZE;
            }
        }else{
            if(gridCol == GameBoardPanel.GRID_SIZE - GameBoardPanel.SUBGRID_SIZE){
                gridRow++;
                gridCol = 0;

                //If reach last row
                if(gridRow >= GameBoardPanel.GRID_SIZE){
                    return true;
                }
            }
        }

        //Add number into grid IF all 3 conditions are met
        for(int newNumber = 1; newNumber <= GameBoardPanel.GRID_SIZE; newNumber++){
            if(ifNumberNotRepeated(gridRow, gridCol, newNumber)){

                //System.out.println("Checking if " + newNumber + " can be placed at (" + gridRow + ", " + gridCol + ")" + ifNumberNotRepeated(gridRow, gridCol, newNumber));
                
                numbers[gridRow][gridCol] = newNumber;

                //recursive step, stops when reach false
                if(fillNonDiagonalSubgrids(gridRow, gridCol+1)){
                     return true;
                }

                //backtracking
                numbers[gridRow][gridCol] = 0;
            }   
        }
        return false;
    }

    
    //Calls both fillDigonal and fillNonDiagonal in order to fill the entire grid
    private void fillGameGrid(){
        fillDiagonalSubgrid();
        fillNonDiagonalSubgrids(0, GameBoardPanel.SUBGRID_SIZE);
    }

    //Singleton Design pattern, only 1 instance
    public static Puzzle getInstance(){
        return newPuzzle;
    }

    //public method to reset the instance to new numbers; 
    public void newBoard(int x){
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
               numbers[row][col] = 0;
            }
         }
        fillGameGrid(); 
        // difficulty
        createMissingDigits2DArray(puzzleMissingDigitArray[x]);
    }
}