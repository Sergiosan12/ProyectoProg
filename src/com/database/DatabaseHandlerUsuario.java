package com.database;

import com.model.usuario.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandlerUsuario {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "debian";

    public Usuario selectData(String usuario) {
        Usuario resultado = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtener una conexión a la base de datos
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Crear una declaración SQL con parámetros
            String sql = "SELECT * FROM usuario WHERE usuario = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario);

            // Ejecutar la consulta SQL
            rs = pstmt.executeQuery();

            // Procesar el conjunto de resultados
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String contrasena = rs.getString("contrasena");
                int edad = rs.getInt("edad");
                resultado = new Usuario(nombre, email, contrasena, edad, usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al seleccionar datos de la base de datos: " + e.getMessage());
        } finally {
            // Cerrar todas las conexiones
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
            }
        }
        return resultado;
    }
}
