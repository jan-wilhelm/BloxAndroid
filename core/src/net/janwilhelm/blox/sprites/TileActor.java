package net.janwilhelm.blox.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import net.janwilhelm.blox.GameLogicHandler;
import net.janwilhelm.blox.Utilities;
import net.janwilhelm.blox.colors.GameColor;

/**
 * This class represents one of the four squares in the center of the screen. Each of those squares
 * has its own color and position.
 */
public class TileActor extends Actor {

    public Sprite texture; // The texture of the Actor which gets generated before rendering and therefore improves performance
    public GameLogicHandler gameLogicHandler; // Callback handler which gets used when a user taps on the Actor

    private GameColor color;
    private Corner[] corners; // The corners which should be rounded
    private int cornerRadius;

    public TileActor(Corner[] corners, int width, int height, int cornerRadius, final GameColor color) {
        this.setWidth(width);
        this.setHeight(height);
        this.refreshBounds();

        this.corners = corners;

        this.cornerRadius = cornerRadius;
        this.color = color;
        this.texture = new Sprite(Utilities.createRoundedRectangle(corners, width, height, cornerRadius, Color.WHITE));
        this.texture.setColor(color.getGdxColor());

        final TileActor instance = this; // Create a copy of the instance of the TileActor to use it in the anonymous InputListener
        addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                event.handle();
                if (gameLogicHandler != null) {
                    gameLogicHandler.tapped(instance);
                }
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // return true in order to continue handling the event in #touchUp
                return true;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        texture.draw(batch);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        texture.setX(x);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        texture.setY(y);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        this.setX(x);
        this.setY(y);
    }

    /**
     * Always call this function after moving / resizing / transforming the Actors. Otherwise, the taps might not get passed through to the actor.
     */
    public void refreshBounds() {
        setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public Color getColor() {
        return this.color.getGdxColor();
    }

    public void setColor(GameColor color) {
        this.color = color;
        this.texture.setColor(color.getGdxColor());
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        System.out.println("Setting bounds with x " + x + " y " + y + " w " + width + " h " + height);
        super.setBounds(x, y, width, height);
    }
}
