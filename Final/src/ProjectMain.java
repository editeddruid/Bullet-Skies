/**
 * @author John D'Arcy and Philip Melavila
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ProjectMain extends JFrame implements ActionListener
{
	//Creating Fields
	private Player player;
	private Timer t;
	private ArrayList<Bullet> bullets, playerBullets, newBullets;
	private ArrayList<Enemy> enemies;
	private ArrayList<String> inputs;
	private WaveManager manager;
	private SoundManager soundManager;
	private int health, tick, currentWave; //For if we decide to do multiple levels and want to transfer over health
	private JLabel remainingClears, remainingHealth, titleLogo;
	private JButton startButton, startControls, creditsButton, exitButton;
	private HealthBar playerHealth;
	private Background background;
	private boolean start;
	public ProjectMain()
    {
        //Basic initialization
        setTitle("Bullet Skies");
        setBounds(100, 100, 800, 1000);
        setLayout(null);
        setResizable(false);
        //Adding Background Image
        background = new Background();
        add(background);
        //Adding the WaveManager
        manager = new WaveManager();
        currentWave = 0;
        //Adding the sound manager
        soundManager = new SoundManager();
        //Creating the Bullet ArrayList
        bullets = new ArrayList<Bullet>();
        playerBullets = new ArrayList<Bullet>();
        newBullets = new ArrayList<Bullet>();
        //Adding the start screen things
        start = false;
        startButton = new JButton("Start Game");
        startControls = new JButton("Controls");
        creditsButton = new JButton("Credits");
        exitButton = new JButton("Exit Game");
        Border line = new LineBorder(Color.WHITE);
        startButton.setBounds(250,400, 100, 20);
        startButton.setFocusable(false);
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        startButton.setBorder(line);
        background.add(startButton);
        startControls.setBounds(250, 450, 100, 20);
        startControls.setFocusable(false);
        startControls.setForeground(Color.WHITE);
        startControls.setBackground(Color.BLACK);
        startControls.setBorder(line);
        background.add(startControls);
        creditsButton.setBounds(450, 400, 100, 20);
        creditsButton.setFocusable(false);
        creditsButton.setForeground(Color.WHITE);
        creditsButton.setBackground(Color.BLACK);
        creditsButton.setBorder(line);
        background.add(creditsButton);
        exitButton.setBounds(450, 450, 100, 20);
        exitButton.setFocusable(false);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.BLACK);
        exitButton.setBorder(line);
        background.add(exitButton);
        titleLogo = new JLabel();
        titleLogo.setIcon(new ImageIcon("art\\logo.png"));
        titleLogo.setBounds(0,0,800,1000);
        background.add(titleLogo);
        //Adding action listeners for the start screen
        startButton.addActionListener(new ActionListener()
        		{
					@Override
					public void actionPerformed(ActionEvent e) //Starts the game and makes the start screen invisible and uninteractable
					{
						start = true;
						startButton.setVisible(false);
						startButton.setEnabled(false);
						startControls.setVisible(false);
						startControls.setEnabled(false);
						creditsButton.setVisible(false);
						creditsButton.setEnabled(false);
						exitButton.setVisible(false);
						exitButton.setEnabled(false);
						titleLogo.setVisible(false);
						playerHealth.setVisible(true);
						remainingClears.setVisible(true);
				        remainingHealth.setVisible(true);
						t.start();
						player.setLocation(396, 600);
						soundManager.setFile("sound\\song2.wav");
					}
        		});
        startControls.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Controls:\nMovement: WASD\nShooting: SPACE\nScreen Clears: SHIFT\nSurvive for 6 waves to win."); //TODO Implement without JOptionPane
			}
        });
        creditsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Coding by John D'Arcy and Philip Melavila\nArt by John D'Arcy\nSound effects obtained from zapsplat.com\nOST by Winter D'Arcy"); //TODO Implement without JOptionPane
			}
        });
        exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
        });
        //Adding enemy array list
        enemies = new ArrayList<Enemy>();
        //Adding the inputs array
        inputs = new ArrayList<String>();
        //Creating the player character
        health = 100;
        player = new Player(300, 400, health); 
        playerHealth = new HealthBar(500, 20, 100, Color.RED);
        background.add(player);
        background.add(playerHealth);
        playerHealth.setVisible(false);
        //Creating the Jlabels
        remainingClears = new JLabel("Remaining Clears: " + player.getScreenClears());
        remainingClears.setBounds(100, 20, 150, 50);
        remainingHealth = new JLabel("Health: " + player.getHealth());
        remainingHealth.setBounds(500, 20, 150, 50);
        background.add(remainingClears);
        background.add(remainingHealth);
        remainingClears.setVisible(false);
        remainingHealth.setVisible(false);
        //Adding the Timer
        t = new Timer(10, this);
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
				//Movement. When keys are pressed they are added to an ArrayList that is read in ActionPerformed to do actions
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
				//Shooting
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
						soundManager.setFile("sound\\screen_clear.wav");
						soundManager.play();
						player.setScreenClear(player.getScreenClears()-1);
						for(int i = 0; i < bullets.size(); i++)
						{
							if(bullets.get(i).getHostile()) //Deletes the bullets
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
    public static void main(String[] args) //Creates the game
    {
        new ProjectMain();
    }
    @Override
	public void actionPerformed(ActionEvent e) 
	{
    	tick ++;
    	//Checking to see if you win
    	if(currentWave > 8)
    	{
    		for(int i = 0; i < bullets.size(); i++)
			{
				background.remove(bullets.get(i));
				bullets.remove(i);
				i--;
			}
    		for(int enem = 0; enem < enemies.size(); enem ++)
    		{
    			background.remove(enemies.get(enem));
				enemies.remove(enem);
				enem--;
    		}
    		for(int i = 0; i < playerBullets.size(); i++)
			{
				background.remove(playerBullets.get(i));
				playerBullets.remove(i);
				i--;
			}
    		repaint();
    		t.stop();
    		JOptionPane.showMessageDialog(null, "You Win");
    		reset();
    	}
    	//Adding waves
    	if(enemies.size() == 0 && start == true && tick > 150)
    	{
    		currentWave ++;
    		ArrayList<Enemy> newWave = manager.newWave(currentWave);
    		if(newWave.size() != 0)
        		{
    				if(currentWave == 6) //Clearing the screen when the boss appears
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
        			for(int enem = 0; enem < newWave.size(); enem++) //Actually adding the enemies
            		{
            			Enemy newEnem = newWave.get(enem);
            			enemies.add(newEnem);
            			background.add(newEnem);
            			newWave.remove(enem);
            			enem--;
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
    				soundManager.setFile("sound\\fire.wav");
    				soundManager.play();
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
				soundManager.setFile("sound\\enemy_death.wav");
				soundManager.play();
				background.remove(enemies.get(enem));
				enemies.remove(enem);
				enem --;
				continue;
			}
			//Moving and shooting
			enemies.get(enem).move();
			enemies.get(enem).update();
			newBullets = enemies.get(enem).shoot();
			if(newBullets != null) //Runs when the shoot method works
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
			if(bullets.get(b).getY() > 1000 || bullets.get(b).getX() < -50 || bullets.get(b).getX() > 900) //Removing bullets that have gone offscreen
			{
				background.remove(bullets.get(b));
				bullets.remove(b);
				b--;
				continue;
			}
			bullets.get(b).update();
			//Collision detection: 2 Rectangles are made and if they intersect there is a collision
			Rectangle r1 = new Rectangle(bullets.get(b).getX(), bullets.get(b).getY(), 
			bullets.get(b).getWidth(), bullets.get(b).getHeight());
			Rectangle r2 = new Rectangle(player.getX(), player.getY(), 
			player.getWidth(), player.getHeight());
			//Checking if the player is hit
			if(r1.intersects(r2))
			{
				soundManager.setFile("sound\\player_hit.wav");
				soundManager.play();
				player.setHealth(bullets.get(b).getDamage() * -1);
				remainingHealth.setText("Health: " + player.getHealth());
				playerHealth.makeSmaller((int) ((player.getHealth() / 100.0) * 150));
				background.remove(bullets.get(b));
				bullets.remove(b);
				b --;
			}
		}
		bulletCollision: for(int i = playerBullets.size() - 1; i >= 0; i--) //More collision, but this time for the bullets fired by a player
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
					soundManager.setFile("sound\\enemy_hit.wav");
					soundManager.play();
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
			//Removing player bullets that are off the screen
			if(playerBullets.get(i).getY() < 0)
			{
				background.remove(playerBullets.get(i));
				playerBullets.remove(i);
				i--;
			}
		}
		//Keeping the player within bounds
		if(player.getX() < 0)
		{
			player.setLocation(0, player.getY());
		}
		if(player.getX() > 780)
		{
			player.setLocation(780, player.getY());
		}
		if(player.getY() < 60)
		{
			player.setLocation(player.getX(), 60);
		}
		if(player.getY() > 956)
		{
			player.setLocation(player.getX(), 956);
		}
		repaint(); //Repainting everything
		//Checking to see if the player dies. If the player is out of health the player disappears and a game over box appears.
		if(player.getHealth() <= 0)
		{
			player.setVisible(false);
			t.stop();
			JOptionPane.showMessageDialog(null, "Game Over"); //TODO Implement without JOptionPane
			reset();
		}
	}
    
    private void reset()
    {
    	soundManager.stop();
    	currentWave = 0; //Resetting the game.
    	tick = 0;
    	for(int i = 0; i < enemies.size(); i++)
    	{
    		background.remove(enemies.get(i));
    		enemies.remove(i);
    		i--;
    	}
    	for(int i = 0; i < bullets.size(); i++)
    	{
    		background.remove(bullets.get(i));
    		bullets.remove(i);
    		i--;
    	}
    	for(int i = 0; i < playerBullets.size(); i++)
    	{
    		background.remove(playerBullets.get(i));
    		playerBullets.remove(i);
    		i--;
    	}
    	for(int i = 0; i < inputs.size(); i ++)
    	{
    		inputs.remove(i);
    	}
    	player.setVisible(true);
    	player.setLocation(300, 400);
		start = false;
		startButton.setVisible(true);
		startButton.setEnabled(true);
		startControls.setVisible(true);
		startControls.setEnabled(true);
		creditsButton.setVisible(true);
		creditsButton.setEnabled(true);
		exitButton.setVisible(true);
		exitButton.setEnabled(true);
		titleLogo.setVisible(true);
		playerHealth.setVisible(false);
		remainingClears.setVisible(false);
        remainingHealth.setVisible(false);
        playerHealth.setSize(151, 21);
        player.setHealth(100 - player.getHealth());
        remainingHealth.setText("Health: " + player.getHealth());
        player.setScreenClear(5);
        remainingClears.setText("Remaining Clears: " + player.getScreenClears());
        repaint();
    }
}