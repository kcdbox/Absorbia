package de.itworks.absorbia.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import de.itworks.absorbia.Absorbia;
import de.itworks.absorbia.AudioController;
import de.itworks.absorbia.MenuItem;
import de.itworks.absorbia.Screens.*;

/**
 * MenuScreen
 * 
 * Der Menübildschirm mit klickbaren Bildschirmelementen.
 * 
 * @author Florian Stöber
 */
public class MenuScreen extends AbsorbiaScreen
{
	Texture logo;
	Texture background;
	Rectangle spBounds;
	Texture buttonSingleplayer;
	Rectangle rulBounds;
	Texture buttonGameRules;
	Rectangle optBounds;
	Texture buttonOptions;
	Rectangle rtgBounds;
	Texture buttonReturnToGame;
	MenuItem foobar;
	SpriteBatch batch;
	float passedTime = 0;

	public MenuScreen(Game game) {
		super(game);
	}

	@Override
	public void show ()
	{
		Absorbia.AudioController.playMenuMusic();
		camera = new OrthographicCamera(INTERNAL_WIDTH, INTERNAL_HEIGHT);
		camera.setToOrtho(true);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		logo = new Texture(Gdx.files.internal("data/Logo.png"));
		background = new Texture(Gdx.files.internal("data/MenuBackground.png"));
		buttonSingleplayer = new Texture(Gdx.files.internal("data/Singleplayer.png"));
		buttonSingleplayer = new Texture(Gdx.files.internal("data/Singleplayer.png"));
		buttonGameRules = new Texture(Gdx.files.internal("data/GameRules.png"));
		buttonOptions = new Texture(Gdx.files.internal("data/Options.png"));
		buttonReturnToGame = new Texture(Gdx.files.internal("data/ReturnToGame.png"));
		spBounds = new Rectangle(500, 400, buttonSingleplayer.getWidth() / 2, buttonSingleplayer.getHeight() / 2);
		
		batch = new SpriteBatch();
	}

	@Override
	public void render (float delta) {
		camera.update();
		Gdx.gl.glViewport((int)ViewPort.x, (int)ViewPort.y, (int)ViewPort.width, (int)ViewPort.height);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(Gdx.input.justTouched())
		{
//			touchPoint.x = Gdx.input.getX();
//			touchPoint.y = Gdx.input.getY();
//			camera.unproject(touchPoint);
//			touchPos.x = touchPoint.x;
//			touchPos.y = touchPoint.y;
//			lastTouchPos.set(touchPos);
//			
//			if(spBounds.contains(touchPos))
//			{
				game.setScreen(new GameScreen(game));
//			}
		}
		
		batch.begin();
		batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
		batch.draw(logo, 140, 500);
		batch.draw(
				buttonSingleplayer,
				500,
				400,
				buttonSingleplayer.getWidth() / 2,
				buttonSingleplayer.getHeight() / 2);
		batch.draw(buttonOptions, 500, 300, buttonOptions.getWidth() / 2, buttonOptions.getHeight() / 2);
		batch.draw(buttonGameRules, 500, 200, buttonGameRules.getWidth() / 2, buttonGameRules.getHeight() / 2);
		batch.draw(buttonReturnToGame, 500, 100, buttonReturnToGame.getWidth() / 2, buttonReturnToGame.getHeight() / 2);
		
		batch.end();
	}

	@Override
	public void hide () {
		Absorbia.AudioController.stopMenuMusic();
		batch.dispose();
	}
	
	@Override
	public void pause() {
		game.setScreen(new MenuScreen(game));
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
