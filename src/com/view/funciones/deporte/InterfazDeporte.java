package com.view.funciones.deporte;

import com.database.DatabaseHandlerDeporte;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class InterfazDeporte {

    public void showDeportesGUI(JFrame previousFrame) {
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseHandlerDeporte dbHandler = new DatabaseHandlerDeporte();
                JFrame frame = new JFrame("Fases y Deportes");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.getContentPane().setBackground(Color.decode("#FFF1F1"));

                // Crear un panel para la pregunta y JComboBox
                JPanel leftPanel = new JPanel(new BorderLayout());
                leftPanel.setBackground(Color.decode("#FFF1F1"));

                // Crear un panel superior con la pregunta
                JPanel topPanel = new JPanel();
                topPanel.setBackground(Color.decode("#FFF1F1"));
                JLabel questionLabel = new JLabel("¿Qué deporte prefieres para cada fase?");
                topPanel.add(questionLabel);
                leftPanel.add(topPanel, BorderLayout.NORTH);

                // Crear un panel central para los JComboBox
                JPanel centerPanel = new JPanel();
                centerPanel.setLayout(new GridLayout(4, 1));
                centerPanel.setBackground(Color.decode("#FFF1F1"));

                // Obtener los nombres de las fases
                List<String> fases = dbHandler.getFases();

                // Crear y agregar los JComboBox al panel central
                for (String fase : fases) {
                    List<String> deportes = dbHandler.getDeportesPorFase(fase);
                    JComboBox<String> comboBox = new JComboBox<>(deportes.toArray(new String[0]));
                    comboBox.setBorder(BorderFactory.createTitledBorder(fase));
                    comboBox.setBackground(Color.decode("#FFF1F1"));
                    centerPanel.add(comboBox);
                }

                leftPanel.add(centerPanel, BorderLayout.CENTER);

                // Agregar el panel izquierdo al frame
                frame.add(leftPanel, BorderLayout.WEST);

                // Crear un panel para la imagen
                JPanel rightPanel = new JPanel(new BorderLayout());
                rightPanel.setBackground(Color.decode("#FFF1F1"));

                // Cargar y mostrar la imagen
                ImageIcon imageIcon = new ImageIcon("src/com/img/deportes2.jpg"); // Cambia la ruta por la ruta de tu imagen
                JLabel imageLabel = new JLabel(imageIcon);
                rightPanel.add(imageLabel, BorderLayout.CENTER);

                // Agregar el panel derecho al frame
                frame.add(rightPanel, BorderLayout.CENTER);

                // Crear un panel inferior con los botones "Volver" y "Generar Informe"
                JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                bottomPanel.setBackground(Color.decode("#FFF1F1"));

                JButton backButton = new JButton("Volver");
                backButton.setBackground(Color.decode("#F6C4F6"));
                backButton.addActionListener(e -> {
                    frame.dispose();  // Cerrar la ventana actual
                    previousFrame.setVisible(true);  // Volver a la ventana anterior
                });
                bottomPanel.add(backButton);

                JButton continueButton = new JButton("Generar Informe");
                continueButton.setBackground(Color.decode("#F6C4F6"));
                bottomPanel.add(continueButton);

                frame.add(bottomPanel, BorderLayout.SOUTH);
                //C
                // Configurar el frame y hacerlo visible
                frame.pack();
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);

                // Ocultar la ventana anterior
                previousFrame.setVisible(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}