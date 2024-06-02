package com.model.usuario;

/**
 * La clase DeportesUsuario modela los deportes recomendados a un usuario en función de la fase de su ciclo menstrual.

 */
public class DeportesUsuario {
    String usuario;
    String deporteFaseMenstrual;
    String deporteFaseFolicular;
    String deporteFaseOvular;
    String deporteFaseLutea;

    public DeportesUsuario() {
    }

    /**
     * Constructor de la clase DeportesUsuario.
     * @param usuario El nombre del usuario.
     * @param deporteFaseMenstrual El deporte recomendado en la fase menstrual.
     * @param deporteFaseFolicular El deporte recomendado en la fase folicular.
     * @param deporteFaseOvular El deporte recomendado en la fase ovular.
     * @param deporteFaseLutea El deporte recomendado en la fase lútea.
     */
    public DeportesUsuario(String usuario, String deporteFaseMenstrual, String deporteFaseFolicular, String deporteFaseOvular, String deporteFaseLutea) {
        this.usuario = usuario;
        this.deporteFaseMenstrual = deporteFaseMenstrual;
        this.deporteFaseFolicular = deporteFaseFolicular;
        this.deporteFaseOvular = deporteFaseOvular;
        this.deporteFaseLutea = deporteFaseLutea;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDeporteFaseMenstrual() {
        return deporteFaseMenstrual;
    }

    public void setDeporteFaseMenstrual(String deporteFaseMenstrual) {
        this.deporteFaseMenstrual = deporteFaseMenstrual;
    }

    public String getDeporteFaseFolicular() {
        return deporteFaseFolicular;
    }

    public void setDeporteFaseFolicular(String deporteFaseFolicular) {
        this.deporteFaseFolicular = deporteFaseFolicular;
    }

    public String getDeporteFaseOvular() {
        return deporteFaseOvular;
    }

    public void setDeporteFaseOvular(String deporteFaseOvular) {
        this.deporteFaseOvular = deporteFaseOvular;
    }

    public String getDeporteFaseLutea() {
        return deporteFaseLutea;
    }

    public void setDeporteFaseLutea(String deporteFaseLutea) {
        this.deporteFaseLutea = deporteFaseLutea;
    }
}
