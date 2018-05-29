package net.janwilhelm.blox;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
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
	private Group centerTiles = new Group(); // The four squares centered in the stage
	private ArrayList<GameColor> currentColors; // An ordered list of the current state of the board represented by its colors
	private UpperBarActor upperBarActor; // The white bar on the upper end of the screen

	/**
	 * Initialize all variables and set up the stage
	 */
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
		upperBarActor.setY(screenHeight - 125); // Move the bar to the top of the screen
		gameStage.addActor(upperBarActor);
	}

	/**
	 * Handles tap events from the user
	 * @param actor The TileActor which was tapped in the event
	 */
	@Override
	public void tapped(TileActor actor) {
		if (colorManager.isColor(actor.getColor())) {
			this.next();
		}
	}

	/**
	 * When the right TileActor was tapped, this function increases the score and resets the UI to a next round with randomly chosen colors
	 */
	public void next() {
		colorManager.refreshColor();

		score ++;
		upperBarActor.scoreLabel.setText(score + "");

		final Color newColor = colorManager.getActiveColor().getGdxColor();
		this.upperBarActor.scoreLabel.setColor(newColor.r, newColor.g, newColor.b, 1f);

		// this makes sure that we never get the same order of colors on the board twice in a row
		ArrayList<GameColor> newColors;
		do {
			newColors = colorManager.getShuffledColors();
		} while (newColors == currentColors);

		currentColors = newColors;

		int index = 0;
		for (Actor actor : this.centerTiles.getChildren()) {
			final TileActor tileActor = (TileActor) actor;
			tileActor.setColor(newColors.get(index));	// update the colors of all the TileActors
			index++;
		}
	}

	/**
	 * UI building helper function which creates the four main central squares
	 */
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
			tileActor.refreshBounds(); // set the new bounds. This is extremely important when it comes to check if a tap event was inside a certain TileActor
			this.centerTiles.addActor(tileActor);
			index ++;
		}
		gameStage.addActor(centerTiles);
	}

	/**
	 * UI helper function creating the white background for the center squares
	 */
	private void createWhiteBackgroundTile() {
		final TileActor whiteBackgroundActor = new TileActor(Corner.values(), 440, 440, 50, new GameColor("white", Color.WHITE));
		whiteBackgroundActor.texture.setAlpha(0.8f);
		whiteBackgroundActor.setTouchable(Touchable.disabled);
		whiteBackgroundActor.setPosition(screenWidth / 2 - 220, screenHeight / 2 - 220);		// center the square
		gameStage.addActor(whiteBackgroundActor);
	}

	/**
	 * Render the stage. Gets called 60 times a second
	 */
	@Override
	public void render () {
		//logger.log();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		final Color color = this.getColorManager().getActiveColor().getGdxColor();
		Gdx.gl.glClearColor(color.r, color.g, color.b, 1f); // set the background color

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
