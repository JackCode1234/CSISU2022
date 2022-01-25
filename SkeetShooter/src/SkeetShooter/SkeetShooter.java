package SkeetShooter;

//Importing all necessary packages
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


//Beginning of Title Screen class
public class SkeetShooter {

	//Creating JFrame and JPanel and creating fonts 
	JFrame frame;
	Font titleFont = new Font ("Algerian", Font.BOLD, 80);
	Font startFont = new Font ("Algerian", Font.BOLD, 40);
	Font instructionsF = new Font("hooge 05_54", Font.PLAIN, 23);
	JPanel titlep;
	JPanel startGame;
	
	
	//Declaring height and width of the Title Screen window
	static int screenHeight = 800;
	static int screenWidth = 800;
	
	
	//Public Main class that creates an instance of this class as an object
	public static void main(String[] args) {
		SkeetShooter spaceGame = new SkeetShooter();
	}
	
	
	//Constructor for this class that executes all code inside when instance of object is created
	SkeetShooter() {
		
		
		//Setting up JFrame (size, color, layout...)
		frame = new JFrame("Skeet Shooter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, screenWidth, screenHeight);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(32, 209, 245));
		
		
		//Setting up title JPanel (size, color, layout...)
		titlep = new JPanel();
		titlep.setBorder(new LineBorder(Color.white));
		titlep.setBounds(85,100,635,100);
		titlep.setLayout(null);
		titlep.setBackground(Color.black);
		frame.add(titlep);
		
		
		//Creating and setting up title "Skeet Shooter"
		JLabel title = new JLabel("Skeet Shooter");
		title.setForeground(Color.red);
		title.setVerticalAlignment(1);
		title.setFont(titleFont);
		title.setBounds(0,0,800,800);
		titlep.add(title);
		
		
		//Setting up JPanel for the start button
		JPanel buttonp = new JPanel();
		buttonp.setBounds(300,240,200,75);
		buttonp.setLayout(null);
		frame.add(buttonp);
		
		
		//Creating and setting up start button
		JButton start = new JButton("Start");
		start.setBorder(new LineBorder(Color.white));
		start.setForeground(Color.red);
		start.setBackground(Color.black);
		start.setFont(startFont);
		start.setBounds(0,0,200,75);
		buttonp.add(start);
		
		
		//Creating and setting up instructions panel
		JPanel instruct = new JPanel();
		instruct.setBorder(new LineBorder(Color.white));
		instruct.setBackground(Color.gray);
		instruct.setBounds(25,350,750,350);
		instruct.setLayout(null);
		frame.add(instruct);
		
		
		//Creating and setting up first instructions label
		JLabel i1 = new JLabel("Instructions: Click to shoot the skeets as");
		i1.setForeground(Color.black);
		i1.setFont(instructionsF);
		i1.setBounds(20,40,800,50);
		instruct.add(i1);
		
		
		//Creating and setting up second instructions label
		JLabel i2 = new JLabel("they fly across the screen. Every skeet");
		i2.setForeground(Color.black);
		i2.setFont(instructionsF);
		i2.setBounds(30,100,800,50);
		instruct.add(i2);
		
		
		//Creating and setting up third instructions label
		JLabel i3 = new JLabel("you miss, decreases the remaining");
		i3.setForeground(Color.black);
		i3.setFont(instructionsF);
		i3.setBounds(90,160,800,50);
		instruct.add(i3);
		
		
		//Creating and setting up fourth instructions label
		JLabel i4 = new JLabel("shots you have. Keep playing to");
		i4.setForeground(Color.black);
		i4.setFont(instructionsF);
		i4.setBounds(100,220,800,50);
		instruct.add(i4);
		
		
		//Creating and setting up fifth instructions label
		JLabel i5 = new JLabel("obtain the highscore!");
		i5.setForeground(Color.black);
		i5.setFont(instructionsF);
		i5.setBounds(180,280,800,50);
		instruct.add(i5);
		
		
		//Displaying the JFrame with its contents (title, button, instructions...)
		frame.setVisible(true);
		
		
		//Action listener that calls second JFrame 
		//and closes this JFrmae when button clicked
		start.addActionListener(e -> {
			SkeetShootGame open = new SkeetShootGame();
			frame.dispose();
		});
		
	}
	
}
