package ClientGUI;
import javax.swing.*;
//import java.awt.event.*;
import java.awt.*;
import Painter.Painter;
import Painter.ShapeManager;

public class CGUI {
    //    private JTextField strokeField;
    public static ShapeManager.ShapeType shapeType = ShapeManager.ShapeType.LINE;
    public static JPanel container;

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
        colorPanel.setBackground(Color.CYAN);
        colorPanel.setBounds(0, 0, 90, container.getHeight());
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
        panel.setLayout(null);
        JButton lineBtn = new JButton();
        JButton triangleBtn = new JButton();
        JButton rectangleBtn = new JButton();
        JButton ovalBtn = new JButton();

        lineBtn.setBounds(5, 520, 40,40);
        triangleBtn.setBounds(45, 520, 40,40);
        rectangleBtn.setBounds(5, 560, 40,40);
        ovalBtn.setBounds(45, 560, 40,40);

        lineBtn.setIcon(new ImageIcon("src\\picture\\line.png"));
        triangleBtn.setIcon(new ImageIcon("src\\picture\\triangle.png"));
        rectangleBtn.setIcon(new ImageIcon("src\\picture\\rectangle.png"));
        ovalBtn.setIcon(new ImageIcon("src\\picture\\oval.png"));

        lineBtn.addActionListener(e -> shapeType = ShapeManager.ShapeType.LINE);
        triangleBtn.addActionListener(e -> shapeType = ShapeManager.ShapeType.TRIANGLE);
        rectangleBtn.addActionListener(e -> shapeType = ShapeManager.ShapeType.RECTANGLE);
        ovalBtn.addActionListener(e -> shapeType = ShapeManager.ShapeType.OVAL);

        panel.add(lineBtn);
        panel.add(triangleBtn);
        panel.add(rectangleBtn);
        panel.add(ovalBtn);
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
