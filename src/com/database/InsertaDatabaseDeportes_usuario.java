package com.database;

import java.sql.*;

/**
 * La clase InsertaDatabaseDeportes_usuario maneja la inserción y actualización de los datos
 * relacionados con los deportes de los usuarios en la base de datos.
 */
public class InsertaDatabaseDeportes_usuario {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/proyectodb";
    private static final String USER = "postgres";
    private static final String PASS = "debian";

    /**
     * Inserta o actualiza los deportes de un usuario en la base de datos.
     *
     * @param usuario El nombre del usuario.
     * @param deporteFaseMenstrual El deporte durante la fase menstrual.
     * @param deporteFaseFolicular El deporte durante la fase folicular.
     * @param deporteFaseOvular El deporte durante la fase ovular.
     * @param deporteFaseLutea El deporte durante la fase lútea.
     */
    public void insertDeportesUsuario(String usuario, String deporteFaseMenstrual, String deporteFaseFolicular, String deporteFaseOvular, String deporteFaseLutea) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            // Comprobar si el usuario existe en la tabla usuario
            ps = connection.prepareStatement(getCheckUserSql());
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                // El usuario existe, proceder a insertar/actualizar deportes
                ps = connection.prepareStatement(getInsertSql());
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

    /**
     * Devuelve la consulta SQL para comprobar si el usuario existe en la tabla usuario.
     *
     * @return La consulta SQL.
     */
    private static String getCheckUserSql() {
        return "SELECT 1 FROM usuario WHERE usuario = ?";
    }

    /**
     * Devuelve la consulta SQL para insertar o actualizar los deportes de un usuario.
     *
     * @return La consulta SQL.
     */
    private static String getInsertSql() {
        return "INSERT INTO deportes_usuario (usuario, deporte_fase_menstrual, deporte_fase_folicular, deporte_fase_ovular, deporte_fase_lutea) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON CONFLICT (usuario) DO UPDATE SET " +
                "deporte_fase_menstrual = EXCLUDED.deporte_fase_menstrual, " +
                "deporte_fase_folicular = EXCLUDED.deporte_fase_folicular, " +
                "deporte_fase_ovular = EXCLUDED.deporte_fase_ovular, " +
                "deporte_fase_lutea = EXCLUDED.deporte_fase_lutea";
    }
}
