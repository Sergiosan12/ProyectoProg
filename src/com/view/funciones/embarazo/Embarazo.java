package com.view.funciones.embarazo;

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

/**
 * La clase Embarazo proporciona una interfaz para generar un informe relacionado con el embarazo
 * basado en los datos de menstruación proporcionados.
 */
public class Embarazo {

    /**
     * El panel principal que contiene los componentes de la interfaz de usuario relacionados con el embarazo.
     */
    private JPanel panel1;

    /**
     * Botón para generar el informe de embarazo.
     */
    private JButton buttonGenerarInforme;

    /**
     * Botón para volver a la pantalla anterior.
     */
    private JButton buttonVolver;

    /**
     * Etiqueta para mostrar la fase folicular del ciclo menstrual.
     */
    private JLabel FaseFolicilar;

    /**
     * Instancia de la clase GenerateDiaFases utilizada para calcular las fases del ciclo menstrual.
     */
    private GenerateDiaFases generateDiaFases;

    /**
     * Etiqueta para mostrar una foto relacionada con el embarazo.
     */
    private JLabel FotoEmbarazo;

    /**
     * Constante de instancia que indica la opción seleccionada para generar el informe.
     */
    private final int OPCIONSELECCIONADA = 1;

    /**
     * Instancia de InformeBuilder para construir el informe de embarazo.
     */
    InformeBuilder informeBuilder = new InformeBuilder();

    /**
     * Constructor de la clase Embarazo.
     * @param menstruacion Los datos de menstruación utilizados para calcular el informe de embarazo.
     */
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

    /**
     * Calcula todas las fases del ciclo menstrual y muestra la información en la interfaz de usuario.
     */
    private void calculateAndDisplayPhases() {
        int mediaFaseFolicular = generateDiaFases.CalculoMediaFaseFolicular();
        int mediaFaseLutea = generateDiaFases.CalculoFaseLutea(mediaFaseFolicular);

        Date inicioFaseOvulacion = generateDiaFases.CalculoInicioFaseOvulacion(mediaFaseFolicular,mediaFaseLutea);
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

    /**
     * Devuelve el panel principal que contiene los componentes de la interfaz de usuario relacionados con el embarazo.
     * @return El panel principal.
     */
    public JPanel getPanel() {
        return panel1;
    }
}
