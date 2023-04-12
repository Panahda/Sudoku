package Sudoku;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Sudoku.bin.GameLogic.DifficultySelectionScreen;
import Sudoku.bin.Utility.*;

public class SudokuMain extends JFrame{
    
    private static final long serialVersionUID = 1L;
	JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel;
    JLabel titleNameLabel;

    Font sudokuTitle = Fonts.loadFont(200);
    Font normalFont = new Font("Serif", Font.PLAIN, 30);
    JButton startButton;
    
    public static void main(String[] args){
    	SwingUtilities.invokeLater(new Runnable(){
    	public void run() {
        new SudokuMain();
    	}
    	});
    }

    public SudokuMain(){
        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.white);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100,100,100,150);
        titleNamePanel.setBackground(Colours.BG_TO_GUESS);

        titleNameLabel = new JLabel("Sudoku");
        titleNameLabel.setForeground(Colours.FG_GIVEN);
        titleNameLabel.setFont(sudokuTitle);
        
        startButtonPanel = new JPanel(); 
        startButtonPanel.setBounds(100,100,100,100);
        startButtonPanel.setBackground(Colours.BG_TO_GUESS);
        
        startButton = new JButton("Start!");
        startButton.setBackground(Colours.white_yellow);
        startButton.setForeground(Colours.FG_GIVEN);
        startButton.setFont(normalFont);
        
        
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        
        con.add(titleNamePanel, BorderLayout.NORTH);
        con.add(startButtonPanel,BorderLayout.CENTER); 
        
        StartBtnInputListener listener = new StartBtnInputListener();
        startButton.addActionListener(listener);
    }
        private class StartBtnInputListener implements ActionListener {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		new DifficultySelectionScreen();
        		window.dispose();
        	}
        }

}