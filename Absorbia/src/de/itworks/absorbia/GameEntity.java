package de.itworks.absorbia;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * GameEntity
 * 
 * Basisklasse für SpielObjekte.
 * Alle GameEntities können im Koordinatensystem positioniert und
 * auf dem Bildschirm dargestellt werden.
 * 
 * @author Florian Stöber
 */
public abstract class GameEntity {
	public Vector2 position = new Vector2();
	public boolean active;
	public Texture texture;
	public Sprite sprite;
	
	public void setPosition(float x, float y)
	{
		position.x = x;
		position.y = y;
		sprite.setPosition(x, y);
	}
	

	/**
	 * dispose
	 * 
	 * Speicherbereich, die der GC nicht selbst freigeben kann freigeben.
	 */
	public void dispose()
	{
		texture.dispose();
	}
	
	/**
	 * render
	 * 
	 * Zeichnet das Sprite auf dem Bildschirm.
	 * 
	 * @param batch
	 */
	public void render(SpriteBatch batch)
	{
		sprite.draw(batch);
	}
}