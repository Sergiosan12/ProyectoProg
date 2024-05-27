package com.database;

import java.sql.*;

public class InsertaDatabaseDeportes_usuario {



        private static final String DB_URL = "jdbc:postgresql://localhost:5432/proyectodb";
        private static final String USER = "postgres";
        private static final String PASS = "debian";

        public void insertDeportesUsuario(String usuario, String deporteFaseMenstrual, String deporteFaseFolicular, String deporteFaseOvular, String deporteFaseLutea) {
            Connection connection = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);

                // Comprobar si el usuario existe en la tabla usuario
                String checkUserSql = "SELECT 1 FROM usuario WHERE usuario = ?";
                ps = connection.prepareStatement(checkUserSql);
                ps.setString(1, usuario);
                rs = ps.executeQuery();

                if (rs.next()) {
                    // El usuario existe, proceder a insertar/actualizar deportes
                    String insertSql = "INSERT INTO deportes_usuario (usuario, deporte_fase_menstrual, deporte_fase_folicular, deporte_fase_ovular, deporte_fase_lutea) " +
                            "VALUES (?, ?, ?, ?, ?) " +
                            "ON CONFLICT (usuario) DO UPDATE SET " +
                            "deporte_fase_menstrual = EXCLUDED.deporte_fase_menstrual, " +
                            "deporte_fase_folicular = EXCLUDED.deporte_fase_folicular, " +
                            "deporte_fase_ovular = EXCLUDED.deporte_fase_ovular, " +
                            "deporte_fase_lutea = EXCLUDED.deporte_fase_lutea";

                    ps = connection.prepareStatement(insertSql);
                    ps.setString(1, usuario);
                    ps.setString(2, deporteFaseMenstrual);
                    ps.setString(3, deporteFaseFolicular);
                    ps.setString(4, deporteFaseOvular);
                    ps.setString(5, deporteFaseLutea);
                    ps.executeUpdate();
                } else {
                    System.out.println("El usuario no existe en la tabla usuario.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }