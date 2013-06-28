package de.itworks.absorbia.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * SplashScreen
 * 
 * Der SplashScreen wird bei Start der Anwendung angezeigt und
 * soll Zeit verschaffen um Objekte für das Spiel zu erzeugen.
 * 
 * @author Florian Stöber
 */
public class SplashScreen extends AbsorbiaScreen
{
	Texture splashTexture;
	Sprite splashSprite;
	SpriteBatch batch;
	float passedTime;

	public SplashScreen(Game Game)
	{
		super(Game);
	}
	
	@Override
	public void show()
	{
		camera = new OrthographicCamera(INTERNAL_WIDTH, INTERNAL_HEIGHT);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		splashSprite = new Sprite(new Texture(Gdx.files.internal("data/Splashscreen.png")));
	}

	@Override
	public void render(float delta)
	{
		camera.update();
		Gdx.gl.glViewport((int)ViewPort.x, (int)ViewPort.y, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.begin();
		batch.draw(splashSprite, -INTERNAL_WIDTH / 2, -INTERNAL_HEIGHT / 2, INTERNAL_WIDTH, INTERNAL_HEIGHT);
		batch.end();
		
		passedTime += delta;
		if(passedTime > 0f)
		{
			if(Gdx.input.justTouched())
			{
				game.setScreen(new MenuScreen(game));
			}
		}
	}
 
	@Override
	public void hide()
	{
		batch.dispose();
		splashSprite.getTexture().dispose();
	}

}
