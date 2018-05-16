package net.janwilhelm.blox.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.janwilhelm.blox.Blox;
import net.janwilhelm.blox.fonts.Font;

import static com.badlogic.gdx.graphics.Color.WHITE;

public class GameState extends State {

    private Font scoreFont;
    private Sprite rectangle;

    public GameState(Blox blox) {
        super(blox);
        this.scoreFont = new Font("data/arial-black-72.fnt", "Test Text", false);
        this.scoreFont.setColor(1,1,1,0.8f);
        this.rectangle = new Sprite(Blox.createRoundedRectangle(blox.screenWidth, 100, 20, WHITE));
        this.rectangle.setAlpha(0.8f);
        this.rectangle.setPosition(0, this.blox.screenHeight - rectangle.getHeight());
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        this.blox.renderBackground();
        batch.begin();
        rectangle.draw(batch);
        scoreFont.render(batch, this.blox.screenWidth / 2, this.blox.screenHeight);
        batch.end();
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    protected void handleInput() {

    }
}
