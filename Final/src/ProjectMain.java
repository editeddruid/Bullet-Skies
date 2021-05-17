/**
 * @author John D'Arcy
 */

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class ProjectMain extends JFrame implements ActionListener
{
	//Creating Fields
	private Player player;
	private Timer t;
	private ArrayList<Bullet> bullets, playerBullets;
	private ArrayList<Enemy> enemies;
	private ArrayList<String> inputs;
	private int health, tick; //For if we decide to do multiple levels and want to transfer over health
	//TODO add a previous dx and dy so that the stopping thing doesn't happen
	private JLabel remainingClears, remainingHealth;
	public ProjectMain()
    {
        //Basic initialization
        setTitle("Placeholder Title");
        setBounds(100, 100, 800, 1000);
        setLayout(null);
        setResizable(false);
        
        //Creating the Bullet ArrayList
        //Adding an example bullet to make sure bullet stuff works
        bullets = new ArrayList<Bullet>();
        playerBullets = new ArrayList<Bullet>();
        bullets.add(new Bullet());
        for(Bullet b : bullets)
        {
        	add(b);
        }
        //Adding an example enemy and enemy array list
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(200,200,100,20,20,0));
        for(Enemy e : enemies)
        {
        	add(e);
        }
        //Adding the inputs array
        inputs = new ArrayList<String>();
        //Creating the player character
        health = 100;
        player = new Player(400, 400, health); //Location is just a placeholder
        add(player);
        //Creating the remainingClears label
        remainingClears = new JLabel("Remaining Clears: " + player.getScreenClears());
        remainingClears.setBounds(100, 20, 150, 50);
        remainingHealth = new JLabel("Health: " + player.getHealth());
        remainingHealth.setBounds(500, 20, 150, 50);
        add(remainingClears);
        add(remainingHealth);
        //Adding the Timer
        t = new Timer(10, this);
        t.start();
        //Ticks
        tick = 0;
        //More initialization
        setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Adding a key listener
		addKeyListener(new KeyListener() 
		{
			@Override
			public void keyTyped(KeyEvent e) 
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) 
			{
				//Movement
				if(e.getKeyCode() == KeyEvent.VK_W)
				{
//					player.setDy(-4);
					if(!inputs.contains("W"))
						inputs.add("W");
				}
				if(e.getKeyCode() == KeyEvent.VK_S)
				{
//					player.setDy(4);
					if(!inputs.contains("S"))
						inputs.add("S");
				}
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
//					player.setDx(-4);
					if(!inputs.contains("A"))
						inputs.add("A");
				}
				if(e.getKeyCode() == KeyEvent.VK_D)
				{
//					player.setDx(4);
					if(!inputs.contains("D"))
						inputs.add("D");
				}
				//TODO add it so that pressing space fires
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
				{
//					Bullet fired = new Bullet(player.getX() + 1, player.getY(), 0, -8, 2, 10, false, Color.BLUE);
//					playerBullets.add(fired);
//					add(fired);
					if(!inputs.contains("Space"))
						inputs.add("Space");
				} //TODO add a way to queue inputs so that you can fire while moving
			}
			@Override
			public void keyReleased(KeyEvent e) 
			{
				//Stopping Movement
				if(e.getKeyCode() == KeyEvent.VK_W)
				{
					player.setDy(0);
					inputs.remove("W");
				}
				if(e.getKeyCode() == KeyEvent.VK_S)
				{
					player.setDy(0);
					inputs.remove("S");
				}
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
					player.setDx(0);
					inputs.remove("A");
				}
				if(e.getKeyCode() == KeyEvent.VK_D)
				{
					player.setDx(0);
					inputs.remove("D");
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					inputs.remove("Space");
				}
				
				//Uses a screen clear that removes all bullets onscreen. Only works if a screen clear is available.
				if(e.getKeyCode() == KeyEvent.VK_SHIFT)
				{
					if(player.getScreenClears() > 0)
					{
						player.useScreenClear();
						for(int i = 0; i < bullets.size(); i++)
						{
							if(bullets.get(i).getHostile())
							{
								remove(bullets.get(i));
								bullets.remove(i);
								i--;
							}
						}
						remainingClears.setText("Remaining Clears: " + player.getScreenClears());
					}
				}
			}
			
		});

    }
    public static void main(String[] args) 
    {
        new ProjectMain();
    }
    @Override
	public void actionPerformed(ActionEvent e) 
	{
    	tick ++;
    	//Iterating over inputs
    	for(String i : inputs)
    	{
    		if(i == "W")
    		{
    			player.setDy(-4);
    		}
    		if(i == "S")
    		{
    			player.setDy(4);
    		}
    		if(i == "A")
    		{
    			player.setDx(-4);
    		}
    		if(i == "D")
    		{
    			player.setDx(4);
    		}
    		if(i == "Space")
    		{
    			if(tick % 10 == 0)
    			{
    				Bullet fired = new Bullet(player.getX() + 1, player.getY(), 0, -8, 2, 10, false, Color.BLUE);
    				playerBullets.add(fired);
    				add(fired);
    			}
    		}
    	}
    	//Updating the player's location
		player.update();
		//Updating the enemies
		for(int enem = 0; enem < enemies.size(); enem ++)
		{
			//Checking to see if the enemy is dead
			if(enemies.get(enem).getHealth() <= 0)
			{
				remove(enemies.get(enem));
				enemies.remove(enem);
				enem --;
				continue;
			}
			//Moving and shooting
			enemies.get(enem).move();
			enemies.get(enem).update();
			Bullet bull = enemies.get(enem).shoot();
			if(bull != null)
			{
				bullets.add(bull);
				add(bull);
			}
		}
		//Updating the bullets
		for(int b = 0; b < bullets.size(); b++)
		{
			bullets.get(b).update();
			Rectangle r1 = new Rectangle(bullets.get(b).getX(), bullets.get(b).getY(), 
			bullets.get(b).getWidth(), bullets.get(b).getHeight());
			Rectangle r2 = new Rectangle(player.getX(), player.getY(), 
			player.getWidth(), player.getHeight());
			if(r1.intersects(r2))
			{
				player.setHealth(bullets.get(b).getDamage() * -1);
				remainingHealth.setText("Health: " + player.getHealth());
				remove(bullets.get(b));
				bullets.remove(b);
				b --;
			}
		}
		for(int i = 0; i < playerBullets.size(); i++)
		{
			playerBullets.get(i).update();
			for(int enem = 0; enem < enemies.size(); enem ++)
			{
				Rectangle r1 = new Rectangle(playerBullets.get(i).getX(), playerBullets.get(i).getY(), 
				playerBullets.get(i).getWidth(), playerBullets.get(i).getHeight());
				Rectangle r2 = new Rectangle(enemies.get(enem).getX(), enemies.get(enem).getY(), 
				enemies.get(enem).getWidth(), enemies.get(enem).getHeight());
				if(r1.intersects(r2))
				{
					System.out.print("Collision");
					enemies.get(enem).setHealth(playerBullets.get(i).getDamage() * -1);
					remove(playerBullets.get(i));
					playerBullets.remove(i);
					i--;
				}
			}
		} //TODO fix whatever is wrong with this code
		for(int i = 0; i < playerBullets.size(); i++)
		{
			//Removing bullets that are off the screen
			if(playerBullets.get(i).getY() < 0)
			{
				remove(playerBullets.get(i));
				playerBullets.remove(i);
				i--;
			}
		}
		
		repaint();
		
		//Keeping the player within bounds
		if(player.getX() < 0)
		{
			player.setLocation(0, player.getY());
		}
		if(player.getX() > 782)
		{
			player.setLocation(782, player.getY());
		}
		if(player.getY() < 0)
		{
			player.setLocation(player.getX(), 0);
		}
		if(player.getY() > 960)
		{
			player.setLocation(player.getX(), 960);
		}
	}
}