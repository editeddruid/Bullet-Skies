import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

/**
 * 
 * @author John
 *
 */
public class Bullet extends JComponent
{
	private Ellipse2D.Double bullet;
	private int dx, dy, size, damage;
	private Color color;
	//Basic constructor
	public Bullet()
	{
		bullet = new Ellipse2D.Double(0, 0, 15, 15);
		setBounds(0, 0, 16, 16);
		dx = 0;
		dy = 0;
		color = Color.BLACK;
		damage = 1;
	}
	//More advanced constructor
	public Bullet(int x, int y, int dx, int dy, int size, int damage, Color color)
	{
		bullet = new Ellipse2D.Double(0, 0, size, size);
		setBounds(x, y, size+1, size+1);
		this.dx = dx;
		this.dy = dy;
		this.color = color;
		this.damage = damage;
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
	//Update location
	public void update()
	{
		setLocation(getX() + dx, getY() + dy);
	}
}
