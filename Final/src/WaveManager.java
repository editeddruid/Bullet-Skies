import java.util.ArrayList;
/**
 * 
 * @author John D'Arcy
 *
 */
public class WaveManager 
{
	private ArrayList<Enemy> wave, wave1, wave2, wave3, wave4;
	public WaveManager()
	{
		wave = new ArrayList<Enemy>();
		wave1 = new ArrayList<Enemy>();
		wave2 = new ArrayList<Enemy>();
		wave3 = new ArrayList<Enemy>();
		wave4 = new ArrayList<Enemy>();
		for(int i = 0; i < 5; i ++)
		{
			wave1.add(new Enemy(100 + i * 40, 150 + i * 20, 50, 20, 20, 0));
			wave2.add(new Enemy(400 + i * 40, 150 + i * 20, 50, 20, 20, 0));
			wave3.add(new Enemy(400 + i * 40, 150 + i * 20, 50, 20, 20, 0));
			wave4.add(new Enemy(400 + i * 40, 150 + i * 20, 50, 20, 20, 0));
		}
	}
	public ArrayList<Enemy> getWave(int wave)
	{
		if(wave == 1)
			return wave1;
		else if(wave == 2)
			return wave2;
		else if(wave == 3)
			return wave3;
		else if(wave == 4)
			return wave4;
		else
			return null;
	}
	public ArrayList<Enemy> newWave(int currentWave)
	{
		if(currentWave < 8)
		{
			for(int i = 0; i < currentWave + 2; i++)
			{
				wave.add(new Enemy(260 + i * 40, 150 + (int) (Math.random() * (20 - -20) + -20), 20, 20, 20, 0));
				wave.add(new Enemy(560 + (i * 40), 150 + (int) (Math.random() * (20 - -20) + -20), 20, 20, 20, 0));
			}
		}
		return wave;
	}
}