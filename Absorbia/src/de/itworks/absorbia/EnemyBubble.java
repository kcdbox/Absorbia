package de.itworks.absorbia;

import java.util.ArrayList;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * EnemyBubble
 * 
 * Feindliche, CPU-Gesteuerte Bubbles bewegen sich selbstständig und zufällig.
 * 
 * @auhor Alexander Tobie
 */
public class EnemyBubble extends BubbleEntity {
	private static final long serialVersionUID = 7715303778983132025L;
	private Texture texture2;
	private Texture texture3;
	private Sprite sprite2;
	private Sprite sprite3;
	private float angle;
	private float xSign;
	private float ySign;
	private int moveCount;
	private boolean conscious = false;
	private Vector2 acceleration = new Vector2();
	private int cntr = 1;
	
	public EnemyBubble(float x, float y, float Radius)
	{
//		texture2 = new Texture(Gdx.files.internal("data/red_2.png"));
//		texture3 = new Texture(Gdx.files.internal("data/red_3.png"));
//		sprite2 = new Sprite(texture2);
//		sprite3 = new Sprite(texture3);
		
		v3Position = new Vector3();
		active = true;
		
		if(Math.random() * 100 < 40)
		{
			conscious = true;
		}
		
		position.x = x;
		position.y = y;
		bounds = new Circle(position.x, position.y, Radius);		
		texture = new Texture(Gdx.files.internal("data/red_1.png"));
		sprite = new Sprite(texture);
		sprite.setBounds(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2, Radius, Radius);
	}
	
	public void move(float delta)
	{
		// Nicht bei jedem move()-Aufruf bewegen,
		// da move() bei jedem update()-Tick aufgerufen wird.
		if(Math.random() < 0.1d)
		{
			xSign = 1f;
			ySign = 1f;
			
			if (Math.random() < 0.5d)
			{
				xSign = -1f;
			}
			if (Math.random() < 0.5d)
			{
				ySign = -1f;
			}
			
			acceleration.x += 0.05f * xSign;
			acceleration.y += 0.05f * ySign;
		}
	}
	
	/**
	 * update
	 * 
	 * Ruft zusätzlich zur update()-Methode des Parents, auch die
	 * Move()-Methode dieser Klasse auf um automatische Bewegung realisieren.
	 * 
	 * @param delta
	 * @param bubbleEntities
	 */
	@Override
	public void update(float delta, ArrayList<BubbleEntity> bubbleEntities)
	{
		position.add(acceleration);
		sprite.setBounds(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2, bounds.radius * 2, bounds.radius * 2);
		
		move(delta);
		super.update(delta, bubbleEntities);
	}
}
