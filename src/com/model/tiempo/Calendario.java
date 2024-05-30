package com.model.tiempo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * La clase {@code Calendario} representa un calendario gráfico que muestra
 * los días del mes y resalta ciertos días en rojo.
 */
public class Calendario extends JFrame {
    private JPanel panel;
    private List<LocalDate> fechasEnRojo;

    /**
     * Crea una nueva instancia de {@code Calendario}.
     *
     * @param fechasEnRojo una lista de fechas que se deben resaltar en rojo. Estas fechas
     *                     deben ser instancias de {@link LocalDate} y deben corresponder
     *                     al mes que se va a mostrar en el calendario.
     */
    public Calendario(List<LocalDate> fechasEnRojo) {
        this.fechasEnRojo = fechasEnRojo;
        try {
            // Configurar la ventana
            setTitle("Calendario");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            panel = new JPanel(new GridLayout(0, 7));

            // Añadir etiquetas para los días de la semana
            getEtiquetasDiaSemana();
            // Llenar el calendario con los días del mes
            FechaActual fechaActual = getFechaActual();
            getDiasDelMes(fechasEnRojo, getDiaDeLaSemana(fechaActual.anhoActual(), fechaActual.mesActual()), fechaActual);

            add(panel, BorderLayout.CENTER);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al inicializar el calendario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Llena el calendario con los días del mes, resaltando los días en la lista {@code fechasEnRojo}.
     *
     * @param fechasEnRojo   una lista de fechas que se deben resaltar en rojo
     * @param diaDeLaSemana  el día de la semana en que empieza el mes (1 para Lunes, 7 para Domingo)
     * @param fechaActual    la fecha actual incluyendo el año y el mes
     */
    private void getDiasDelMes(List<LocalDate> fechasEnRojo, int diaDeLaSemana, FechaActual fechaActual) {
        for (int i = 1; i < diaDeLaSemana; i++) {
            panel.add(new JLabel(""));
        }
        for (int i = 1; i <= fechaActual.fechaActual().lengthOfMonth(); i++) {
            JLabel label = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            LocalDate fechaLabel = LocalDate.of(fechaActual.anhoActual(), fechaActual.mesActual(), i);
            if (fechasEnRojo.contains(fechaLabel)) {
                label.setForeground(Color.RED);
            }
            panel.add(label);
        }
    }

    /**
     * Añade las etiquetas de los días de la semana al panel del calendario.
     */
    private void getEtiquetasDiaSemana() {
        String[] diasSemana = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};
        for (String dia : diasSemana) {
            JLabel label = new JLabel(dia, SwingConstants.CENTER);
            panel.add(label);
        }
    }

    /**
     * Obtiene el día de la semana del primer día del mes.
     *
     * @param anho el año actual
     * @param mes  el mes actual
     * @return el día de la semana del primer día del mes (1 para Lunes, 7 para Domingo)
     */
    private static int getDiaDeLaSemana(int anho, int mes) {
        LocalDate primerDiaDelMes = LocalDate.of(anho, mes, 1);
        return primerDiaDelMes.getDayOfWeek().getValue();
    }

    /**
     * Obtiene la fecha actual, incluyendo el año y el mes.
     *
     * @return una instancia de {@link FechaActual} que contiene la fecha actual, el mes actual y el año actual
     */
    private static FechaActual getFechaActual() {
        LocalDate fechaActual = LocalDate.now();
        int mesActual = fechaActual.getMonthValue();
        int anhoActual = fechaActual.getYear();
        return new FechaActual(fechaActual, mesActual, anhoActual);
    }

    /**
     * Clase record que representa la fecha actual, incluyendo el año y el mes.
     */
    private record FechaActual(LocalDate fechaActual, int mesActual, int anhoActual) {
    }

    /**
     * Devuelve una representación en texto del calendario.
     * La representación incluye los días de la semana y los días del mes,
     * con indicaciones para las fechas que están resaltadas en rojo.
     *
     * @return una cadena de texto que representa el calendario.
     */
    public String toText() {
        StringBuilder sb = new StringBuilder();
        try {
            // Añadir los días de la semana
            String[] diasSemana = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};
            for (String dia : diasSemana) {
                sb.append(dia).append("\t");
            }
            sb.append("\n");

            // Añadir los días del mes
            // Añadir espacios en blanco para los días anteriores al primer día del mes
            FechaActual fechaActual = getFechaActual();
            int diaDeLaSemana = getDiaDeLaSemana(fechaActual.anhoActual(), fechaActual.mesActual());
            for (int i = 1; i < diaDeLaSemana; i++) {
                sb.append("\t");
            }
            for (int i = 1; i <= fechaActual.fechaActual().lengthOfMonth(); i++) {
                sb.append(i).append("\t");
                if (fechasEnRojo.contains(LocalDate.of(fechaActual.anhoActual(), fechaActual.mesActual(), i))) {
                    sb.append("(Rojo)");
                }
                if ((i + diaDeLaSemana - 1) % 7 == 0) {
                    sb.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar el texto del calendario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return sb.toString();
    }
}
