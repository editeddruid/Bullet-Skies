import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * 
 * @author John D'Arcy
 * 
 * Image code from Brendan Cashman
 *
 */

public class Player extends JComponent
{
	//Fields
	private Rectangle2D.Double player;
	private int dx, dy, health, screenClears;
	private BufferedImage image;
	//Constructor
	public Player(int x, int y, int health)
	{
		player = new Rectangle2D.Double(0, 0, 6, 6); //This isn't really necessary but it's a pain to remove
		setSize(7, 7); //Player is 6x6
		dx = 0;
		dy = 0;
		this.health = health;
		screenClears = 5;
		try {                
	          image = ImageIO.read(new File("art\\Player.png")); //Sets the player to the player image
	       } catch (IOException ex) {
	            System.out.println("ERROR");
	       }
		setLocation(x,y);
	}
	//Painting the Component
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, 0, 0, this); 
	}
	//Setting dx and dy
	public void setDx(int x)
	{
		dx = x;
	}
	public void setDy(int y)
	{
		dy = y;
	}
	//Getting dx and dy
	public int getDx()
	{
		return dx;
	}
	public int getDy()
	{
		return dy;
	}
	//Setting health
	public void setHealth(int h)
	{
		health += h;
	}
	//Getting health
	public int getHealth()
	{
		return health;
	}
	
	//Using a screen clear
	public int getScreenClears()
	{
		return screenClears;
	}
	public void setScreenClear(int i)
	{
		screenClears = i;
	}
	//Updating coordinates
	public void update()
	{
		this.setLocation(getX() + dx, getY() + dy);
	}
}