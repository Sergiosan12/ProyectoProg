package com.calendario;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class SelectorFecha extends JFrame {
    protected JDatePickerImpl datePicker;

    public SelectorFecha() {
        JPanel panel = getjPanel();
        getBtnContinuar(panel);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void getBtnContinuar(JPanel panel) {
        JButton btnContinuar = new JButton("Continuar");

        // Add action listeners to the buttons
        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Día seleccionado: " + getDiaSeleccionado());
                System.out.println("Mes seleccionado: " + getMesSeleccionado());
                System.out.println("Año seleccionado: " + getAnhoSeleccionado());
            }
        });

        // Add the buttons to the panel
        panel.add(btnContinuar);
    }

    private JPanel getjPanel() {
        setTitle("Selector de Fecha");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the date picker
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Hoy");
        p.put("text.month", "Mes");
        p.put("text.year", "Año");
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        JPanel panel = new JPanel();
        panel.add(datePicker);
        return panel;
    }

    public int getDiaSeleccionado() {
        return datePicker.getModel().getDay();
    }

    public int getMesSeleccionado() {
        return datePicker.getModel().getMonth() + 1;  // Months are zero-based in Java
    }

    public int getAnhoSeleccionado() {
        return datePicker.getModel().getYear();
    }

    public static void main(String[] args) {
        new SelectorFecha();
    }

}