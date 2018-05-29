package net.janwilhelm.blox.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import net.janwilhelm.blox.screens.transition.Transition;

public class TransitionScreen implements Screen {

    public Game game;
    public Stage inStage, outStage;
    public Transition transition;

    public TransitionScreen(Transition transition, Stage inStage, Stage outStage) {
        this.transition = transition;
        this.inStage = inStage;
        this.outStage = outStage;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        transition.render(inStage, outStage, delta);
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

