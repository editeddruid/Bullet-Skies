import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * 
 * @author Philip Melavila
 *
 */
public class Enemy extends JComponent {
	
	//Fields
	private Rectangle2D.Double enemy;
	private int dx, dy, health, width, height;
	
	//Constructor
	public Enemy(int x, int y, int health, int width, int height) {
		enemy = new Rectangle2D.Double(0,0,width,height);
		setSize(width + 1,height + 1);
		dx = 0;
		dy = 0;
		this.health = health;
		setLocation(x,y);
		
	}
	
	
	//Abstract Methods (Different for every enemy)
	public void move() {
		
	}
	
	public void shoot() {
		
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.RED);
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
		setLocation(getX() + dx, getY() + dy);
	}
	

}