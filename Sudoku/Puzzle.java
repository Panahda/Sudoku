package Sudoku;
import java.util.Random;

public final class Puzzle {
    int numbers[][] = new int [GameBoardPanel.GRID_SIZE][GameBoardPanel.GRID_SIZE];
    boolean isGiven[][] = new boolean [GameBoardPanel.GRID_SIZE][GameBoardPanel.GRID_SIZE];

    //puzzle object
    private static final Puzzle newPuzzle = new Puzzle();

    private Puzzle(){
        int stepCount = 0;

        //generate random sudoku numbers & inputfield
        for(int puzzleRow = 0; puzzleRow < GameBoardPanel.GRID_SIZE; puzzleRow++){
            for(int puzzleCol = 0; puzzleCol < GameBoardPanel.GRID_SIZE; puzzleCol++){
                //copy number from [1,9]
                numbers[puzzleRow][puzzleCol] = numberGenerator(9) + 1;

                if(!(stepCount <= 3)){
                    int isFilled = numberGenerator(2);
                    stepCount = 0;
                    if(isFilled == 1){
                        isGiven[puzzleRow][puzzleCol] = true;
                    }else{
                        isGiven[puzzleRow][puzzleCol] = false;
                    }
                }else{
                    stepCount ++;
                    isGiven[puzzleRow][puzzleCol] = true;
                }
            }
        }
    }

    private int numberGenerator(int maxNumber){
    
        //generate random number with upperbound (maxNumber)
        Random randomNumberGenerated = new Random();
        int puzzleNumberGenerated = randomNumberGenerated.nextInt(maxNumber);
        return (puzzleNumberGenerated);
    }

    public static Puzzle getInstance(){
        return newPuzzle;
    }

}