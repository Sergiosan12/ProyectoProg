package com.view;

import com.controller.GenerarPDF;
import com.controller.GenerateDiaFases;
import com.controller.InformeBuilder;
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
InformeBuilder informeBuilder = new InformeBuilder();
    public Embarazo(Menstruacion menstruacion) {
        generateDiaFases = new GenerateDiaFases(menstruacion);
         informeBuilder.fromMenstruacion(menstruacion);
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
                GenerarPDF generarPDF = new GenerarPDF();
                generarPDF.generarInforme();

            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }
}
