package com.view.funciones;

import com.controller.GenerarPDF;
import com.controller.GenerateDiaFases;
import com.controller.InformeBuilder;
import com.model.funciones.Menstruacion;
import com.view.cuestionarios.uso.UsoProg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Embarazo {
    private JPanel panel1;
    private JButton buttonGenerarInforme;
    private JButton buttonVolver;
    private JLabel FaseFolicilar;
    private GenerateDiaFases generateDiaFases;
    private JLabel FotoEmbarazo;

    private final int OPCIONSELECCIONADA = 1; // Constante de instancia
    InformeBuilder informeBuilder = new InformeBuilder();

    public Embarazo(Menstruacion menstruacion) {
        // Inicializa generateDiaFases con la instancia de menstruacion
        generateDiaFases = new GenerateDiaFases(menstruacion);
        informeBuilder.fromMenstruacion(menstruacion.getUsuario());

        // Cambia el color de los botones
        buttonVolver.setBackground(Color.decode("#F6C4F6"));
        buttonGenerarInforme.setBackground(Color.decode("#F6C4F6"));

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
                generarPDF.generarInforme(OPCIONSELECCIONADA);
                JOptionPane.showMessageDialog(panel1, "Pdf generado correctamente");
            }
        });
    }

    private void calculateAndDisplayPhases() {
        int mediaFaseFolicular = generateDiaFases.CalculoMediaFaseFolicular();
        int mediaFaseLutea = generateDiaFases.CalculoFaseLutea(mediaFaseFolicular);

        Date inicioFaseOvulacion = generateDiaFases.CalculoInicioFaseOvulacion(mediaFaseFolicular);
        Date inicioFaseLutea = generateDiaFases.CalculoInicioFaseLutea(mediaFaseFolicular);
        Date inicioFaseFolicular = generateDiaFases.CalculoInicioFaseFolicular();

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