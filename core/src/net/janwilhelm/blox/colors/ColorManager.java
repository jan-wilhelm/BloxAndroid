package net.janwilhelm.blox.colors;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Responsible for managing all the color related functions needed for the game.
 */
public class ColorManager {

    private final ArrayList<GameColor> colors = new ArrayList<GameColor>();
    private GameColor activeColor;
    private final Random random = new Random();

    public ColorManager() {

        // Create the needed colors manually and assign their names
        this.colors.add(new GameColor("red", getColorFrom255RGB(255,37,37)));
        this.colors.add(new GameColor("yellow", getColorFrom255RGB(255,222,0)));
        this.colors.add(new GameColor("blue", getColorFrom255RGB(0,45,211)));
        this.colors.add(new GameColor("green", getColorFrom255RGB(0,218,46)));

        // initialize the class by settings its first color
        this.refreshColor();
    }

    public GameColor getActiveColor() {
        return activeColor;
    }

    /**
     * Set the ColorManager#activeColor to a new randomly chosen
     * color until it's different from the most recent color
     */
    public void refreshColor() {
        int newIndex;
        GameColor newColor;

        do {
            newIndex = getRandomIndex();
            System.out.println(newIndex);
            newColor = this.colors.get(newIndex);
        } while (activeColor != null && colorEquals(newColor.getGdxColor(), activeColor.getGdxColor()));

        this.activeColor = newColor;
    }

    private int getRandomIndex() {
        return random.nextInt(this.colors.size());
    }

    /**
     * Convenience method to get a Color object from 0-255 rgb values with
     * an alpha value of 1 (255)
     * @param red the red component of the color
     * @param green the green component of the color
     * @param blue the blue component of the color
     * @return The Color object created from the rgb values
     */
    public static Color getColorFrom255RGB(float red, float green, float blue) {
        return getColorFromRGB(red / 255F, green / 255F, blue / 255F, 1);
    }

    public static Color getColorFromRGB(float red, float green, float blue) {
        return getColorFromRGB(red, green, blue, 1);
    }

    public static Color getColorFromRGB(float red, float green, float blue, float alpha) {
        return new Color(red, green, blue, alpha);
    }

    public ArrayList<GameColor> getColors() {
        return colors;
    }

    public ArrayList<GameColor> getShuffledColors() {
        ArrayList<GameColor> colorsCopy = (ArrayList<GameColor>) colors.clone();
        Collections.shuffle(colorsCopy);
        System.out.println(colorsCopy);
        return colorsCopy;
    }

    public boolean colorEquals(Color color1, Color color2) {
        if (color1 == null || color2 == null) {
            return false;
        }

        return color1.r == color2.r && color1.g == color2.g && color1.b == color2.b;
    }

    public boolean isColor(Color color) {
        final Color activeGDXColor = activeColor.getGdxColor();

        return colorEquals(color, activeGDXColor);
    }

}
