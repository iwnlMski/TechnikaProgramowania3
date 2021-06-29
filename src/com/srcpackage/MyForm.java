package com.srcpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyForm {

    private JPanel panel;
    private JButton runButton;
    private JButton submitButton;
    private JButton timePlusButton;
    private JButton timeMinusButton;
    private JButton delayValue;
    private JButton distancePlusButton;
    private JButton distanceMinusButton;
    private JTextField numberOfLines;
    private JButton distValue;
    private static int nSkipped;
    public static JFrame frame = new JFrame("App");
    private static int time = 10;
    private static double dist = 0.1;
    private static int view_dist = 10;
    private static boolean going = false;
    public static MyForm myform;
    public static Runnable engine;

    public MyForm() {
        runButton.setEnabled(false);
        timePlusButton.setEnabled(false);
        timeMinusButton.setEnabled(false);
        distancePlusButton.setEnabled(false);
        distanceMinusButton.setEnabled(false);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = numberOfLines.getText();
                if (isNumber(text)) {
                    if (Integer.parseInt(text) < 100 && Integer.parseInt(text) >= 0) {
                        nSkipped = Integer.parseInt(text);
                        runButton.setEnabled(true);
                        timePlusButton.setEnabled(true);
                        timeMinusButton.setEnabled(true);
                        distancePlusButton.setEnabled(true);
                        distanceMinusButton.setEnabled(true);
                        submitButton.setEnabled(false);
                        numberOfLines.setEnabled(false);
                        System.out.println(nSkipped);
                    } else {
                        JOptionPane.showMessageDialog(null, "Your number must be between 0 and 99 inclusive");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please provide valid number");
                }
            }

        });
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runGraph();
                runButton.setEnabled(false);
                timePlusButton.setEnabled(false);
                timeMinusButton.setEnabled(false);
                distancePlusButton.setEnabled(false);
                distanceMinusButton.setEnabled(false);
                System.out.println("Running");
            }
        });
        timePlusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(time < 10 && time > 0){
                    time = time + 1;
                    delayValue.setText("Delay = " + time + "ms");
                }
                else if(time <= 490 && time >=10){
                    time = time + 10;
                    delayValue.setText("Delay = " + time + "ms");
                }
            }
        });
        timeMinusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(time >= 20){
                    time = time - 10;
                    delayValue.setText("Delay = " + time + "ms");
                }
                else if(time <= 10 && time > 1){
                    time = time - 1;
                    delayValue.setText("Delay = " + time + "ms");
                }
            }
        });
        distancePlusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dist <= 0.6){
                    dist = dist + 0.05;
                    view_dist = view_dist + 5;
                    distValue.setText("Distance = " + view_dist);
                }
            }
        });
        distanceMinusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dist >= 0.15){
                    dist = dist - 0.05;
                    view_dist = view_dist - 5;
                    distValue.setText("Distance = " + view_dist);
                }
            }
        });
    }

    public static void main(String[] args) {
        engine = new Runnable() {
            @Override
            public void run() {
                JComponent draw = new GraphicsDemo(nSkipped, time, dist);
                JPanel panel1 = new JPanel();
                panel1.setLayout(new BorderLayout());
                panel1.setPreferredSize(new Dimension(1000, 1000));
                panel1.add(draw, BorderLayout.CENTER);
                panel1.revalidate();
                panel1.repaint();
                panel1.setVisible(true);
                frame.add(panel1, BorderLayout.CENTER);
                frame.repaint();
                frame.pack();
                frame.setVisible(true);
            }
        };

        frame.setSize(1800, 1000);
        frame.setPreferredSize(new Dimension(1800, 1000));
        myform = new MyForm();
        frame.setContentPane(myform.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static boolean isNumber(String s) {
        if (s == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void endGraph(){
        runButton.setEnabled(true);
        timePlusButton.setEnabled(true);
        timeMinusButton.setEnabled(true);
        distancePlusButton.setEnabled(true);
        distanceMinusButton.setEnabled(true);

    }

    public static void runGraph(){
        SwingUtilities.invokeLater(engine);
    }
}
