package de.itworks.absorbia;

import com.badlogic.gdx.utils.Pool;

/**
 * BubblePool
 * 
 * Der BubblePool erzeugt vorab eine definierte Menge an Objekten,
 * die zur Laufzeit aus dem Pool entnommen, manipuliert und deaktiviert werden k�nne.
 * Dadurch sparen wir uns etwas Zeit und erh�hen Performance zur Laufzeit.
 * 
 * @author Florian St�ber
 */
public class BubblePool extends Pool<BubbleEntity>{

	@Override
	protected BubbleEntity newObject()
	{
		
		return null;
	}

}
