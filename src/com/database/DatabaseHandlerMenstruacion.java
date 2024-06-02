package com.database;

import com.model.funciones.Menstruacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandlerMenstruacion {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/proyectodb";
    private static final String USER = "postgres";
    private static final String PASS = "debian";

    public Menstruacion selectData(String usuario) {
        Menstruacion resultado = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            pstmt = conn.prepareStatement(getSql());
            pstmt.setString(1, usuario);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                resultado = new Menstruacion();
                resultado.setUsuario(rs.getString("usuario"));
                resultado.setMediaCiclo(rs.getInt("mediaciclo"));
                resultado.setMediaSangrado(rs.getInt("mediasangrado"));
                resultado.setDuracionFaseFolicular(rs.getInt("duracionFaseFolicular"));
                resultado.setDuracionFaseOvular(rs.getInt("duracionFaseOvular"));
                resultado.setDuracionFaseLutea(rs.getInt("duracionFaseLutea"));
                resultado.setLastperiod(rs.getDate("lastperiod"));
                resultado.setNextPeriod(rs.getDate("nextPeriod"));
                resultado.setNextFaseFolicular(rs.getDate("nextFaseFolicular"));
                resultado.setNextFaseOvular(rs.getDate("nextFaseOvular"));
                resultado.setNextFaseLutea(rs.getDate("nextFaseLutea"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al seleccionar datos de la base de datos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
            }
        }
        return resultado;
    }

    private static String getSql() {
        return "SELECT * FROM menstruacion WHERE usuario = ?";
    }
}
