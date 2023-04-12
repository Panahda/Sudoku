package Sudoku.bin.ExtraFeatures;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Sudoku.bin.GameLogic.Cell;

public class GameClock extends JPanel{

    //initialise values
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    JLabel timeLabel = new JLabel();

    String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);

    ActionListener timeIncrememet = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            elapsedTime = elapsedTime + 1000;
            hours = (elapsedTime / 3600000); // total ms / (ms in 1 hour) == current hour
            minutes = (elapsedTime / 60000) % 60; //current minute if not over 60
            seconds = (elapsedTime / 1000) % 60; //current seconds if not over 60

            seconds_string = String.format("%02d", seconds);
	        minutes_string = String.format("%02d", minutes);
	        hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        }
    };

    Timer timer = new Timer(1000, timeIncrememet);

    private static final GameClock newTimer = new GameClock();

    //Show label (timer) and start timer
    GameClock(){
        super.setPreferredSize(new Dimension(GameBoardPanel.BOARD_WIDTH, 60));

        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        super.add(timeLabel);
        timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
        timeLabel.setForeground(Cell.FG_NOT_GIVEN);
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        timer.start();
    }

    //method to reset time to 0 & restart timer
    public void reset(){
		elapsedTime=0;
		seconds =0;
		minutes=0;
		hours=0;
		seconds_string = String.format("%02d", seconds);
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);

        //restart timer
        timer.start();
    }

    //stop timer
    public void stop(){
        timer.stop();
    }

    //singleton design
    public static GameClock getInstance(){
        return newTimer;
    }
}
