package de.itworks.absorbia.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * AbsorbiaScreen
 * 
 * Die Basisklasse für alle Absorbia Screens.
 * Hier werden Eigenschaften definiert, die jeder Screen haben soll.
 * 
 * @author Florian Stöber
 */
public abstract class AbsorbiaScreen implements Screen {
	protected Game game;
	protected Rectangle ViewPort;
	protected static final int INTERNAL_WIDTH = 800;
	protected static final int INTERNAL_HEIGHT = 480;
	protected static final float ASPECT_RATIO = (float)INTERNAL_WIDTH / (float)INTERNAL_HEIGHT;
	protected static float scale;
	protected OrthographicCamera camera;
	protected SpriteBatch batch;
	
	public AbsorbiaScreen(Game Game)
	{
		scale = 1f;
		ViewPort = new Rectangle();
		ViewPort.setWidth(Gdx.graphics.getWidth());
		ViewPort.setHeight(Gdx.graphics.getHeight());
		camera = new OrthographicCamera(INTERNAL_WIDTH, INTERNAL_HEIGHT);
		camera.position.set(INTERNAL_WIDTH / 2, INTERNAL_HEIGHT / 2, 0f);
		resizeScreen(ViewPort.width, ViewPort.height);
		camera.setToOrtho(true);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		game = Game;
	}
	
	public void resizeScreen(float width, float height)
	{
		float aspectRatio = (float)width / (float)height;
//		float scale = 1f;
		Vector2 crop = new Vector2(0f, 0f);
		
		if (aspectRatio < ASPECT_RATIO)
		{
			scale = (float)width / INTERNAL_WIDTH;
			crop.y = (width - INTERNAL_WIDTH * scale) / 2f;
		}
		else if (aspectRatio > ASPECT_RATIO)
		{
			scale = (float)height / INTERNAL_HEIGHT;
			crop.x = (height - INTERNAL_HEIGHT * scale) / 2f;
		}
		else
		{
			scale = (float)width / INTERNAL_WIDTH;
		}
		
		float w = (float)INTERNAL_WIDTH * scale;
		float h = (float)INTERNAL_HEIGHT * scale;
		ViewPort.set(crop.x, crop.y, w, h);
	}
	
	@Override
	public void render(float delta) {
		camera.update();
		Gdx.gl.glViewport((int)ViewPort.x, (int)ViewPort.y, (int)ViewPort.width, (int)ViewPort.height);
	}

	@Override
	public void resize(int width, int height) {
		resizeScreen(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * hide
	 * 
	 * Wird aufgerufen, wenn dieser Screen nicht mehr der aktive
	 * Screen von Game ist.
	 * Bsp.: Game.setScreen(AbsorbiaScreen)
	 * 		 Game.setScreen(MenuScreen)
	 * AbsorbiaScreen.hide() wird ausgeführt, da MenuScreen nun der
	 * aktive Screen von Game ist.
	 * 
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
