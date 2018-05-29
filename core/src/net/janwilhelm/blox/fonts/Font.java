package net.janwilhelm.blox.fonts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Font {

    private BitmapFont font;
    private String text;
    private GlyphLayout layout;

    public Font(String fileName, String text, boolean flip) {
        font = new BitmapFont(Gdx.files.internal(fileName), flip);
        this.layout = new GlyphLayout(this.font, text);
        this.text = text;
    }

    public void setColor(float r, float g, float b, float a) {
        this.font.setColor(r, g, b, a);
    }

    public void setText(String text) {
        this.text = text;
        this.layout.setText(this.font, text);
    }

    public void render(Batch batch, float x, float y) {
        font.draw(batch, this.text, x - (layout.width) / 2, y);
    }

}
