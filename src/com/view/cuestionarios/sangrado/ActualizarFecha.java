package com.view.cuestionarios.sangrado;

import com.controller.GenerateDiaFases;
import com.database.InsertDatabaseMenstruacion;
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

public class ActualizarFecha extends JFrame {

    // Constantes de mensajes
    private static final String ERROR_MSG_INIT_INTERFACE = "Error al inicializar la interfaz: ";
    private static final String ERROR_MSG_PROCESS_DATE = "Error al procesar la fecha: ";
    private static final String ERROR_MSG_CREATE_PANEL = "Error al crear el panel de selección de fecha: ";
    private static final String MSG_SELECT_DATE = "Por favor, selecciona una fecha.";
    private static final String MSG_INVALID_DATE = "Selecciona una fecha dentro del rango válido.";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    protected JDatePickerImpl datePicker;
    private Date today;
    private Date oneYearAgo;
    private final InsertDatabaseMenstruacion insertDatabase = new InsertDatabaseMenstruacion();
    private Menstruacion menstruacion;

    public ActualizarFecha() {
        // Constructor vacío si es necesario
    }

    public ActualizarFecha(Menstruacion menstruacion) {
        this.menstruacion = menstruacion;
        initialize();
    }

    private void initialize() {
        try {
            JPanel panel = createMainPanel();
            addButton(panel);
            add(panel, BorderLayout.CENTER);
        } catch (Exception e) {
            handleException(ERROR_MSG_INIT_INTERFACE, e);
        }
        setupFrame();
    }

    private void setupFrame() {
        setTitle("Selector de Fecha");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addButton(JPanel panel) {
        JButton btnContinuar = new JButton("Continuar");
        styleButton(btnContinuar);
        btnContinuar.addActionListener(e -> handleContinueButton(panel));
        addButtonToPanel(panel, btnContinuar);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(255, 105, 180));
        button.setForeground(Color.WHITE);
    }

    private void addButtonToPanel(JPanel panel, JButton button) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(button, gbc);
    }

    private void handleContinueButton(JPanel panel) {
        try {
            Date selectedDate = (Date) datePicker.getModel().getValue();
            if (selectedDate == null) {
                showMessage(panel, MSG_SELECT_DATE, "Fecha no seleccionada", JOptionPane.WARNING_MESSAGE);
                return;
            }

            updateMenstruationData(selectedDate);
            dispose();
            openUsoProg();
        } catch (Exception ex) {
            handleException(ERROR_MSG_PROCESS_DATE, ex);
        }
    }

    private void updateMenstruationData(Date selectedDate) throws Exception {
        String formattedDate = formatDate(selectedDate);
        GenerateDiaFases.CambiarDateLastPeriod(selectedDate);
        menstruacion.setLastperiod(selectedDate);
        GenerateDiaFases.calcularTodasLasFases();
        insertDatabase.updateDatabase(menstruacion.getUsuario(), menstruacion);
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(date);
    }

    private void openUsoProg() {
        UsoProg usoProg = new UsoProg(menstruacion);
        usoProg.setVisible(true);
    }

    private JPanel createMainPanel() {
        try {
            setupCalendar();
            UtilDateModel model = createDateModel();
            JDatePanelImpl datePanel = createDatePanel(model);
            datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

            return createPanelWithComponents(datePicker);
        } catch (Exception e) {
            handleException(ERROR_MSG_CREATE_PANEL, e);
            return new JPanel();
        }
    }

    private void setupCalendar() {
        Calendar calendar = Calendar.getInstance();
        today = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        oneYearAgo = calendar.getTime();
    }

    private UtilDateModel createDateModel() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        UtilDateModel model = new UtilDateModel();
        model.setDate(currentYear, currentMonth, currentDay);
        model.setSelected(true);
        addDateRangeListener(model);

        return model;
    }

    private void addDateRangeListener(UtilDateModel model) {
        model.addPropertyChangeListener(evt -> {
            if ("value".equals(evt.getPropertyName())) {
                handleDateChange(evt, model);
            }
        });
    }

    private void handleDateChange(PropertyChangeEvent evt, UtilDateModel model) {
        Object selectedValue = evt.getNewValue();
        if (selectedValue instanceof Date) {
            Date selectedDate = (Date) selectedValue;
            if (selectedDate.after(today) || selectedDate.before(oneYearAgo)) {
                model.setValue((Date) evt.getOldValue());
                showMessage(null, MSG_INVALID_DATE, "Fecha inválida", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private JDatePanelImpl createDatePanel(UtilDateModel model) {
        Properties p = new Properties();
        p.put("text.today", "Hoy");
        p.put("text.month", "Mes");
        p.put("text.year", "Año");
        return new JDatePanelImpl(model);
    }

    private JPanel createPanelWithComponents(JDatePickerImpl datePicker) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 241, 241));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel label = new JLabel("¿Cuándo fue tu último periodo?");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(datePicker, gbc);

        return panel;
    }

    private void showMessage(Component parent, String message, String title, int messageType) {
        JOptionPane.showMessageDialog(parent, message, title, messageType);
    }

    private void handleException(String message, Exception e) {
        e.printStackTrace();
        showMessage(this, message + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
