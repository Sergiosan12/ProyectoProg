package com.view.cuestionarios.sangrado;

import com.view.SelectorFecha;
import com.model.tiempo.Duracion;
import com.model.funciones.Menstruacion;
import com.model.usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CuestionarioFInal {
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
    // Almacena el usuario registrado
    private Menstruacion menstruacion;

    public CuestionarioFInal(Usuario usuario) {
        this.usuario = usuario;
        this.menstruacion = new Menstruacion(); // Inicializa la instancia de Menstruacion
        menstruacion.setUsuario(usuario.getUsuario()); // Usa el usuario pasado al constructor
        getJpanel();
    }

    public void getJpanel() {
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
                    new SelectorFecha(menstruacion,duracion); // Pasa la instancia de Menstruacion a SelectorFecha
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce números válidos.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
}
