package net.janwilhelm.blox.states;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.janwilhelm.blox.Blox;
import net.janwilhelm.blox.colors.GameColor;
import net.janwilhelm.blox.fonts.Font;
import net.janwilhelm.blox.sprites.Corner;

import static com.badlogic.gdx.graphics.Color.WHITE;

public class GameState extends State {

    private Font scoreFont;
    private Sprite rectangle;
    private Sprite[] tiles;

    public GameState(Blox blox) {
        super(blox);

        this.createUpperbar();
        this.createRectangles();
    }

    private void createUpperbar() {
        this.scoreFont = new Font("data/arial-black-120.fnt", "0", false);

        final Color activeColor = blox.getColorManager().getActiveColor().getGdxColor();
        this.scoreFont.setColor(activeColor.r, activeColor.g, activeColor.b, activeColor.a);
        this.rectangle = new Sprite(Blox.createRoundedRectangle(Corner.bottom(), blox.screenWidth, 100, 20, WHITE));

        this.rectangle.setAlpha(0.8f);
        this.rectangle.setPosition(0, this.blox.screenHeight - rectangle.getHeight());
    }

    private void createRectangles() {
        int index = 0;
        for(GameColor color : blox.getColorManager().getShuffledColors()) {
            /*for color in (self.playGameDelegate?.getColorManager().getShuffledColors())! {
                    let rect = CGRect(x: (index % 2 == 0 ? (-200) : (0)), y: (index < 2 ? (-200) : (0)), width: 200, height: 200)
            let path = UIBezierPath(roundedRect: rect, byRoundingCorners: [corners[index]], cornerRadii: CGSize(width: 50, height: 50)).cgPath

            let sprite = SKShapeNode(path: path)
            sprite.fillColor = color.uiColor

            self.addChild(sprite)

            tiles.append(sprite)
            index += 1*/

            // SWIFT Code that needs to be implemented. TODO
        }

    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        this.blox.renderBackground();
        batch.begin();
        rectangle.draw(batch);
        scoreFont.render(batch, this.blox.screenWidth / 2, this.blox.screenHeight - 10);
        batch.end();
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    protected void handleInput() {

    }
}
