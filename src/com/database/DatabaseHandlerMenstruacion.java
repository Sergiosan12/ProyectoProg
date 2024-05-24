package com.database;

import com.model.funciones.Menstruacion;

import java.sql.*;

public class DatabaseHandlerMenstruacion {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "debian";

    /**
     * Método para seleccionar datos de la base de datos basándose en el nombre de usuario.
     *
     * @param usuario El nombre de usuario.
     * @return Un objeto Usuario que coincide con el criterio de búsqueda, o null si no se encuentra.
     */
    public Menstruacion selectData(String usuario) {
        Menstruacion resultado = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtener una conexión a la base de datos
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Crear una declaración SQL con parámetros
            String sql = "SELECT * FROM menstruacion WHERE usuario = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario);

            // Ejecutar la consulta SQL
            rs = pstmt.executeQuery();

            // Procesar el conjunto de resultados
            if (rs.next()) {
                int mediaciclo = rs.getInt("mediaciclo");
                int mediasangrado = rs.getInt("mediasangrado");
                Date lastperiod = rs.getDate("lastperiod");
                resultado = new Menstruacion(usuario, mediaciclo, mediasangrado, lastperiod);
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
