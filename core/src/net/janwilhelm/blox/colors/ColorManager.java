package net.janwilhelm.blox.colors;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

public class ColorManager {

    private final ArrayList<GameColor> colors = new ArrayList<GameColor>();
    private GameColor activeColor;
    private final Random random = new Random();

    public ColorManager() {
        this.colors.add(new GameColor("red", getColorFrom255RGB(255,37,37)));
        this.colors.add(new GameColor("yellow", getColorFrom255RGB(255,222,0)));
        this.colors.add(new GameColor("blue", getColorFrom255RGB(0,45,211)));
        this.colors.add(new GameColor("green", getColorFrom255RGB(0,218,46)));

        this.refreshColor();
    }

    public GameColor getActiveColor() {
        return activeColor;
    }

    public void refreshColor() {
        final GameColor lastColor = this.activeColor;
        int newIndex = this.getRandomIndex();
        GameColor newColor = lastColor;

        do {
            newIndex = getRandomIndex();
            newColor = this.colors.get(newIndex);
        } while (newColor.equals(lastColor));

        this.activeColor = newColor;
    }

    private int getRandomIndex() {
        return random.nextInt(this.colors.size());
    }

    public static Color getColorFrom255RGB(float red, float green, float blue) {
        return getColorFromRGB(red / 255F, green / 255F, blue / 255F, 1);
    }

    public static Color getColorFromRGB(float red, float green, float blue) {
        return getColorFromRGB(red, green, blue, 1);
    }

    public static Color getColorFromRGB(float red, float green, float blue, float alpha) {
        return new Color(red, green, blue, alpha);
    }

}
