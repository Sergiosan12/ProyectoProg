/**
 * La clase Menstruacion representa la información de la menstruación de un usuario.
 * Contiene información como el nombre del usuario, la media del ciclo, la media del sangrado y la fecha del último periodo.
 */
package com.periodo;

import java.util.Date;

public class Menstruacion {
    private String Usuario; // El nombre del usuario
    private int MediaCiclo; // La media del ciclo menstrual del usuario
    private int MediaSangrado; // La media del sangrado menstrual del usuario
    private Date lastperiod; // La fecha del último periodo menstrual del usuario

    /**
     * Constructor de la clase Menstruacion.
     * @param usuario El nombre del usuario.
     * @param mediaCiclo La media del ciclo menstrual del usuario.
     * @param mediaSangrado La media del sangrado menstrual del usuario.
     * @param lastperiod La fecha del último periodo menstrual del usuario.
     */
    public Menstruacion(String usuario, int mediaCiclo, int mediaSangrado, Date lastperiod) {
        Usuario = usuario;
        MediaCiclo = mediaCiclo;
        MediaSangrado = mediaSangrado;
        this.lastperiod = lastperiod;
    }

    public Menstruacion() {
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

    public Date getLastperiod() {
        return lastperiod;
    }

    public void setLastperiod(Date lastperiod) {
        this.lastperiod = lastperiod;
    }
}
