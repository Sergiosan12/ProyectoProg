package com.interfaces.deporte;

import com.informe.GenerarInforme;
import com.informe.Informe;
import com.interfaces.cuestionarios.uso.UsoProg;

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
                UsoProg usoProg = new UsoProg();
                // Hace visible la nueva instancia de UsoProg
                usoProg.setVisible(true);
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
                GenerarInforme generarInforme = new GenerarInforme(informe);
            }
        });
    }
}
