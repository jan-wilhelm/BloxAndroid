package net.janwilhelm.blox.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.janwilhelm.blox.colors.ColorManager;

public class MenuState extends State {

    public MenuState(GameStateManager gameStateManager, ColorManager colorManager) {
        super(gameStateManager, colorManager);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.colorManager.getActiveColor().getGdxColor());

        // Test render
        shapeRenderer.rect(0,0, 100, 100);
        shapeRenderer.end();
    }

}
