import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 
 * @author John D'Arcy
 *
 */
public class SoundManager 
{
	private Clip clip, song;
	private AudioInputStream sound;
	
	public SoundManager(){}
	
	public void setFile(String soundEffect)
	{
		try {
			File file = new File(soundEffect);
			sound = AudioSystem.getAudioInputStream(file);
			if(soundEffect.equals("sound\\song2.wav"))
			{
				song = AudioSystem.getClip();
				song.open(sound);
				song.setFramePosition(0);
				song.start();
			}
			clip = AudioSystem.getClip();
			clip.open(sound);
		}
		catch(Exception e){}
	}
	
	public void play() 
	{
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop()
	{
		song.stop();
	}
}
