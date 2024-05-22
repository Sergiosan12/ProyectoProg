package com.calendario;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * La clase {@code Calendario} representa un calendario mensual que se puede mostrar en una ventana.
 * Cada instancia de {@code Calendario} muestra un mes específico del año actual y resalta ciertas fechas en rojo.
 * La interfaz gráfica se construye utilizando componentes de Swing y se organiza en un diseño de cuadrícula.
 * Las fechas resaltadas en rojo son especificadas a través de una lista de objetos {@code LocalDate}.
 *
 * <p>La clase hereda de {@link JFrame}, por lo que es una ventana independiente que puede mostrarse
 * al usuario. El calendario se representa visualmente mediante etiquetas de texto {@link JLabel}
 * organizadas en un panel con un diseño de cuadrícula de 7 columnas (una por cada día de la semana).</p>
 *
 * <p>La clase proporciona también un método {@link #toText()} que devuelve una representación textual
 * del calendario, incluyendo los días de la semana y los días del mes, con indicaciones para las fechas
 * resaltadas en rojo.</p>
 *
 * <h2>Ejemplo de Uso:</h2>
 * <pre>{@code
 * List<LocalDate> fechasEnRojo = Arrays.asList(LocalDate.of(2024, 5, 15), LocalDate.of(2024, 5, 20));
 * Calendario calendario = new Calendario(fechasEnRojo);
 * System.out.println(calendario.toText());
 * }</pre>
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
        // Configurar la ventana
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
        String[] diasSemana = { "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom" };
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

    /**
     * Devuelve una representación en texto del calendario.
     * La representación incluye los días de la semana y los días del mes,
     * con indicaciones para las fechas que están resaltadas en rojo.
     *
     * @return una cadena de texto que representa el calendario.
     */
    public String toText() {
        StringBuilder sb = new StringBuilder();

        // Añadir los días de la semana
        String[] diasSemana = { "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom" };
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
        // Añadir espacios en blanco para los días anteriores al primer día del mes
        for (int i = 1; i < diaDeLaSemana; i++) {
            sb.append("\t");
        }
        // Añadir los días del mes
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
