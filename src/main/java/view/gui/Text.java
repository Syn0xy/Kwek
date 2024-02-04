package view.gui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Text extends GUIComponent {

    private static final String DEFAULT_FONT = Font.SERIF;

    private static final int DEFAULT_FONT_STYLE = Font.PLAIN;

    private static final int DEFAULT_FONT_SIZE = 20;

    private static final HorizontalAlignment DEFAULT_TEXT_ANCHOR_X = HorizontalAlignment.CENTER;

    private static final VerticalAlignment DEFAULT_TEXT_ANCHOR_Y = VerticalAlignment.MIDDLE;

    private String fontName;

    private int fontStyle;

    private int fontSize;

    protected HorizontalAlignment horizontalAlignment;
    
    protected VerticalAlignment verticalAlignment;

    private String text;

    public Text(String text, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment){
        this.fontName = DEFAULT_FONT;
        this.fontStyle = DEFAULT_FONT_STYLE;
        this.fontSize = DEFAULT_FONT_SIZE;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.text = text;
    }

    public Text(String text){
        this(text, DEFAULT_TEXT_ANCHOR_X, DEFAULT_TEXT_ANCHOR_Y);
    }

    public String getText() {
        return text;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }

    public void setFontSize(int size) {
        this.fontSize = size;
    }

    public void setDefaultHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public void setDefaultVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    @Override
    protected void action() {
        return;
    }
    
    @Override
    protected void paint(Graphics graphics, GUIContainer container) {
        Font font = new Font(fontName, fontStyle, fontSize);
        
        graphics.setColor(color);
        graphics.setFont(font);
        
        FontMetrics metrics = graphics.getFontMetrics(font);

        int px = positionx;
        int py = positiony;

        switch (horizontalAlignment) {
            case LEFT: break;
            case CENTER: px += (width - metrics.stringWidth(text)) / 2; break;
            case RIGHT: px += width - metrics.stringWidth(text); break;
        }

        switch (verticalAlignment) {
            case UPPER: py += metrics.getAscent(); break;
            case MIDDLE: py += (height - metrics.getHeight()) / 2 + metrics.getAscent(); break;
            case LOWER: py += height - metrics.getAscent() / 2; break;
        }
        
        graphics.drawString(text, px, py);
        super.paint(graphics, this);
    }

}
