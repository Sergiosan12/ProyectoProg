package com.database;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class DatabaseHandlerDeporte {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/proyectodb";
    private static final String USER = "postgres";
    private static final String PASS = "debian";
    private Connection conn;

    public DatabaseHandlerDeporte() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Conexión a la base de datos establecida correctamente.");
    }

    public List<String> getDeportesPorFase(String fase) throws SQLException {
        List<String> deportes = new ArrayList<>();
        String query = "SELECT nombre_deporte FROM deporte " +
                "JOIN fase_deporte ON deporte.id_deporte = fase_deporte.id_deporte " +
                "JOIN fases_menstruacion ON fase_deporte.id_fase = fases_menstruacion.id_fase " +
                "WHERE fases_menstruacion.nombre_fase = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, fase);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            deportes.add(rs.getString("nombre_deporte"));
        }
        rs.close();
        stmt.close();
        return deportes;
    }

    public List<String> getFases() throws SQLException {
        List<String> fases = new ArrayList<>();
        String query = "SELECT nombre_fase FROM fases_menstruacion";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            fases.add(rs.getString("nombre_fase"));
        }
        rs.close();
        stmt.close();
        return fases;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseHandlerDeporte dbHandler = new DatabaseHandlerDeporte();
                JFrame frame = new JFrame("Fases y Deportes");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.getContentPane().setBackground(Color.decode("#FFF1F1"));

                // Crear un panel superior con la pregunta
                JPanel topPanel = new JPanel();
                topPanel.setBackground(Color.decode("#FFF1F1"));
                JLabel questionLabel = new JLabel("¿Qué deporte prefieres para cada fase?");
                topPanel.add(questionLabel);
                frame.add(topPanel, BorderLayout.NORTH);

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

                frame.add(centerPanel, BorderLayout.CENTER);

                // Crear un panel inferior con el botón "Continuar"
                JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                bottomPanel.setBackground(Color.decode("#FFF1F1"));
                JButton continueButton = new JButton("Generar Informe");
                continueButton.setBackground(Color.decode("#F6C4F6"));
                bottomPanel.add(continueButton);
                frame.add(bottomPanel, BorderLayout.SOUTH);

                // Configurar el marco y hacerlo visible
                frame.pack();
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}

