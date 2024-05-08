package com.informe;

import java.util.Date;

public class Informe {
    private String nombre;
    private Date fechaInforme;
    private Date fechaNacimiento;
    private Date ultimaMenstruacion;
    private String mediaDuracionPeriodo;
    private String mediaDuracionCiclo;

    public Informe(String nombre, Date fechaInforme, Date fechaNacimiento, Date ultimaMenstruacion, String mediaDuracionPeriodo, String mediaDuracionCiclo) {
        this.nombre = nombre;
        this.fechaInforme = fechaInforme;
        this.fechaNacimiento = fechaNacimiento;
        this.ultimaMenstruacion = ultimaMenstruacion;
        this.mediaDuracionPeriodo = mediaDuracionPeriodo;
        this.mediaDuracionCiclo = mediaDuracionCiclo;
    }

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

