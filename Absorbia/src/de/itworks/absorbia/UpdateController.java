package de.itworks.absorbia;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;

import de.itworks.absorbia.Screens.GameScreen;

/**
 * UpdateController
 * 
 * Sorgt dafür, das die logischen Elemente der SpielObjekte aktualisiert werden.
 * 
 * @author Florian Stöber
 */
public class UpdateController {
	public UpdateController(Screen game)
	{
		
	}
	

	/**
	 * update
	 * 
	 * Ruft die update()-Methode aller übergebenen Entities auf.
	 * 
	 * @param bubbles
	 * @param player
	 * @param delta
	 */
	public void update(ArrayList<BubbleEntity> bubbles, PlayerBubble player, float delta)
	{	
		if(!GameScreen.touchPosition.equals(Vector2.Zero))
		{
			player.move(GameScreen.touchPosition, bubbles);
			GameScreen.touchPosition.set(0, 0);
		}
		
		// Debug Weiche
		if(bubbles.size() > 0)
		{
			// Alle aktiven Entities updaten
			for (BubbleEntity bubbleEntity : bubbles) {
				// Wenn Entity aktiv ist. Inaktive Entities bleiben allokiert bis Pause oder Programmende
				if(bubbleEntity.active)
				{
					bubbleEntity.update(delta, bubbles);
				}
			}
		}
	}
}
