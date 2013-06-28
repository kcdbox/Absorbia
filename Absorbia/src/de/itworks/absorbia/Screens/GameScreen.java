package de.itworks.absorbia.Screens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import de.itworks.absorbia.Absorbia;
import de.itworks.absorbia.AudioController;
import de.itworks.absorbia.BubbleEntity;
import de.itworks.absorbia.EnemyBubble;
import de.itworks.absorbia.PlayerBubble;
import de.itworks.absorbia.PreferenceController;
import de.itworks.absorbia.UpdateController;
import de.itworks.absorbia.WorldEntity;

/**
 * GameScreen
 * 
 * Der GameScreen ist die Anzeige während des Spielens.
 * Hier werden alle Spielfiguren und die Spielwelt erzeugt,
 * aktualisiert und auf den Bildschirm ausgegeben.
 * 
 * @author Florian Stöber
 */
public class GameScreen extends AbsorbiaScreen
{
	private UpdateController UpdateController;
	public static PlayerBubble Player;
	public static WorldEntity World;
	public static ArrayList<BubbleEntity> Bubbles;
	private FPSLogger Fps;
	private TextureRegion pauseButton;
	private SpriteBatch batch;
	private BitmapFont screenFont1;
	private String screenText1;
	public static Vector2 touchPosition;
	public static Vector2 lastTouchPos;
	public Vector3 touchPoint;

	public GameScreen(Game Game)
	{
		super(Game);
		Absorbia.AudioController.playGameMusic();
		UpdateController = new UpdateController(this);
		Bubbles = new ArrayList<BubbleEntity>();
		Fps = new FPSLogger();
		touchPosition = new Vector2();
		touchPoint = new Vector3();
		lastTouchPos = new Vector2();
		batch = new SpriteBatch();
		screenFont1 = new BitmapFont();
		pauseButton = new TextureRegion(new Texture(Gdx.files.internal("data/PauseButton.png")));
		camera = new OrthographicCamera(INTERNAL_WIDTH, INTERNAL_HEIGHT);
		camera.setToOrtho(true);
		batch.setProjectionMatrix(camera.combined);
		
		// Spieler hinzufügen
		Player = new PlayerBubble(200, 200, 50);
		Bubbles.add(Player);
		
		// Spielfeld erzeugen
		World = new WorldEntity(0, 0);
		
		// CPU-Gegner random auf Spielfeld hinzufügen
		for(int i = 0; i<30; i++)
		{
			Bubbles.add(new EnemyBubble(
					(float)Math.random() * World.bounds.width,
					(float)Math.random() * World.bounds.height,
					(float)Math.random() * 60)
			);
		}
	}

	@Override
	public void render(float delta)
	{		
		// Fps auf Console ausgeben
		Fps.log();
		
		UpdateController.update(Bubbles, Player, delta);
		// Rendern überspringen, wenn FPS unter 60 fallen
		if(delta < 0.166f)
		{			
			// Render graphics
			camera.update();
			Gdx.gl.glViewport((int)ViewPort.x, (int)ViewPort.y, (int)ViewPort.width, (int)ViewPort.height);
			
			// Toucheingabe verarbeiten, wird noch ausgelagert!
			if(Gdx.input.justTouched())
			{
				touchPoint.x = Gdx.input.getX();
				touchPoint.y = Gdx.input.getY();
				camera.unproject(touchPoint);
				touchPosition.x = touchPoint.x;
				touchPosition.y = touchPoint.y;
				lastTouchPos.set(touchPosition);
			}
			
			batch.begin();

			// Erst Hintergrund
			World.render(batch);
			
			// SpielObjekte zeichnen
			for (BubbleEntity bubbleEntity : Bubbles) {
				if (bubbleEntity.active)
				{
					bubbleEntity.render(batch);
					
					// Debug Info Spielerkoordinaten
//					if(bubbleEntity.getClass().equals(PlayerBubble.class))
//					{
						// Absorb Debugtext
//						screenText1 = "Player: X" + bubbleEntity.position.x + " Y" + bubbleEntity.position.y +
//								"\nBounds: X" + bubbleEntity.bounds.x + " Y" + bubbleEntity.bounds.y + 
//								"\nRadius: R" + bubbleEntity.bounds.radius +
//								"\nSprite: X" + bubbleEntity.sprite.getX() + " Y" + bubbleEntity.sprite.getY() +
//								"\nSprite: Width" + bubbleEntity.sprite.getWidth() + " Height" + bubbleEntity.sprite.getHeight();
						// Touch Debugtext
//						screenText1 = "Acceleration: X" + bubbleEntity.acceleration.x + " Y" + bubbleEntity.acceleration.y +
//								"\nPosition: X" + bubbleEntity.position.x + " Y" + bubbleEntity.position.y + 
//								"\nTouchPos: X" + lastTouchPos.x +
//								" Y" + lastTouchPos.y +
//								"\nAccelHelper: X" + bubbleEntity.accelerationHelper.x + " Y" + bubbleEntity.accelerationHelper.y;
//						screenFont1.drawMultiLine(
//										batch,
//										screenText1,
//										0,0);
//					}
				}
			}
			
			batch.draw(pauseButton, 0, 0);
			batch.end();
		}
	}

	@Override
	public void show() {
//		AudioController.playMusic();
		
	}

	@Override
	public void hide() {
		Absorbia.AudioController.stopGameMusic();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose()
	{
		batch.dispose();
		World.dispose();
		for (BubbleEntity bubbleEntity : Bubbles) {
			bubbleEntity.dispose();
		}
	}

}
