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
    private Sprite[] tiles = new Sprite[4];
    private Sprite whiteBackgroundTile;

    public GameState(Blox blox) {
        super(blox);

        this.createUpperbar();
        this.createRectangles();
    }

    private void createUpperbar() {
        this.scoreFont = new Font("data/arial-black-120.fnt", "0", false);

        final Color activeColor = blox.getColorManager().getActiveColor().getGdxColor();
        this.scoreFont.setColor(activeColor.r, activeColor.g, activeColor.b, activeColor.a);
        this.rectangle = new Sprite(Blox.createRoundedRectangle(Corner.bottom(), blox.screenWidth, 125, 30, WHITE));

        this.rectangle.setAlpha(0.8f);
        this.rectangle.setPosition(0, this.blox.screenHeight - rectangle.getHeight());
    }

    private void createRectangles() {
        int index = 0;
        for(GameColor color : blox.getColorManager().getShuffledColors()) {

            final Texture rect = Blox.createRoundedRectangle(new Corner[] {Corner.values()[index]}, 200, 200, 50, WHITE);
            final Sprite tile = new Sprite(rect);

            tile.setPosition( this.blox.screenWidth / 2 + ((index % 2 == 0) ? (-200f) : (0f)),  this.blox.screenHeight / 2 + ((index < 2 ? (-200f) : (0f))));
            tile.setColor(color.getGdxColor());

            this.tiles[index] = tile;

            index ++;
        }

        this.createWhiteBackgroundTile();
    }

    private void createWhiteBackgroundTile() {
        whiteBackgroundTile = new Sprite(Blox.createRoundedRectangle(Corner.values(), 440, 440, 50, WHITE));

        whiteBackgroundTile.setAlpha(0.8f);
        whiteBackgroundTile.setPosition(blox.screenWidth / 2 - 220, blox.screenHeight / 2 - 220);
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        this.blox.renderBackground();
        batch.begin();

        rectangle.draw(batch); // draw the upper bar for the score etc.

        this.whiteBackgroundTile.draw(batch);

        // render the 4 tiles
        for (int i = 0; i < tiles.length; i++) {
            if (this.tiles[i] == null) {
                continue;
            }
            this.tiles[i].draw(batch);
        }

        scoreFont.render(batch, this.blox.screenWidth / 2, this.blox.screenHeight - 20);
        batch.end();
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    protected void handleInput() {

    }
}
