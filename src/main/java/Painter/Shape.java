package Painter;

import java.awt.*;

;

public class Shape extends BaseShape{

    public int x;
    public int y;
    public int endX;
    public int endY;
//    public int stroke;


    public Shape(int x, int y, int endX, int endY, ShapeType shapeType, Color color) {
        this.x = x;
        this.y = y;
        this.endX = endX;
        this.endY = endY;
        this.shapeType = shapeType;
        this.color = color;
    }
}


