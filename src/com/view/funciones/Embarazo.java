package com.view.funciones;

import com.controller.GenerarPDF;
import com.controller.GenerateDiaFases;
import com.controller.InformeBuilder;
import com.model.funciones.Menstruacion;
import com.view.cuestionarios.uso.UsoProg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Embarazo {
    private JPanel panel1;
    private JButton buttonGenerarInforme;
    private JButton buttonVolver;
    private JLabel FaseFolicilar;
    private JLabel FotoEmbarazo;

    private static final int OPCION_SELECCIONADA = 1;
    private final Menstruacion menstruacion;
    private final GenerateDiaFases generateDiaFases;
    private final InformeBuilder informeBuilder = new InformeBuilder();

    public Embarazo(Menstruacion menstruacion) {
        this.menstruacion = menstruacion;
        this.generateDiaFases = new GenerateDiaFases(menstruacion);
        this.informeBuilder.fromMenstruacion(menstruacion.getUsuario());

        initializeUI();
        calculateAndDisplayPhases();
        addListeners();
    }

    private void initializeUI() {
        buttonVolver.setBackground(Color.decode("#F6C4F6"));
        buttonGenerarInforme.setBackground(Color.decode("#F6C4F6"));
    }

    private void calculateAndDisplayPhases() {
        int mediaFaseFolicular = generateDiaFases.CalculoMediaFaseFolicular();
        int mediaFaseLutea = generateDiaFases.CalculoFaseLutea(mediaFaseFolicular);

        Date inicioFaseOvulacion = generateDiaFases.CalculoInicioFaseOvulacion(mediaFaseFolicular, mediaFaseLutea);
        Date inicioFaseLutea = generateDiaFases.CalculoInicioFaseLutea(mediaFaseFolicular);
        Date inicioFaseFolicular = generateDiaFases.CalculoInicioFaseFolicular();

        inicioFaseOvulacion = defaultIfNull(inicioFaseOvulacion);
        inicioFaseLutea = defaultIfNull(inicioFaseLutea);

        String texto = String.format("Tu periodo fértil comprende de: %s a %s",
                formatDate(inicioFaseOvulacion), formatDate(inicioFaseLutea));
        FaseFolicilar.setText(texto);
    }

    private Date defaultIfNull(Date date) {
        return date == null ? new Date() : date;
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    private void addListeners() {
        buttonVolver.addActionListener(this::handleButtonVolverClick);
        buttonGenerarInforme.addActionListener(this::handleButtonGenerarInformeClick);
    }

    private void handleButtonVolverClick(ActionEvent e) {
        UsoProg usoProg = new UsoProg(menstruacion);
        usoProg.setVisible(true);
        ((JFrame) SwingUtilities.getWindowAncestor(panel1)).dispose();
    }

    private void handleButtonGenerarInformeClick(ActionEvent e) {
        GenerarPDF generarPDF = new GenerarPDF(menstruacion);
        generarPDF.generarInforme(OPCION_SELECCIONADA);
        JOptionPane.showMessageDialog(panel1, "PDF generado correctamente");
    }

    public JPanel getPanel() {
        return panel1;
    }
}
