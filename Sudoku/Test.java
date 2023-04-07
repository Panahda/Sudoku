package Sudoku;

public class Test {

    public static void main(String args[]){
        Puzzle newPuzzle = Puzzle.getInstance();
        for (int i = 0; i<9; i++)
        {
            for (int j = 0; j<9; j++)
                System.out.print(newPuzzle.isGiven[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}
