package Sudoku.bin.HighScore;
import java.util.*;

public class HighScoreDatabase{
    public static final int HIGHSCORE_MAX_DISPLAY = 5;
    int index = 0;
    
    public LinkedList<Integer> highScoreLL = new LinkedList<>();
    public LinkedList<String> highScoreNameLL = new LinkedList<>();

    int highScoreCount = 0;

    //Singleton
    private static final HighScoreDatabase db = new HighScoreDatabase();

    public HighScoreDatabase(){}

    public void addNewScore(int newScore, String newName){

        if(highScoreCount == 0){
            highScoreLL.add(newScore);
            highScoreNameLL.add(newName);

            highScoreCount ++;
        }else{
            index = 0;
            while(index != highScoreLL.size() && highScoreLL.get(index) < newScore){
                index++;
            }

            if (highScoreLL.size() <= index) {
                highScoreLL.add(newScore);
                highScoreNameLL.add(newName);
            } else {
                highScoreLL.add(index, newScore);
                highScoreNameLL.add(index, newName);
            }
            
            highScoreCount ++;
        }

        System.out.println("HighScore LinkedList : " + highScoreLL + highScoreNameLL);
    }

    public static HighScoreDatabase getInstance(){
        return db;
    }
}
