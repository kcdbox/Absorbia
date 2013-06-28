package de.itworks.absorbia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * PreferenceController
 * 
 * Der PreferenceController verwaltet alle Aufgaben, die das Lesen und Schreiben von
 * Einstellungen betreffen.
 * 
 * @Author Matthias Henrich
 */
public class PreferenceController {
	private static final String PREFS = "AbsorbiaEinstellungen";
	private static final String AUDIO_ENABLED = "AudioEnabled";
	private static final String MVOL = "MusicVolume";
	private static final String EVOL = "EffectsVolume";

	public PreferenceController()
	{
		// Default Einstellungen schreiben
		getPrefs().putBoolean(AUDIO_ENABLED, true);
		getPrefs().putFloat(MVOL, 1f);
		getPrefs().putFloat(EVOL, 1f);
		getPrefs().flush();
	}
	
	public static boolean getAudioEnabled()
	{
		return getPrefs().getBoolean(AUDIO_ENABLED);
	}
	
	public static void setAudioEnabled(boolean AudioEnabled)
	{
		getPrefs().putBoolean(AUDIO_ENABLED, AudioEnabled);
		getPrefs().flush();
	}
	
	public static float getMusicVolume()
	{
		return getPrefs().getFloat(MVOL);
	}
	
	public static void setMusicVolume(float MusicVolume)
	{
		getPrefs().putFloat(MVOL, MusicVolume);
		getPrefs().flush();
	}
	
	public static float getEffectsVolume()
	{
		return getPrefs().getFloat(EVOL);
	}
	
	public static void setEffectsVolume(float EffectsVolume)
	{
		getPrefs().putFloat(EVOL, EffectsVolume);
		getPrefs().flush();
	}
	
	public static Preferences getPrefs()
	{
		return Gdx.app.getPreferences(PREFS);
	}
}
