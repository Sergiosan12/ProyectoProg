package com.view.funciones.deporte;

import com.controller.GenerarPDF;
import com.controller.InformeBuilder;
import com.database.DatabaseHandlerDeporte;
import com.database.InsertaDatabaseDeportes_usuario;
import com.model.funciones.Menstruacion;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class InterfazDeporte {
    private JPanel panel1;
    private static final int OPCION_DEPORTE = 2;
    private Menstruacion menstruacion;
    private InformeBuilder informeBuilder = new InformeBuilder();

    public InterfazDeporte(Menstruacion menstruacion) {
        this.menstruacion = menstruacion;
    }

    public void showDeportesGUI(JFrame previousFrame) {
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseHandlerDeporte dbHandler = new DatabaseHandlerDeporte();
                JFrame frame = createFrame(previousFrame);
                JPanel leftPanel = createLeftPanel(dbHandler);
                JPanel rightPanel = createRightPanel();
                JPanel bottomPanel = createBottomPanel(frame, previousFrame, leftPanel);

                frame.add(leftPanel, BorderLayout.WEST);
                frame.add(rightPanel, BorderLayout.CENTER);
                frame.add(bottomPanel, BorderLayout.SOUTH);

                configureAndShowFrame(frame, previousFrame);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private JFrame createFrame(JFrame previousFrame) {
        JFrame frame = new JFrame("Fases y Deportes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.decode("#FFF1F1"));
        frame.setSize(800, 400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        previousFrame.setVisible(false);
        return frame;
    }

    private JPanel createLeftPanel(DatabaseHandlerDeporte dbHandler) throws SQLException {
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.decode("#FFF1F1"));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#FFF1F1"));
        JLabel questionLabel = new JLabel("¿Qué deporte prefieres para cada fase?");
        topPanel.add(questionLabel);
        leftPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1));
        centerPanel.setBackground(Color.decode("#FFF1F1"));

        List<String> fases = dbHandler.getFases();
        HashMap<String, JComboBox<String>> comboBoxes = createComboBoxes(dbHandler, fases);

        for (String fase : comboBoxes.keySet()) {
            centerPanel.add(comboBoxes.get(fase));
        }

        leftPanel.add(centerPanel, BorderLayout.CENTER);
        return leftPanel;
    }

    private HashMap<String, JComboBox<String>> createComboBoxes(DatabaseHandlerDeporte dbHandler, List<String> fases) throws SQLException {
        HashMap<String, JComboBox<String>> comboBoxes = new HashMap<>();

        for (String fase : fases) {
            List<String> deportes = dbHandler.getDeportesPorFase(fase);
            JComboBox<String> comboBox = new JComboBox<>(deportes.toArray(new String[0]));
            comboBox.setBorder(BorderFactory.createTitledBorder(fase));
            comboBox.setBackground(Color.decode("#FFF1F1"));
            if (comboBox.getItemCount() > 0) {
                comboBox.setSelectedIndex(0);
            }
            comboBoxes.put(fase, comboBox);
        }

        return comboBoxes;
    }

    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.decode("#FFF1F1"));
        ImageIcon imageIcon = new ImageIcon("src/com/img/deportes2.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        rightPanel.add(imageLabel, BorderLayout.CENTER);
        return rightPanel;
    }

    private JPanel createBottomPanel(JFrame frame, JFrame previousFrame, JPanel leftPanel) {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.decode("#FFF1F1"));

        JButton backButton = createBackButton(frame, previousFrame);
        bottomPanel.add(backButton);

        JButton continueButton = createContinueButton(leftPanel);
        bottomPanel.add(continueButton);

        return bottomPanel;
    }

    private JButton createBackButton(JFrame frame, JFrame previousFrame) {
        JButton backButton = new JButton("Volver");
        backButton.setBackground(Color.decode("#F6C4F6"));
        backButton.addActionListener(e -> {
            frame.dispose();
            previousFrame.setVisible(true);
        });
        return backButton;
    }

    private JButton createContinueButton(JPanel leftPanel) {
        JButton continueButton = new JButton("Generar Informe");
        continueButton.setBackground(Color.decode("#F6C4F6"));
        continueButton.addActionListener(e -> generateReport(leftPanel));
        return continueButton;
    }

    private void generateReport(JPanel leftPanel) {
        try {
            HashMap<String, JComboBox<String>> comboBoxes = extractComboBoxes(leftPanel);

            String deporteFaseMenstrual = getSelectedItem(comboBoxes.get("Menstrual"));
            String deporteFaseFolicular = getSelectedItem(comboBoxes.get("Folicular"));
            String deporteFaseOvulacion = getSelectedItem(comboBoxes.get("Ovulación"));
            String deporteFaseLutea = getSelectedItem(comboBoxes.get("Lútea"));

            String usuario = menstruacion.getUsuario();
            InsertaDatabaseDeportes_usuario dbDeportesUsuario = new InsertaDatabaseDeportes_usuario();
            dbDeportesUsuario.insertDeportesUsuario(usuario, deporteFaseMenstrual, deporteFaseFolicular, deporteFaseOvulacion, deporteFaseLutea);

            informeBuilder.withDeportes(usuario);
            GenerarPDF generarPDF = new GenerarPDF(menstruacion);
            generarPDF.generarInforme(OPCION_DEPORTE);

            JOptionPane.showMessageDialog(panel1, "Pdf generado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel1, "Error al generar el informe: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private HashMap<String, JComboBox<String>> extractComboBoxes(JPanel leftPanel) {
        HashMap<String, JComboBox<String>> comboBoxes = new HashMap<>();
        Component[] components = ((JPanel) leftPanel.getComponent(1)).getComponents();
        for (Component component : components) {
            if (component instanceof JComboBox) {
                JComboBox<String> comboBox = (JComboBox<String>) component;
                String title = ((javax.swing.border.TitledBorder) comboBox.getBorder()).getTitle();
                comboBoxes.put(title, comboBox);
            }
        }
        return comboBoxes;
    }

    private String getSelectedItem(JComboBox<String> comboBox) {
        return comboBox != null && comboBox.getSelectedItem() != null ? (String) comboBox.getSelectedItem() : null;
    }

    private void configureAndShowFrame(JFrame frame, JFrame previousFrame) {
        frame.setVisible(true);
        previousFrame.setVisible(false);
    }
}
