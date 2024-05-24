package com.cuestionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase CuestionarioSwing es una ventana de GUI (Interfaz Gráfica de Usuario) que permite al usuario ingresar la duración
 * del ciclo menstrual y del sangrado menstrual a través de campos de texto y un botón de aceptar.
 * Esta clase interactúa con un objeto Duracion para establecer y mostrar la duración del ciclo y del sangrado.
 */
public class CuestionarioSwing extends JFrame {

    private JTextField txtCiclo;
    private JTextField txtSangrado;
    public int duracionCiclo;
    public int duracionSangrado;

    /**
     * Constructor de la clase CuestionarioSwing que inicializa la ventana de GUI y configura sus componentes.
     * @param duracion El objeto Duracion con el que se interactúa para establecer y mostrar la duración del ciclo y del sangrado.
     */
    public CuestionarioSwing(Duracion duracion) {
        setTitle("Cuestionario del Ciclo Menstrual");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblCiclo = new JLabel("Duración del ciclo menstrual:");
        txtCiclo = new JTextField();
        JLabel lblSangrado = new JLabel("Duración del sangrado menstrual:");
        txtSangrado = new JTextField();
        JButton btnAceptar = new JButton("Aceptar");

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    duracionCiclo = Integer.parseInt(txtCiclo.getText());
                    duracionSangrado = Integer.parseInt(txtSangrado.getText());
                    duracion.setDuracionCiclo(duracionCiclo);
                    duracion.setDuracionSangrado(duracionSangrado);

                    if (duracionCiclo <= 0 || duracionSangrado <= 0 || duracionSangrado > duracionCiclo) {
                        throw new IllegalArgumentException("Por favor, introduce valores válidos.");
                    }

                    JOptionPane.showMessageDialog(null, "Información guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    mostrarError("Por favor, introduce números válidos.");
                } catch (IllegalArgumentException ex) {
                    mostrarError(ex.getMessage());
                }
            }
        });

        panel.add(lblCiclo);
        panel.add(txtCiclo);
        panel.add(lblSangrado);
        panel.add(txtSangrado);
        panel.add(btnAceptar);

        add(panel);
        setVisible(true);
    }

    /**
     * Método privado utilizado para mostrar mensajes de error en un cuadro de diálogo.
     * @param mensaje El mensaje de error a mostrar.
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
