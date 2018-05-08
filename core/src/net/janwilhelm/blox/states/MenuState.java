package net.janwilhelm.blox.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.janwilhelm.blox.Blox;

public class MenuState extends State {

    private BitmapFont font;
    private GlyphLayout layout;

    public MenuState(Blox blox) {
        super(blox);
        font = new BitmapFont(Gdx.files.internal("data/arial-black-120.fnt"), false);
        font.setColor(1,1,1,0.8f);
        layout = new GlyphLayout(font, "Hello World!");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        // Test render
        this.blox.renderBackground();
        batch.begin();
        font.draw(batch, "Hello World", (blox.screenWidth - layout.width) / 2, (blox.screenHeight) / 2);
        batch.end();
    }

}
