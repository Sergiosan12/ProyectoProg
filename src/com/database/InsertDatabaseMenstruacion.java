package com.database;

import com.model.funciones.Menstruacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * La clase InsertDatabaseMenstruacion maneja la inserción y actualización de los datos de menstruación en la base de datos.
 */
public class InsertDatabaseMenstruacion {

    /**
     * Constructor por defecto.
     */
    public InsertDatabaseMenstruacion() {
    }

    /**
     * Inserta la información de menstruación en la base de datos.
     *
     * @param menstruacion la información de menstruación de la usuaria.
     */
    public void insertDateIntoDatabase(Menstruacion menstruacion) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Database.getConnection();
            pstmt = conn.prepareStatement(getInsertSql());
            setPreparedStatementParameters(menstruacion, pstmt);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fecha registrada exitosamente");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar los datos en la base de datos: " + e.getMessage());
        } finally {
            closeResources(conn, pstmt);
        }
    }

    /**
     * Actualiza la información de menstruación en la base de datos.
     *
     * @param userName     el nombre de usuario.
     * @param menstruacion la nueva información de menstruación.
     */
    public void updateDatabase(String userName, Menstruacion menstruacion) {
        String sql = "UPDATE menstruacion SET mediaciclo = ?, mediasangrado = ?, duracionFaseFolicular = ?, duracionFaseOvular = ?, duracionFaseLutea = ?, lastperiod = ?, nextPeriod = ?, nextFaseFolicular = ?, nextFaseOvular = ?, nextFaseLutea = ? WHERE usuario = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Database.getConnection();
            pstmt = conn.prepareStatement(sql);
            setPreparedStatementParameters(menstruacion, pstmt);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(null, "Datos actualizados exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el usuario para actualizar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos en la base de datos: " + e.getMessage());
        } finally {
            closeResources(conn, pstmt);
        }
    }

    /**
     * Establece los parámetros del PreparedStatement con los datos de menstruación.
     *
     * @param menstruacion la información de menstruación.
     * @param pstmt        el PreparedStatement.
     * @throws SQLException si ocurre un error al establecer los parámetros.
     */
    private static void setPreparedStatementParameters(Menstruacion menstruacion, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, menstruacion.getUsuario());
        pstmt.setInt(2, menstruacion.getMediaCiclo());
        pstmt.setInt(3, menstruacion.getMediaSangrado());
        pstmt.setInt(4, menstruacion.getDuracionFaseFolicular());
        pstmt.setInt(5, menstruacion.getDuracionFaseOvular());
        pstmt.setInt(6, menstruacion.getDuracionFaseLutea());
        pstmt.setDate(7, new java.sql.Date(menstruacion.getLastperiod().getTime()));
        pstmt.setDate(8, new java.sql.Date(menstruacion.getNextPeriod().getTime()));
        pstmt.setDate(9, new java.sql.Date(menstruacion.getNextFaseFolicular().getTime()));
        pstmt.setDate(10, new java.sql.Date(menstruacion.getNextFaseOvular().getTime()));
        pstmt.setDate(11, new java.sql.Date(menstruacion.getNextFaseLutea().getTime()));
    }

    /**
     * Cierra los recursos de la base de datos.
     *
     * @param conn  la conexión a la base de datos.
     * @param pstmt el PreparedStatement.
     */
    private void closeResources(Connection conn, PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cerrar la declaración SQL: " + e.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión a la base de datos: " + e.getMessage());
            }
        }
    }

    /**
     * Devuelve la consulta SQL para insertar datos de menstruación.
     *
     * @return la consulta SQL.
     */
    private static String getInsertSql() {
        return "INSERT INTO menstruacion (usuario, mediaciclo, mediasangrado, duracionFaseFolicular, duracionFaseOvular, duracionFaseLutea, lastperiod, nextPeriod, nextFaseFolicular, nextFaseOvular, nextFaseLutea) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
}
