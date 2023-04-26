package Painter;

import java.awt.*;
import java.util.HashMap;

public class PaintManager {

    public static HashMap<Integer, Color> ColorMap = new HashMap<>() {
          {
                put(0, new Color(0,255,255));
                put(1, new Color(0, 0, 0));
                put(2, new Color(0, 0, 255));
                put(3, new Color(255, 0, 255));
                put(4, new Color(128, 128, 128));
                put(5, new Color(0, 128, 0));
                put(6, new Color(0, 255, 0));
                put(7, new Color(128, 0, 0));
                put(8, new Color(0, 0, 128));
                put(9, new Color(128, 128, 0));
                put(10, new Color(128, 0, 128));
                put(11, new Color(255, 0, 0));
                put(12, new Color(192, 192, 192));
                put(13, new Color(0, 128, 128));
                put(14, new Color(255, 255, 255));
                put(15, new Color(255, 255, 0));
          }
    };


    public static void drawShape(BaseShape s, Graphics2D g2d) {
        g2d.setColor(s.color);
        if (s.shapeType == ShapeType.TEXT) {
            TextShape textShape = (TextShape) s;
            g2d.drawString(textShape.text, textShape.x, textShape.y);
        }else {
            Shape shape = (Shape) s;
            switch (shape.shapeType) {
                case LINE -> g2d.drawLine(shape.x, shape.y, shape.endX, shape.endY);
                case OVAL -> g2d.drawOval(shape.x, shape.y, Math.abs(shape.endX - shape.x), Math.abs(shape.endY - shape.y));
                case TRIANGLE -> {
                    int[] xPoints = {shape.x, shape.endX, 2 * shape.x - shape.endX};
                    int[] yPoints = {shape.y, shape.endY, shape.endY};
                    g2d.drawPolygon(xPoints, yPoints, 3);
                }
                case RECTANGLE -> g2d.drawRect(Math.min(shape.x, shape.endX), Math.min(shape.y, shape.endY), Math.abs(shape.endX - shape.x), Math.abs(shape.endY - shape.y));
            }
        }

    }
}
