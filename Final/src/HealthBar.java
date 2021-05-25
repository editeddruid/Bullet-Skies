/**
 * @author John D'Arcy
 * Image code from Brendan Cashman
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class HealthBar extends JComponent
{
	private Rectangle2D.Double bar;
	private int health;
	private Color color;
	
	public HealthBar(int x, int y, int health, Color color)
	{
		bar = new Rectangle2D.Double(0, 0, 150, 20);
		setSize(151, 21);
		this.health = health;
		this.color = color;
		setLocation(x, y);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(color);
		g2.fill(bar);
	}
	
	public void makeSmaller(int amount)
	{
		setSize(amount, 21);
	}
}
