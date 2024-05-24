package com.view.deporte;

import com.controller.GenerarPDF;
import com.model.funciones.Informe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deporte {
    private JPanel panel1;
    private JButton buttonGenerarInforme;
    private JButton buttonVolver;
    private JLabel PeriodoFertilLable;

    public Deporte() {
        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crea una nueva instancia de UsoProg
                // Cierra la ventana actual
                ((JFrame) SwingUtilities.getWindowAncestor(panel1)).dispose();
            }
        });
        buttonGenerarInforme.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crea una nueva instancia de Informe
                Informe informe = new Informe();
                GenerarPDF generarPDF = new GenerarPDF();
            }
        });
    }
}
