package com.calendario;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class Calendario extends JFrame {
    private JPanel panel;
    private List<LocalDate> fechasEnRojo;

    public Calendario(List<LocalDate> fechasEnRojo) {
        this.fechasEnRojo = fechasEnRojo;

        setTitle("Calendario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridLayout(0, 7));

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue();
        int anhoActual = fechaActual.getYear();

        // Calcular el día de la semana en el que comienza el mes
        LocalDate primerDiaDelMes = LocalDate.of(anhoActual, mesActual, 1);
        int diaDeLaSemana = primerDiaDelMes.getDayOfWeek().getValue();

        // Añadir etiquetas para los días de la semana
        String[] diasSemana = { "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb","Dom"};
        for (String dia : diasSemana) {
            JLabel label = new JLabel(dia, SwingConstants.CENTER);
            panel.add(label);
        }

        // Llenar el calendario con los días del mes
        for (int i = 1; i < diaDeLaSemana; i++) {
            panel.add(new JLabel(""));
        }
        for (int i = 1; i <= fechaActual.lengthOfMonth(); i++) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            LocalDate fechaLabel = LocalDate.of(anhoActual, mesActual, i);
            if (fechasEnRojo.contains(fechaLabel)) {
                label.setForeground(Color.RED);
            }
            panel.add(label);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
    public String toText() {
        StringBuilder sb = new StringBuilder();

        // Añadir los días de la semana
        String[] diasSemana = { "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb","Dom"};
        for (String dia : diasSemana) {
            sb.append(dia).append("\t");
        }
        sb.append("\n");

        // Añadir los días del mes
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue();
        int anhoActual = fechaActual.getYear();
        LocalDate primerDiaDelMes = LocalDate.of(anhoActual, mesActual, 1);
        int diaDeLaSemana = primerDiaDelMes.getDayOfWeek().getValue();
        for (int i = 1; i < diaDeLaSemana; i++) {
            sb.append("\t");
        }
        for (int i = 1; i <= fechaActual.lengthOfMonth(); i++) {
            sb.append(i).append("\t");
            if (fechasEnRojo.contains(LocalDate.of(anhoActual, mesActual, i))) {
                sb.append("(Rojo)");
            }
            if (i % 7 == 0) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}

