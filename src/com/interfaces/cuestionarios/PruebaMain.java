package com.interfaces.cuestionarios;

import com.interfaces.cuestionarios.uso.UsoProg;
import javax.swing.*;

public class PruebaMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsoProg usoProg = new UsoProg();
            usoProg.setVisible(true);
        });
    }
}