/**
 * La clase Menstruacion representa la información de la menstruación de un usuario.
 * Contiene información como el nombre del usuario, la media del ciclo, la media del sangrado y la fecha del último periodo.
 */
package com.model.funciones;

import java.util.Date;

public class Menstruacion extends Informe {
    private String Usuario; // El nombre del usuario
    private int MediaCiclo; // La media del ciclo menstrual del usuario
    private int MediaSangrado; // La media del sangrado menstrual del usuario
    private int duracionFaseFolicular;

    private int duracionFaseOvular;

    private int duracionFaseLutea;

    private Date lastperiod; // La fecha del último periodo menstrual del usuario

    private Date nextPeriod;

    private Date nextFaseFolicular;
    private Date nextFaseOvular;
    private Date nextFaseLutea;

    public Menstruacion() {
    }

    public Menstruacion(String usuario, int mediaCiclo, int mediaSangrado, int duracionFaseFolicular, int duracionFaseOvular, int duracionFaseLutea, Date lastperiod, Date nextPeriod, Date nextFaseFolicular, Date nextFaseOvular, Date nextFaseLutea) {
        Usuario = usuario;
        MediaCiclo = mediaCiclo;
        MediaSangrado = mediaSangrado;
        this.duracionFaseFolicular = duracionFaseFolicular;
        this.duracionFaseOvular = duracionFaseOvular;
        this.duracionFaseLutea = duracionFaseLutea;
        this.lastperiod = lastperiod;
        this.nextPeriod = nextPeriod;
        this.nextFaseFolicular = nextFaseFolicular;
        this.nextFaseOvular = nextFaseOvular;
        this.nextFaseLutea = nextFaseLutea;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public int getMediaCiclo() {
        return MediaCiclo;
    }

    public void setMediaCiclo(int mediaCiclo) {
        MediaCiclo = mediaCiclo;
    }

    public int getMediaSangrado() {
        return MediaSangrado;
    }

    public void setMediaSangrado(int mediaSangrado) {
        MediaSangrado = mediaSangrado;
    }

    public int getDuracionFaseFolicular() {
        return duracionFaseFolicular;
    }

    public void setDuracionFaseFolicular(int duracionFaseFolicular) {
        this.duracionFaseFolicular = duracionFaseFolicular;
    }

    public int getDuracionFaseOvular() {
        return duracionFaseOvular;
    }

    public void setDuracionFaseOvular(int duracionFaseOvular) {
        this.duracionFaseOvular = duracionFaseOvular;
    }

    public int getDuracionFaseLutea() {
        return duracionFaseLutea;
    }

    public void setDuracionFaseLutea(int duracionFaseLutea) {
        this.duracionFaseLutea = duracionFaseLutea;
    }

    public Date getLastperiod() {
        return lastperiod;
    }

    public void setLastperiod(Date lastperiod) {
        this.lastperiod = lastperiod;
    }

    public Date getNextPeriod() {
        return nextPeriod;
    }

    public void setNextPeriod(Date nextPeriod) {
        this.nextPeriod = nextPeriod;
    }

    public Date getNextFaseFolicular() {
        return nextFaseFolicular;
    }

    public void setNextFaseFolicular(Date nextFaseFolicular) {
        this.nextFaseFolicular = nextFaseFolicular;
    }

    public Date getNextFaseOvular() {
        return nextFaseOvular;
    }

    public void setNextFaseOvular(Date nextFaseOvular) {
        this.nextFaseOvular = nextFaseOvular;
    }

    public Date getNextFaseLutea() {
        return nextFaseLutea;
    }

    public void setNextFaseLutea(Date nextFaseLutea) {
        this.nextFaseLutea = nextFaseLutea;
    }
}