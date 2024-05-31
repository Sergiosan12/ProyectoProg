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
import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 * La clase SelectorFecha proporciona una interfaz gráfica para seleccionar la fecha del último periodo menstrual.
 * Permite al usuario seleccionar una fecha y actualizar la información en la base de datos.
 */
public class SelectorFecha extends JFrame {

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

    /**
     * Constructor vacío de la clase SelectorFecha.
     */
    public SelectorFecha() {
        // Constructor vacío si es necesario
    }

    /**
     * Constructor de la clase SelectorFecha que acepta un objeto Menstruacion.
     *
     * @param menstruacion el objeto Menstruacion que contiene la información menstrual del usuario.
     */
    public SelectorFecha(Menstruacion menstruacion) {
        this.menstruacion = menstruacion;
        initialize();
    }

    /**
     * Inicializa los componentes de la interfaz gráfica.
     */
    private void initialize() {
        try {
            JPanel panel = createMainPanel();
            addContinueButton(panel);
            add(panel, BorderLayout.CENTER);
        } catch (Exception e) {
            handleException(ERROR_MSG_INIT_INTERFACE, e);
        }
        setupFrame();
    }

    /**
     * Configura la ventana principal.
     */
    private void setupFrame() {
        setTitle("Selector de Fecha");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Añade el botón "Continuar" al panel principal.
     *
     * @param panel el panel principal.
     */
    private void addContinueButton(JPanel panel) {
        JButton btnContinuar = createContinueButton();
        btnContinuar.addActionListener(e -> handleContinueButton(panel));
        addButtonToPanel(panel, btnContinuar);
    }

    /**
     * Crea el botón "Continuar" estilizado.
     *
     * @return el botón "Continuar" estilizado.
     */
    private JButton createContinueButton() {
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBackground(new Color(255, 105, 180));
        btnContinuar.setForeground(Color.WHITE);
        return btnContinuar;
    }

    /**
     * Añade el botón estilizado al panel principal.
     *
     * @param panel  el panel principal.
     * @param button el botón a añadir.
     */
    private void addButtonToPanel(JPanel panel, JButton button) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(button, gbc);
    }

    /**
     * Maneja la acción del botón "Continuar", procesando la fecha seleccionada y actualizando la base de datos.
     *
     * @param panel el panel principal.
     */
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

    /**
     * Actualiza los datos menstruales con la fecha seleccionada y guarda los cambios en la base de datos.
     *
     * @param selectedDate la fecha seleccionada.
     * @throws Exception si ocurre un error durante el proceso.
     */
    private void updateMenstruationData(Date selectedDate) throws Exception {
        GenerateDiaFases.CambiarDateLastPeriod(selectedDate);
        menstruacion.setLastperiod(selectedDate);
        GenerateDiaFases.calcularTodasLasFases();
        insertDatabase.insertDateIntoDatabase(menstruacion);
    }

    /**
     * Abre la ventana de uso del programa.
     */
    private void openUsoProg() {
        UsoProg usoProg = new UsoProg(menstruacion);
        usoProg.setVisible(true);
    }

    /**
     * Crea el panel principal que contiene el selector de fecha.
     *
     * @return el panel principal.
     */
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

    /**
     * Configura el calendario con la fecha actual y la fecha de hace un año.
     */
    private void setupCalendar() {
        Calendar calendar = Calendar.getInstance();
        today = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        oneYearAgo = calendar.getTime();
    }

    /**
     * Crea un modelo de fecha configurado con la fecha actual y añade un listener para controlar el rango de fechas seleccionables.
     *
     * @return el modelo de fecha.
     */
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

    /**
     * Añade un listener al modelo de fecha para controlar el rango de fechas seleccionables.
     *
     * @param model el modelo de fecha.
     */
    private void addDateRangeListener(UtilDateModel model) {
        model.addPropertyChangeListener(evt -> {
            if ("value".equals(evt.getPropertyName())) {
                handleDateChange(evt, model);
            }
        });
    }

    /**
     * Maneja el cambio de fecha, asegurándose de que la fecha seleccionada esté dentro del rango válido.
     *
     * @param evt   el evento de cambio de propiedad.
     * @param model el modelo de fecha.
     */
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

    /**
     * Crea el panel de selección de fecha con las propiedades especificadas.
     *
     * @param model el modelo de fecha.
     * @return el panel de selección de fecha.
     */
    private JDatePanelImpl createDatePanel(UtilDateModel model) {
        Properties p = new Properties();
        p.put("text.today", "Hoy");
        p.put("text.month", "Mes");
        p.put("text.year", "Año");
        return new JDatePanelImpl(model);
    }

    /**
     * Crea un panel con los componentes de selección de fecha.
     *
     * @param datePicker el componente de selección de fecha.
     * @return el panel con los componentes de selección de fecha.
     */
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
        /**
         * Muestra un mensaje emergente.
         *
         * @param parent el componente padre del cuadro de diálogo.
         * @param message el mensaje a mostrar.
         * @param title el título del cuadro de diálogo.
         * @param messageType el tipo de mensaje.
         */
        private void showMessage (Component parent, String message, String title,int messageType){
            JOptionPane.showMessageDialog(parent, message, title, messageType);
        }

        /**
         * Maneja las excepciones mostrando un cuadro de diálogo con el mensaje de error.
         *
         * @param message el mensaje de error a mostrar.
         * @param e la excepción que se ha producido.
         */
        private void handleException (String message, Exception e){
            e.printStackTrace();
            showMessage(this, message + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

