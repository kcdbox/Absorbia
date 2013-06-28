package de.itworks.absorbia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import de.itworks.absorbia.Screens.GameScreen;
import de.itworks.absorbia.Screens.SplashScreen;

/*
 * Absorbia
 * 
 * Abstrahiert die laufende Anwendung und wird als Hülle für Screens verwandt.
 * Die Screens sind der eigentliche Inhalt, der dargestellt und manipuliert wird.
 * 
 * @author Florian Stöber
 */
public class Absorbia extends Game
{
	public static AudioController AudioController;
	public static PreferenceController PreferenceController;

	@Override
	public void create() {
		PreferenceController = new PreferenceController();
		AudioController = new AudioController();
		
		// SplashScreen anzeigen
		setScreen(new SplashScreen(this));
	}

}
