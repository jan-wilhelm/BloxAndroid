package net.janwilhelm.blox.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import net.janwilhelm.blox.Blox;
import net.janwilhelm.blox.Utilities;
import net.janwilhelm.blox.colors.GameColor;
import net.janwilhelm.blox.sprites.Corner;
import net.janwilhelm.blox.sprites.TileActor;
import net.janwilhelm.blox.sprites.UpperBarActor;

import java.util.ArrayList;

public class GameScreen implements Screen {

    public Stage gameStage;
    private Group centerTiles = new Group(); // The four squares centered in the stage
    private ArrayList<GameColor> currentColors; // An ordered list of the current state of the board represented by its colors
    private UpperBarActor upperBarActor; // The white bar on the upper end of the screen

    public GameScreen() {
        gameStage = new Stage();
        Gdx.input.setInputProcessor(gameStage);

        this.createCenterTiles();

        upperBarActor = new UpperBarActor(Blox.instance().screenWidth, 125, 30, Blox.instance().getColorManager().getActiveColor());
        upperBarActor.setX(0);
        upperBarActor.setY(Blox.instance().screenHeight - 125); // Move the bar to the top of the screen
        gameStage.addActor(upperBarActor);
    }


    /**
     * UI building helper function which creates the four main central squares
     */
    private void createCenterTiles() {
        this.createWhiteBackgroundTile();

        int index = 0;
        this.currentColors = Blox.instance().getColorManager().getShuffledColors();
        for(GameColor color : currentColors) {

            final TileActor tileActor = new TileActor(new Corner[] {Corner.values()[index]}, 200,200,50, color);
            tileActor.setTouchable(Touchable.enabled);
            tileActor.gameLogicHandler = Blox.instance();

            // Set the position using some simple logic depending on the index of the color
            final float x = Blox.instance().screenWidth / 2 + ((index % 2 == 0) ? (-200f) : (0f));
            final float y = Blox.instance().screenHeight / 2 + ((index < 2 ? (-200f) : (0f)));

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
        whiteBackgroundActor.setPosition(Blox.instance().screenWidth / 2 - 220, Blox.instance().screenHeight / 2 - 220);		// center the square
        gameStage.addActor(whiteBackgroundActor);
    }

    public void next() {
        upperBarActor.scoreLabel.setText(Blox.instance().score + "");

        final Color newColor = Blox.instance().getColorManager().getActiveColor().getGdxColor();
        this.upperBarActor.scoreLabel.setColor(newColor.r, newColor.g, newColor.b, 1f);

        // this makes sure that we never get the same order of colors on the board twice in a row
        ArrayList<GameColor> newColors;
        do {
            newColors = Blox.instance().getColorManager().getShuffledColors();
        } while (newColors == currentColors);

        currentColors = newColors;

        int index = 0;
        for (Actor actor : this.centerTiles.getChildren()) {
            final TileActor tileActor = (TileActor) actor;
            tileActor.setColor(newColors.get(index));	// update the colors of all the TileActors
            index++;
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        System.out.println("gameScreen render");
        Utilities.renderBackground();
        gameStage.act(Gdx.graphics.getDeltaTime());
        gameStage.draw();
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

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameStage.dispose();
    }
}
