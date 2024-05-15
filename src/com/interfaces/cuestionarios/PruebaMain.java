package com.interfaces.cuestionarios;

import com.interfaces.cuestionarios.sangrado.CuestionarioFInal;
import com.interfaces.cuestionarios.uso.UsoProg;
import javax.swing.*;

public class PruebaMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CuestionarioFInal cuestionarioFInal = new CuestionarioFInal();
            cuestionarioFInal.setVisible(true);
        });
    }
}