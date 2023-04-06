package Sudoku;
public class Testing {
    public static void main(String[] args){
        Puzzle newPuzzle = Puzzle.getInstance();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.printf("%d", newPuzzle.numbers[i][j]);
            }
            System.out.printf("\n");
        }

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.printf("     %b", newPuzzle.isGiven[i][j]);
            }
            System.out.printf("\n");
        }
    }
}