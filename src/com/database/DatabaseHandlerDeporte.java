package com.database;

import java.sql.*;
import java.util.*;

/**
 * La clase DatabaseHandlerDeporte se encarga de manejar la conexión y las consultas
 * a la base de datos relacionadas con los deportes y las fases del ciclo menstrual.
 */
public class DatabaseHandlerDeporte {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/proyectodb";
    private static final String USER = "postgres";
    private static final String PASS = "debian";
    private final Connection conn;

    /**
     * Constructor de la clase DatabaseHandlerDeporte. Establece la conexión con la base de datos.
     *
     * @throws SQLException Si hay un error al conectar a la base de datos.
     */
    public DatabaseHandlerDeporte() throws SQLException {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Conexión a la base de datos establecida correctamente.");
    }

    /**
     * Obtiene una lista de deportes recomendados para una fase específica del ciclo menstrual.
     *
     * @param fase El nombre de la fase del ciclo menstrual.
     * @return Una lista de nombres de deportes recomendados para la fase especificada.
     * @throws SQLException Si hay un error al realizar la consulta a la base de datos.
     */
    public List<String> getDeportesPorFase(String fase) throws SQLException {
        List<String> deportes = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement(getQueryNombreDeporte());
        stmt.setString(1, fase);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            deportes.add(rs.getString("nombre_deporte"));
        }
        rs.close();
        stmt.close();
        return deportes;
    }

    /**
     * Genera la consulta SQL para obtener los nombres de los deportes recomendados
     * para una fase específica del ciclo menstrual.
     *
     * @return La consulta SQL como un String.
     */
    private static String getQueryNombreDeporte() {
        String query = "SELECT nombre_deporte FROM deporte " +
                "JOIN fase_deporte ON deporte.id_deporte = fase_deporte.id_deporte " +
                "JOIN fases_menstruacion ON fase_deporte.id_fase = fases_menstruacion.id_fase " +
                "WHERE fases_menstruacion.nombre_fase = ?";
        return query;
    }

    /**
     * Obtiene una lista de todas las fases del ciclo menstrual registradas en la base de datos.
     *
     * @return Una lista de nombres de fases del ciclo menstrual.
     * @throws SQLException Si hay un error al realizar la consulta a la base de datos.
     */
    public List<String> getFases() throws SQLException {
        List<String> fases = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(getQueryFases());
        while (rs.next()) {
            fases.add(rs.getString("nombre_fase"));
        }
        rs.close();
        stmt.close();
        return fases;
    }

    /**
     * Genera la consulta SQL para obtener los nombres de todas las fases del ciclo menstrual.
     *
     * @return La consulta SQL como un String.
     */
    private static String getQueryFases() {
        String query = "SELECT nombre_fase FROM fases_menstruacion";
        return query;
    }

}
