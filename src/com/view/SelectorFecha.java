package com.view;

import com.database.InsertDatabase;
import com.model.decoracion.DateLabelFormatter;
import com.model.funciones.Menstruacion;
import com.view.cuestionarios.uso.UsoProg;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * La clase {@code SelectorFecha} representa una interfaz gráfica de usuario que permite al usuario seleccionar una fecha.
 * Esta fecha representa el último período de la usuaria.
 * La fecha seleccionada se inserta en la base de datos y se utiliza para calcular la próxima fecha de menstruación.
 *
 * <p>Esta clase hereda de {@link JFrame}, proporcionando una ventana independiente con componentes de selección de fecha.
 * Utiliza la librería JDatePicker para permitir la selección de fechas y se comunica con una base de datos para almacenar
 * la fecha seleccionada.</p>
 *
 */
public class SelectorFecha extends JFrame {
    protected JDatePickerImpl datePicker;
    private Date today;
    private Date oneYearAgo;
    InsertDatabase insertDatabase;

    /**
     * Crea una nueva instancia de {@code SelectorFecha}.
     *
     * @param menstruacion la menstruación de la usuaria que contiene la información del período.
     */
    public SelectorFecha(Menstruacion menstruacion) {
        JPanel panel = getjPanel();
        getBtnContinuar(panel, menstruacion);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Crea y añade un botón "Continuar" al panel dado.
     * Cuando se hace clic en este botón, se guarda la fecha seleccionada en la base de datos y se abre la interfaz de usuario principal.
     *
     * @param panel el panel al que se añadirá el botón.
     * @param menstruacion la menstruación de la usuaria.
     */
    private void getBtnContinuar(JPanel panel, Menstruacion menstruacion) {
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBackground(new Color(255, 105, 180)); // Rosa más oscuro
        btnContinuar.setForeground(Color.WHITE);

        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = (Date) datePicker.getModel().getValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = sdf.format(selectedDate);
                menstruacion.setLastperiod(selectedDate);
                insertDatabase.insertDateIntoDatabase(menstruacion);
                dispose();
                UsoProg usoProg = new UsoProg();
                usoProg.setVisible(true);
            }
        });

        // Añadir el botón al panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(btnContinuar, gbc);
    }

    /**
     * Crea y devuelve un {@code JPanel} que contiene todos los componentes de la interfaz de usuario.
     *
     * @return un {@code JPanel} que contiene todos los componentes de la interfaz de usuario.
     */
    private JPanel getjPanel() {
        setTitle("Selector de Fecha");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        today = calendar.getTime();

        // Obtener la fecha de hace un año
        calendar.add(Calendar.YEAR, -1);
        oneYearAgo = calendar.getTime();

        // Restablecer el calendario a la fecha actual
        calendar.add(Calendar.YEAR, 1);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        // Crear el modelo de fecha y establecer la fecha inicial
        UtilDateModel model = new UtilDateModel();
        model.setDate(currentYear, currentMonth, currentDay);
        model.setSelected(true);

        // Añadir un escuchador de cambios de propiedad al modelo
        model.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("value".equals(evt.getPropertyName())) {
                    Object selectedValue = evt.getNewValue();
                    if (selectedValue instanceof Date) {
                        Date selectedDate = (Date) selectedValue;
                        if (selectedDate.after(today) || selectedDate.before(oneYearAgo)) {
                            model.setValue((Date) evt.getOldValue());
                            JOptionPane.showMessageDialog(null, "Selecciona una fecha dentro del rango válido.");
                        }
                    }
                }
            }
        });

        // Crear el selector de fecha
        Properties p = new Properties();
        p.put("text.today", "Hoy");
        p.put("text.month", "Mes");
        p.put("text.year", "Año");
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Crear el panel con GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 241, 241));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Añadir la etiqueta
        JLabel label = new JLabel("¿Cuándo fue tu último periodo?");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        // Añadir el selector de fecha
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(datePicker, gbc);

        return panel;
    }


}
