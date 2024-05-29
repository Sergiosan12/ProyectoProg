package com.database;
import com.model.usuario.DeportesUsuario;
import java.sql.*;

/**
 * La clase DatabaseHandlerDeportes_usuario maneja la conexión y las consultas
 * a la base de datos relacionadas con los deportes preferidos por un usuario
 * en diferentes fases del ciclo menstrual.
 */
public class DatabaseHandlerDeportes_usuario {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/proyectodb";
    private static final String USER = "postgres";
    private static final String PASS = "debian";

    /**
     * Recupera los datos de deportes preferidos de un usuario específico de la base de datos.
     *
     * @param usuario El nombre del usuario.
     * @return Un objeto DeportesUsuario con los datos recuperados o null si no se encuentra el usuario.
     */
    public DeportesUsuario selectData(String usuario) {
        DeportesUsuario resultado = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtener una conexión a la base de datos
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Crear una declaración SQL con parámetros
            pstmt = conn.prepareStatement(getSql());
            pstmt.setString(1, usuario);

            // Ejecutar la consulta SQL
            rs = pstmt.executeQuery();

            // Procesar el conjunto de resultados
            if (rs.next()) {
                Result result = getResult(rs);
                resultado = new DeportesUsuario(usuario, result.deporteFaseMenstrual(), result.deporteFaseFolicular(), result.deporteFaseOvular(), result.deporteFaseLutea());
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
     * Procesa el ResultSet para extraer los datos de deportes preferidos.
     *
     * @param rs El ResultSet que contiene los datos de la consulta.
     * @return Un objeto Result con los datos extraídos del ResultSet.
     * @throws SQLException Si ocurre un error al procesar el ResultSet.
     */
    private static Result getResult(ResultSet rs) throws SQLException {
        String deporteFaseMenstrual = rs.getString("deporte_fase_menstrual");
        String deporteFaseFolicular = rs.getString("deporte_fase_folicular");
        String deporteFaseOvular = rs.getString("deporte_fase_ovular");
        String deporteFaseLutea = rs.getString("deporte_fase_lutea");
        return new Result(deporteFaseMenstrual, deporteFaseFolicular, deporteFaseOvular, deporteFaseLutea);
    }

    /**
     * La clase Result es una clase auxiliar que almacena los datos de deportes preferidos.
     */
    private record Result(String deporteFaseMenstrual, String deporteFaseFolicular, String deporteFaseOvular, String deporteFaseLutea) {
    }

    /**
     * Genera la consulta SQL para seleccionar los datos de deportes preferidos de un usuario.
     *
     * @return La consulta SQL como un String.
     */
    private static String getSql() {
        return "SELECT * FROM deportes_usuario WHERE usuario = ?";
    }
}
