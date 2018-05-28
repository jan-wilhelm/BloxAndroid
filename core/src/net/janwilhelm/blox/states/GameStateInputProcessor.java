package net.janwilhelm.blox.states;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameStateInputProcessor implements InputProcessor {

    private final GameState state;

    public GameStateInputProcessor(GameState state) {
        this.state = state;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenY = this.state.blox.screenHeight - screenY; // Get the "real" y
        final Sprite tile = this.state.getTileFromPosition(screenX, screenY);

        if (tile != null) {

            if (this.state.blox.getColorManager().isColor(tile.getColor())) {
                this.state.next();
            } else {
                this.state.lost();
            }
        }

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
