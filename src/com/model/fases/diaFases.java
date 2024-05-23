package com.model.fases;

import java.util.Date;

public class diaFases {
    private Date InicioFolicular;
    private Date inicioOvulacion;
    private Date inicioLutea;
    private Date inicioMenstrual;

    public diaFases() {
    }

    public diaFases(Date faseFolicular, Date faseOvulacion, Date faseLutea, Date faseMenstrual) {
        InicioFolicular = faseFolicular;
        inicioOvulacion = faseOvulacion;
        inicioLutea = faseLutea;
        inicioMenstrual = faseMenstrual;
    }

    public Date getInicioFolicular() {
        return InicioFolicular;
    }

    public void setInicioFolicular(Date inicioFolicular) {
        InicioFolicular = inicioFolicular;
    }

    public Date getInicioOvulacion() {
        return inicioOvulacion;
    }

    public void setInicioOvulacion(Date inicioOvulacion) {
        this.inicioOvulacion = inicioOvulacion;
    }

    public Date getInicioLutea() {
        return inicioLutea;
    }

    public void setInicioLutea(Date inicioLutea) {
        this.inicioLutea = inicioLutea;
    }

    public Date getInicioMenstrual() {
        return inicioMenstrual;
    }

    public void setInicioMenstrual(Date inicioMenstrual) {
        this.inicioMenstrual = inicioMenstrual;
    }
}
