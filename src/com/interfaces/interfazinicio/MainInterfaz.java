package com.interfaces.interfazinicio;

import com.interfaces.interfazinicio.gui.Register;
import com.interfaces.interfazinicio.gui.SignIn;

import javax.swing.*;

public class MainInterfaz {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignIn register = new SignIn();
            register.setSize(1200, 570);
            register.setVisible(true);
            register.setLocationRelativeTo(null);
            register.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        });
    }
}