package MsgManager;

import Protocol.*;
import Painter.Painter;
import ServerUI.SGUI;


public  class MsgManager {

    public static Painter paintPanel;
    public static void MsgPaint (MsgBase msg) {
        MsgPaint msgPaint = (MsgPaint)msg;
        Painter.shapes.add(msgPaint.shape);
        paintPanel.repaint();
    }


    public static void MsgTest (MsgBase msg) {
        MsgTest msgText = (MsgTest)msg;
        System.out.println(msgText.test);
    }

    public static void MsgJoin(MsgBase msg) {
        MsgJoin msgJoin = (MsgJoin)msg;
        SGUI.createDialog(msgJoin.username);
    }
}

