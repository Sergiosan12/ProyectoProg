package com.view;

import com.controller.GenerarPDF;
import com.controller.GenerateDiaFases;
import com.controller.InformeBuilder;
import com.model.funciones.Menstruacion;
import com.view.cuestionarios.uso.UsoProg;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Embarazo {
    private JPanel panel1;
    private JButton buttonGenerarInforme;
    private JButton buttonVolver;
    private JLabel FaseFolicilar;
    private JLabel FotoEmbarazo;
    private GenerateDiaFases generateDiaFases;
    InformeBuilder informeBuilder = new InformeBuilder();

    public Embarazo(Menstruacion menstruacion) {
        // Inicializa generateDiaFases con la instancia de menstruacion
        generateDiaFases = new GenerateDiaFases(menstruacion);
        informeBuilder.fromMenstruacion(menstruacion);

        // Calcula todas las fases y actualiza la etiqueta FaseFolicilar
        calculateAndDisplayPhases();

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
                GenerarPDF generarPDF = new GenerarPDF(menstruacion);
                generarPDF.generarInforme();
                JOptionPane.showMessageDialog(panel1, "Pdf generado correctamente");
            }
        });
    }

    private void calculateAndDisplayPhases() {
        int mediaFaseFolicular = generateDiaFases.CalculoMediaFaseFolicular();
        int mediaFaseLutea = generateDiaFases.CalculoFaseLutea(mediaFaseFolicular);

        Date inicioFaseOvulacion = generateDiaFases.CalculoInicioFaseOvulacion(mediaFaseFolicular, mediaFaseLutea);
        Date inicioFaseLutea = generateDiaFases.CalculoInicioFaseLutea(mediaFaseFolicular);
        Date inicioFaseFolicular = generateDiaFases.CalculoInicioFaseFolicular(mediaFaseFolicular);

        // Verificar si las fechas calculadas son nulas y asignar la fecha actual como valor predeterminado
        if (inicioFaseOvulacion == null) {
            inicioFaseOvulacion = new Date(); // Fecha actual
        }
        if (inicioFaseLutea == null) {
            inicioFaseLutea = new Date(); // Fecha actual
        }

        // Formatear las fechas como texto
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String texto = "Tu periodo fértil comprende de: " + sdf.format(inicioFaseOvulacion) + " a " + sdf.format(inicioFaseLutea);
        FaseFolicilar.setText(texto);
    }

    public JPanel getPanel() {
        return panel1;
    }
}