package com.interfaces.cuestionarios.uso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase UsoProg representa una interfaz gráfica de usuario que permite al usuario seleccionar el uso que le dará a la aplicación.
 * Esta interfaz contiene botones para seleccionar si el usuario utilizará la aplicación para el seguimiento del deporte, embarazo o planificación de ocio.
 */
public class UsoProg {
    private JLabel PreguntaLabel;
    private JButton buttonDeporte;
    private JButton buttonEmbarazo;
    private JButton planningOcioButton;
    private JPanel PanelPrincipal;
    private JFrame frame;

    /**
     * Crea una nueva instancia de UsoProg.
     * Inicializa la interfaz gráfica de usuario y configura los listeners de los botones.
     */
    public UsoProg() {
        frame = new JFrame("UsoProg");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(PanelPrincipal);
        buttonEmbarazo.addActionListener(new ActionListener() {
            /**
             * Ocurre cuando se hace clic en el botón de embarazo.
             *
             * @param e evento que se produce al hacer clic en el botón.
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        buttonDeporte.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /**
     * Establece la visibilidad de la interfaz gráfica de usuario.
     * @param b un booleano que indica si la interfaz gráfica de usuario debe ser visible o no
     */
    public void setVisible(boolean b) {
        frame.setVisible(b);
    }

}