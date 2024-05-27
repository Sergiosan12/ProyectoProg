package com.view.cuestionarios.uso;

import com.controller.GenerateDiaFases;
import com.model.funciones.Menstruacion;
import com.view.funciones.deporte.InterfazDeporte;
import com.view.funciones.Embarazo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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
    Menstruacion menstruacion;

    private GenerateDiaFases generateDiaFases;

    // Constructor de la clase UsoProg corregido
    public UsoProg(Date ultimoPeriodo) {
        if (ultimoPeriodo != null) {
            // Código para inicializar la clase con la fecha del último periodo
        } else {
            // Manejo del caso en el que la fecha del último periodo es null
            System.out.println("La fecha del último período no puede ser nula");
        }
    }

    /**
     * Crea una nueva instancia de UsoProg.
     * Inicializa la interfaz gráfica de usuario y configura los listeners de los botones.
     * @param menstruacion la instancia de Menstruacion que se pasará a otras vistas
     */
    public UsoProg(Menstruacion menstruacion) {
        this.menstruacion = menstruacion;
        this.generateDiaFases = new GenerateDiaFases(menstruacion);
        generateDiaFases.calcularTodasLasFases();
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
                Embarazo embarazo = new Embarazo(menstruacion);
                JFrame embarazoFrame = new JFrame("Embarazo");
                embarazoFrame.setContentPane(embarazo.getPanel());
                embarazoFrame.pack();
                embarazoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                embarazoFrame.setVisible(true);
                frame.dispose();
            }
        });

        buttonDeporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazDeporte interfazDeporte = new InterfazDeporte(menstruacion);
                interfazDeporte.showDeportesGUI(frame);
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
