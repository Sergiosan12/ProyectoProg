package com.view.funciones.deporte;

import com.controller.GenerarPDF;
import com.model.funciones.Informe;
import com.model.funciones.Menstruacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deporte {
    private JPanel panel1;
    private JButton buttonGenerarInforme;
    private JButton buttonVolver;
    private JLabel PeriodoFertilLable;
    private final int OPCION_DEPORTE = 1;

    // Agrega una instancia de Menstruacion
    private Menstruacion menstruacion;

    public Deporte(Menstruacion menstruacion) {
        // Inicializa la instancia de Menstruacion
        this.menstruacion = menstruacion;

        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra la ventana actual
                ((JFrame) SwingUtilities.getWindowAncestor(panel1)).dispose();
            }
        });

        buttonGenerarInforme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crea una nueva instancia de GenerarPDF con la instancia de Menstruacion
                GenerarPDF generarPDF = new GenerarPDF(menstruacion);
                // Llama al método para generar el informe
                generarPDF.generarInforme(OPCION_DEPORTE);
            }
        });
    }
}
