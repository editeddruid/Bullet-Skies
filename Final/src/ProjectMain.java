/**
 * @author John D'Arcy and Philip Melavila
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ProjectMain extends JFrame implements ActionListener
{
	//Creating Fields
	private Player player;
	private Timer t;
	private ArrayList<Bullet> bullets, playerBullets, newBullets;
	private ArrayList<Enemy> enemies;
	private ArrayList<String> inputs;
	private WaveManager manager;
	private int health, tick, currentWave; //For if we decide to do multiple levels and want to transfer over health
	private JLabel remainingClears, remainingHealth;
	private HealthBar playerHealth;
	private Background background;
	public ProjectMain()
    {
        //Basic initialization
        setTitle("Placeholder Title");
        setBounds(100, 100, 800, 1000);
        setLayout(null);
        setResizable(false);
        //Adding Background Image
        background = new Background();
        add(background);
        //Adding the WaveManager
        manager = new WaveManager();
        currentWave = 7;
        //Creating the Bullet ArrayList
        bullets = new ArrayList<Bullet>();
        playerBullets = new ArrayList<Bullet>();
        newBullets = new ArrayList<Bullet>();
        //Adding an example enemy and enemy array list
        enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(200,200,100,20,20,0));
        for(Enemy e : enemies)
        {
        	background.add(e);
        }
        //Adding the inputs array
        inputs = new ArrayList<String>();
        //Creating the player character
        health = 100;
        player = new Player(400, 400, health); 
        playerHealth = new HealthBar(500, 20, 100, Color.RED);
        background.add(player);
        background.add(playerHealth);
        //Creating the remainingClears label
        remainingClears = new JLabel("Remaining Clears: " + player.getScreenClears());
        remainingClears.setBounds(100, 20, 150, 50);
        remainingHealth = new JLabel("Health: " + player.getHealth());
        remainingHealth.setBounds(500, 20, 150, 50);
        background.add(remainingClears);
        background.add(remainingHealth);
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
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) 
			{
				//Movement
				if(e.getKeyCode() == KeyEvent.VK_W)
				{
					if(!inputs.contains("W")) //player.setDy(-4);
						inputs.add("W");
				}
				if(e.getKeyCode() == KeyEvent.VK_S)
				{
					if(!inputs.contains("S")) //player.setDy(4);
						inputs.add("S");
				}
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
					if(!inputs.contains("A")) //player.setDx(-4);
						inputs.add("A");
				}
				if(e.getKeyCode() == KeyEvent.VK_D)
				{
					if(!inputs.contains("D")) //player.setDx(4);
						inputs.add("D");
				}
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
				{
//					Bullet fired = new Bullet(player.getX() + 1, player.getY(), 0, -8, 2, 10, false, Color.BLUE);
//					playerBullets.add(fired);
//					add(fired);
					if(!inputs.contains("Space"))
						inputs.add("Space");
				} 
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
								background.remove(bullets.get(i));
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
    	//Adding waves
    	if(enemies.size() == 0)
    	{
    		currentWave ++;
    		ArrayList<Enemy> newWave = manager.newWave(currentWave);
    		if(newWave.size() != 0)
        		{
    				if(currentWave == 8)
    				{
    					player.setLocation(400, 500);
    					for(int i = 0; i < bullets.size(); i++)
						{
							if(bullets.get(i).getHostile())
							{
								background.remove(bullets.get(i));
								bullets.remove(i);
								i--;
							}
						}
    				}
        			for(int enem = 0; enem < newWave.size(); enem++)
            		{
            			Enemy newEnem = newWave.get(enem);
            			enemies.add(newEnem);
            			background.add(newEnem);
            			newWave.remove(enem);
            		}
        		}
    	}
    	//Iterating over inputs
    	for(String i : inputs)
    	{
    		if(i == "W")
    		{
    			player.setDy(-2);
    		}
    		if(i == "S")
    		{
    			player.setDy(2);
    		}
    		if(i == "A")
    		{
    			player.setDx(-2);
    		}
    		if(i == "D")
    		{
    			player.setDx(2);
    		}
    		if(i == "Space")
    		{
    			if(tick % 10 == 0)
    			{
    				Bullet fired = new Bullet(player.getX() + 1, player.getY(), 0, -10, 4, 10, false, Color.YELLOW, 0);
    				playerBullets.add(fired);
    				background.add(fired);
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
				background.remove(enemies.get(enem));
				enemies.remove(enem);
				enem --;
				continue;
			}
			//Moving and shooting
			enemies.get(enem).move();
			enemies.get(enem).update();
			newBullets = enemies.get(enem).shoot();
			if(newBullets != null)
			{
				for(int i = 0; i < newBullets.size(); i++)
				{
					Bullet bull = newBullets.get(i);
					newBullets.remove(i);
					i--;
					if(bull != null)
					{
						bullets.add(bull);
						background.add(bull);
					}
				} 
			}
		}
		//Updating the bullets
		for(int b = 0; b < bullets.size(); b++)
		{
			if(bullets.get(b).getY() > 1000 || bullets.get(b).getX() < -50 || bullets.get(b).getX() > 900)
			{
				background.remove(bullets.get(b));
				bullets.remove(b);
				b--;
				continue;
			}
			bullets.get(b).update();
			Rectangle r1 = new Rectangle(bullets.get(b).getX(), bullets.get(b).getY(), 
			bullets.get(b).getWidth(), bullets.get(b).getHeight());
			Rectangle r2 = new Rectangle(player.getX(), player.getY(), 
			player.getWidth(), player.getHeight());
			//Checking if the player is hit
			if(r1.intersects(r2))
			{
				player.setHealth(bullets.get(b).getDamage() * -1);
				remainingHealth.setText("Health: " + player.getHealth());
				playerHealth.makeSmaller((int) ((player.getHealth() / 100.0) * 150));
				background.remove(bullets.get(b));
				bullets.remove(b);
				b --;
			}
		}
		bulletCollision: for(int i = playerBullets.size() - 1; i >= 0; i--)
		{
			playerBullets.get(i).update();
			Rectangle r1 = new Rectangle(playerBullets.get(i).getX(), playerBullets.get(i).getY(), 
			playerBullets.get(i).getWidth(), playerBullets.get(i).getHeight());
			//Checking if an enemy is hit
			for(int enem = 0; enem < enemies.size(); enem ++)
			{
				Rectangle r2 = new Rectangle(enemies.get(enem).getX(), enemies.get(enem).getY(), 
				enemies.get(enem).getWidth(), enemies.get(enem).getHeight());
				if(r1.intersects(r2))
				{
					enemies.get(enem).setHealth(playerBullets.get(i).getDamage() * -1);
					background.remove(playerBullets.get(i));
					playerBullets.remove(i);
					i--; 
					continue bulletCollision;
				}
			}
		}
		for(int i = 0; i < playerBullets.size(); i++)
		{
			//Removing bullets that are off the screen
			if(playerBullets.get(i).getY() < 0)
			{
				background.remove(playerBullets.get(i));
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
		if(player.getY() < 60)
		{
			player.setLocation(player.getX(), 60);
		}
		if(player.getY() > 960)
		{
			player.setLocation(player.getX(), 960);
		}
		//Checking to see if the player dies
		if(player.getHealth() <= 0)
		{
			player.setVisible(false);
			t.stop();
			JOptionPane.showMessageDialog(null, "Game Over"); //TODO Implement without JOptionPane
		}
	}
}