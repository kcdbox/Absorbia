package de.itworks.absorbia;

import java.util.Collection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;

public class BubbleEntity extends GameEntity {
	public Circle bounds;
	
	public BubbleEntity(float x, float y, float Radius)
	{
		position.x = x;
		position.y = y;
		bounds = new Circle(position.x, position.y, Radius);
		active = true;
		
		texture = new Texture(Gdx.files.internal("data/Bubble.png"));
		sprite = new Sprite(texture);
		sprite.setSize(0.1f, 0.1f);
		sprite.setPosition(-x/2, -y/2);
	}
	
	public void setPosition(float x, float y)
	{
		position.x = x;
		position.y = y;
		sprite.setPosition(position.x, position.y);
	}
	
	public void dispose()
	{
		texture.dispose();
	}
	
	public void move(Vector3 touchpos)
	{
		acceleration = position.sub(touchpos).mul(-1);
	}
	
	public void render(SpriteBatch batch)
	{
		sprite.rotate(90f);
		sprite.draw(batch);
	}
	
	public void udpate(SpriteBatch batch, float delta)
	{
//		position = position.add(acceleration.mul(delta));

		sprite.setPosition(position.x, position.y);
		sprite.rotate(90f);
		sprite.draw(batch);
	}
}
