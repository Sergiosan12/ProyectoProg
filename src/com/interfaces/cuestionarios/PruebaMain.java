package com.interfaces.cuestionarios;

import com.interfaces.cuestionarios.sangrado.CuestionarioFInal;

import javax.swing.*;

public class PruebaMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Cuestionario Final");
        frame.setSize(600, 370);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        CuestionarioFInal cuestionario = new CuestionarioFInal();
        frame.getContentPane().add(cuestionario.panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
