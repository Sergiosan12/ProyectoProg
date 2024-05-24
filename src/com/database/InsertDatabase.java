package com.database;

import com.model.funciones.LastPeriod;
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
