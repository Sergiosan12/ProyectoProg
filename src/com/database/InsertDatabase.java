package com.database;

import com.model.funciones.Menstruacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDatabase {

    public InsertDatabase() {
    }

    /**
     * Inserta la fecha seleccionada en la base de datos.
     *
     * @param menstruacion la menstruación de la usuaria.
     */
    public void insertDateIntoDatabase(Menstruacion menstruacion) {
        String sql = "INSERT INTO menstruacion (usuario, mediaciclo, mediasangrado, lastperiod) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            try {
                conn = Database.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al obtener la conexión a la base de datos: " + e.getMessage());
                return;
            }

            try {
                pstmt = conn.prepareStatement(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al preparar la declaración SQL: " + e.getMessage());
                return;
            }

            try {
                pstmt.setString(1, menstruacion.getUsuario());
                pstmt.setInt(2, menstruacion.getMediaCiclo());
                pstmt.setInt(3, menstruacion.getMediaSangrado());
                pstmt.setDate(4, new java.sql.Date(menstruacion.getLastperiod().getTime()));
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al establecer los parámetros de la declaración SQL: " + e.getMessage());
                return;
            }

            try {
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Fecha registrada exitosamente");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al ejecutar la declaración SQL: " + e.getMessage());
            }

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
