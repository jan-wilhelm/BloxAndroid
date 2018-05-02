package net.janwilhelm.blox;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.janwilhelm.blox.colors.ColorManager;
import net.janwilhelm.blox.states.GameStateManager;
import net.janwilhelm.blox.states.MenuState;

public class Blox extends ApplicationAdapter {

	SpriteBatch batch;
	private GameStateManager gameStateManager;
	private ColorManager colorManager;
	private ShapeRenderer shapeRenderer;
	private FPSLogger logger;
	
	@Override
	public void create () {
		colorManager = new ColorManager();
		gameStateManager = new GameStateManager();
		shapeRenderer = new ShapeRenderer();

		logger = new FPSLogger();

		batch = new SpriteBatch();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		gameStateManager.push(new MenuState(gameStateManager, colorManager));
	}

	@Override
	public void render () {
		logger.log();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(this.batch, this.shapeRenderer);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
