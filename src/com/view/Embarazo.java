package com.view;

import com.controller.GenerarInforme;
import com.controller.GenerateDiaFases;
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
        GenerateDiaFases generateDiaFases=new GenerateDiaFases();

    public Embarazo() {

        String texto = "Tu periodo fertil comprende de: " +  generateDiaFases.CalculoInicioFaseOvulacion()+ "a" +generateDiaFases.CalculoInicioFaseLutea();
        FaseFolicilar.setText(texto);

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
