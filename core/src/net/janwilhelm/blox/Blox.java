package net.janwilhelm.blox;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import net.janwilhelm.blox.colors.ColorManager;
import net.janwilhelm.blox.colors.GameColor;
import net.janwilhelm.blox.sprites.Corner;
import net.janwilhelm.blox.sprites.TileActor;
import net.janwilhelm.blox.sprites.UpperBarActor;

import java.util.ArrayList;

public class Blox extends ApplicationAdapter implements GameLogicHandler {

	private ColorManager colorManager;
	private FPSLogger logger;
	public int screenWidth, screenHeight;
	public int score = 0;

	private Stage gameStage;
	private Group centerTiles = new Group();
	private ArrayList<GameColor> currentColors;
	private UpperBarActor upperBarActor;

	@Override
	public void create () {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		// Initialize the needed attributes in order to pass them to the States
		colorManager = new ColorManager();

		logger = new FPSLogger();

		gameStage = new Stage();
		Gdx.input.setInputProcessor(gameStage);

		this.createCenterTiles();

		upperBarActor = new UpperBarActor(screenWidth, 125, 30, this.colorManager.getActiveColor());
		upperBarActor.setX(0);
		upperBarActor.setY(screenHeight - 125);
		gameStage.addActor(upperBarActor);
	}

	@Override
	public void tapped(TileActor actor) {
		if (colorManager.isColor(actor.getColor())) {
			this.next();
		}
	}

	public void next() {
		colorManager.refreshColor();

		score ++;
		upperBarActor.scoreLabel.setText(score + "");

		final Color newColor = colorManager.getActiveColor().getGdxColor();
		this.upperBarActor.scoreLabel.setColor(newColor.r, newColor.g, newColor.b, 1f);

		ArrayList<GameColor> newColors;
		do {
			newColors = colorManager.getShuffledColors();
		} while(newColors == currentColors);

		currentColors = newColors;

		int index = 0;
		for (Actor actor : this.centerTiles.getChildren()) {
			final TileActor tileActor = (TileActor) actor;
			tileActor.setColor(newColors.get(index));
			index++;
		}
	}

	private void createCenterTiles() {
		this.createWhiteBackgroundTile();

		int index = 0;
		this.currentColors = colorManager.getShuffledColors();
		for(GameColor color : currentColors) {

			final TileActor tileActor = new TileActor(new Corner[] {Corner.values()[index]}, 200,200,50, color);
			tileActor.setTouchable(Touchable.enabled);
			tileActor.gameLogicHandler = this;

			// Set the position using some simple logic depending on the index of the color
			final float x = this.screenWidth / 2 + ((index % 2 == 0) ? (-200f) : (0f));
			final float y = this.screenHeight / 2 + ((index < 2 ? (-200f) : (0f)));

			tileActor.setX(x);
			tileActor.setY(y);
			tileActor.refreshBounds();
			this.centerTiles.addActor(tileActor);
			index ++;
		}
		gameStage.addActor(centerTiles);
	}

	private void createWhiteBackgroundTile() {
		final TileActor whiteBackgroundActor = new TileActor(Corner.values(), 440, 440, 50, new GameColor("white", Color.WHITE));
		whiteBackgroundActor.texture.setAlpha(0.8f);
		whiteBackgroundActor.setTouchable(Touchable.disabled);
		whiteBackgroundActor.setPosition(screenWidth / 2 - 220, screenHeight / 2 - 220);
		gameStage.addActor(whiteBackgroundActor);
	}

	@Override
	public void render () {
		//logger.log();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		final Color color = this.getColorManager().getActiveColor().getGdxColor();
		Gdx.gl.glClearColor(color.r, color.g, color.b, 1f);

		gameStage.act(Gdx.graphics.getDeltaTime());
		gameStage.draw();
	}

	@Override
	public void dispose () {
		gameStage.dispose();
	}

	public ColorManager getColorManager() {
		return colorManager;
	}

}
