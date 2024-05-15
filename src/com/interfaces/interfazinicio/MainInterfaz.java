package com.interfaces.interfazinicio;

import com.interfaces.cuestionarios.sangrado.CuestionarioFInal;
import com.interfaces.interfazinicio.gui.Register;
import com.interfaces.interfazinicio.gui.SignIn;

import javax.swing.*;

public class MainInterfaz {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignIn signIn = new SignIn();
            signIn.setSize(1200, 560);
            signIn.setVisible(true);
            signIn.setLocationRelativeTo(null);
            signIn.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        });
    }
}