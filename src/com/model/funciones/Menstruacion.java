package com.model.funciones;

import java.util.Date;

/**
 * La clase {@code Menstruacion} representa la información de la menstruación de un usuario.
 * <p>
 * Contiene información como el nombre del usuario, la media del ciclo, la media del sangrado y la fecha del último periodo.
 * </p>
 */
public class Menstruacion extends Informe {
    private String Usuario; // El nombre del usuario
    private int MediaCiclo; // La media del ciclo menstrual del usuario
    private int MediaSangrado; // La media del sangrado menstrual del usuario
    private int duracionFaseFolicular; // La duración de la fase folicular del ciclo menstrual
    private int duracionFaseOvular; // La duración de la fase de ovulación del ciclo menstrual
    private int duracionFaseLutea; // La duración de la fase lutea del ciclo menstrual
    private Date lastperiod; // La fecha del último periodo menstrual del usuario
    private Date nextPeriod; // La fecha del siguiente periodo menstrual estimada
    private Date nextFaseFolicular; // La fecha de inicio de la siguiente fase folicular estimada
    private Date nextFaseOvular; // La fecha de inicio de la siguiente fase de ovulación estimada
    private Date nextFaseLutea; // La fecha de inicio de la siguiente fase lutea estimada

    /**
     * Constructor por defecto.
     */
    public Menstruacion() {
    }

    /**
     * Constructor que inicializa todos los campos de la clase {@code Menstruacion}.
     *
     * @param usuario            el nombre del usuario.
     * @param mediaCiclo         la media del ciclo menstrual del usuario.
     * @param mediaSangrado      la media del sangrado menstrual del usuario.
     * @param duracionFaseFolicular la duración de la fase folicular del ciclo menstrual.
     * @param duracionFaseOvular la duración de la fase de ovulación del ciclo menstrual.
     * @param duracionFaseLutea  la duración de la fase lutea del ciclo menstrual.
     * @param lastperiod         la fecha del último periodo menstrual del usuario.
     * @param nextPeriod         la fecha del siguiente periodo menstrual estimada.
     * @param nextFaseFolicular  la fecha de inicio de la siguiente fase folicular estimada.
     * @param nextFaseOvular     la fecha de inicio de la siguiente fase de ovulación estimada.
     * @param nextFaseLutea      la fecha de inicio de la siguiente fase lutea estimada.
     */
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