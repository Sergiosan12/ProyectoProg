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
    private final Connection conn;

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

}
