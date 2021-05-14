import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

/**
 * 
 * @author John D'Arcy
 *
 */
public class Bullet extends JComponent
{
	private Ellipse2D.Double bullet;
	private int dx, dy, size, damage;
	private Color color;
	private Boolean hostile;
	//Basic constructor
	public Bullet()
	{
		bullet = new Ellipse2D.Double(0, 0, 15, 15);
		setBounds(50, 50, 16, 16);
		dx = 0;
		dy = 2;
		color = Color.BLACK;
		damage = 1;
		hostile = true;
	}
	//More advanced constructor
	public Bullet(int x, int y, int dx, int dy, int size, int damage, boolean hostile, Color color)
	{
		bullet = new Ellipse2D.Double(0, 0, size, size);
		setBounds(x, y, size+1, size+1);
		this.dx = dx;
		this.dy = dy;
		this.color = color;
		this.damage = damage;
		this.hostile = hostile;
	}
	//Paint
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fill(bullet);
	}
	//Getters and setters for dx and dy
	public int getDx()
	{
		return dx;
	}
	public int getDy()
	{
		return dy;
	}
	public void setDx(int x)
	{
		dx = x;
	}
	public void setDy(int y)
	{
		dy = y;
	}
	//Getter for damage
	public int getDamage()
	{
		return damage;
	}
	//Getter to see if hostile
	public boolean getHostile()
	{
		return hostile;
	}
	//Update location
	public void update()
	{
		setLocation(getX() + dx, getY() + dy);
	}
}
