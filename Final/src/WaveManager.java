import java.util.ArrayList;
/**
 * 
 * @author John D'Arcy
 *
 */
public class WaveManager 
{
	private ArrayList<Enemy> wave;
	public WaveManager()
	{
		wave = new ArrayList<Enemy>();
	}
	public ArrayList<Enemy> newWave(int currentWave)
	{
		if(currentWave < 8)
		{
			for(int i = 0; i < currentWave + 2; i++)
			{
				wave.add(new Enemy(120 + i * 40, 150 + (int) (Math.random() * (20 - -20) + -20), 20, 20, 20, 0));
				wave.add(new Enemy(260 + i * 40, 150 + (int) (Math.random() * (20 - -20) + -20), 20, 20, 20, 0));
				wave.add(new Enemy(560 + (i * 40), 150 + (int) (Math.random() * (20 - -20) + -20), 20, 20, 20, 0));
			}
		}
		else if(currentWave == 8)
		{
			wave.add(new BossEnemy(100, 100, 2000, 600, 200, 0));
		}
		else
		{
			wave.add(new Enemy(500 + (int) (Math.random() * 160) - 80, 150 + (int) (Math.random() * (40) -20), 20, 20, 20, 0));
		}
		return wave;
	}
}