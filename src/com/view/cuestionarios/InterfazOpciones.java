package com.view.cuestionarios;

import com.model.funciones.Menstruacion;
import com.view.cuestionarios.logIn.SignIn;
import com.view.cuestionarios.sangrado.ActualizarFecha;
import com.view.cuestionarios.uso.UsoProg;

import javax.swing.*;
import java.awt.*;

/**
 * La clase InterfazDespuesInicio proporciona una interfaz gráfica que se muestra después de que el usuario ha iniciado sesión.
 * Permite al usuario acceder a diferentes funcionalidades como usar la aplicación, actualizar la fecha del último periodo o cerrar sesión.
 */
public class InterfazOpciones extends JFrame {

    private static final Color BACKGROUND_COLOR = Color.decode("#FFF1F1");
    private static final Color BUTTON_COLOR = Color.decode("#F6C4F6");

    /**
     * Constructor de la clase InterfazDespuesInicio.
     *
     * @param menstruacion el objeto Menstruacion que contiene la información del ciclo menstrual del usuario.
     */
    public InterfazOpciones(Menstruacion menstruacion) {
        super("Interfaz Después de Inicio");
        setupFrame();
        add(createMainPanel(menstruacion));
    }

    /**
     * Configura el marco principal de la ventana.
     */
    private void setupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    /**
     * Crea el panel principal de la interfaz con los botones correspondientes.
     *
     * @param menstruacion el objeto Menstruacion que contiene la información del ciclo menstrual del usuario.
     * @return el panel principal con los botones.
     */
    private JPanel createMainPanel(Menstruacion menstruacion) {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(BACKGROUND_COLOR);

        panel.add(createButton("Usar Aplicación", () -> openUsoProg(menstruacion)));
        panel.add(createButton("Seleccionar Nuevo Período", () -> openActualizarFecha(menstruacion)));
        panel.add(createButton("Cerrar Sesión", this::openSignIn));

        return panel;
    }

    /**
     * Crea un botón con el texto especificado y la acción a realizar cuando se presione.
     *
     * @param text el texto del botón.
     * @param action la acción a realizar cuando se presione el botón.
     * @return el botón creado.
     */
    private JButton createButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_COLOR);
        button.addActionListener(e -> action.run());
        return button;
    }

    /**
     * Abre la interfaz de uso de la aplicación.
     *
     * @param menstruacion el objeto Menstruacion que contiene la información del ciclo menstrual del usuario.
     */
    private void openUsoProg(Menstruacion menstruacion) {
        UsoProg usoProg = new UsoProg(menstruacion);
        usoProg.setVisible(true);
        dispose();
    }

    /**
     * Abre la interfaz para actualizar la fecha del último período.
     *
     * @param menstruacion el objeto Menstruacion que contiene la información del ciclo menstrual del usuario.
     */
    private void openActualizarFecha(Menstruacion menstruacion) {
        ActualizarFecha actualizarFecha = new ActualizarFecha(menstruacion);
        actualizarFecha.setVisible(true);
        dispose();
    }

    /**
     * Abre la interfaz de inicio de sesión.
     */
    private void openSignIn() {
        SignIn signIn = new SignIn();
        signIn.setVisible(true);
        dispose();
    }
}
