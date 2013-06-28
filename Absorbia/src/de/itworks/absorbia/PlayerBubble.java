package de.itworks.absorbia;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import de.itworks.absorbia.Screens.GameScreen;

/**
 * PlayerBubble
 * 
 * Die Spielfigur bzw der Spieler.
 * Erweitert die Klasse BubbleEntity um spielergesteuerte Bewegungslogik.
 * 
 * @author Florian Stöber
 */
public class PlayerBubble extends BubbleEntity {
	private static final long serialVersionUID = 136124068807573888L;
	private Vector2 childAcceleration;

	public PlayerBubble(float x, float y, float Radius) {
		super(x, y, Radius);
		texture = new Texture(Gdx.files.internal("data/Blase_blau.png"));
		childAcceleration = new Vector2();
	}
	
	/**
	 * move
	 * 
	 * Verrechnet einen festen Beschleunigungswert mit der Beschleunigung
	 * der Playerbubble.
	 * 
	 * @param touchpos
	 * @param bubbles
	 */
	public void move(Vector2 touchpos, ArrayList<BubbleEntity> bubbles)
	{
		// Helper zurücksetzen
		accelerationHelper.set(0, 0);

		// Delta zwischen touchPos und Position		
		accelerationHelper.x = touchpos.x - position.x;
		accelerationHelper.y = touchpos.y - position.y;
		
		// Delta Prozentual auf Beschleunigungsachsen aufteilen
		childAcceleration.x = ((100 / (Math.abs(accelerationHelper.x) + Math.abs(accelerationHelper.y))) * accelerationHelper.x) * -0.15f;
		childAcceleration.y = ((100 / (Math.abs(accelerationHelper.x) + Math.abs(accelerationHelper.y))) * accelerationHelper.y) * -0.15f;
		
		// Beschleunigung durch Touch auf existierende Beschleunigung addieren
		acceleration.x += childAcceleration.x;
		acceleration.y += childAcceleration.y;
		
		// Kleine Bubble erzeugen und Größe der Bubble vom Spieler abziehen
		// Die abgestoßene Bubble "verursacht" aus Spielersicht die Beschleunigung 
//		bubbles.add(new BubbleEntity(position, this.shrink(5f), childAcceleration.cpy().scl(-1)));
		Absorbia.AudioController.playMoveSound();
	}
	
	/**
	 * checkWorldCollision
	 * 
	 * Spielt bei Kollision einen Sound ab und ruft 
	 * den Kollisionscheck der Parent-Klasse auf.
	 * 
	 * @param world
	 */
	@Override
	public boolean checkWorldCollision(WorldEntity world)
	{
		if(super.checkWorldCollision(world))
		{
			// Abprall-Sound abspielen
			Absorbia.AudioController.playBorderSound();
			return true;
		}
		return false;
	}
}
