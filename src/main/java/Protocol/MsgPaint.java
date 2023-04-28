package Protocol;

import Painter.BaseShape;
import Painter.ShapeType;

import java.awt.*;

public class MsgPaint extends MsgBase{
    private static final long serialVersionUID = 2L;
    public int x;
    public int y;
    public int endX;
    public int endY;
    public ShapeType shapeType;
    public Color color;


    public MsgPaint(int x, int y, int endX, int endY, ShapeType shapeType, Color color) {
        this.x = x;
        this.y = y;
        this.endX = endX;
        this.endY = endY;
        this.shapeType = shapeType;
        this.color = color;
    }
}
