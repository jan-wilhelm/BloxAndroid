package net.janwilhelm.blox.screens.transition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import net.janwilhelm.blox.Utilities;

public class SlideInTransition extends Transition {

    private float totalDeltas;

    public SlideInTransition(float duration, Runnable finish) {
        super(duration, finish);
    }

    @Override
    public void render(Stage inStage, Stage outStage, float delta) {
        Utilities.renderBackground();
        totalDeltas += delta;
        if (totalDeltas >= duration) {
            inStage.getCamera().position.y = Gdx.graphics.getHeight() / 2;
            inStage.getCamera().update();
            outStage.getCamera().position.y = Gdx.graphics.getHeight() * 3 / 2;
            outStage.getCamera().update();

            inStage.act(delta);
            outStage.act(delta);
            inStage.draw();
            outStage.draw();

            finish.run();
            return;
        }

        final int halfHeight = Gdx.graphics.getHeight() / 2;
        final int x = Math.round(Gdx.graphics.getHeight() * Math.min(Math.max(1 - totalDeltas / this.duration, 0), 1));

        final int inY = x + halfHeight;
        final int outY = x - halfHeight;

        inStage.getCamera().position.y = inY;
        outStage.getCamera().position.y = outY;
        inStage.getCamera().update();
        outStage.getCamera().update();

        inStage.act(delta);
        outStage.act(delta);
        inStage.draw();
        outStage.draw();
    }
}
