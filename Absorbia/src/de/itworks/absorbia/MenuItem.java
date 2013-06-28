package de.itworks.absorbia;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * MenuItem 
 * 
 * Vereinfacht das Handling mit Sprites als Menüobjekte. 
 * 
 * @author Florian Stöber
 */
public class MenuItem extends GameEntity
{
	public MenuItem(float x, float y, FileHandle textureFile)
	{
		texture = new Texture(textureFile);
		sprite = new Sprite(texture);
		sprite.setBounds(x + sprite.getWidth() / 2, y, 300, 150);
	}
	
	public Rectangle getBounds()
	{
		return sprite.getBoundingRectangle();
	}
}
