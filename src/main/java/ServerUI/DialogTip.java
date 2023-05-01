package ServerUI;

import Protocol.MsgBase;
import Protocol.MsgJoinRes;
import ServerThread.ServerThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogTip {
    public DialogTip(String username) {
        JFrame frame = new JFrame("Simple Dialog");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JButton agreeButton = new JButton("Agree");
        agreeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MsgBase msg = new MsgJoinRes(Boolean.TRUE);
                ServerThread.SendMsg(msg);
                frame.dispose();
            }
        });

        JButton rejectButton = new JButton("Reject");
        rejectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MsgBase msg = new MsgJoinRes(Boolean.FALSE);
                ServerThread.SendMsg(msg);
                frame.dispose();
            }
        });

        Object[] options = {agreeButton, rejectButton};
        JOptionPane.showOptionDialog(
                frame,
                username + " want to join the white board.",
                "Join Request",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
    }
}
