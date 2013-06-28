package de.itworks.absorbia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin.TintedDrawable;

/**
 * WorldEntity
 * 
 * Die Spielwelt bzw das Level.
 * Verantwortlich für die Spielgröße, den Spielhintergrund und die Schwierigkeit.
 * 
 * @author Florian Stöber
 */
public class WorldEntity extends GameEntity 
{
	public Rectangle bounds;
	
	public WorldEntity(float x, float y)
	{
		float width = 1280;
		float height = 768f;
		active = true;
		position.x = x;
		position.y = y;
		bounds = new Rectangle(0f, 0f, width, height);
		texture = new Texture(Gdx.files.internal("data/Hintergrund_4.png"));
		sprite = new Sprite(texture);
		sprite.setBounds(0, 0, width, height);
	}
}
