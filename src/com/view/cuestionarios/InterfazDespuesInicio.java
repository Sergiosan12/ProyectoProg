package com.view.cuestionarios;

import com.model.funciones.Menstruacion;
import com.view.cuestionarios.gui.SignIn;
import com.view.cuestionarios.sangrado.SelectorFecha;
import com.view.cuestionarios.uso.UsoProg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazDespuesInicio extends JFrame {

    public InterfazDespuesInicio() {
        super("Interfaz Después de Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.decode("#FFF1F1")); // Establece el color de fondo

        JButton buttonUsarApp = new JButton("Usar Aplicación");
        JButton buttonSeleccionarPeriodo = new JButton("Seleccionar Nuevo Período");
        JButton buttonCerrarSesion = new JButton("Cerrar Sesión");

        buttonUsarApp.setBackground(Color.decode("#F6C4F6")); // Establece el color del botón
        buttonSeleccionarPeriodo.setBackground(Color.decode("#F6C4F6")); // Establece el color del botón
        buttonCerrarSesion.setBackground(Color.decode("#F6C4F6")); // Establece el color del botón

        buttonUsarApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsoProg usoProg = new UsoProg(new Menstruacion());
                usoProg.setVisible(true);
                dispose();
            }
        });

        buttonSeleccionarPeriodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectorFecha selectorFecha = new SelectorFecha(new Menstruacion());
                selectorFecha.setVisible(true);
                dispose();
            }
        });

        buttonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn signIn = new SignIn();
                signIn.setVisible(true);
                dispose();
            }
        });

        panel.add(buttonUsarApp);
        panel.add(buttonSeleccionarPeriodo);
        panel.add(buttonCerrarSesion);

        add(panel);
    }
}
