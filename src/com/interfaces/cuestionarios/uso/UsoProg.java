package com.interfaces.cuestionarios.uso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsoProg {
    private JLabel PreguntaLabel;
    private JButton buttonDeporte;
    private JButton buttonEmbarazo;
    private JButton planningOcioButton;
    private JPanel PanelPrincipal;
    private JFrame frame;

    public UsoProg() {
        frame = new JFrame("UsoProg");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(PanelPrincipal);
        buttonEmbarazo.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        planningOcioButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonDeporte.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}