package Sudoku;
import java.util.*;

public class HighScoreDatabase{
    public static final int HIGHSCORE_MAX_DISPLAY = 5;
    int index = 0;
    
    public LinkedList<Integer> highScoreLL = new LinkedList<>();
    int highScoreCount = 0;

    //Singleton
    private static final HighScoreDatabase db = new HighScoreDatabase();

    public HighScoreDatabase(){}

    public void addNewScore(int newScore){

        ListIterator<Integer> highScoreLLIterator = highScoreLL.listIterator(0);

        if(highScoreCount == 0){
            highScoreLL.add(newScore);
            highScoreCount ++;
        }else{
            index = 0;
            while(highScoreLLIterator.hasNext() && highScoreLL.get(index) < newScore){
                index++;
            }

            highScoreLL.add(index, newScore);
            highScoreCount ++;
        }

        System.out.println("HighScore LinkedList : " + highScoreLL);
    }

    public static HighScoreDatabase getInstance(){
        return db;
    }
}
