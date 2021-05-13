import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 * 
 * @author John D'Arcy
 *
 */

public class Player extends JComponent
{
	//Fields
	private Rectangle2D.Double player;
	private int dx, dy, health, screenClears;
	//Constructor
	public Player(int x, int y, int health)
	{
		player = new Rectangle2D.Double(0, 0, 4, 4);
		setSize(5, 5);
		dx = 0;
		dy = 0;
		this.health = health;
		screenClears = 3;
		setLocation(x,y);
	}
	//Painting the Component
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.BLACK);
		g2.fill(player);
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
	//Setting health
	public void setHealth(int h)
	{
		health += h;
	}
	
	//Using a screen clear
	public int getScreenClears()
	{
		return screenClears;
	}
	public void useScreenClear()
	{
		screenClears --;
	}
	//Updating coordinates
	public void update()
	{
		this.setLocation(getX() + dx, getY() + dy);
	}
}