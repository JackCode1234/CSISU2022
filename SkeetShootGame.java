package SkeetShooter;


//Importing all necessary packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


//Beginning of game screen class
public class SkeetShootGame extends JPanel implements ActionListener {

	
	//Creating JFrame, fonts, buttons, labels, initializing random for 
	//start of game.  Creating variable that dictates the games state
	JFrame gameBackground;
	Random random = new Random();
	Font startFont = new Font("Bauhaus 93", Font.PLAIN, 65);
	Font startbtn = new Font("hooge 05_54", Font.PLAIN, 50);
	JButton play;
	JLabel start;
	boolean running = false;
	Timer t = new Timer(75, this);
	
	
	//Variables that are responsible for drawing the background
	int treex;
	int treey;
	int[] xPos = new int[20];
	int[] yPos = new int[20];
	int cloudx;
	int cloudy;
	int[] cloudPosx = new int[8];
	int[] cloudPosy = new int[8];
	boolean skeetOnly = true;

	
	// Objects and Variables for the skeet.  They are responsible
	//for drawing, movement, trajectory, speed...
	int discx = 0;
	int discy = 0;
	int disc2x = 0;
	int disc2y = 0;
	int movex = 1;
	int movey = 0;
	int discx2 = 0;
	int discy2 = 0;
	int disc2x2 = 0;
	int disc2y2 = 0;
	int movex2 = 1;
	int movey2 = 0;
	boolean delay = false;
	int discDelay = 400;
	int traj1 = 1;
	int traj2 = 1;
	int[] skeet1Traj = new int[4];
	int[] skeet2Traj = new int[4];
	boolean changePath1 = false;
	boolean changePath2 = false;
	int speedChange = 1;
	int speedChange2 = 1;

	
	// Variables for shooting at the skeets
	int points = 0;
	int numShots = 10;
	boolean shot = false;
	boolean shot2 = false;
	
	
	//Variables for creating font and displaying the
	//remaining shots and points
	Font score = new Font("Algerian", Font.PLAIN, 30);
	String textShots = " ";
	String textPoints = " ";

	
	//Fonts and variables for end of game
	boolean gameOver = false;
	Font endTitle = new Font("Bauhaus 93", Font.PLAIN, 80);
	Font highscore = new Font("hooge 05_54", Font.PLAIN, 30);
	Font endBody = new Font("hooge 05_54", Font.PLAIN, 20);
	
	
	//Variables used in the high score algorithm
	String tempName = " ";
	int tempInt = 0;
	String[] names = { "User 1", "User 2", "User 3", "User 4", "User 5", "User 6" };
	int[] scores = { 0, 0, 0, 0, 0, 0 };
	boolean nameEnter = false;
	String tempString = " ";

	
	//Creating and setting up buttons (fonts, variables for when clicked...)
	JButton cont;
	JButton end;
	Font finBtn = new Font("hooge 05_54", Font.PLAIN, 20);
	boolean userCont = false;

	
	//Constructor that is called when the instance of the class is created
	//in the title screen class.  Executes all code inside
	SkeetShootGame() {
		
		
		// Creating and setting up JFrame
		gameBackground = new JFrame("Skeet Shooter");
		gameBackground.setBounds(0, 0, 800, 800);
		gameBackground.setLayout(null);
		gameBackground.getContentPane().setBackground(new Color(32, 209, 245));
		gameBackground.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameBackground.setResizable(false);
		gameBackground.setLocationRelativeTo(null);

		
		// Setting up JPanel and adding it to JFrame
		this.setBounds(0, 0, 800, 800);
		gameBackground.add(this);
		gameBackground.setVisible(true);
		this.setFocusable(true);

		
		// Calling start game method and starting the timer
		startGame();
		t.start();

		
		// Starts the game when user clicks play button.
		//Sets variables to certain states and values.
		play.addActionListener(e -> {
			running = true;
			userStart();
			repaint();
			numShots = 10;
		});

		
		//Checks if skeet has been hit when mouse button is clicked
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				checkHits();
			}
		});

		
		//Restarts game by resetting variables making certain
		//buttons visible and invisible
		cont.addActionListener(e -> {
			userCont = true;
			cont.setVisible(false);
			end.setVisible(false);
			numShots = 10;
			points = 0;
			gameOver = false;
			nameEnter = false;
			t.restart();
			movex = 0;
			running = true;
			repaint();
		});

		
		// Closes game when clicked
		end.addActionListener(e -> {
			gameBackground.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gameBackground.dispose();
		});

	}

	
	//Method called from constructor that calls other methods
	public void startGame() {
		userStart();
		playAgain();

	}

	
	//Intro method that displays how to start game
	public void userStart() {
		
		
		// Creating and setting up instructions to start game
		if (running == false) {
			start = new JLabel("Click to Start");
			start.setVisible(true);
			start.setFont(startFont);
			this.setLayout(null);
			start.setBounds(200, 0, 600, 200);
			start.setForeground(Color.black);
			this.add(start);

			
			//Creating and setting up start game button
			play = new JButton("START");
			play.setVisible(true);
			play.setBounds(250, 200, 270, 75);
			play.setHorizontalAlignment(SwingConstants.CENTER);
			play.setVerticalAlignment(SwingConstants.CENTER);
			play.setFont(startbtn);
			play.setBackground(Color.orange);
			play.setForeground(Color.red);
			this.add(play);
		} else {
			
			
			//Sets button and title to invisible when game is started
			start.setVisible(false);
			play.setVisible(false);
		}
	}

	
	//Paint method that draws the background, skeets, displays scores...
	public void paintComponent(Graphics g) {

		
		//Referencing the superclass to obtain the paint component
		super.paintComponent(g);

		
		//Drawing sky
		g.setColor(new Color(32, 209, 245));
		g.fillRect(0, 0, 800, 600);

		
		// Drawing ground
		g.setColor(new Color(55, 148, 1));
		g.fillRect(0, 600, 800, 200);

		
		//Loop that draws and places all the trees in random locations
		//and creates trees of random sizes
		for (int i = 0; i < 20; i++) {
			if (skeetOnly) {
				treex = random.nextInt(800);
				treey = random.nextInt(40);

				xPos[i] = treex;
				yPos[i] = treey;

			}

			
			//Individual code for tree trunks
			g.setColor(new Color(135, 72, 5));
			g.fillRect(20 + xPos[i], 540 - yPos[i], 20, 60 + yPos[i]);

			
			//Individual code for leaves
			g.setColor(new Color(8, 115, 22));
			g.fillOval(0 + xPos[i], 530 - yPos[i], 30, 30);
			g.fillOval(15 + xPos[i], 540 - yPos[i], 30, 30);
			g.fillOval(30 + xPos[i], 530 - yPos[i], 30, 30);
			g.fillOval(2 + xPos[i], 510 - yPos[i], 30, 30);
			g.fillOval(28 + xPos[i], 510 - yPos[i], 30, 30);
			g.fillOval(15 + xPos[i], 500 - yPos[i], 30, 30);
			g.fillOval(15 + xPos[i], 530 - yPos[i], 30, 30);
		}

		
		// Loop that draws and places all the clouds in random locations
		for (int i = 0; i < 8; i++) {
			if (skeetOnly) {
				cloudx = random.nextInt(800);
				cloudy = random.nextInt(200);

				cloudPosx[i] = cloudx;
				cloudPosy[i] = cloudy;
			}

			// Individual code for clouds
			g.setColor(new Color(245, 242, 237));
			g.fillRect(20 + cloudPosx[i], 40 + cloudPosy[i], 60, 30);
			g.fillOval(20 + cloudPosx[i], 25 + cloudPosy[i], 30, 30);
			g.fillOval(60 + cloudPosx[i], 25 + cloudPosy[i], 30, 30);
			g.fillOval(10 + cloudPosx[i], 40 + cloudPosy[i], 30, 30);
			g.fillOval(70 + cloudPosx[i], 40 + cloudPosy[i], 30, 30);
			g.fillOval(40 + cloudPosx[i], 20 + cloudPosy[i], 30, 30);
		}
		
		
		//Variable that only draws the trees and clouds in random locations once
		//at the beginning of the game.  After that the trees and clouds remain
		//in the same positins for the rest of the game
		skeetOnly = false;

		
		//Draws fence posts on the left side of the screen
		Graphics2D f = (Graphics2D) g;
		f.setStroke(new BasicStroke(10));
		f.setColor(new Color(145, 100, 23));
		f.drawLine(118, 630, 118, 660);
		f.drawLine(149, 670, 149, 710);
		f.drawLine(180, 720, 180, 760);

		f.drawLine(93, 600, 93, 630);
		f.drawLine(65, 600, 65, 630);
		f.drawLine(35, 600, 35, 630);
		f.drawLine(5, 600, 5, 630);

		
		//Draws fence posts on the right side of the screen
		f.setColor(new Color(145, 100, 23));
		f.drawLine(682, 630, 682, 660);
		f.drawLine(651, 670, 651, 710);
		f.drawLine(620, 720, 620, 760);

		f.drawLine(707, 600, 707, 630);
		f.drawLine(735, 600, 735, 630);
		f.drawLine(765, 600, 765, 630);
		f.drawLine(795, 600, 795, 630);

		
		//Draws the railing for the fence
		f.setColor(new Color(173, 129, 55));
		f.drawLine(95, 600, 250, 800);
		f.drawLine(704, 601, 550, 800);

		f.drawLine(0, 600, 93, 600);
		f.drawLine(800, 600, 707, 600);

		
		// Drawing the first skeet
		if (running == true && shot == false) {
			g.setColor(Color.black);
			g.fillOval(discx - movex, discy - movey, 40, 40);
			g.setColor(new Color(255, 102, 0));
			g.fillOval(disc2x - movex, disc2y - movey, 33, 32);
		} else {
			discx = 400;
			discy = 200;
			disc2x = 404;
			disc2y = 204;
		}

		
		// Drawing the second skeet
		if (running == true && shot2 == false && delay == true) {
			g.setColor(Color.black);
			g.fillOval(discx2 + movex2, discy2 - movey2, 40, 40);
			g.setColor(new Color(255, 102, 0));
			g.fillOval(disc2x2 + movex2, disc2y2 - movey2, 33, 32);
		} else {
			
			discx2 = 0;
			discy2 = 200;
			disc2x2 = 4;
			disc2y2 = 204;
		}

		
		// Drawing crosshair that follows mouse around the screen
		//Drawing the number of remaining shots and points as well
		if (running) {

			Graphics2D g2 = (Graphics2D) g;
			
			int mouseX = (int) (MouseInfo.getPointerInfo().getLocation().getX() - 426);
			int mouseY = (int) (MouseInfo.getPointerInfo().getLocation().getY() - 75);
			int mouseX2 = (int) (MouseInfo.getPointerInfo().getLocation().getX() - 416);
			int mouseY2 = (int) (MouseInfo.getPointerInfo().getLocation().getY() - 65);

			g2.setColor(new Color(140, 0, 0));
			g2.setStroke(new BasicStroke(5));
			g2.drawOval(mouseX, mouseY, 40, 40);
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(3));
			g2.drawOval(mouseX2, mouseY2, 20, 20);
			g2.drawLine(mouseX - 6, mouseY + 20, mouseX2, mouseY2 + 10);
			g2.drawLine(mouseX + 32, mouseY + 20, mouseX2 + 36, mouseY2 + 10);
			g2.drawLine(mouseX + 20, mouseY - 5, mouseX2 + 10, mouseY2);
			g2.drawLine(mouseX + 20, mouseY + 45, mouseX2 + 10, mouseY2 + 20);

			
			// Displays the number of shots left
			textShots = "Shots: " + numShots;
			g.setColor(Color.black);
			g.setFont(score);
			g.drawString(textShots, 50, 50);

			
			// Displays the number of points
			textPoints = "Points: " + points;
			g.drawString(textPoints, 600, 50);
		}

		
		// End of game display
		if (running == false && gameOver == true && userCont == false) {
			
			
			// game over panel
			g.setColor(Color.black);
			g.fillRect(195, 50, 410, 465);
			g.setColor(Color.gray);
			g.fillRect(200, 55, 400, 455);

			
			// game over title
			g.setColor(Color.orange);
			g.setFont(endTitle);
			g.drawString("GAMEOVER", 205, 140);
			g.setColor(Color.red);
			g.drawString("GAMEOVER", 205, 145);

			
			// game over high scores title
			g.setFont(highscore);
			g.setColor(Color.orange);
			g.drawString("HIGHSCORES", 280, 255);
			g.setColor(Color.red);
			g.drawString("HIGHSCORES", 280, 258);

			
			// Loops that display names for high scores
			if (nameEnter) {
				g.setFont(endBody);
				g.setColor(Color.red);
				for (int i = 0; i < 5; i++) {
					g.drawString(names[i], 280, 300 + (i * 30));
				}

				//Loop that displays points for high scores
				for (int i = 0; i < 5; i++) {
					g.drawString(Integer.toString(scores[i]), 475, 300 + (i * 30));
				}
			}

		}

	}
	
	
	//Method that the skeets use to move
	public void move() {
		
		
		// Setting location of 1st skeet to right side of screen
		discx = 800;
		disc2x = 804;

		
		// Setting location of 2nd skeet to left side of screen
		discx2 = 0;
		disc2x2 = 4;

		
		// Different combinations of trajectories for skeet 1
		skeet1Traj[0] = (int) (((-0.001) * (Math.pow((movex - 400), 2))) + 190);
		skeet1Traj[1] = (int) (((-0.005) * (Math.pow((movex - 400), 2))) + 190);
		skeet1Traj[2] = (int) (((-0.002) * (Math.pow((movex - 368), 2))) + 50);
		skeet1Traj[3] = (int) (((-0.007) * (Math.pow((movex - 368), 2))) - 100);

		
		// Different combinations of trajectories for skeet 2
		skeet2Traj[0] = (int) (((-0.001) * (Math.pow((movex2 - 400), 2))) + 190);
		skeet2Traj[1] = (int) (((-0.005) * (Math.pow((movex2 - 400), 2))) + 190);
		skeet2Traj[2] = (int) (((-0.002) * (Math.pow((movex2 - 368), 2))) + 50);
		skeet2Traj[3] = (int) (((-0.007) * (Math.pow((movex2 - 368), 2))) - 100);

		
		// Randomizes trajectories for skeet 1
		if (changePath1 == true) {
			traj1 = random.nextInt(3);
			changePath1 = false;
		}

		
		// Randomizes  trajectories for skeet 2
		if (changePath2 == true) {
			traj2 = random.nextInt(3);
			changePath2 = false;
		}

		
		//Sets speed for the first skeet
		if (points > 250) {
			movex += (20 + speedChange + ((points / 100) * 2));
		} else {
			movex += (10 + speedChange);
		}

		movey = skeet1Traj[traj1];

		
		// Delay for the second disc
		if (movex > discDelay && movex < (discDelay + 100)) {
			delay = true;
		}

		
		//Sets speed for the second skeet
		if (delay == true) {
			if (points > 250) {
				movex2 += (20 + speedChange2 + ((points / 100) * 2));
			} else {
				movex2 += (10 + speedChange2);
			}

			movey2 = skeet2Traj[traj2];
		}

		
		// If Statement that resets the location of the 1st skeet as its shot
		if (movex > 790) {
			shot = false;
			movex = 1;
			speedChange = random.nextInt(10);
			changePath1 = true;
		}

		
		// If statement that resets the location of the 2nd skeet as its shot
		if (movex2 > 790) {
			shot2 = false;
			movex2 = 1;
			speedChange2 = random.nextInt(10);
			changePath2 = true;
			delay = false;
			discDelay = random.nextInt(800);
		}

	}

	
	// Method that determines if crosshair is close enough to target when shot fired.
	//Then either adding points or decreasing shots
	public void checkHits() {
		if (running) {
			
			double userX = MouseInfo.getPointerInfo().getLocation().getX() - 411;
			double userY = MouseInfo.getPointerInfo().getLocation().getY() - 60;

			
			//Creaing hitboxs for both skeets
			if (userX > ((discx - movex) - 100) && userX < ((discx - movex) + 100) && userY > ((discy - movey) - 100)
					&& userY < ((discy - movey) + 100)) {
				shot = true;
				points += 50;
			} else if (userX > ((discx2 + movex2) - 100) && userX < ((discx2 + movex2) + 100)
					&& userY > ((discy2 - movey2) - 100) && userY < ((discy2 - movey2) + 100)) {
				shot2 = true;
				points += 50;
			} else {
				numShots--;
			}
		}
	}

	
	// Method that stops game and resets certain variables when user is out of shots
	public void gameOver() {
		if (numShots < 1) {
			running = false;
			gameOver = true;
			userCont = false;
			movex = 0;
			movex2 = 0;
		}
	}

	
	// Method that receives users' initials
	public void dialogue() {
		tempName = JOptionPane.showInputDialog(null, "Enter your initials");
	}

	
	//High score algorithm that sorts and determines the top five
	public void sort() {
		
		
		//Sorts the previous top scores
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (scores[i] > scores[j]) {
					tempInt = scores[i];
					scores[i] = scores[j];
					scores[j] = tempInt;

					tempString = names[i];
					names[i] = names[j];
					names[j] = tempString;

				}
			}
		}

		
		//Checks if new score is higher than old and sorts accordingly
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (points >= scores[j]) {
					tempInt = scores[j];
					scores[j] = points;
					points = tempInt;

					tempString = names[j];
					names[j] = tempName;
					tempName = tempString;
				}
			}
		}
	}

	
	// Method that creates and displays gameOver buttons
	public void playAgain() {
		
		
		//Prompts the user to choose a button
		if (running == false && gameOver == false) {
			cont = new JButton("Play Again?");
			end = new JButton("Close");

			
			//Setting up play again button
			cont.setVisible(false);
			cont.setBounds(220, 450, 210, 50);
			cont.setBackground(new Color(255, 166, 0));
			cont.setForeground(Color.red);
			cont.setFont(finBtn);
			this.add(cont);

			
			//Setting up close game button
			end.setVisible(false);
			end.setBounds(450, 450, 130, 50);
			end.setBackground(new Color(255, 166, 0));
			end.setForeground(Color.red);
			end.setFont(finBtn);
			this.add(end);

			
			//Else that hides the buttons if game is not over
		} else if (running == false && gameOver == true && userCont == false) {
			cont.setVisible(true);
			end.setVisible(true);
		}
	}

	
	//Action listener that works with the timer to continually check certain
	//methods (move, gameOver, playAgain...).  It also continually redraws the
	//background, remaining shots, points, skeets...
	@Override
	public void actionPerformed(ActionEvent e) {

		if (running) {
			move();
			gameOver();
		}
		repaint();

		
		// If that calls dialogue method and determines when to sort high scores
		if (running == false && gameOver == true && nameEnter == false && userCont == false) {
			dialogue();
			sort();
			nameEnter = true;
		}

		
		//If that calls play again method, determining when to display end of game buttons
		if (running == false && gameOver == true && nameEnter == true) {
			playAgain();
		}

	}

}
