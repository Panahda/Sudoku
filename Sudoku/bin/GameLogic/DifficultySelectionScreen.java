package Sudoku.bin.GameLogic;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Sudoku.bin.Utility.*;

public class DifficultySelectionScreen extends JFrame{
    
    private static final long serialVersionUID = 1L;
	JFrame window;
    Container con;
    JPanel titleNamePanel, diffButtonPanel;
    JLabel titleNameLabel, diffLabel;

    //Fonts
    Font sudokuTitle = Fonts.loadFont(200);
    Font normalFont = new Font("Serif", Font.PLAIN, 30);
    Font diffFont = Fonts.loadFont(60);


    JButton easyButton, intermediateButton,hardButton;
    


    public DifficultySelectionScreen(){
        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
        con = window.getContentPane();

        //title Initialisation
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100,100,600,150);
        titleNamePanel.setBackground(Colours.FG_GIVEN);
        
        titleNameLabel = new JLabel("  Sudoku  ");
        titleNameLabel.setForeground(Colours.BG_CORRECT_GUESS);
        titleNameLabel.setFont(sudokuTitle);

        diffLabel = new JLabel("  Select Difficulty  ");
        diffLabel.setForeground(Colours.white_yellow);
        diffLabel.setFont(diffFont);
        
        //buttons initialisation
        diffButtonPanel = new JPanel(); 
        diffButtonPanel.setLayout(new GridLayout(3, 1, 0, 20));
        diffButtonPanel.setBackground(Colours.FG_GIVEN);
        
        easyButton = new JButton("Easy");
        easyButton.setBackground(Colours.white_yellow);
        easyButton.setForeground(Colours.FG_GIVEN);
        easyButton.setFont(normalFont);

        intermediateButton = new JButton("Intermediate");
        intermediateButton.setBackground(Colours.white_yellow);
        intermediateButton.setForeground(Colours.FG_GIVEN);
        intermediateButton.setFont(normalFont);
        
        hardButton = new JButton("Difficult");
        hardButton.setBackground(Colours.white_yellow);
        hardButton.setForeground(Colours.FG_GIVEN);
        hardButton.setFont(normalFont);
        
        titleNamePanel.add(titleNameLabel, BorderLayout.NORTH);
        titleNamePanel.add(diffLabel, BorderLayout.CENTER);
        diffButtonPanel.add(easyButton);
        diffButtonPanel.add(intermediateButton);
        diffButtonPanel.add(hardButton);
        
        con.add(titleNamePanel, BorderLayout.CENTER);
        con.add(diffButtonPanel,BorderLayout.SOUTH); 
        
        EasyBtnInputListener easyListener = new EasyBtnInputListener();
        easyButton.addActionListener(easyListener);
        
        IntermediateBtnInputListener intListener = new IntermediateBtnInputListener();
        intermediateButton.addActionListener(intListener);
        
        HardBtnInputListener hardListener = new HardBtnInputListener();
        hardButton.addActionListener(hardListener);
    }
        private class EasyBtnInputListener implements ActionListener {
        	public void actionPerformed(ActionEvent evt) {
        		new SudokuGame(0);
        		window.dispose();
        	}
        }
        
        private class IntermediateBtnInputListener implements ActionListener {
        	public void actionPerformed(ActionEvent evt) {
        		new SudokuGame(1);
        		window.dispose();
        	}
        }
        
        private class HardBtnInputListener implements ActionListener {
        	public void actionPerformed(ActionEvent evt) {
        		new SudokuGame(2);
        		window.dispose();
        	}
        }

}

