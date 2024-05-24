package com.view;

import com.controller.GenerarInforme;
import com.controller.GenerateDiaFases;
import com.model.funciones.Menstruacion;
import com.model.fases.FaseFolicular;
import com.model.fases.FaseOvulacion;
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
                GenerarInforme generarInforme = new GenerarInforme(informe);
generarInforme.generarInforme();
            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }
}
