package com.database;

import com.database.Database;
import com.model.usuario.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDatabaseUsuario {

    /**
     * Inserta los datos del usuario en la base de datos.
     *
     * @param usuario el objeto Usuario que contiene la información del usuario.
     */
    public void insertDataIntoDatabase(Usuario usuario) {
        String sql = "INSERT INTO usuario (usuario, nombre, contrasenha, email, edad) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getUsuario());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getContrasena());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setInt(5, usuario.getEdad());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}