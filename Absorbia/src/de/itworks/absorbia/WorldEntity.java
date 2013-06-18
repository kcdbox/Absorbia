package de.itworks.absorbia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WorldEntity extends GameEntity 
{
	public WorldEntity(float x, float y)
	{
		position.x = x;
		position.y = y;
		texture = new Texture(Gdx.files.internal("data/MenuBackground.png"));
		sprite = new Sprite(texture);
		sprite.setSize(0.1f, 0.1f);
		sprite.setPosition(-x/2, -y/2);
	}
}
