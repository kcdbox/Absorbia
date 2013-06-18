package de.itworks.absorbia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class AbsorbiaGame implements ApplicationListener {
	private RenderDelegate renderer;
	private UpdateDelegate updater;
	private BubbleEntity player;
	private WorldEntity world;
	private ArrayList<GameEntity> entities;
	private float delta;
	private FPSLogger fps;
	
	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		updater = new UpdateDelegate();
		renderer = new RenderDelegate();
		entities = new ArrayList<GameEntity>();
		fps = new FPSLogger();
		
		player = new BubbleEntity(0, 0, 50);
		entities.add(player);
		world = new WorldEntity(0, 0);
		entities.add(world);
	}

	@Override
	public void dispose() {
		renderer.dispose(entities);
	}

	@Override
	public void render() {
		// Time since last render() call
		delta = Gdx.graphics.getDeltaTime();
		
		// Print FPS to console
		fps.log();
		
		// Clear screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		updater.update();
		
		// Skip frame rendering if fps drops below 60
		if(delta < 0.166f)
		{			
			// Render graphics
			renderer.render(entities);
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
