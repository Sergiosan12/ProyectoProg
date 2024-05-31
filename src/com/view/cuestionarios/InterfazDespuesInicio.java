package com.view.cuestionarios;

import com.model.funciones.Menstruacion;
import com.view.cuestionarios.gui.SignIn;
import com.view.cuestionarios.sangrado.ActualizarFecha;
import com.view.cuestionarios.uso.UsoProg;

import javax.swing.*;
import java.awt.*;

public class InterfazDespuesInicio extends JFrame {

    private static final Color BACKGROUND_COLOR = Color.decode("#FFF1F1");
    private static final Color BUTTON_COLOR = Color.decode("#F6C4F6");

    public InterfazDespuesInicio(Menstruacion menstruacion) {
        super("Interfaz Después de Inicio");
        setupFrame();
        add(createMainPanel(menstruacion));
    }

    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    private JPanel createMainPanel(Menstruacion menstruacion) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(BACKGROUND_COLOR);

        panel.add(createButton("Usar Aplicación", () -> openUsoProg(menstruacion)));
        panel.add(createButton("Seleccionar Nuevo Período", () -> openActualizarFecha(menstruacion)));
        panel.add(createButton("Cerrar Sesión", this::openSignIn));

        return panel;
    }

    private JButton createButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_COLOR);
        button.addActionListener(e -> action.run());
        return button;
    }

    private void openUsoProg(Menstruacion menstruacion) {
        UsoProg usoProg = new UsoProg(menstruacion);
        usoProg.setVisible(true);
        dispose();
    }

    private void openActualizarFecha(Menstruacion menstruacion) {
        ActualizarFecha actualizarFecha = new ActualizarFecha(menstruacion);
        actualizarFecha.setVisible(true);
        dispose();
    }

    private void openSignIn() {
        SignIn signIn = new SignIn();
        signIn.setVisible(true);
        dispose();
    }
}
