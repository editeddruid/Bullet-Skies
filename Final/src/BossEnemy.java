import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BossEnemy extends Enemy
{
	private Rectangle2D.Double boss;
	private int dx, dy, health, width, height, tick, pattern;
	private ArrayList<Bullet> bullets;

	public BossEnemy(int x, int y, int health, int width, int height, int pattern)
	{
		super(x, y, health, width, height, pattern);
		boss = new Rectangle2D.Double(0,0,width,height);
		setSize(width + 1,height + 1);
		bullets = new ArrayList<Bullet>();
	}
	
	public void move()
	{
		
	}
	
	public ArrayList<Bullet> shoot()
	{
		if(tick % 20 == 0 && tick > 200)
		{
			if(bullets.size() == 0);
			{
				bullets.add(new Bullet(getX() + getWidth() - 15, getY() + getHeight(), 0, 5, 15, 40, true, Color.CYAN, 0));
				bullets.add(new Bullet(getX(), getY() + getHeight(), 0, 5, 15, 40, true, Color.CYAN, 0));
				bullets.add(new Bullet(getX() + (getWidth()/2), getY() + getHeight(), 3, 2, 20, 20, true, Color.RED, 2));
				bullets.add(new Bullet(getX() + (getWidth()/2) + (int) (Math.random() * (20)-10), getY() + getHeight(), 0, 4, 20, 30, true, Color.BLACK, 2));
				bullets.add(new Bullet(getX() + (getWidth()/2), getY() + getHeight(), -3, 2, 20, 20, true, Color.RED, 2));
			}
			return bullets;
		}
		else
			return null;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.CYAN);
		g2.fill(boss);
	}
	
	public void update()
	{
		tick ++;
		setLocation(getX() + dx, getY() + dy);
	}
}
