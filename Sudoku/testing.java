package Sudoku;

public class testing{
    public static void main(String args[]){
        Puzzle newP = Puzzle.getInstance();


        for (int i = 0; i<9; i++)
        {
            for (int j = 0; j<9; j++)
                System.out.print(newP.numbers[i][j] + " ");
                System.out.println();
        }
        System.out.println();


        Puzzle.getInstance().newBoard();

        for (int i = 0; i<9; i++)
        {
            for (int j = 0; j<9; j++)
                System.out.print(newP.numbers[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}
