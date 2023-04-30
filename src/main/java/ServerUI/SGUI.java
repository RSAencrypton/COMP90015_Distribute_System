package ServerUI;
import javax.swing.*;
//import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MsgManager.MsgManager;
import Painter.*;
import Painter.Painter;
import Protocol.MsgBase;
import Protocol.MsgJoinRes;
import ServerThread.ServerThread;

public class SGUI {
    //    private JTextField strokeField;
    public static ShapeType shapeType = ShapeType.LINE;
    public static JPanel container;
    public static Color color = Color.BLACK;

    public SGUI() {

        UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Color.BLACK));
        JFrame frame = new JFrame();
        frame.setTitle("White Board");
        frame.setBounds(100, 100, 1300, 665);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.BLACK);
        menuBar.setBorderPainted(false);
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setBorderPainted(false);
        createFileBtn(fileMenu);
        JMenu userMenu = new JMenu("User Management");
        userMenu.setBorderPainted(false);
        menuBar.add(fileMenu);
        menuBar.add(userMenu);

        container = new JPanel();
        container.setLayout(null);
        container.setBounds(0,0, frame.getWidth(), frame.getHeight());

        JPanel colorPanel = new JPanel();
        colorPanel.setBounds(0, 0, 90, container.getHeight());
        colorPanel.setLayout(null);
        CreateColorBtn(colorPanel);
        CreateShapeBtn(colorPanel);
        container.add(colorPanel);

        Painter painter = new Painter();
        painter.setBackground(Color.white);
        painter.setBounds(90, 0, 690, container.getHeight());
        MsgManager.paintPanel = painter;
        container.add(painter);




        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBounds(780, 0, 520, 450);
        chatArea.setBackground(Color.BLUE);
        container.add(chatArea);


        JTextArea inputField = new JTextArea();
        inputField.setBounds(780, 450, 520, 120);
        inputField.setBackground(Color.GREEN);
        inputField.setLineWrap(true);
        inputField.setWrapStyleWord(true);
        container.add(inputField);

        JButton sendBtn = new JButton("Send");
        sendBtn.setBounds(1200,573,80,30);
        container.add(sendBtn);

        frame.add(container);
        frame.setVisible(true);
    }


    private void CreateShapeBtn(JPanel panel) {
        JButton lineBtn = new JButton();
        JButton triangleBtn = new JButton();
        JButton rectangleBtn = new JButton();
        JButton ovalBtn = new JButton();
        JButton textBtn = new JButton();

        textBtn.setBounds(5, 480, 40, 40);
        lineBtn.setBounds(5, 520, 40,40);
        triangleBtn.setBounds(45, 520, 40,40);
        rectangleBtn.setBounds(5, 560, 40,40);
        ovalBtn.setBounds(45, 560, 40,40);

        textBtn.setIcon(new ImageIcon("src\\picture\\text.png"));
        lineBtn.setIcon(new ImageIcon("src\\picture\\line.png"));
        triangleBtn.setIcon(new ImageIcon("src\\picture\\triangle.png"));
        rectangleBtn.setIcon(new ImageIcon("src\\picture\\rectangle.png"));
        ovalBtn.setIcon(new ImageIcon("src\\picture\\oval.png"));

        textBtn.addActionListener(e -> shapeType = ShapeType.TEXT);
        lineBtn.addActionListener(e -> shapeType = ShapeType.LINE);
        triangleBtn.addActionListener(e -> shapeType = ShapeType.TRIANGLE);
        rectangleBtn.addActionListener(e -> shapeType = ShapeType.RECTANGLE);
        ovalBtn.addActionListener(e -> shapeType = ShapeType.OVAL);

        panel.add(textBtn);
        panel.add(lineBtn);
        panel.add(triangleBtn);
        panel.add(rectangleBtn);
        panel.add(ovalBtn);
    }

    public static void Tip(String tip) {
        JOptionPane.showMessageDialog(null, tip);
    }

    public static void createDialog(String username) {
        JFrame frame = new JFrame("Simple Dialog");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

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
                username + "want to join the white board.",
                "Join Request",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
    }

    private void CreateColorBtn(JPanel panel) {
        int posX = 5;
        int posY = 5;

        for (int i = 0; i < 16; i++) {
            JButton btn = new JButton();
            btn.setBounds(posX, posY, 40, 40);
            btn.setBackground(PaintManager.ColorMap.get(i));
            btn.addActionListener(e -> color = btn.getBackground());
            panel.add(btn);
            posX += 45;
            if (posX > 45 + 5) {
                posX = 5;
                posY += 45;
            }
        }
    }


    private void createFileBtn(JMenu fileMenu) {
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem saveAsMenuItem = new JMenuItem("Save As");
        newMenuItem.setBorderPainted(false);
        openMenuItem.setBorderPainted(false);
        saveMenuItem.setBorderPainted(false);
        saveAsMenuItem.setBorderPainted(false);
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
    }

}
