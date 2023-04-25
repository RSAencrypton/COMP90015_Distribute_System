package Painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Painter extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private int x, y, endX, endY;
    private int stroke = 3;

    public Painter() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                shapes.add(new Shape(x, y, endX, endY, stroke));
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                shapes.add(new Shape(x, y, endX, endY, stroke));
                x = endX;
                y = endY;
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Shape s : shapes) {
            g2d.setStroke(new BasicStroke(s.stroke));
            g2d.drawLine(s.x, s.y, s.endX, s.endY);
        }
    }

    public void changeStroke(int stroke) {
        this.stroke = stroke;
    }

    public void clear() {
        shapes.clear();
        repaint();
    }

}
