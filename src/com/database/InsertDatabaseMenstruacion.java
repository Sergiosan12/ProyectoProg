package com.database;

import com.model.funciones.Menstruacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDatabaseMenstruacion {

    public InsertDatabaseMenstruacion() {
    }

    /**
     * Inserta la fecha seleccionada en la base de datos.
     *
     * @param menstruacion la menstruación de la usuaria.
     */
    public void insertDateIntoDatabase(Menstruacion menstruacion) {
        String sql = "INSERT INTO menstruacion (usuario, mediaciclo, mediasangrado, duracionFaseFolicular, duracionFaseOvular, duracionFaseLutea, lastperiod, nextPeriod, nextFaseFolicular, nextFaseOvular, nextFaseLutea) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Database.getConnection();
            pstmt = conn.prepareStatement(sql);

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

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Fecha registrada exitosamente");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar los datos en la base de datos: " + e.getMessage());
        } finally {
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
    }
    public void updateDatabase(String userName, Menstruacion menstruacion) {
        String sql = "UPDATE menstruacion SET mediaciclo = ?, mediasangrado = ?, duracionFaseFolicular = ?, duracionFaseOvular = ?, duracionFaseLutea = ?, lastperiod = ?, nextPeriod = ?, nextFaseFolicular = ?, nextFaseOvular = ?, nextFaseLutea = ? WHERE usuario = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = Database.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, menstruacion.getMediaCiclo());
            pstmt.setInt(2, menstruacion.getMediaSangrado());
            pstmt.setInt(3, menstruacion.getDuracionFaseFolicular());
            pstmt.setInt(4, menstruacion.getDuracionFaseOvular());
            pstmt.setInt(5, menstruacion.getDuracionFaseLutea());
            pstmt.setDate(6, new java.sql.Date(menstruacion.getLastperiod().getTime()));
            pstmt.setDate(7, new java.sql.Date(menstruacion.getNextPeriod().getTime()));
            pstmt.setDate(8, new java.sql.Date(menstruacion.getNextFaseFolicular().getTime()));
            pstmt.setDate(9, new java.sql.Date(menstruacion.getNextFaseOvular().getTime()));
            pstmt.setDate(10, new java.sql.Date(menstruacion.getNextFaseLutea().getTime()));
            pstmt.setString(11, userName);

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
    }
}
