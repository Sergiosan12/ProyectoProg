package com.informe;

import com.interfaces.interfazinicio.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La clase DatosUsuario se encarga de obtener los datos del usuario y de la menstruación desde la base de datos.
 * Los datos obtenidos incluyen el nombre del usuario, el email, la media del ciclo y la media de la menstruación.
 */
public class DatosUsuario {
    private String usuario;
    private String nombre;
    private String email;
    private int mediaCiclo;
    private int mediaMenstruacion;

    /**
     * Crea una nueva instancia de DatosUsuario.
     * Al instanciar la clase, se obtienen los datos del usuario y de la menstruación desde la base de datos.
     */
    public DatosUsuario() {
        obtenerDatosUsuario();
        obtenerDatosMenstruacion();
    }

    /**
     * Obtiene los datos del usuario desde la base de datos.
     */
    private void obtenerDatosUsuario() {
        String sql = "SELECT * FROM usuario WHERE usuario = (SELECT usuario FROM usuario ORDER BY usuario DESC LIMIT 1)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuario = rs.getString("usuario");
                nombre = rs.getString("nombre");
                email = rs.getString("email");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene los datos de la menstruación desde la base de datos.
     */
    private void obtenerDatosMenstruacion() {
        if (usuario == null || usuario.isEmpty()) {
            System.out.println("Usuario no definido. No se puede obtener datos de menstruación.");
            return;
        }

        String sql = "SELECT * FROM menstruacion WHERE usuario = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                mediaCiclo = rs.getInt("mediaciclo");
                mediaMenstruacion = rs.getInt("mediasangrado");
            } else {
                System.out.println("No se encontraron datos de menstruación para el usuario: " + usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters para los atributos de la clase DatosUsuario
    public String getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getMediaCiclo() {
        return mediaCiclo;
    }

    public int getMediaMenstruacion() {
        return mediaMenstruacion;
    }
    public static void main(String[] args) {
        DatosUsuario datosUsuario = new DatosUsuario();
        System.out.println("Usuario: " + datosUsuario.getUsuario());
        System.out.println("Nombre: " + datosUsuario.getNombre());
        System.out.println("Email: " + datosUsuario.getEmail());
        System.out.println("Media Ciclo: " + datosUsuario.getMediaCiclo());
        System.out.println("Media Menstruacion: " + datosUsuario.getMediaMenstruacion());
    }
}