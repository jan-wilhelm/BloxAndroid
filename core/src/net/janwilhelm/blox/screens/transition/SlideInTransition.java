package net.janwilhelm.blox.screens.transition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SlideInTransition extends Transition {

    private float totalDeltas;
    private SlideInDirection direction;
    private Runnable renderHook;

    public SlideInTransition(float duration, Runnable finish, Runnable renderHook, SlideInDirection direction) {
        super(duration, finish);
        this.direction = direction;
        this.renderHook = renderHook;
    }

    public SlideInTransition(float duration, Runnable finish, SlideInDirection direction) {
        this(duration, finish, null, direction);
    }

    public SlideInTransition(float duration, SlideInDirection direction) {
        this(duration, null, direction);
    }

    @Override
    public void render(Stage inStage, Stage outStage, float delta) {
        if (renderHook != null) {
            renderHook.run();
        }

        totalDeltas += delta;
        if (totalDeltas >= duration) {
            setEndPosition(inStage, outStage);

            updateAndHandle(inStage, outStage, delta);
            finish.run();
            return;
        }

        final int halfHeight = Gdx.graphics.getHeight() / 2;
        final int halfWidth = Gdx.graphics.getWidth() / 2;
        final float process = Math.min(Math.max(totalDeltas / this.duration, 0), 1);

        updatePositions(inStage, outStage, halfHeight, halfWidth, process);

        updateAndHandle(inStage, outStage, delta);
    }

    private void updatePositions(Stage inStage, Stage outStage, int halfHeight, int halfWidth, float process) {
        float newInY = inStage.getCamera().position.y;
        float newInX = inStage.getCamera().position.x;
        float newOutY = outStage.getCamera().position.y;
        float newOutX = outStage.getCamera().position.x;

        switch (this.direction) {
            case TOP_TO_BOTTOM:
                final int y1 = Math.round(Gdx.graphics.getHeight() * (process));
                newInY = y1 - halfHeight;
                newOutY = y1 + halfHeight;
                break;
            case BOTTOM_TO_TOP:
                final int y2 = Math.round(Gdx.graphics.getHeight() * (1 - process));
                newInY = y2 + halfHeight;
                newOutY = y2 - halfHeight;
                break;
            default:
                break;
        }
        inStage.getCamera().position.y = newInY;
        outStage.getCamera().position.y = newOutY;
        inStage.getCamera().position.x = newInX;
        outStage.getCamera().position.x = newOutX;
    }

    private void setEndPosition(Stage inStage, Stage outStage) {
        switch (this.direction) {
            case BOTTOM_TO_TOP:
                inStage.getCamera().position.y = Gdx.graphics.getHeight() / 2;
                outStage.getCamera().position.y = Gdx.graphics.getHeight() * 3 / 2;
                break;
            case TOP_TO_BOTTOM:
                inStage.getCamera().position.y = Gdx.graphics.getHeight() * 3/ 2;
                outStage.getCamera().position.y = Gdx.graphics.getHeight() / 2;
                break;
            default:
                break;
        }
    }

    private void updateAndHandle(Stage inStage, Stage outStage, float delta) {
        inStage.getCamera().update();
        outStage.getCamera().update();

        inStage.act(delta);
        outStage.act(delta);
        inStage.draw();
        outStage.draw();
    }
}
