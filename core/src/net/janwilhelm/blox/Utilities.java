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
        System.out.println("====================");
        System.out.println("tile x " + tile.getX());
        System.out.println("tile y " + tile.getY());
        System.out.println("tile w " + tile.getWidth());
        System.out.println("tile h " + tile.getHeight());
        System.out.println("x " + x);
        System.out.println("y " + y);
        System.out.println("c " + tile.getColor());
        return tile.getX() <= x && tile.getX() + tile.getWidth() >= x && tile.getY() <= y && tile.getY() + tile.getHeight() >= y;
    }

}
