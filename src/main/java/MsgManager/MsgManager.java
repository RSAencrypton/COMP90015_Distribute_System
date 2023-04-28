package MsgManager;

import Protocol.*;
import Painter.Painter;
import Painter.Shape;


import java.io.ObjectOutputStream;
import java.net.Socket;

public  class MsgManager {

    public static Painter paintPanel;
    public static void MsgPaint (MsgBase msg) {
        MsgPaint msgPaint = (MsgPaint)msg;
        Shape shape = new Shape(msgPaint.x, msgPaint.y, msgPaint.endX, msgPaint.endY, msgPaint.shapeType, msgPaint.color);
        Painter.shapes.add(shape);
        paintPanel.repaint();
    }

    public static void MsgTest (MsgBase msg) {
        MsgTest msgText = (MsgTest)msg;
        System.out.println(msgText.test);
    }
}

