package de.itworks.absorbia;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;

public class RenderDelegate {
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont font;
	
	public RenderDelegate()
	{
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
	}
	
	// Render all active entities
	public void render(ArrayList<GameEntity> entities)
	{		
		batch.begin();
		font.draw(batch, "Testasdadasdasdasdasdasdadsasdasdasdasdwrölwjglegrlshglkrhselkr", 0, 0);
		
		for (GameEntity gameEntity : entities) {
			if (gameEntity.active)
			{
				gameEntity.render(batch);
			}
		}
		
		batch.end();
	}
	
	// Free memory
	public void dispose(ArrayList<GameEntity> entities)
	{
		batch.dispose();
		for (GameEntity gameEntity : entities) {
			gameEntity.dispose();
		}
	}
}
