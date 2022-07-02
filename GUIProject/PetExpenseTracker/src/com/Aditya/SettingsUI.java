package com.Aditya;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*******************************************************************************************************
 * The SettingsUI class
 * This class contains all code in order for SettingsUI to run smoothly
 * Once opened, users can change their background color of pet expense tracker
 *******************************************************************************************************/
public class SettingsUI extends JFrame implements ActionListener, ChangeListener {
    private final JSlider rSlider, gSlider, bSlider;
    private final JLabel red, blue, green, explain;
    //red green blue colors
    private int r,g,b;
    private final JButton change;
    private Font font = new Font("SansSerif", Font.BOLD, 22);

    public SettingsUI()
    {
        super("Settings");

        //-------------------------LABELS-------------------------------------------------------------------------------------------------
        red = GUI.createText("Red", new Color(169, 25, 25), font, true);
        blue =  GUI.createText("Blue", new Color(0, 124, 241), font, true);
        green =  GUI.createText("Green", new Color(34, 255, 0), font, true);
        explain =  GUI.createText("Change your background color!", new Color(255, 145, 255), font, true);
        //-------------------------LABELS-------------------------------------------------------------------------------------------------

        //-------------------------BUTTONS--------------------------------------------------
        change = new JButton("Change BG color!");
        change.setBackground(new Color(114, 107, 107));
        change.addActionListener(this);
        //-------------------------BUTTONS--------------------------------------------------

        //-------------------------SLIDERS--------------------------------------------------
        rSlider = new JSlider(0,255);
        rSlider.setPreferredSize(new Dimension(400,200));
        rSlider.setPaintTicks(true);
        rSlider.setMinorTickSpacing(10);
        rSlider.setMajorTickSpacing(50);
        rSlider.setPaintTrack(true);
        rSlider.setPaintLabels(true);
        rSlider.setFont(font);
        rSlider.addChangeListener(this);

        gSlider = new JSlider(0,255);
        gSlider.setPreferredSize(new Dimension(400,200));
        gSlider.setPaintTicks(true);
        gSlider.setMinorTickSpacing(10);
        gSlider.setMajorTickSpacing(50);
        gSlider.setPaintTrack(true);
        gSlider.setPaintLabels(true);
        gSlider.setFont(font);
        gSlider.addChangeListener(this);

        bSlider = new JSlider(0,255);
        bSlider.setPreferredSize(new Dimension(400,200));
        bSlider.setPaintTicks(true);
        bSlider.setMinorTickSpacing(10);
        bSlider.setMajorTickSpacing(50);
        bSlider.setPaintTrack(true);
        bSlider.setPaintLabels(true);
        bSlider.setFont(font);
        bSlider.addChangeListener(this);
        //-------------------------SLIDERS--------------------------------------------------

        JPanel pane = (JPanel) this.getContentPane();
        pane.setLayout(new GridLayout(9,2));
        pane.add(explain);
        pane.add(red);
        pane.add(rSlider);
        pane.add(green);
        pane.add(gSlider);
        pane.add(blue);
        pane.add(bSlider);
        pane.add(change);

        setSize(800, 700);
        pack();
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == change)
        {
            System.out.println("Something has happened!");
            r = rSlider.getValue();
            b = bSlider.getValue();
            g = gSlider.getValue();
            GUI.topCentrePanel.setBackground(new Color(r,g,b));
            GUI.midCentrePanel.setBackground(new Color(r,g,b));
            GUI.bottomCentrePanel.setBackground(new Color(r,g,b));
        }
    }


    @Override
    public void stateChanged(ChangeEvent e)
    {
        red.setText("R" + rSlider.getValue());
        green.setText("G" + gSlider.getValue());
        blue.setText("B" + bSlider.getValue());
    }
}
