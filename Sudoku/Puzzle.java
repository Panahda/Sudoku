package Sudoku;
import java.util.Random;

public final class Puzzle {
    int numbers[][] = new int [GameBoardPanel.GRID_SIZE][GameBoardPanel.GRID_SIZE];
    boolean isGiven[][] = new boolean [GameBoardPanel.GRID_SIZE][GameBoardPanel.GRID_SIZE];
    final int maxPuzzleDifficulty = 3;
    int puzzleCurrentDifficultyLevel = 1;
    int puzzleMissingDigitArray[] = new int[3];


    //puzzle object
    private static final Puzzle newPuzzle = new Puzzle();

    Puzzle(){
        //initialise missing digit array
        puzzleMissingDigitArray[0] = 18;
        puzzleMissingDigitArray[1] = 27;
        puzzleMissingDigitArray[2] = 36;

        fillGameGrid();
        createMissingDigits2DArray(maxPuzzleDifficulty);
        //fillDiagonalSubgrid();

    }

    private void createMissingDigits2DArray(int digitsMissing){
        for(int row = 0; row < GameBoardPanel.GRID_SIZE; row++){
            for(int col = 0; col < GameBoardPanel.GRID_SIZE; col ++){
                if(col == randomNumberGenerator()-1){
                    isGiven[row][col] = false;
                }else{
                    isGiven[row][col] = true;
                }
            }
        }
    }

    private void fillDiagonalSubgrid(){
        for(int rowAndCol = 0; rowAndCol < GameBoardPanel.GRID_SIZE; rowAndCol = rowAndCol + GameBoardPanel.SUBGRID_SIZE){
            fillSubgrid(rowAndCol, rowAndCol);
        }
    }
    
    private int randomNumberGenerator(){
        Random randomNumberGenerated = new Random();
        int puzzleNumberGenerated = randomNumberGenerated.nextInt(GameBoardPanel.GRID_SIZE);
        return (puzzleNumberGenerated+1);
    }
    
    private boolean unusedInSubgrid(int gridRow, int gridCol, int newNumber){
        for(int row = 0; row < GameBoardPanel.SUBGRID_SIZE; row++){
            for(int col = 0; col < GameBoardPanel.SUBGRID_SIZE; col++){
                if(numbers[gridRow + row][gridCol + col] == newNumber){
                    return false;
                }
            }
        }
        return true;
    }

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


    private boolean unusedInRow(int gridRow, int newNumber){
        for(int col = 0; col < GameBoardPanel.GRID_SIZE; col++){
            if(numbers[gridRow][col] == newNumber){
                return false;
            }
        }
        return true;
    }

    private boolean unusedInCol(int gridCol, int newNumber){
        for(int row = 0; row < GameBoardPanel.GRID_SIZE; row++){
            if(numbers[row][gridCol] == newNumber){
                return false;
            }
        }
        return true;
    }

    private boolean ifNumberNotRepeated(int gridRow, int gridCol, int newNumber){
        if(unusedInRow(gridRow, newNumber) && unusedInCol(gridCol, newNumber) && unusedInSubgrid(gridRow-gridRow%GameBoardPanel.SUBGRID_SIZE, gridCol-gridCol%GameBoardPanel.SUBGRID_SIZE, newNumber)){
            return true;
        }else{
            return false;
        }
    }

    private boolean fillNonDiagonalSubgrids(int gridRow, int gridCol){
        //System.out.println("fillNonDiagonalSubgrids(" + gridRow + ", " + gridCol + ")");
        if(gridCol >= GameBoardPanel.GRID_SIZE && gridRow < GameBoardPanel.GRID_SIZE-1){
            gridRow ++;
            gridCol = 0;
        }
        if(gridRow >= GameBoardPanel.GRID_SIZE && gridCol >= GameBoardPanel.GRID_SIZE){
            return true;
        }
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
                if(gridRow >= GameBoardPanel.GRID_SIZE){
                    return true;
                }
            }
        }
        for(int newNumber = 1; newNumber <= GameBoardPanel.GRID_SIZE; newNumber++){
            if(ifNumberNotRepeated(gridRow, gridCol, newNumber)){

                //System.out.println("Checking if " + newNumber + " can be placed at (" + gridRow + ", " + gridCol + ")" + ifNumberNotRepeated(gridRow, gridCol, newNumber));
                
                numbers[gridRow][gridCol] = newNumber;
                if(fillNonDiagonalSubgrids(gridRow, gridCol+1)){
                     return true;
                }
                numbers[gridRow][gridCol] = 0;
            }   
        }
        return false;
    }

    

    private void fillGameGrid(){
        fillDiagonalSubgrid();
        fillNonDiagonalSubgrids(0, GameBoardPanel.SUBGRID_SIZE);
    }

    public static Puzzle getInstance(){
        return newPuzzle;
    }

}