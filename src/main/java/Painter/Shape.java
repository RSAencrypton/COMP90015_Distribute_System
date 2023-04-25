package Painter;

public class Shape {

    public int x;
    public int y;
    public int endX;
    public int endY;
//    public int stroke;
    public ShapeManager.ShapeType shapeType;

    public Shape(int x, int y, int endX, int endY, ShapeManager.ShapeType shapeType) {
        this.x = x;
        this.y = y;
        this.endX = endX;
        this.endY = endY;
        this.shapeType = shapeType;
    }
}
