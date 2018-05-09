package net.janwilhelm.blox.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.janwilhelm.blox.Blox;
import net.janwilhelm.blox.fonts.Font;

public class MenuState extends State {

    private Font helloWorldFont;

    public MenuState(Blox blox) {
        super(blox);
        this.helloWorldFont = new Font("data/arial-black-120.fnt", "Hello World", false);
        this.helloWorldFont.setColor(1,1,1,0.8f);
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
        this.helloWorldFont.render(batch, blox.screenWidth / 2, blox.screenHeight / 2);
        batch.end();
    }

}
