package com.srcpackage;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.*;


public class GraphicsDemo extends JComponent{
    private int nSkipped;
    private Vector<Point2D.Double> points;
    private Vector<Point2D.Double> trashcan;

    public GraphicsDemo(int n, int t, double d) {

        nSkipped = n;
        points = createPoints(50, 500, d);
        trashcan = new Vector<>();
        trashcan.add(points.get(0));
        points.remove(0);
        Timer timer = new Timer(t, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (points.isEmpty()) {
                    ((Timer) e.getSource()).stop();
                    MyForm.myform.endGraph();
                } else {
                    trashcan.add(points.get(0));
                    points.remove(0);
                }
                repaint();
            }
        });
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.lightGray);
        g2D.setStroke(new BasicStroke(1));
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //X=50 Y=500
        for(int j = 0; j < 1000; j++){
            g2D.drawLine(0, 500 + j*MyForm.view_dist, 10000, 500 + j*MyForm.view_dist);
            g2D.drawLine(0, 500 - j*MyForm.view_dist, 10000, 500 - j*MyForm.view_dist);

            g2D.drawLine(50 + j*MyForm.view_dist, 0, 50 + j*MyForm.view_dist, 10000);
            g2D.drawLine(50 - j*MyForm.view_dist, 0, 50 - j*MyForm.view_dist, 10000);
        }

        g2D.setColor(Color.black);
        g2D.drawLine(0, 500, 10000, 500);
        g2D.drawLine(50, 0, 50, 10000);

        for(int j = 0; j < 10000; j++){
            g2D.drawLine(50+j*MyForm.view_dist, 497, 50+j*MyForm.view_dist, 503);
            g2D.drawString(""+j, 50+j*10*MyForm.view_dist-2, 512);
            g2D.drawLine(50-j*MyForm.view_dist, 497, 50-j*MyForm.view_dist, 503);

            g2D.drawLine(47, 500 - j*MyForm.view_dist, 53, 500 - j*MyForm.view_dist);
            g2D.drawLine(47, 500 + j*MyForm.view_dist, 53, 500 + j*MyForm.view_dist);
            g2D.drawString(""+j, 40, 500-j*10*MyForm.view_dist+5);

            g2D.drawString("-"+(j+1), 36, 500+j*10*MyForm.view_dist+MyForm.view_dist*10+5);
        }

        g2D.drawString("Axis: X", 1730, 515);
        g2D.drawString("Axis: Y", 60, 15);

        g2D.setColor(Color.magenta);
        g2D.setStroke(new BasicStroke(2));
        for(int i = 0; i < trashcan.size()-1; i++)
            g2D.draw(new Line2D.Double(trashcan.get(i), trashcan.get(i+1)));

        }


    public Vector<Point2D.Double> createPoints(double x_r, double y_r, double dist){
        Vector<Point2D.Double> list = new Vector<>();
        double x_root = x_r;
        double y_root = y_r;
        double distance = dist;
        Vector<Double> arr_omega_z = ReadFile.getData(nSkipped);
        list.add(new Point2D.Double(x_root, y_root));

        for (int i = 0; i < arr_omega_z.size(); i++) {
            double alpha;
            if (arr_omega_z.get(i) < 0)
                alpha = -1 * arr_omega_z.get(i) / 25;
            else
                alpha = arr_omega_z.get(i) / 25;

            double x_next = x_root + distance * Math.cos(Math.toRadians(alpha));
            double y_next;
            if (arr_omega_z.get(i) < 0)
                y_next = y_root - distance * Math.sin(Math.toRadians(alpha));
            else
                y_next = y_root + distance * Math.sin(Math.toRadians(alpha));

            x_root = x_next;
            y_root = y_next;
            list.add(new Point2D.Double(x_next, y_next));
        }
        return list;
    }
}
