package de.itworks.absorbia;

import java.io.Serializable;
import java.util.ArrayList;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import de.itworks.absorbia.Screens.GameScreen;

/**
 * BubbleEntity
 * 
 * Basisklasse für Spielfiguren.
 * 
 * @author Florian Stöber
 */
public class BubbleEntity extends GameEntity implements Serializable {

	private static final long serialVersionUID = -2764740658678243668L;
	Circle bounds;
	public Vector2 speed = new Vector2();
	public Vector2 acceleration = new Vector2();
	public Vector2 accelerationHelper = new Vector2();
	protected float sizeModifier = 0.025f;
	protected Vector3 v3Position;
	protected float shrinkVal;
	
	public BubbleEntity()
	{
		float Radius = 20f;
		float x = 0f;
		float y = 0f;
		v3Position = new Vector3();
		active = true;
		position.x = x;
		position.y = y;
		bounds = new Circle(position.x, position.y, Radius);		
		texture = new Texture(Gdx.files.internal("data/Blase_blau.png"));
		sprite = new Sprite(texture);
		sprite.setBounds(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2, Radius, Radius);
	}
	
	/**
	 * BubbleEntity
	 * 
	 * Konstruktor um Bubbles mit anderem Radius zu erzeugen.
	 */
	public BubbleEntity(float x, float y, float Radius)
	{
		v3Position = new Vector3();
		active = true;
		position.x = x;
		position.y = y;
		bounds = new Circle(position.x, position.y, Radius);		
		texture = new Texture(Gdx.files.internal("data/Blase_blau.png"));
		sprite = new Sprite(texture);
		sprite.setBounds(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2, Radius * 2, Radius * 2);
	}
	
	/**
	 * BubbleEntity
	 * 
	 * Konstruktor um erzeugten Bubbles eine initiale Bewegung mitzugeben.
	 * Wird verwendet um Bubbles bei Bewegung von Spieler zu erzeugen.
	 */
	public BubbleEntity(Vector2 Position, float Radius, Vector2 Acceleration)
	{
		v3Position = new Vector3();
		this.acceleration = Acceleration;
		active = true;
		position.set(Position);
		bounds = new Circle(position.x, position.y, Radius);		
		texture = new Texture(Gdx.files.internal("data/Blase_blau.png"));
		sprite = new Sprite(texture);
		sprite.setBounds(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2, Radius * 2, Radius * 2);
	}
	
	/**
	 * setPosition
	 * 
	 * Bubble auf neue Position setzen.
	 * 
	 * @param float x X-Koordinate
	 * @param float y Y-Koordinate
	 */
	public void setPosition(float x, float y)
	{
		position.x = x;
		position.y = y;
		
		// Bounds auf neue Position setzen
		bounds.x = position.x;
		bounds.y = position.y;
		
		// Sprite auf neue Position setzen
		sprite.setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2);
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
	 * Zeichnet das Sprite dieser BubbleEntity auf dem Bildschirm.
	 * 
	 * @param batch
	 */
	public void render(SpriteBatch batch)
	{
		sprite.draw(batch);
	}
	
	/**
	 * update
	 * 
	 * Verrechnet die Position der BubbleEntity mit dem Delta seit dem letzten
	 * Render()-Aufruf, aktualisiert die Position und ruft die Methoden zur
	 * Kollisionsüberprüfung auf.
	 * 
	 * @param delta Zeit seit letztem Render()-Aufruf
	 * @param bubbleEntities Liste der erzeugten BubbleEntity-Objekte
	 */
	public void update(float delta, ArrayList<BubbleEntity> bubbleEntities)
	{
		position.x += acceleration.x * delta;
		position.y += acceleration.y * delta;
//		position.add(acceleration).scl(delta);
		
		// Objekt auf neue Position aktualisieren
		this.setPosition(position.x, position.y);
		
		// Auf Kollisionen prüfen
		this.checkWorldCollision(GameScreen.World);
		this.checkCollisions(bubbleEntities);
	}
	
	public float getRadius()
	{
		return bounds.radius;
	}
	
	/**
	 * getPosition
	 * 
	 * Gibt die Position der Bubble als Vector3 zurück, um Vektormethoden des Touchvektors
	 * direkt mit einer BubbleEntity benutzen zu können.
	 * 
	 * @return Vector3 position
	 */
	public Vector3 getPosition()
	{
		v3Position.x = position.x;
		v3Position.y = position.y;
		v3Position.z = 0;
		return v3Position;
	}
	
