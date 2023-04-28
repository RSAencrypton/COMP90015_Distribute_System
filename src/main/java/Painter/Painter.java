package Painter;

import ServerUI.SGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Painter extends JPanel {
    public static ArrayList<BaseShape> shapes = new ArrayList<BaseShape>();
    private int x, y, endX, endY;
    private Shape curDraw;
//    private int stroke = 3;


    public Painter() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SGUI.shapeType == ShapeType.TEXT) {
                    String text = JOptionPane.showInputDialog("Enter text");
                    if (text != null) {
                        shapes.add(new TextShape(e.getX(), e.getY(), text, SGUI.color));
                        repaint();
                    }
                    return;
                }
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                shapes.add(new Shape(x, y, endX, endY, SGUI.shapeType, SGUI.color));
                curDraw = null;
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                curDraw = new Shape(x, y, endX, endY, SGUI.shapeType, SGUI.color);
                repaint();

            }
        });
    }




    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        for (BaseShape s : shapes) {
            PaintManager.drawShape(s, g2d);
        }

        if (curDraw != null) {
            PaintManager.drawShape(curDraw, g2d);
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
