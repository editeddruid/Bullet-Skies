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
		pattern = 0;
	}
	
	public void move()
	{
		
	}
	
	public ArrayList<Bullet> shoot()
	{
		if(tick % 20 == 0 && tick > 200)
		{
			if(pattern == 0);
			{
				bullets.add(new Bullet(getX() + getWidth() - 15, getY() + getHeight(), 0, 5, 15, 40, true, Color.CYAN, 0));
				bullets.add(new Bullet(getX(), getY() + getHeight(), 0, 5, 15, 40, true, Color.CYAN, 0));
				bullets.add(new Bullet(getX() + (getWidth()/2), getY() + getHeight(), 3, 2, 20, 20, true, Color.RED, 2));
				bullets.add(new Bullet(getX() + (getWidth()/2) + (int) (Math.random() * (20)-10), getY() + getHeight(), 0, 4, 20, 30, true, Color.BLACK, 2));
				bullets.add(new Bullet(getX() + (getWidth()/2), getY() + getHeight(), -3, 2, 20, 20, true, Color.RED, 2));
				for(int p = 0; p < 50; p++)
				{
					bullets.add(new Bullet(getX() + (int) (Math.random() * getWidth()), getY() + getHeight(), (int) (Math.random() * 8) - 4, 
					(int) (Math.random() * 2) + 1, 8, 5, true, Color.ORANGE, (int) (Math.random() * 2)));
				}
			}
//			if(pattern == 1)
//			{
//				bullets.add(new Bullet(getX() + getWidth() - 15, getY() + getHeight(), 0, 5, 15, 40, true, Color.CYAN, 0));
//				bullets.add(new Bullet(getX(), getY() + getHeight(), 0, 5, 15, 40, true, Color.CYAN, 0));
//				for(int p = 0; p < 65; p++)
//				{
//					bullets.add(new Bullet(getX() + (int) (Math.random() * getWidth()), getY() + getHeight(), (int) (Math.random() * 8) - 4, 
//					(int) (Math.random() * 2) + 1, 8, 5, true, Color.ORANGE, (int) (Math.random() * 2)));
//				}
//			}
			return bullets;
		}
		else
			return null;
	}
	
	public int getHealth()
	{
		return health;
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
//		if(tick == 1000)
//		{
//			tick = 200;
//			pattern++;
//			System.out.println(pattern);
//		}
//		if(pattern == 2)
//		{
//			pattern = 0;
//		}
	}
}
