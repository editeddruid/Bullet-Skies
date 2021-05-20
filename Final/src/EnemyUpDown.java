import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * 
 * @author John D'Arcy
 *
 */
public class EnemyUpDown extends Enemy
{
	private Rectangle2D.Double enemy;
	private int dx, dy, health, width, height, tick, pattern;
	private ArrayList<Bullet> bullets;

	public EnemyUpDown(int x, int y, int health, int width, int height, int pattern) 
	{
		super(x, y, health, width, height, pattern);
		enemy = new Rectangle2D.Double(0,0,width,height);
		setSize(width + 1,height + 1);
		bullets = new ArrayList<Bullet>();
		pattern = 0;
	}
	//Unique Methods
	public void move()
	{
//		if (pattern == 0) {
//			if (tick == 0) {
//					setDy(-2);
//			}
//
//			if (getY() < 200) {
//				setLocation(getX(),200);
//				setDy(2);
//			}
//			if (getY() > 980 - height) {
//				setLocation(getX(),980 - height);
//				setDy(-2);
//				
//			}
//				
//		}
	}
	
	public ArrayList<Bullet> shoot() 
	{
		if(tick % 100 == 0)
		{
			if(bullets.size() == 0)
			{
				bullets.add(new Bullet(getX() + (width), getY() + (height/2), (int) (Math.random() * 2) + 1, 0, 10, 10, true, Color.MAGENTA, 0));
			}
			return bullets;
		}
		else
			return null;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.RED);
		g2.fill(enemy);
	}
	
	public void update() {
		tick ++;
		setLocation(getX() + dx, getY() + dy);
		if(tick == 1000)
		{
			setHealth(-20);
		}
	}
}
