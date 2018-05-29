package net.janwilhelm.blox.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;

import net.janwilhelm.blox.Utilities;
import net.janwilhelm.blox.colors.GameColor;
import net.janwilhelm.blox.sprites.Corner;
import net.janwilhelm.blox.sprites.TileActor;

public class MenuScreen implements Screen {

    public Stage stage = new Stage();

    public MenuScreen() {
        TileActor a = new TileActor(Corner.top(), Gdx.graphics.getWidth(), 50, 20, new GameColor("a", Color.PINK));
        TileActor b = new TileActor(Corner.bottom(), Gdx.graphics.getWidth(), 50, 20, new GameColor("a", Color.BROWN));
        a.setY(0);
        b.setY(Gdx.graphics.getHeight() - 50);
        stage.addActor(a);
        stage.addActor(b);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Utilities.renderBackground();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }
}
