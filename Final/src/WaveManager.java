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
		for(int w = 0; w < wave.size(); w++) //Removes all the old enemies from the ArrayList
		{
			wave.remove(w);
			w--;
		}
		if(currentWave == 8) //Adding the boss on wave 8
		{
			wave.add(new BossEnemy(100, 100, 2000, 600, 200, 0));
			return wave;
		}
		if(currentWave < 8) //Adding the basic enemies //TODO Figure out why enemies are spawning inconsistently
		{
			for(int i = 0; i < currentWave + 1; i++)
			{
				wave.add(new Enemy((int) (Math.random() * 900) + 50, 150 + (int) (Math.random() * (20 - -20) + -20), 20, 20, 20, 0));
				wave.add(new Enemy((int) (Math.random() * 900), 150 + (int) (Math.random() * (20 - -20) + -20), 20, 20, 20, 0));
				wave.add(new EnemyUpDown(0, 320 + i * 80, 20, 20, 20, 0));
			}
		}
		if(currentWave >= 4 && currentWave < 8)
		{
			wave.add(new EnemyWavey(100 + 30 * currentWave, 150 + (int) (Math.random() * (20 - -20) -20), 30, 30, 40, 0));
			wave.add(new EnemyWavey(700 - 30 * currentWave, 150 + (int) (Math.random() * (20 - -20) -20), 30, 30, 40, 0));
		}
		if(currentWave > 8)
		{
			wave.add(new Enemy(500 + (int) (Math.random() * 160) - 80, 150 + (int) (Math.random() * (40) -20), 20, 20, 20, 0));
		}
		return wave;
	}
}