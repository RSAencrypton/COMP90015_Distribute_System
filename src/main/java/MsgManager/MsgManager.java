package MsgManager;

import Protocol.*;
import Painter.Painter;
import ServerUI.DialogTip;
import ServerUI.SGUI;

import javax.swing.*;


public  class MsgManager {

    public static Painter paintPanel;
    public static void MsgPaint (MsgBase msg) {
        MsgPaint msgPaint = (MsgPaint)msg;
        SwingUtilities.invokeLater(
                () -> {
                    paintPanel.shapes.add(msgPaint.shape);
                    paintPanel.repaint();
                }
        );
    }


    public static void MsgTest (MsgBase msg) {
        MsgTest msgText = (MsgTest)msg;
        System.out.println(msgText.test);
    }

    public static void MsgJoin(MsgBase msg) {
        MsgJoin msgJoin = (MsgJoin)msg;
        new DialogTip(msgJoin.username);
    }

    public static void MsgChat(MsgBase msg) {
        MsgChat msgChat = (MsgChat)msg;
        SwingUtilities.invokeLater(
                () -> {
                    SGUI.chatArea.append(msgChat.username + "\n");
                    SGUI.chatArea.append(msgChat.content + "\n" + "\n");
                }
        );
    }
}

