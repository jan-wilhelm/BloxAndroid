package net.janwilhelm.blox;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.janwilhelm.blox.colors.ColorManager;
import net.janwilhelm.blox.sprites.Corner;
import net.janwilhelm.blox.states.GameState;
import net.janwilhelm.blox.states.GameStateManager;
import net.janwilhelm.blox.states.MenuState;

public class Blox extends ApplicationAdapter {

	SpriteBatch batch;
	private GameStateManager gameStateManager;
	private ColorManager colorManager;
	private ShapeRenderer shapeRenderer;
	private FPSLogger logger;
	public int screenWidth, screenHeight;
	public int score = 0;

	@Override
	public void create () {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		// Initialize the needed attributes in order to pass them to the States
		colorManager = new ColorManager();
		gameStateManager = new GameStateManager();
		shapeRenderer = new ShapeRenderer();

		logger = new FPSLogger();

		batch = new SpriteBatch();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		gameStateManager.push(new GameState(this));
	}

	@Override
	public void render () {
		logger.log();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(this.batch, this.shapeRenderer);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public ColorManager getColorManager() {
		return colorManager;
	}

	public GameStateManager getGameStateManager() {
		return gameStateManager;
	}

	public void renderBackground() {
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(this.getColorManager().getActiveColor().getGdxColor());
		shapeRenderer.rect(0,0, this.screenWidth, this.screenHeight);
		shapeRenderer.end();
	}

	public static boolean contains(Object[] array, Object obj) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == obj || array[i].equals(obj)) {
				return true;
			}
		}
		return false;
	}

	public static Corner[] getInverseCorners(Corner[] corners) {
		Corner[] inverseCorners = new Corner[4 - corners.length];
		int i = 0;
		for(Corner corner : Corner.values()) {
			if (!contains(corners, corner)) {
				inverseCorners[i] = corner;
				i++;
			}
		}
		return inverseCorners;
	}

	public static Texture createRoundedRectangle(Corner[] corners, int width, int height, int cornerRadius, Color color) {
		Texture retTexture;
		Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
		Pixmap ret = new Pixmap(width, height, Pixmap.Format.RGBA8888);

		pixmap.setColor(color);

		Corner[] inverseCorners = getInverseCorners(corners);

		for (Corner corner: corners) {
			switch (corner) {
				case TOP_LEFT:
					pixmap.fillCircle(cornerRadius, cornerRadius, cornerRadius);
					break;
				case TOP_RIGHT:
					pixmap.fillCircle(width - cornerRadius - 1, cornerRadius, cornerRadius);
					break;
				case BOTTOM_LEFT:
					pixmap.fillCircle(cornerRadius, height - cornerRadius - 1, cornerRadius);
					break;
				case BOTTOM_RIGHT:
					pixmap.fillCircle(width - cornerRadius - 1, height - cornerRadius - 1, cornerRadius);
					break;
			}
		}

		for (Corner corner: inverseCorners) {
			switch (corner) {
				case TOP_LEFT:
					pixmap.fillRectangle(0,0,cornerRadius,cornerRadius);
					break;
				case TOP_RIGHT:
					pixmap.fillRectangle(width - cornerRadius,0,cornerRadius,cornerRadius);
					break;
				case BOTTOM_LEFT:
					pixmap.fillRectangle(0,height - cornerRadius, cornerRadius,cornerRadius);
					break;
				case BOTTOM_RIGHT:
					pixmap.fillRectangle(width - cornerRadius,height - cornerRadius, cornerRadius,cornerRadius);
					break;
			}
		}

		pixmap.fillRectangle(cornerRadius, 0, width - cornerRadius * 2, height);
		pixmap.fillRectangle(0, cornerRadius, width, height - cornerRadius * 2);

		ret.setColor(color);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (pixmap.getPixel(x, y) != 0) ret.drawPixel(x, y);
			}
		}
		retTexture = new Texture(pixmap);
		pixmap.dispose();

		return retTexture;
	}

	public static Texture createRoundedRectangle(int width, int height, int cornerRadius, Color color) {
		Corner[] allCorners = Corner.values();
		return createRoundedRectangle(allCorners, width, height, cornerRadius, color);
	}
}
