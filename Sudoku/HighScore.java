package Sudoku;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class HighScore extends JPanel{
    String[] highScoreList = new String[HighScoreDatabase.HIGHSCORE_MAX_DISPLAY+1];
    JLabel header = new JLabel();
    private final JTable table;
    private final DefaultTableModel tableModel;

    HighScoreDatabase db = HighScoreDatabase.getInstance();

    int maxRank = 1;

    //Singleton design
    private static final HighScore highscore = new HighScore();

    HighScore(){

        super.setPreferredSize(new Dimension(200,GameBoardPanel.BOARD_HEIGHT+60));
        
        // Set up the panel
        setLayout(new BorderLayout());

        // Set up the table
        tableModel = new DefaultTableModel(new Object[]{"Rank", "Time"}, 0);

        table = new JTable(tableModel);

        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.setFont(new Font("Arial", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Set the preferred width of the "Rank" column to 50 pixels
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10); 
    }

    public void updateScore(){

        int hours = 0; // total ms / (ms in 1 hour) == current hour
        int minutes = 0; //current minute if not over 60
        int seconds = 0; //current seconds if not over 60
        String newScore;

        tableModel.setRowCount(0);
        for(int count = 0; count < HighScoreDatabase.getInstance().highScoreCount; count ++){

            hours = (db.highScoreLL.get(count) / 3600000); // total ms / (ms in 1 hour) == current hour
            minutes = (db.highScoreLL.get(count) / 60000) % 60; //current minute if not over 60
            seconds = (db.highScoreLL.get(count) / 1000) % 60; //current seconds if not over 60

            newScore = String.format("%02d:%02d:%02d", hours, minutes, seconds);

            tableModel.addRow(new Object[]{count+1, newScore});
        }
    }

    public static HighScore getInstance(){
        return highscore;
    }
}
