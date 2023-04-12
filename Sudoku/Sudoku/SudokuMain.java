package Sudoku.Sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Sudoku.Sudoku.bin.ExtraFeatures.DifficultySelectionScreen;

public class SudokuMain extends JFrame{
    
    private static final long serialVersionUID = 1L;
	JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel;
    JLabel titleNameLabel;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
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
        window.getContentPane().setBackground(Color.black);
        window.setLayout(new BorderLayout());
        window.setVisible(true);
        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100,100,600,150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("Sudoku");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
        
        startButtonPanel = new JPanel(); 
        startButtonPanel.setBounds(100,100,100,100);
        startButtonPanel.setBackground(Color.black);
        
        startButton = new JButton("Start!");
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.black);
        startButton.setFont(normalFont);
        
        
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        
        con.add(titleNamePanel, BorderLayout.CENTER);
        con.add(startButtonPanel,BorderLayout.SOUTH); 
        
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