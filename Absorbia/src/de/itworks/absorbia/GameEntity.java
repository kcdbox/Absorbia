package de.itworks.absorbia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public abstract class GameEntity {
	public Vector3 position = new Vector3();
	public Vector3 speed = new Vector3();
	public Vector3 acceleration = new Vector3();
	public boolean active;
	public Texture texture;
	public Sprite sprite;
	
	public void setPosition(float x, float y)
	{
		position.x = x;
		position.y = y;
		sprite.setPosition(x, y);
	}
	
	public void dispose()
	{
		texture.dispose();
	}
	
	public void render(SpriteBatch batch)
	{
		sprite.draw(batch);
	}
	
	public void udpate(SpriteBatch batch, float delta)
	{
		sprite.setPosition(position.x, position.y);
	}
}
