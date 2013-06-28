package de.itworks.absorbia;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * MainActivity
 * 
 * Starterklasse zur Ausführung auf einem Android Gerät.
 * 
 * @author Florian
 */
public class MainActivity extends AndroidApplication {
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        // Beschleunigungssensor und Magnetfeldsensor aka "Compass"
        // deaktivieren, um Strom zu sparen
        cfg.useAccelerometer = false;
        cfg.useCompass = false;
        
        initialize(new Absorbia(), cfg);
	}
}