	/**
	 * shrink
	 * 
	 * Verkleinert diese BubbleEntity und passt Position und Größe des Sprites an.
	 */
	public void shrink()
	{
		// Bubble stirbt wenn Radius <= 0
		if (bounds.radius <= 0)
		{
			// Flag setzen um Objekt im Speicher zu behalten und
			// damit vor dem GarbageCollector zu bewahren.
			// Während das Spiel aktiv ist sollten keine Objekte deallokiert werden,
			// um Ruckler zu vermeiden!
			active = false;
			shrinkVal = 0;
		}
		else
		{
			shrinkVal = sizeModifier;
			bounds.radius -= shrinkVal;
			sprite.setBounds(
					position.x - sprite.getWidth() / 2,
					position.y - sprite.getHeight() / 2,
					sprite.getWidth() - (shrinkVal),
					sprite.getHeight() - (shrinkVal)
					);
		}
	}
	
	
	/**
	 * shrink
	 * 
	 * Verkleinert diese BubbleEntity um die übergebene Menge und 
	 * passt Position und Größe des Sprites an.
	 * 
	 * @param amount Menge um die verkleinert werden soll
	 * @return float amount * 1.5 um mit dem Return neue Bubbles bei Aufruf 
	 * 						durch PlayerBubble.move() zu erzeugen
	 */
	public float shrink(float amount)
	{
		// Bubble stirbt wenn Radius <= 0
		if (bounds.radius <= 0)
		{
			// Flag setzen um Objekt im Speicher zu behalten und
			// damit vor dem GarbageCollector zu bewahren.
			// Während das Spiel aktiv ist sollten keine Objekte deallokiert werden,
			// um Ruckler zu vermeiden!
			active = false;
			return 0f;
		}
		else
		{
			bounds.radius -= amount;
			sprite.setBounds(
					position.x - sprite.getWidth() / 4,
					position.y - sprite.getHeight() / 4,
					sprite.getWidth() - amount,
					sprite.getHeight() - amount
					);
			return amount * 1.5f;
		}
	}
	
	/**
	 * grow
	 * 
	 * Vergrößert die BubbleEntity passt Position und Größe des Sprites an.
	 */
	public void grow()
	{
		bounds.radius += sizeModifier / 2;
		sprite.setBounds(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2, sprite.getWidth() + sizeModifier / 2, sprite.getHeight() + sizeModifier / 2);
	}
	
	/**
	 * absorb
	 * 
	 * Absorbiert die übergebene BubbleEntity.
	 * Ruft die grow()-Methode der aufrufenden BubbleEntity auf und
	 * die shrink()-Methode der absorbierten BubbleEntity.
	 * 
	 * @param bubbleEntity
	 */
	public void absorb(BubbleEntity bubbleEntity)
	{
		if (bubbleEntity.active)
		{
			this.grow();
			bubbleEntity.shrink();
			if(bubbleEntity.getClass() == PlayerBubble.class)
			{
				// Mit AbsorbSoundDatei stimmt evtl etwas nicht, verzerrter Sound
//				Absorbia.AudioController.playAbsorbSound();
			}
		}
	}
	
	/**
	 * checkCollisions
	 * 
	 * Prüft ob diese BubbleEntity mit einer aus der übergebenen ArrayList kollidiert
	 * und ruft die absorb()-Methode der jeweils größeren Bubble auf.
	 * 
	 * @param entityList BubbleEntities zur Kollisionsprüfung
	 */
	public void checkCollisions(ArrayList<BubbleEntity> entityList)
	{		
		for (BubbleEntity bubbleEntity : entityList) {
			if(Intersector.overlaps(this.bounds, bubbleEntity.bounds))
			{				
				if (this.bounds.radius < bubbleEntity.bounds.radius)
				{
					bubbleEntity.absorb(this);
				}
				else
				{
					this.absorb(bubbleEntity);
				}
			}
		}
	}
	
	/**
	 * checkWorldCollision
	 * 
	 * Prüft ob eine Kollision der Bubbel mit der übergebenen WorldEntity vorliegt und
	 * verändert bei Kollision den Beschleunigungsvektor um die Bubble von der WorldEntity
	 * abprallen zu lassen. 
	 * 
	 * @param WorldEntity world WorldEntity gegen deren Bounds der Check ausgeführt wird.
	 */
	public boolean checkWorldCollision(WorldEntity world)
	{
		// Kollision mit linkem Rand
		if(this.bounds.x - this.bounds.radius < world.bounds.x)
		{
			this.acceleration.x = -this.acceleration.x;
			this.position.x = world.bounds.x + this.bounds.radius;
			return true;
		}
		// Kollision mit oberem Rand
		else if(this.bounds.y - this.bounds.radius < world.bounds.y)
		{
			this.acceleration.y = -this.acceleration.y;
			this.position.y = world.bounds.y + this.bounds.radius;
			return true;
		}
		// Kollision mit rechtem Rand
		else if(this.bounds.x + this.bounds.radius > world.bounds.x + world.bounds.width)
		{
			this.acceleration.x = -this.acceleration.x;
			this.position.x = world.bounds.x + world.bounds.width - this.bounds.radius;
			return true;
		}
		// Kollision mit unterem Rand
		else if (this.bounds.y + this.bounds.radius > world.bounds.y + world.bounds.height)
		{
			this.acceleration.y = -this.acceleration.y;
			this.position.y = world.bounds.y + world.bounds.height - this.bounds.radius;
			return true;
		}
		
		return false;
	}
}
