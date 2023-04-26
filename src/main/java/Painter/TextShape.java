package Painter;

import java.awt.*;

public class TextShape extends BaseShape {
    public int x;
    public int y;
    public String text;



    public TextShape(int x, int y,String text, Color color) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
        this.shapeType = ShapeType.TEXT;
    }
}
