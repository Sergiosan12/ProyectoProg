package com.model.funciones;

import java.util.Date;

/**
 * La clase Informe modela un informe sobre el ciclo menstrual de la usuaria.
 */
public class Informe {
    private String usuario;
    private String nombre;
    private String email;
    private String contrasenha;
    private int edad;
    private int mediaCiclo;
    private int mediaSangrado;
    private Date lastperiod;
    private int duracionFaseMenstrual;
    private int duracionFaseFolicular;
    private int duracionFaseOvulacion;
    private int duracionFaseLutea;
    private Date inicioFaseMenstrual;
    private Date inicioFaseFolicular;
    private Date inicioFaseOvulacion;
    private Date inicioFaseLutea;
    private Date inicioSiguientePeriodo;
    private String DeporteFaseMenstrual;
    private String DeporteFaseFolicular;
    private String DeporteFaseOvulacion;
    private String DeporteFaseLutea;

    public String getDeporteFaseMenstrual() {
        return DeporteFaseMenstrual;
    }

    public void setDeporteFaseMenstrual(String deporteFaseMenstrual) {
        DeporteFaseMenstrual = deporteFaseMenstrual;
    }

    public String getDeporteFaseFolicular() {
        return DeporteFaseFolicular;
    }

    public void setDeporteFaseFolicular(String deporteFaseFolicular) {
        DeporteFaseFolicular = deporteFaseFolicular;
    }

    public String getDeporteFaseOvulacion() {
        return DeporteFaseOvulacion;
    }

    public void setDeporteFaseOvulacion(String deporteFaseOvulacion) {
        DeporteFaseOvulacion = deporteFaseOvulacion;
    }

    public String getDeporteFaseLutea() {
        return DeporteFaseLutea;
    }

    public void setDeporteFaseLutea(String deporteFaseLutea) {
        DeporteFaseLutea = deporteFaseLutea;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
// Getters and setters for all fields

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

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getMediaCiclo() {
        return mediaCiclo;
    }

    public void setMediaCiclo(int mediaCiclo) {
        this.mediaCiclo = mediaCiclo;
    }

    public int getMediaSangrado() {
        return mediaSangrado;
    }

    public void setMediaSangrado(int mediaSangrado) {
        this.mediaSangrado = mediaSangrado;
    }

    public Date getLastperiod() {
        return lastperiod;
    }

    public void setLastperiod(Date lastperiod) {
        this.lastperiod = lastperiod;
    }

    public int getDuracionFaseMenstrual() {
        return duracionFaseMenstrual;
    }

    public void setDuracionFaseMenstrual(int duracionFaseMenstrual) {
        this.duracionFaseMenstrual = duracionFaseMenstrual;
    }

    public int getDuracionFaseFolicular() {
        return duracionFaseFolicular;
    }

    public void setDuracionFaseFolicular(int duracionFaseFolicular) {
        this.duracionFaseFolicular = duracionFaseFolicular;
    }

    public int getDuracionFaseOvulacion() {
        return duracionFaseOvulacion;
    }

    public void setDuracionFaseOvulacion(int duracionFaseOvulacion) {
        this.duracionFaseOvulacion = duracionFaseOvulacion;
    }

    public int getDuracionFaseLutea() {
        return duracionFaseLutea;
    }

    public void setDuracionFaseLutea(int duracionFaseLutea) {
        this.duracionFaseLutea = duracionFaseLutea;
    }

    public Date getInicioFaseMenstrual() {
        return inicioFaseMenstrual;
    }

    public void setInicioFaseMenstrual(Date inicioFaseMenstrual) {
        this.inicioFaseMenstrual = inicioFaseMenstrual;
    }

    public Date getInicioFaseFolicular() {
        return inicioFaseFolicular;
    }

    public void setInicioFaseFolicular(Date inicioFaseFolicular) {
        this.inicioFaseFolicular = inicioFaseFolicular;
    }

    public Date getInicioFaseOvulacion() {
        return inicioFaseOvulacion;
    }

    public void setInicioFaseOvulacion(Date inicioFaseOvulacion) {
        this.inicioFaseOvulacion = inicioFaseOvulacion;
    }

    public Date getInicioFaseLutea() {
        return inicioFaseLutea;
    }

    public void setInicioFaseLutea(Date inicioFaseLutea) {
        this.inicioFaseLutea = inicioFaseLutea;
    }

    public Date getInicioSiguientePeriodo() {
        return inicioSiguientePeriodo;
    }

    public void setInicioSiguientePeriodo(Date inicioSiguientePeriodo) {
        this.inicioSiguientePeriodo = inicioSiguientePeriodo;
    }
}