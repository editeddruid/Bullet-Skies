/**
 * @author John D'Arcy
 */

import java.awt.Color;
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
	private int health; //For if we decide to do multiple levels and want to transfer over health
	//TODO add a previous dx and dy so that the stopping thing doesn't happen
	private JLabel remainingClears;
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
        enemies.add(new Enemy(200,200,100,20,20));
        for(Enemy e : enemies)
        {
        	add(e);
        }
        //Creating the player character
        health = 100;
        player = new Player(400, 400, health); //Location is just a placeholder
        add(player);
        
        //Creating the remainingClears label
        remainingClears = new JLabel("Remaining Clears: " + player.getScreenClears());
        remainingClears.setBounds(100, 20, 150, 50);
        add(remainingClears);
        //Adding the Timer
        t = new Timer(10, this);
        t.start();

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
					player.setDy(-4);
				}
				if(e.getKeyCode() == KeyEvent.VK_S)
				{
					player.setDy(4);
				}
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
					player.setDx(-4);
				}
				if(e.getKeyCode() == KeyEvent.VK_D)
				{
					player.setDx(4);
				}
				//TODO add it so that pressing space fires
				if(e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					Bullet fired = new Bullet(player.getX() + 1, player.getY(), 0, -8, 2, 10, false, Color.BLUE);
					playerBullets.add(fired);
					add(fired);
				} //TODO add a way to queue inputs so that you can fire while moving
			}
			@Override
			public void keyReleased(KeyEvent e) 
			{
				//Stopping Movement
				if(e.getKeyCode() == KeyEvent.VK_W)
				{
					player.setDy(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_S)
				{
					player.setDy(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
					player.setDx(0);
				}
				if(e.getKeyCode() == KeyEvent.VK_D)
				{
					player.setDx(0);
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
    	//Updating the player's location
		player.update();
		
		//Updating the bullets
		for(Bullet b : bullets)
		{
			b.update();
		}
		for(int i = 0; i < playerBullets.size(); i++)
		{
			playerBullets.get(i).update();
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
