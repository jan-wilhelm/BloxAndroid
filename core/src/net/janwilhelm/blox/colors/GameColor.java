package net.janwilhelm.blox.colors;

import com.badlogic.gdx.graphics.Color;

/**
 * Represents a color as part of the game.
 * The color can be rendered to the screen by retrieving
 * the GameColor#gdxColor attribute.
 */
public class GameColor {

    private Color gdxColor;
    private String name;

    public GameColor(String name, Color gdxColor) {
        this.gdxColor = gdxColor;
        this.name = name;
    }

    public Color getGdxColor() {
        return gdxColor;
    }

    public String getName() {
        return name;
    }
}
