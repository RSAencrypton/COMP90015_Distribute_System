package Painter;

import ClientGUI.CGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Painter extends JPanel {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();
    private int x, y, endX, endY;
    private Shape curDraw;
//    private int stroke = 3;

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
                shapes.add(new Shape(x, y, endX, endY, CGUI.shapeType));
                curDraw = null;
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                curDraw = new Shape(x, y, endX, endY, CGUI.shapeType);
                repaint();

            }
        });
    }

    private void drawShape(Shape s, Graphics2D g2d) {
        switch (s.shapeType) {
            case LINE -> g2d.drawLine(s.x, s.y, s.endX, s.endY);
            case OVAL -> g2d.drawOval(s.x, s.y, Math.abs(s.endX - s.x), Math.abs(s.endY - s.y));
            case TRIANGLE -> {
                int[] xPoints = {s.x, s.endX, 2 * s.x - s.endX};
                int[] yPoints = {s.y, s.endY, s.endY};
                g2d.drawPolygon(xPoints, yPoints, 3);
            }
            case RECTANGLE -> g2d.drawRect(Math.min(s.x, s.endX), Math.min(s.y, s.endY), Math.abs(s.endX - s.x), Math.abs(s.endY - s.y));
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        for (Shape s : shapes) {
            drawShape(s, g2d);
        }

        if (curDraw != null) {
            drawShape(curDraw, g2d);
        }
    }

//    public void changeStroke(int stroke) {
//        this.stroke = stroke;
//    }

    public void clear() {
        shapes.clear();
        repaint();
    }

}
