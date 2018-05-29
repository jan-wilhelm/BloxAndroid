package net.janwilhelm.blox.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import net.janwilhelm.blox.Utilities;
import net.janwilhelm.blox.colors.GameColor;
import net.janwilhelm.blox.fonts.Font;

public class UpperBarActor extends Actor {

    private Sprite rectangle;
    private GameColor color;
    public Font scoreLabel;

    public UpperBarActor(int width, int height, int cornerRadius, GameColor activeColor) {
        this.setWidth(width);
        this.setHeight(height);

        this.scoreLabel = new Font("data/arial-black-120.fnt", "0", false);
        this.setColor(activeColor);
        this.rectangle = new Sprite(Utilities.createRoundedRectangle(Corner.bottom(), width, height, cornerRadius, Color.WHITE));
        this.rectangle.setAlpha(0.8f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.rectangle.draw(batch);
        this.scoreLabel.render(batch, this.getWidth() / 2, this.getY() + 105);
    }

    public void setColor(GameColor color) {
        this.color = color;
        final Color gdxColor = color.getGdxColor();
        this.scoreLabel.setColor(gdxColor.r, gdxColor.g, gdxColor.b, gdxColor.a);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        this.rectangle.setX(x);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        this.rectangle.setY(y);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
}
