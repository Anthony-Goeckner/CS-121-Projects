import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * CS 121 Project 1: Traffic Animation
 *
 * Animates a car driving along a road
 *
 * @author BSU CS 121 Instructors
 * @author anthonygoeckner
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel
{
	// This is where you declare constants and variables that need to keep their
	// values between calls	to paintComponent(). Any other variables should be
	// declared locally, in the method where they are used.

	/**
	 * A constant to regulate the frequency of Timer events.
	 * Note: 100ms is 10 frames per second - you should not need
	 * a faster refresh rate than this
	 */
	private final int DELAY = 100; //milliseconds

	/**
	 * The anchor coordinate for drawing / animating. All of your vehicle's
	 * coordinates should be relative to this offset value.
	 */
	private int xOffset = 0;

	/**
	 * The number of pixels added to xOffset each time paintComponent() is called.
	 */
	private int stepSize = 10;

	private final Color BACKGROUND_COLOR = Color.blue;

	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		// Get the current width and height of the window.
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height

		// Fill the graphics page with the background color.
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);

		// Calculate the new xOffset position of the moving object.
		xOffset  = (xOffset + stepSize) % width;

		// TODO: Use width, height, and xOffset to draw your scalable objects
		// at their new positions on the screen
		Color grassGreen = new Color (126, 200, 80);
		g.setColor(grassGreen);
		g.fillRect(0, height/2, width, height/2);

		// variable for sidewalk offset
		int sidewalkWidth = width / 6;
		g.setColor(Color.GRAY);
		// centers sidewalk in center of screen
		g.fillRect(width / 2 - sidewalkWidth / 2, height / 2, sidewalkWidth, height / 2);

		// draw building in background
		int buildingWidth = width / 6;
		int buildingHeight = height / 2;
		g.fillRect(0, 0, buildingWidth, buildingHeight);
		g.setColor(Color.cyan);
		g.fillRect(5, 5, buildingWidth - 10, buildingHeight - 10);
		g.setColor(Color.GRAY);
		g.fillRect((width / 12) - buildingWidth / 8, 0, buildingWidth / 4, buildingHeight);
		g.fillRect(0, buildingHeight / 5, buildingWidth, buildingHeight / 10);
		g.fillRect(0, (int) (2.5 * buildingHeight) / 5, buildingWidth, buildingHeight / 10);
		g.fillRect(0, 4 * buildingHeight / 5, buildingWidth, buildingHeight / 10);

		// Draw road
		g.fillRect(0, height / 2, width, height / 5);

		// Draws car
		int yCenter = height / 2;
		g.setColor(Color.DARK_GRAY);
		
		int[] xArray = {xOffset, xOffset + (width / 5), xOffset + (width * 7 / 20), 
						xOffset + (width * 7 / 20), xOffset + (width * 37 / 120),
						xOffset + (width * 3 / 10), xOffset + (width / 4), 
						xOffset + (width * 29 / 120), xOffset + (width / 8),
						xOffset + (width * 7 / 60), xOffset + (width / 15), 
						xOffset + (width * 7 / 120), xOffset + (width / 60)};
		int[] yArray = {yCenter, yCenter - (height / 16), yCenter + (width * 3 / 80), 
						yCenter + (width / 10), yCenter + (width / 10), yCenter + (width / 20), 
						yCenter + (width * 17 / 400), yCenter + (width / 10), yCenter + (width / 10), 
						yCenter + (width / 20), yCenter + (width * 17 / 400), 
						yCenter + (width / 10), yCenter + (width / 10)};
		g.fillPolygon(xArray, yArray, 13);
		
		// draws tires
		int tireSize = (int) (xArray[8] - xArray[11]) - 5;
		int rearTireX = xArray[11] + 2;
		int rearTireY = yArray[10] + 2;
		g.fillOval(rearTireX, rearTireY, tireSize, tireSize);

		int frontTireX = xArray[7] + 2;
		int frontTireY = yArray[6] + 2;
		g.fillOval(frontTireX, frontTireY, tireSize, tireSize);

		// avatar
		int headCenterX = width / 2;
		int headCenterY = height * 5 / 6;
		int headRadius = width / 24;
		int bodyLength = headRadius * 3;
		g.setColor(Color.BLACK);
		g.drawLine(headCenterX, headCenterY, headCenterX, headCenterY + bodyLength);

		g.drawLine(headCenterX, headCenterY + bodyLength / 2, headCenterX + headRadius, headCenterY + bodyLength / 2 + headRadius);
		g.drawLine(headCenterX, headCenterY + bodyLength / 2, headCenterX - headRadius, headCenterY + bodyLength / 2 + headRadius);

		g.setColor(Color.WHITE);
		g.fillOval(headCenterX - headRadius, headCenterY - headRadius, headRadius * 2, headRadius * 2);
		
		g.setColor(Color.BLUE);
		g.fillRect(headCenterX - headRadius / 2, headCenterY + (int) (headRadius * 1.5), headRadius, headRadius * 2);

		// text
		String msg = "I don't like the city";
		g.setColor(Color.BLACK);
		g.drawString(msg, width / 4, height / 30);

		// Put your code above this line. This makes the drawing smoother.
		Toolkit.getDefaultToolkit().sync();
	}


	//==============================================================
	// You don't need to modify anything beyond this point.
	//==============================================================


	/**
	 * Starting point for this program. Your code will not go in the
	 * main method for this program. It will go in the paintComponent
	 * method above.
	 *
	 * DO NOT MODIFY this method!
	 *
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		// DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins. This method also
	 * sets up a Timer that will call paint() with frequency specified by
	 * the DELAY constant.
	 */
	public TrafficAnimation()
	{
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 600;
		int initHeight = 400;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically.
	 * DO NOT MODIFY this method!
	 */
	private void startAnimation()
	{
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	/**
	 * Repaints the graphics panel every time the timer fires.
	 * DO NOT MODIFY this class!
	 */
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}
