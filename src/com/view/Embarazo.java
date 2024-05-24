package com.view;

import com.controller.GenerarPDF;
import com.controller.GenerateDiaFases;
import com.model.funciones.Menstruacion;
import com.model.funciones.Informe;
import com.view.cuestionarios.uso.UsoProg;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Embarazo {
    private JPanel panel1;
    private JButton buttonGenerarInforme;
    private JButton buttonVolver;
    private JLabel FaseFolicilar;
    private JLabel FotoEmbarazo;
    private GenerateDiaFases generateDiaFases;

    public Embarazo(Menstruacion menstruacion) {
        generateDiaFases = new GenerateDiaFases(menstruacion);

        String texto = "Tu periodo fértil comprende de: " + generateDiaFases.CalculoInicioFaseOvulacion() + " a " + generateDiaFases.CalculoInicioFaseLutea();
        FaseFolicilar.setText(texto);

        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsoProg usoProg = new UsoProg(menstruacion);
                usoProg.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(panel1)).dispose();
            }
        });

        buttonGenerarInforme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Informe informe = new Informe();
                GenerarPDF generarPDF = new GenerarPDF();
                generarPDF.generarInforme();
            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }
}
