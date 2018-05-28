package net.janwilhelm.blox.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.janwilhelm.blox.Blox;
import net.janwilhelm.blox.Utilities;
import net.janwilhelm.blox.colors.GameColor;
import net.janwilhelm.blox.fonts.Font;
import net.janwilhelm.blox.sprites.Corner;

import java.util.ArrayList;

import static com.badlogic.gdx.graphics.Color.WHITE;

public class GameState extends State {

    private Font scoreFont;
    private Sprite rectangle;
    private Sprite[] tiles = new Sprite[4];
    private ArrayList<GameColor> currentColors;
    private Sprite whiteBackgroundTile;
    private GameStateInputProcessor inputProcessor;

    public GameState(Blox blox) {
        super(blox);

        this.inputProcessor = new GameStateInputProcessor(this);

        this.createUpperbar();
        this.createRectangles();
    }

    /**
     * Create the upper bar of the screen. It serves as the background for the score label and has a slightly transparent white color
     */
    private void createUpperbar() {
        this.scoreFont = new Font("data/arial-black-120.fnt", "0", false);

        final Color activeColor = blox.getColorManager().getActiveColor().getGdxColor();
        this.scoreFont.setColor(activeColor.r, activeColor.g, activeColor.b, activeColor.a);
        this.rectangle = new Sprite(Blox.createRoundedRectangle(Corner.bottom(), blox.screenWidth, 125, 30, WHITE));

        this.rectangle.setAlpha(0.8f);
        this.rectangle.setPosition(0, this.blox.screenHeight - rectangle.getHeight());
    }

    /**
     * Create the four rounded square tiles in the center of the screen, one per color.
     * This function also calls #createWhiteBackgroundTile in order to create the background of the tiles
     */
    private void createRectangles() {
        int index = 0;
        this.currentColors = blox.getColorManager().getShuffledColors();
        for(GameColor color : currentColors) {

            final Texture rect = Blox.createRoundedRectangle(new Corner[] {Corner.values()[index]}, 200, 200, 50, WHITE);
            final Sprite tile = new Sprite(rect);

            // Set the position using some simple logic depending on the index of the color
            tile.setPosition( this.blox.screenWidth / 2 + ((index % 2 == 0) ? (-200f) : (0f)),  this.blox.screenHeight / 2 + ((index < 2 ? (-200f) : (0f))));
            tile.setColor(color.getGdxColor());

            this.tiles[index] = tile;

            index ++;
        }

        this.createWhiteBackgroundTile();
    }

    /**
     * Create a square with rounded corners as the background of the four center tiles. This one has a slightly transparent white background as well.
     */
    private void createWhiteBackgroundTile() {
        whiteBackgroundTile = new Sprite(Blox.createRoundedRectangle(Corner.values(), 440, 440, 50, WHITE));

        whiteBackgroundTile.setAlpha(0.8f);
        whiteBackgroundTile.setPosition(blox.screenWidth / 2 - 220, blox.screenHeight / 2 - 220);
    }

    /**
     * Get the square from a click position. Return null if none was matches the coordinates
     * @param x The X coordinate of the click
     * @param y The Y coordinate of the click
     * @return The tile at the position or null if it does not exist
     */
    public Sprite getTileFromPosition(int x, int y) {
        for (Sprite tile : this.tiles) {
            if (Utilities.isInSprite(tile, x, y)) {
                return tile;
            }
        }
        return null;
    }

    public void lost() {

    }

    public void next() {
        this.blox.getColorManager().refreshColor();

        this.blox.score ++;
        this.scoreFont.setText(this.blox.score + "");

        final Color newColor = this.blox.getColorManager().getActiveColor().getGdxColor();
        this.scoreFont.setColor(newColor.r, newColor.g, newColor.b, 1f);

        ArrayList<GameColor> newColors;
        do {
            newColors = blox.getColorManager().getShuffledColors();
        } while(newColors == currentColors);

        currentColors = newColors;

        int index = 0;
        for (Sprite tile : this.tiles) {
            tile.setColor(newColors.get(index).getGdxColor());
            index++;
        }
    }

    /**
     * Main render function. From here, all components call their individual render functions in order to draw on the screen.
     * This function goes background to foreground as beginning to end. The first components draw the background and are therefore rendered firstly.
     * @param batch The SpriteBatch of the screen
     * @param shapeRenderer The ShapeRenderer of the screen
     */
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
        System.out.println("handleInput called");
    }

    @Override
    public void wasShown() {
        Gdx.input.setInputProcessor(this.inputProcessor);
    }
}
