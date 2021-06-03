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
		if(currentWave == 8) //Adding the boss on wave 8
		{
			wave.add(new BossEnemy(100, 100, 2000, 600, 200, 0));
			return wave;
		}
		if(currentWave < 8) //Adding the basic enemies 
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
			return wave;
		}
		return wave;
	}
}