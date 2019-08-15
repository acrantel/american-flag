// Flag starter kit

/*
 * Serena Li
 * Nuha Mozumder
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JApplet;

public class Flag extends JApplet {
	private final int STRIPES = 13;

	// SCALE FACTORS (A through L)
	//
	// Note: Constants in Java should always be ALL_CAPS, even
	// if we are using single letters to represent them
    //
    // NOTE 2: Do not delete or change the names of any of the
    // variables given here

	// Set the constants to exactly what is specified in the documentation
	private final double A = 1.0;  // Hoist (width) of flag
	private final double B = 1.9;  // Fly (length) of flag
	private final double C = 7.0/STRIPES;  // Hoist of Union
	private final double D = .76;  // Fly of Union
	private final double E = .054;  // See flag specification
	private final double F = .054;  // See flag specification
	private final double G = .063;  // See flag specification
	private final double H = .063;  // See flag specification
	private final double K = .0616;  // Diameter of star
	private final double L = 1.0/13;  // Width of stripe

    // You will need to set values for these in paint()
	private double flag_width;      // width of flag in pixels
	private double flag_height;     // height of flag in pixels
	private double stripe_height;   // height of an individual stripe in pixels

    // init() will automatically be called when an applet is run
	public void init() {
		// Choice of width = 1.9 * height to start off
		// 760 : 400 is ratio of FLY : HOIST
		setSize(760, 400);
		repaint();
	}

    // paint() will be called every time a resizing of an applet occurs
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (this.getHeight() / A > this.getWidth() / B)
		{
			this.flag_width = this.getWidth();
			this.flag_height = this.getWidth() * (A / B);
		}
		else
		{
			this.flag_height = this.getHeight();
			this.flag_width = this.getHeight() * (B / A);
		}
		this.stripe_height = this.flag_height / STRIPES;
		
		this.drawBackground(g2);
		this.drawStripes(g2);
		this.drawField(g2);
		this.drawStars(g2);
	}

	private void drawBackground(Graphics2D g2) {
		g2.fillRect(0, 0, (int) (this.getWidth()), (int) (this.getHeight()));
	}
	
	public void drawStripes(Graphics2D g2) {
		for (int i = 0; i < STRIPES; i++)
		{
			if (i % 2 == 0) {
				g2.setColor(Color.RED);
			} else {
				g2.setColor(Color.WHITE);
			}
			g2.fillRect(0, (int) (i*this.stripe_height), (int) (this.flag_width), (int) (this.stripe_height)+1);
		}
	}

	public void drawField(Graphics2D g2) {
		g2.setColor(Color.BLUE);
		int width = (int) (this.flag_width * (D / B));
		int height = (int) (this.flag_height * (C / A));
		g2.fillRect(0, 0, width, height);
	}

	public void drawStars(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		
		for (int i = 0; i < 9; i++)
		{
			int xStart;
			int starsInRow;
			if (i % 2 == 0) {
				xStart = (int) (this.flag_width * (G / B));
				starsInRow = 6;
			} else
			{
				xStart = (int) (this.flag_width * (G+H) / B);
				starsInRow = 5;
			}
			for (int j = 0; j < starsInRow; j++)
			{ 
				double x = xStart + 2*j*(H/B) *flag_width;
				double y = (E/A) *flag_height + i*(F/A)*flag_height;
				draw5PointStar(g2, x, y);
			}
		}
	}
	public void draw5PointStar (Graphics2D g2, double x, double y) {
		int[] xPoints = new int[10];
		int[] yPoints = new int[10];
		double starDiam = (K/B) * flag_width;
		xPoints[0] = (int) (x + starDiam*Math.cos(Math.toRadians(18))/2);
		xPoints[1] = (int) (x + Math.cos(Math.toRadians(18)) * 
				(starDiam * Math.sin(Math.toRadians(18))/(2*Math.sin(Math.toRadians(54)))));
		xPoints[2] = (int) (x + starDiam * Math.cos(Math.toRadians(54))/2);
		xPoints[3] = (int) (x);
		xPoints[4] = (int) (x - starDiam * Math.cos(Math.toRadians(54))/2);
		xPoints[5] = (int) (x - Math.cos(Math.toRadians(18)) * 
				(starDiam * Math.sin(Math.toRadians(18))/(2*Math.sin(Math.toRadians(54)))));
		xPoints[6] = (int) (x - starDiam*Math.cos(Math.toRadians(18))/2);
		xPoints[7] = (int) (x - Math.sin(Math.toRadians(36))*starDiam * Math.sin(Math.toRadians(18))/(2*Math.sin(Math.toRadians(54))));
		xPoints[8] = (int) (x);
		xPoints[9] = (int) (x + Math.sin(Math.toRadians(36))*starDiam * Math.sin(Math.toRadians(18))/(2*Math.sin(Math.toRadians(54))));
		
		yPoints[0] = (int) (y - starDiam*Math.sin(Math.toRadians(18))/2);
		yPoints[1] = (int)(y + Math.sin(Math.toRadians(18)) * starDiam * Math.sin(Math.toRadians(18))/(2*Math.sin(Math.toRadians(54))));
		yPoints[2] = (int)(y + starDiam* Math.sin(Math.toRadians(54))/2);
		yPoints[3] = (int)(y + starDiam * Math.sin(Math.toRadians(18))/(2*Math.sin(Math.toRadians(54))));
		yPoints[4] = yPoints[2];
		yPoints[5] = yPoints[1];
		yPoints[6] = yPoints[0];
		yPoints[7] = (int)(y - starDiam*Math.sin(Math.toRadians(18))/2);
		yPoints[8] = (int) (y - starDiam/2);
		yPoints[9] = yPoints[7];
		
		g2.fillPolygon(xPoints, yPoints, xPoints.length);
	}
}
 