/**
 * La clase Usuario representa a un usuario en el sistema.
 * Contiene información como id, nombre, email, contraseña, edad y nombre de usuario.
 */
package com.model;

public class Usuario {
    private int id; // El identificador del usuario
    private String nombre; // El nombre del usuario
    private String email; // El email del usuario
    private String contrasena; // La contraseña del usuario
    private int edad; // La edad del usuario
    private String usuario; // El nombre de usuario

    /**
     * Constructor vacío de la clase Usuario.
     */
    public Usuario() {
    }

    /**
     * Constructor de la clase Usuario.
     * @param id El identificador del usuario.
     * @param nombre El nombre del usuario.
     * @param email El email del usuario.
     * @param contrasena La contraseña del usuario.
     * @param edad La edad del usuario.
     * @param usuario El nombre de usuario.
     */
    public Usuario(int id, String nombre, String email, String contrasena, int edad, String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.edad = edad;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
