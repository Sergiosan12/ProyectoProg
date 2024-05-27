package com.model.usuario;

public class DeportesUsuario {
    String usuario;
    String deporteFaseMenstrual;
    String deporteFaseFolicular;
    String deporteFaseOvular;
    String deporteFaseLutea;

    public DeportesUsuario() {
    }

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
