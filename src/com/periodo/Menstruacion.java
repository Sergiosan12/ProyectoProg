package com.periodo;

import java.util.Date;

public class Menstruacion {
    private String Usuario;
    private int MediaCiclo;
        private int MediaSangrado;
        private Date lastperiod;

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

    public java.sql.Date getLastperiod() {
        return (java.sql.Date) lastperiod;
    }

    public void setLastperiod(Date lastperiod) {
        this.lastperiod = lastperiod;
    }
}
