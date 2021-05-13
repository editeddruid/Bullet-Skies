/**
 * @author John D'Arcy
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class ProjectMain extends JFrame implements ActionListener
{
	//Creating Fields
	private Player player;
	private Timer t;
	private int health; //For if we decide to do multiple levels and want to transfer over health
    public ProjectMain()
    {
        //Basic initialization
        setTitle("Placeholder Title");
        setBounds(100, 100, 800, 1000);
        setLayout(null);
        setResizable(false);
        
        //Creating the player character
        health = 100;
        player = new Player(400, 400, health); //Location is just a placeholder
        add(player);
        
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
				if(e.getKeyCode() == KeyEvent.VK_W)
				{
					player.setDy(-6);
				}
				if(e.getKeyCode() == KeyEvent.VK_S)
				{
					player.setDy(6);
				}
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
					player.setDx(-6);
				}
				if(e.getKeyCode() == KeyEvent.VK_D)
				{
					player.setDx(6);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) 
			{
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
