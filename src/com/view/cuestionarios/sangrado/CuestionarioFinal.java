package com.view.cuestionarios.sangrado;

import com.controller.GenerateDiaFases;
import com.model.funciones.Menstruacion;
import com.model.usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CuestionarioFinal {
    public JPanel panelPrincipal;
    private JLabel lblCiclo;
    private JLabel lblSangrado;
    private JButton continuarButton;
    private JSpinner numerCiclo;
    private JSpinner numerSangrado;
    int duracionCiclo;
    int duracionSangrado;
    private Usuario usuario;
    Menstruacion menstruacion = new Menstruacion();
    GenerateDiaFases generateDiaFases = new GenerateDiaFases(menstruacion);

    public CuestionarioFinal(Usuario usuario) {
        try {
            this.usuario = usuario;
            menstruacion.setUsuario(usuario.getUsuario()); // Usa el usuario pasado al constructor
            getJpanel();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al inicializar el cuestionario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getJpanel() {
        try {
            numerCiclo.setModel(new SpinnerNumberModel(28, 15, 60, 1));
            numerSangrado.setModel(new SpinnerNumberModel(5, 3, 15, 1));
            continuarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        duracionCiclo = (Integer) numerCiclo.getValue();
                        duracionSangrado = (Integer) numerSangrado.getValue();
                        if (duracionCiclo <= 0 || duracionSangrado <= 0 || duracionSangrado > duracionCiclo) {
                            throw new IllegalArgumentException("Por favor, introduce valores válidos.");
                        }
                        generateDiaFases.CambiarDiasSangrado(duracionSangrado);
                        generateDiaFases.CambiarDiasCiclo(duracionCiclo);
                        new SelectorFecha(menstruacion); // Pasa la instancia de Menstruacion y Duracion a SelectorFecha

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, introduce números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al procesar los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al inicializar el panel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

