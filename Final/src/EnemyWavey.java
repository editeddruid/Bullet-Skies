import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * 
 * @author Philip Melavila and John D'Arcy
 *
 */
public class EnemyWavey extends Enemy {
	
	//Fields
	private Rectangle2D.Double enemy;
	private int dx, dy, health, width, height, tick, pattern;//left = -1, right = 1
	public boolean upOrDown;
	private ArrayList<Bullet> bullets;
	
	//Constructor
	public EnemyWavey(int x, int y, int health, int width, int height, int pattern) {
		super(x,y,health,width,height,pattern);
		enemy = new Rectangle2D.Double(0,0, width, height);
		dx = 0;
		dy = 0;
		tick = 0;
		this.pattern = pattern; 
		this.health = health;
		bullets = new ArrayList<Bullet>();
		setLocation(x,y);
		setSize(width + 1,height + 1);
		upOrDown = true;
	}
	
	
	//Unique Methods (Different for every enemy)
	public void move() {
		if (tick == 0) {
			setDx(-1);
	}

	if (getX() < 0) {
		setLocation(0,getY());
		setDx(1);
	}
	if (getX() > 780 - getWidth()) {
		setLocation(780 - getWidth(),getY());
		setDx(-1);
		
	}
		
	}
	
	public ArrayList<Bullet> shoot() {
		if(tick % 25 == 0)
		{
			if(bullets.size() == 0)
				bullets.add(new Bullet(getX() + (width/2), getY() + height, 0, (int) (Math.random() * 4) + 1, 10, 10, true, Color.GREEN, 5));
			return bullets;
		}
		else
			return null;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.YELLOW);
		g2.fill(enemy);
	}
	
	
	//Getter Methods
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
	
	public int getHealth() {
		return health;
	}
	
	
	
	//Setter Methods
	public void setDx(int x) {
		dx = x;
	}
	
	public void setDy(int y) {
		dy = y;
	}
	
	public void setHealth(int damage) {
		health += damage;
	}
	
	public void update() {
		tick ++;
		setLocation(getX() + getDx(), getY() + getDy());
	}
	

}