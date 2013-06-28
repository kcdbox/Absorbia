package de.itworks.absorbia;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/*
 * Main
 * 
 * Starterklasse zur Ausführung auf einem Desktop Computer
 */
public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Absorbia";
		cfg.useGL20 = false;
		cfg.width = 1280;
		cfg.height = 768;
		
		new LwjglApplication(new Absorbia(), cfg);
	}
}