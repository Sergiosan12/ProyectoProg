package com.view.cuestionarios.sangrado;

import com.view.SelectorFecha;
import com.model.tiempo.Duracion;
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
    Duracion duracion = new Duracion();
    Menstruacion menstruacion = new Menstruacion();

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
            numerCiclo.setModel(new SpinnerNumberModel(28, 1, Integer.MAX_VALUE, 1));
            numerSangrado.setModel(new SpinnerNumberModel(5, 1, Integer.MAX_VALUE, 1));
            continuarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        duracionCiclo = (Integer) numerCiclo.getValue();
                        duracionSangrado = (Integer) numerSangrado.getValue();
                        duracion.setDuracionCiclo(duracionCiclo);
                        duracion.setDuracionSangrado(duracionSangrado);
                        System.out.println("Duración del ciclo: " + duracion.getDuracionCiclo());
                        System.out.println("Duración del sangrado: " + duracion.getDuracionSangrado());
                        if (duracionCiclo <= 0 || duracionSangrado <= 0 || duracionSangrado > duracionCiclo) {
                            throw new IllegalArgumentException("Por favor, introduce valores válidos.");
                        }
                        menstruacion.setMediaCiclo(duracionCiclo);
                        menstruacion.setMediaSangrado(duracionSangrado);
                        new SelectorFecha(menstruacion, duracion); // Pasa la instancia de Menstruacion a SelectorFecha
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
