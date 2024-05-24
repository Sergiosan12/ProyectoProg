package com.view.cuestionarios.uso;

import com.model.funciones.Menstruacion;
import com.view.Embarazo;

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
     * @param menstruacion la instancia de Menstruacion que se pasará a otras vistas
     */
    public UsoProg(Menstruacion menstruacion) {
        try {
            frame = new JFrame("UsoProg");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(PanelPrincipal);

            buttonEmbarazo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Embarazo embarazo = new Embarazo(menstruacion);
                        JFrame embarazoFrame = new JFrame("Embarazo");
                        embarazoFrame.setContentPane(embarazo.getPanel());
                        embarazoFrame.pack();
                        embarazoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        embarazoFrame.setVisible(true);
                        frame.dispose(); // Cierra la ventana actual
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });


        buttonDeporte.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e el evento que se procesa
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para el botón de deporte
                try {
                    // Código que puede lanzar una excepción
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Establece la visibilidad de la interfaz gráfica de usuario.
     * @param b un booleano que indica si la interfaz gráfica de usuario debe ser visible o no
     */
    public void setVisible(boolean b) {
        try {
            frame.setVisible(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}