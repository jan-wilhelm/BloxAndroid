package net.janwilhelm.blox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;

import net.janwilhelm.blox.colors.ColorManager;
import net.janwilhelm.blox.screens.GameScreen;
import net.janwilhelm.blox.screens.MenuScreen;
import net.janwilhelm.blox.screens.TransitionScreen;
import net.janwilhelm.blox.screens.transition.SlideInDirection;
import net.janwilhelm.blox.screens.transition.SlideInTransition;
import net.janwilhelm.blox.sprites.TileActor;

public class Blox extends Game implements GameLogicHandler {

	private ColorManager colorManager;
	private FPSLogger logger;

	private MenuScreen menuScreen;
	private GameScreen gameScreen;

	public int screenWidth, screenHeight;
	public int score = 0;

	private static Blox instance;

	/**
	 * Initialize all variables and set up the stage
	 */
	@Override
	public void create () {
		instance = this;

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		// Initialize the needed attributes in order to pass them to the States
		colorManager = new ColorManager();

		logger = new FPSLogger();

		this.menuScreen = new MenuScreen();

		this.gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	/**
	 * Transition to the MenuScreen using a cool SlideInTransition
	 */
	public void goToMenu() {
		final TransitionScreen transitionScreen = new TransitionScreen(new SlideInTransition(0.5f, new Runnable() {
			@Override
			public void run() {
				setScreen(menuScreen);
			}
		}, new Runnable() {
			@Override
			public void run() {
				Utilities.renderBackground();
			}
		}, SlideInDirection.BOTTOM_TO_TOP), menuScreen.stage, gameScreen.gameStage);
		setScreen(transitionScreen);
	}

	/**
	 * Transition to the MenuScreen using a cool SlideInTransition
	 */
	public void goToGameScreen() {
		final TransitionScreen transitionScreen = new TransitionScreen(new SlideInTransition(0.5f, new Runnable() {
			@Override
			public void run() {
				setScreen(gameScreen);
			}
		}, new Runnable() {
			@Override
			public void run() {
				Utilities.renderBackground();
			}
		}, SlideInDirection.TOP_TO_BOTTOM), gameScreen.gameStage, menuScreen.stage);
		setScreen(transitionScreen);
	}

	/**
	 * Handles tap events from the user
	 * @param actor The TileActor which was tapped in the event
	 */
	@Override
	public void tapped(TileActor actor) {
		if (colorManager.isColor(actor.getColor())) {
			this.next();
		} else {
			this.lost();
		}
	}

	/**
	 * When the right TileActor was tapped, this function increases the score and resets the UI to a next round with randomly chosen colors
	 */
	public void next() {
		colorManager.refreshColor();

		score ++;
		this.gameScreen.next();
	}

	public void lost() {
		score = 0;
		goToMenu();
	}

	/**
	 * Render the stage. Gets called 60 times a second
	 */
	@Override
	public void render () {
		logger.log();
		super.render();
	}

	@Override
	public void dispose () {
		gameScreen.dispose();
	}

	public ColorManager getColorManager() {
		return colorManager;
	}

	public static Blox instance() {
		return instance;
	}

}
