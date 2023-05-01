package ServerUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
//import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Main.Main;
import MsgManager.MsgManager;
import Painter.*;
import Painter.Painter;
import Protocol.MsgBase;
import Protocol.MsgChat;
import Protocol.MsgJoinRes;
import ServerThread.ServerThread;

public class SGUI {
    //    private JTextField strokeField;
    public static ShapeType shapeType = ShapeType.LINE;
    public static JPanel container;
    public static JTextArea chatArea;
    public static Color color = Color.BLACK;

    public SGUI() {

        UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Color.BLACK));
        JFrame frame = new JFrame();
        frame.setTitle("White Board");
        frame.setBounds(100, 100, 1300, 665);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(200,200,200));
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

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBounds(780, 0, 520, 450);
        chatArea.setBackground(new Color(240, 240, 240));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
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

        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = inputField.getText();
                if (content.equals("")) {
                    return;
                }
                chatArea.append(Main.username + "\n");
                chatArea.append(content + "\n" + "\n");
                inputField.setText("");
                ServerThread.SendMsg(new MsgChat(content));
            }
        });

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

        saveMenuItem.addActionListener(e -> SaveFile());
        saveAsMenuItem.addActionListener(e -> SaveAsFile());
        openMenuItem.addActionListener(e -> LoadFile());

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);
    }

    private BufferedImage SaveImage() {
        BufferedImage image = new BufferedImage(MsgManager.paintPanel.getWidth(), MsgManager.paintPanel.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        MsgManager.paintPanel.paint(graphics);
        graphics.dispose();
        return image;
    }

    private void SaveFile() {
        BufferedImage image = SaveImage();
        File output = new File("Image.png");
        try {
            ImageIO.write(image, "png", output);
        }catch (IOException e) {
            System.out.println("Save file failed");
        }
    }

    private void SaveAsFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG(*.png)", "png"));

        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getPath();
            if (!path.endsWith(".png")) {
                path += ".png";
            }
            File output = new File(path);
            BufferedImage image = SaveImage();
            try {
                ImageIO.write(image, "png", output);
            }catch (IOException ex) {
                System.out.println("Save as file failed");
            }
        }
    }

    private void LoadFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG(*.png)", "png"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getPath();
            BufferedImage image = null;
            try {
                image = ImageIO.read(file);
            }catch (IOException e) {
                System.out.println("Load file failed");
            }
            if (image != null) {
                LoadImage(image);
            }
        }
    }

    private void LoadImage(BufferedImage Image) {
        Graphics2D graphics = (Graphics2D) MsgManager.paintPanel.getGraphics();
        graphics.drawImage(Image, 0, 0, MsgManager.paintPanel.getWidth(), MsgManager.paintPanel.getHeight(), null);
        graphics.dispose();
    }

}
