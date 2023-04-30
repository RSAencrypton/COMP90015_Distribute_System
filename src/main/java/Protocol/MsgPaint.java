package Protocol;


import Painter.BaseShape;
import Painter.Shape;
import Painter.ShapeType;

import java.awt.*;

public class MsgPaint extends MsgBase{
    private static final long serialVersionUID = 2L;


    public BaseShape shape;


    public MsgPaint(BaseShape shape) {

        this.protoName = "MsgPaint";

        this.shape = shape;
    }
}
