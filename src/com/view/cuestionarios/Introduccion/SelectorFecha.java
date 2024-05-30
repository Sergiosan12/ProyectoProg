package com.view.cuestionarios.Introduccion;

import com.controller.GenerateDiaFases;
import com.database.InsertDatabaseMenstruacion;
import com.model.decoracion.DateLabelFormatter;
import com.model.funciones.Menstruacion;
import com.view.funciones.uso.UsoProg;
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

public class SelectorFecha extends JFrame {
    protected JDatePickerImpl datePicker;
    private Date today;
    private Date oneYearAgo;
    InsertDatabaseMenstruacion insertDatabase = new InsertDatabaseMenstruacion();
    Menstruacion menstruacion;

    public SelectorFecha() {
    }

    public SelectorFecha(Menstruacion menstruacion) {
        this.menstruacion = menstruacion; // Asegúrate de que la instancia se asigna aquí
        try {
            JPanel panel = getjPanel();
            getBtnContinuar(panel);
            add(panel, BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al inicializar la interfaz: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
    }

    private void getBtnContinuar(JPanel panel) {
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBackground(new Color(255, 105, 180));
        btnContinuar.setForeground(Color.WHITE);

        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date selectedDate = (Date) datePicker.getModel().getValue();
                    if (selectedDate == null) {
                        JOptionPane.showMessageDialog(panel, "Por favor, selecciona una fecha.", "Fecha no seleccionada", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = sdf.format(selectedDate);
                   GenerateDiaFases.CambiarDateLastPeriod(selectedDate);
                    menstruacion.setLastperiod(selectedDate); // Asegúrate de que fechaDelUltimoPeriodo no sea null
                    GenerateDiaFases.calcularTodasLasFases();
                    insertDatabase.insertDateIntoDatabase(menstruacion); // Pasa menstruacion aquí
                    dispose();
                    UsoProg usoProg = new UsoProg(menstruacion);
                    usoProg.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error al procesar la fecha: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(btnContinuar, gbc);
    }

    private JPanel getjPanel() {
        try {
            setTitle("Selector de Fecha");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            Calendar calendar = Calendar.getInstance();
            today = calendar.getTime();

            calendar.add(Calendar.YEAR, -1);
            oneYearAgo = calendar.getTime();

            calendar.add(Calendar.YEAR, 1);
            int currentYear = calendar.get(Calendar.YEAR);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

            UtilDateModel model = new UtilDateModel();
            model.setDate(currentYear, currentMonth, currentDay);
            model.setSelected(true);

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

            Properties p = new Properties();
            p.put("text.today", "Hoy");
            p.put("text.month", "Mes");
            p.put("text.year", "Año");
            JDatePanelImpl datePanel = new JDatePanelImpl(model);
            datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

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
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al crear el panel de selección de fecha: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return new JPanel();
        }
    }
}
