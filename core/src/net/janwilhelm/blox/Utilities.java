package net.janwilhelm.blox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import net.janwilhelm.blox.sprites.Corner;

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



    public static boolean contains(Object[] array, Object obj) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == obj || array[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public static Corner[] getInverseCorners(Corner[] corners) {
        Corner[] inverseCorners = new Corner[4 - corners.length];
        int i = 0;
        for(Corner corner : Corner.values()) {
            if (!contains(corners, corner)) {
                inverseCorners[i] = corner;
                i++;
            }
        }
        return inverseCorners;
    }

    public static Texture createRoundedRectangle(Corner[] corners, int width, int height, int cornerRadius, Color color) {
        Texture retTexture;
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        Pixmap ret = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        pixmap.setColor(color);

        Corner[] inverseCorners = getInverseCorners(corners);

        for (Corner corner: corners) {
            switch (corner) {
                case TOP_LEFT:
                    pixmap.fillCircle(cornerRadius, cornerRadius, cornerRadius);
                    break;
                case TOP_RIGHT:
                    pixmap.fillCircle(width - cornerRadius - 1, cornerRadius, cornerRadius);
                    break;
                case BOTTOM_LEFT:
                    pixmap.fillCircle(cornerRadius, height - cornerRadius - 1, cornerRadius);
                    break;
                case BOTTOM_RIGHT:
                    pixmap.fillCircle(width - cornerRadius - 1, height - cornerRadius - 1, cornerRadius);
                    break;
            }
        }

        for (Corner corner: inverseCorners) {
            switch (corner) {
                case TOP_LEFT:
                    pixmap.fillRectangle(0,0,cornerRadius,cornerRadius);
                    break;
                case TOP_RIGHT:
                    pixmap.fillRectangle(width - cornerRadius,0,cornerRadius,cornerRadius);
                    break;
                case BOTTOM_LEFT:
                    pixmap.fillRectangle(0,height - cornerRadius, cornerRadius,cornerRadius);
                    break;
                case BOTTOM_RIGHT:
                    pixmap.fillRectangle(width - cornerRadius,height - cornerRadius, cornerRadius,cornerRadius);
                    break;
            }
        }

        pixmap.fillRectangle(cornerRadius, 0, width - cornerRadius * 2, height);
        pixmap.fillRectangle(0, cornerRadius, width, height - cornerRadius * 2);

        ret.setColor(color);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (pixmap.getPixel(x, y) != 0) ret.drawPixel(x, y);
            }
        }
        retTexture = new Texture(pixmap);
        pixmap.dispose();

        return retTexture;
    }

    public static Texture createRoundedRectangle(int width, int height, int cornerRadius, Color color) {
        Corner[] allCorners = Corner.values();
        return createRoundedRectangle(allCorners, width, height, cornerRadius, color);
    }

    public static void renderBackground() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        final Color color = Blox.instance().getColorManager().getActiveColor().getGdxColor();
        Gdx.gl.glClearColor(color.r, color.g, color.b, 1f); // set the background color
    }

}
