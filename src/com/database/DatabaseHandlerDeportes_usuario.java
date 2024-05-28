package com.database;
import com.model.usuario.DeportesUsuario;
import java.sql.*;


public class DatabaseHandlerDeportes_usuario {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/proyectodb";
    private static final String USER = "postgres";
    private static final String PASS = "debian";

    public DeportesUsuario selectData(String usuario) {
        DeportesUsuario resultado = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtener una conexión a la base de datos
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Crear una declaración SQL con parámetros
            String sql = "SELECT * FROM deportes_usuario WHERE usuario = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario);

            // Ejecutar la consulta SQL
            rs = pstmt.executeQuery();

            // Procesar el conjunto de resultados
            if (rs.next()) {
                String deporteFaseMenstrual= rs.getString("deporte_fase_menstrual");
                String deporteFaseFOlicular= rs.getString("deporte_fase_folicular");
                String deporteFaseOvular= rs.getString("deporte_fase_ovular");
                String deporteFaseLutea= rs.getString("deporte_fase_lutea");

              resultado= new DeportesUsuario(usuario, deporteFaseMenstrual, deporteFaseFOlicular, deporteFaseOvular, deporteFaseLutea);
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
