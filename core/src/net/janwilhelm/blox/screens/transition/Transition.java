package net.janwilhelm.blox.screens.transition;

import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Transition {

    protected float duration;
    protected Runnable finish;

    public Transition(float duration, Runnable finish) {
        this.duration = duration;
        this.finish = finish;
    }

    public abstract  void render(Stage st1, Stage st2, float delta);

}
