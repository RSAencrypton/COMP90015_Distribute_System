package ClientGUI;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import Painter.Painter;

public class CGUI {
    private JFrame frame;
    private Painter painter;
    private JTextField strokeField;

    public CGUI() {

        UIManager.put("PopupMenu.border", BorderFactory.createLineBorder(Color.BLACK));
        frame = new JFrame();
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

        JPanel container = new JPanel();
        container.setLayout(null);
        container.setBounds(0,0,frame.getWidth(),frame.getHeight());

        JPanel colorBar = new JPanel();
        colorBar.setBackground(Color.CYAN);
        colorBar.setBounds(0, 0, 90, container.getHeight());
        container.add(colorBar);

        painter = new Painter();
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

    private void AddListener() {
        strokeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int stroke = Integer.parseInt(strokeField.getText());
                if (stroke > 0 && stroke < 10) painter.changeStroke(stroke);
            }
        });
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
