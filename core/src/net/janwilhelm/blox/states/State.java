package net.janwilhelm.blox.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import net.janwilhelm.blox.colors.ColorManager;

public abstract class State {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;
    protected ColorManager colorManager;

    // Offset along the y-axis.
    // This could be used for the "sliding" animation
    // which is already implemented in the iOS version
    // of Blox.
    protected float yOffsetForView = 0F;

    protected State(GameStateManager gameStateManager, ColorManager colorManager) {
        // Set or initialize the attributes
        this.gameStateManager = gameStateManager;
        this.colorManager = colorManager;
        this.cam = new OrthographicCamera();
        this.mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch batch, ShapeRenderer shapeRenderer);

}
