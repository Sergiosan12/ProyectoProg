package com.calendario;
import com.interfaces.cuestionarios.uso.UsoProg;
import com.interfaces.interfazinicio.Database;
import com.periodo.Menstruacion;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class SelectorFecha extends JFrame {
    protected JDatePickerImpl datePicker;
    private Date today;
    private Date oneYearAgo;

    public SelectorFecha(Menstruacion menstruacion) {
        JPanel panel = getjPanel();
        getBtnContinuar(panel, menstruacion);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void getBtnContinuar(JPanel panel, Menstruacion menstruacion) {
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBackground(new Color(255, 105, 180)); // Rosa más oscuro
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setBorder(new RoundedBorder(20)); // Borde redondeado

        // Add action listeners to the buttons
        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = (Date) datePicker.getModel().getValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = sdf.format(selectedDate);
                menstruacion.setLastperiod(selectedDate);
                insertDateIntoDatabase(menstruacion);
                dispose();
                UsoProg usoProg = new UsoProg();
                usoProg.setVisible(true);
            }
        });

        // Add the buttons to the panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(btnContinuar, gbc);
    }

    private JPanel getjPanel() {
        setTitle("Selector de Fecha");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Get today's date
        Calendar calendar = Calendar.getInstance();
        today = calendar.getTime();

        // Get the date one year ago
        calendar.add(Calendar.YEAR, -1);
        oneYearAgo = calendar.getTime();

        // Reset calendar to today
        calendar.add(Calendar.YEAR, 1);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        // Create the date model and set the initial date
        UtilDateModel model = new UtilDateModel();
        model.setDate(currentYear, currentMonth, currentDay);
        model.setSelected(true);

        // Add a property change listener to the model
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

        // Create the date picker
        Properties p = new Properties();
        p.put("text.today", "Hoy");
        p.put("text.month", "Mes");
        p.put("text.year", "Año");
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Create the panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 241, 241));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Add the label
        JLabel label = new JLabel("¿Cuándo fue tu último periodo?");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        // Add the date picker
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(datePicker, gbc);

        return panel;
    }

    private void insertDateIntoDatabase(Menstruacion menstruacion) {
        String sql = "INSERT INTO menstruacion (usuario, mediaciclo, mediasangrado, lastperiod) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, menstruacion.getUsuario());
            pstmt.setInt(2, menstruacion.getMediaCiclo());
            pstmt.setInt(3, menstruacion.getMediaSangrado());
            pstmt.setDate(4, new java.sql.Date(menstruacion.getLastperiod().getTime()));

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fecha registrada exitosamente");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar la fecha: " + e.getMessage());
        }
    }
}
