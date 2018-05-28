package net.janwilhelm.blox;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Utilities {

    /**
     * Check if a x and y coordinate are within the region of a Sprite
     * @param tile The sprite
     * @param x The X coordinate
     * @param y The Y coordinate
     * @return True if the coordinates are within the region, false otherwise
     */
    public static boolean isInSprite(Sprite tile, int x, int y) {
        return tile.getX() <= x && tile.getX() + tile.getWidth() >= x && tile.getY() <= y && tile.getY() + tile.getHeight() >= y;
    }

}
