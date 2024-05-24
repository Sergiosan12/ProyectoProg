package com.database;

import com.model.funciones.Menstruacion;

import java.sql.*;

public class DatabaseHandlerMenstruacion {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/proyectodb";
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
        try {
            // Obtener una conexión a la base de datos
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Crear una declaración SQL con parámetros
            String sql = "SELECT * FROM menstruacion WHERE usuario = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario);

            // Ejecutar la consulta SQL
            ResultSet rs = pstmt.executeQuery();

            // Procesar el conjunto de resultados
            if (rs.next()) {
                int mediaciclo = rs.getInt("mediaciclo");
                int mediasangrado = rs.getInt("mediasangrado");
                Date lastperiod = rs.getDate("lastperiod");
                resultado = new Menstruacion(usuario, mediaciclo, mediasangrado, lastperiod);
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
