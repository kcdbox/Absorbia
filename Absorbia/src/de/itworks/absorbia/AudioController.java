package de.itworks.absorbia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;


/*
 * AudioController
 * 
 * Der AudioController übernimmt alle Aufgaben, die mit Audioausgabe zu tun haben.
 * Er ruft die Einstellungen zur Lautstärke und ob Töne ausgegeben werden sollen ab und
 * handelt entsprechend der gelesenen Einstellungen.
 * 
 * @author Saner Cimen
 */
public class AudioController {
	private boolean AudioEnabled;
	private Music GameMusic;
	private Music MenuMusic;
	private Sound BorderSound;
	private Sound AbsorbSound;
	private Sound MoveSound;
	private float MusicVolume;
	private float EffectsVolume;

	public AudioController()
	{
		// AudioDateien laden
		GameMusic = Gdx.audio.newMusic(Gdx.files.internal("data/Background2.mp3"));
		MenuMusic = Gdx.audio.newMusic(Gdx.files.internal("data/MenuMusic.mp3"));
		BorderSound = Gdx.audio.newSound(Gdx.files.internal("data/BorderCollision.mp3"));
		AbsorbSound = Gdx.audio.newSound(Gdx.files.internal("data/AbsorbSound.mp3"));
		MoveSound = Gdx.audio.newSound(Gdx.files.internal("data/MoveSound.mp3"));
		
		// Einstellungen laden
//		AudioEnabled = PreferenceController.getAudioEnabled();
//		MusicVolume = PreferenceController.getMusicVolume();
//		EffectsVolume = PreferenceController.getEffectsVolume();
		// Einstellungen werden unter Android nicht korrekt ausgelesen,
		// daher erstmal hardcoded, bis das Problem gefixt ist.
		AudioEnabled = true;
		MusicVolume = 0.8f;
		EffectsVolume = 0.8f;
	}

	public void playGameMusic()
	{
		if(AudioEnabled)
		{
			GameMusic.setLooping(true);
			GameMusic.setVolume(MusicVolume);
			GameMusic.play();
		}
	}
	
	public void stopGameMusic()
	{
		GameMusic.stop();
	}
	
	public void playMenuMusic()
	{
		if(AudioEnabled)
		{
			MenuMusic.setLooping(true);
			MenuMusic.setVolume(MusicVolume);
			MenuMusic.play();
		}
	}
	
	public void stopMenuMusic()
	{
		MenuMusic.stop();
	}
	
	public void playBorderSound()
	{
		if(AudioEnabled)
		{
			BorderSound.play(EffectsVolume);
		}
	}
	
	public void playAbsorbSound()
	{
		if(AudioEnabled)
		{
			AbsorbSound.play(EffectsVolume);
		}
	}
	
	public void playMoveSound()
	{
		if(AudioEnabled)
		{
			MoveSound.play(EffectsVolume);
		}
	}
	
	public void setAudioEnabled(boolean audioEnabled)
	{
		AudioEnabled = audioEnabled;
		PreferenceController.setAudioEnabled(audioEnabled);
	}
	
	public void setMusicVolume(float Volume)
	{
		GameMusic.setVolume(Volume);
		PreferenceController.setMusicVolume(Volume);
	}
	
	public void setEffectsVolume(float Volume)
	{
		EffectsVolume = Volume;
		PreferenceController.setEffectsVolume(Volume);
	}
}