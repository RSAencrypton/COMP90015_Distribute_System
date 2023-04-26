package ClientGUI;
import javax.swing.*;
//import java.awt.event.*;
import java.awt.*;
import Painter.*;
import Painter.Painter;

public class CGUI {
    //    private JTextField strokeField;
    public static ShapeType shapeType = ShapeType.TEXT;
    public static JPanel container;
    public static Color color = Color.BLACK;

    public CGUI() {

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
        container.add(painter);


        JPanel chatPanel = new JPanel();
        chatPanel.setBackground(Color.PINK);
        chatPanel.setBounds(780, 0, 520, container.getHeight());
        container.add(chatPanel);




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

//    private void AddListener() {
//        strokeField.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int stroke = Integer.parseInt(strokeField.getText());
//                if (stroke > 0 && stroke < 10) painter.changeStroke(stroke);
//            }
//        });
//    }

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
