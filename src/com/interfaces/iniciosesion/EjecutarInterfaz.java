package com.interfaces.iniciosesion;

import javax.swing.*;

/**
 * Clase que modifica la ventana de la interfaz gráfica y la ejecuta
 */
public class EjecutarInterfaz {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new Gui();
            frame.setSize(1200, 570);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
        });
    }
}