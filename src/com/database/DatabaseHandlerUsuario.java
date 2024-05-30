package com.database;

import com.model.usuario.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La clase DatabaseHandlerUsuario maneja la conexión y las consultas
 * a la base de datos relacionadas con los usuarios.
 */
public class DatabaseHandlerUsuario {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/proyectodb";
    private static final String USER = "postgres";
    private static final String PASS = "debian";

    /**
     * Verifica si un usuario existe en la base de datos.
     *
     * @param username El nombre del usuario.
     * @return true si el usuario existe, false en caso contrario.
     */
    public boolean isUserExisting(String username) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE usuario = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Recupera los datos de un usuario específico de la base de datos.
     *
     * @param usuario El nombre del usuario.
     * @return Un objeto Usuario con los datos recuperados o null si no se encuentra el usuario.
     */
    public Usuario selectData(String usuario) {
        Usuario resultado = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtener una conexión a la base de datos
            conn = getConnection();

            // Crear una declaración SQL con parámetros
            String sql = "SELECT * FROM usuario WHERE usuario = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario);

            // Ejecutar la consulta SQL
            rs = pstmt.executeQuery();

            // Procesar el conjunto de resultados
            if (rs.next()) {
                Result result = getResult(rs);
                resultado = new Usuario(result.nombre(), result.email(), result.contrasena(), result.edad(), usuario);
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

    /**
     * Procesa el ResultSet para extraer los datos del usuario.
     *
     * @param rs El ResultSet que contiene los datos de la consulta.
     * @return Un objeto Result con los datos extraídos del ResultSet.
     * @throws SQLException Si ocurre un error al procesar el ResultSet.
     */
    private static Result getResult(ResultSet rs) throws SQLException {
        String nombre = rs.getString("nombre");
        String email = rs.getString("email");
        String contrasena = rs.getString("contrasenha");
        int edad = rs.getInt("edad");
        return new Result(nombre, email, contrasena, edad);
    }

    /**
     * La clase Result es una clase auxiliar que almacena los datos del usuario.
     */
    private record Result(String nombre, String email, String contrasena, int edad) {
    }

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return una conexión a la base de datos.
     * @throws SQLException Si ocurre un error al obtener la conexión.
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    /**
     * Verifica las credenciales de un usuario en la base de datos.
     *
     * @param userName El nombre del usuario.
     * @param password La contraseña del usuario.
     * @return true si las credenciales son correctas, false en caso contrario.
     */
    public boolean checkCredentials(String userName, String password) {
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND contrasenha = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userName);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
