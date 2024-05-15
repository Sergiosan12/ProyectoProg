package com.interfaces.interfazinicio;

import com.interfaces.cuestionarios.sangrado.CuestionarioFInal;
import com.interfaces.interfazinicio.gui.Register;
import com.interfaces.interfazinicio.gui.SignIn;

import javax.swing.*;

public class MainInterfaz {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignIn register = new SignIn();
        register.setVisible(true);
        });
    }
}