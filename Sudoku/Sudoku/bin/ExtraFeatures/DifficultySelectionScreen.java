package Sudoku.Sudoku.bin.ExtraFeatures;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Sudoku.Sudoku.bin.SudokuGame;

public class DifficultySelectionScreen extends JFrame{
    
    private static final long serialVersionUID = 1L;
	JFrame window;
    Container con;
    JPanel titleNamePanel, diffButtonPanel;
    JLabel titleNameLabel, diffLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font diffFont = new Font("Times New Roman", Font.PLAIN, 60);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    JButton easyButton, intermediateButton,hardButton;
    


    public DifficultySelectionScreen(){
        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100,100,600,150);
        titleNamePanel.setBackground(Color.black);
        
        titleNameLabel = new JLabel("  Sudoku  ");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
        diffLabel = new JLabel("  Select Difficulty  ");
        diffLabel.setForeground(Color.white);
        diffLabel.setFont(diffFont);
        
        diffButtonPanel = new JPanel(); 
        diffButtonPanel.setLayout(new GridLayout(3, 1, 0, 20));
        diffButtonPanel.setBackground(Color.black);
        
        easyButton = new JButton("Easy");
        easyButton.setBackground(Color.white);
        easyButton.setForeground(Color.black);
        easyButton.setFont(normalFont);

        intermediateButton = new JButton("Intermediate");
        intermediateButton.setBackground(Color.white);
        intermediateButton.setForeground(Color.black);
        intermediateButton.setFont(normalFont);
        
        hardButton = new JButton("Difficult");
        hardButton.setBackground(Color.white);
        hardButton.setForeground(Color.black);
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

