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
	private Clip clip;
	private AudioInputStream sound;
	
	public SoundManager()
	{
		
	}
	
	public void setFile(String soundEffect)
	{
		try {
			File file = new File(soundEffect);
			sound = AudioSystem.getAudioInputStream(file);
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
	
}
