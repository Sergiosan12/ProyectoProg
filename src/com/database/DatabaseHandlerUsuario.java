package com.database;

import com.model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandlerUsuario {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "debian";

    /**
     * Método para seleccionar datos de la base de datos basándose en el nombre de usuario.
     *
     * @param usuario El nombre de usuario.
     * @return Un objeto Usuario que coincide con el criterio de búsqueda, o null si no se encuentra.
     */
    public Usuario selectData(String usuario) {
        Usuario resultado = null;
        try {
            // Obtener una conexión a la base de datos
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Crear una declaración SQL con parámetros
            String sql = "SELECT * FROM usuario WHERE usuario = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario);

            // Ejecutar la consulta SQL
            ResultSet rs = pstmt.executeQuery();

            // Procesar el conjunto de resultados
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String contrasena = rs.getString("contrasena");
                int edad = rs.getInt("edad");
                resultado = new Usuario(nombre, email, contrasena, edad, usuario);
            }

            // Cerrar todas las conexiones
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
