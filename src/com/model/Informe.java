package com.model;

import java.util.Date;

/**
 * La clase Informe modela un informe sobre el ciclo menstrual de la usuaria.
 */
public class Informe {
    private String usuario;
    private String nombre;
    private Date fechaInforme;
    private Date fechaNacimiento;
    private Date ultimaMenstruacion;
    private String mediaDuracionPeriodo;
    private String mediaDuracionCiclo;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Constructor de la clase Informe.
     * @param nombre de la usuaria.
     * @param fechaInforme fecha en el que se genera el informe.
     * @param fechaNacimiento de la usuaria.
     * @param ultimaMenstruacion fecha de la última menstruación de la usuaria.
     * @param mediaDuracionPeriodo de la usuaria.
     * @param mediaDuracionCiclo de la usuaria.
     */
    public Informe(String nombre, Date fechaInforme, Date fechaNacimiento, Date ultimaMenstruacion, String mediaDuracionPeriodo, String mediaDuracionCiclo) {
        this.nombre = nombre;
        this.fechaInforme = fechaInforme;
        this.fechaNacimiento = fechaNacimiento;
        this.ultimaMenstruacion = ultimaMenstruacion;
        this.mediaDuracionPeriodo = mediaDuracionPeriodo;
        this.mediaDuracionCiclo = mediaDuracionCiclo;
    }
    public Informe(){

    }

    //getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInforme() {
        return fechaInforme;
    }

    public void setFechaInforme(Date fechaInforme) {
        this.fechaInforme = fechaInforme;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getUltimaMenstruacion() {
        return ultimaMenstruacion;
    }

    public void setUltimaMenstruacion(Date ultimaMenstruacion) {
        this.ultimaMenstruacion = ultimaMenstruacion;
    }

    public String getMediaDuracionPeriodo() {
        return mediaDuracionPeriodo;
    }

    public void setMediaDuracionPeriodo(String mediaDuracionPeriodo) {
        this.mediaDuracionPeriodo = mediaDuracionPeriodo;
    }

    public String getMediaDuracionCiclo() {
        return mediaDuracionCiclo;
    }

    public void setMediaDuracionCiclo(String mediaDuracionCiclo) {
        this.mediaDuracionCiclo = mediaDuracionCiclo;
    }
}

