package com.cuestionario;

/**
 * La clase Duracion representa la duración de las diferentes fases del ciclo menstrual.
 * Contiene la duración del ciclo y la duración del sangrado, que se obtienen a través de la clase Cuestionario.
 */
public class Duracion {
    private int duracionCiclo;
    private int duracionSangrado;

    public Duracion(int duracionCiclo, int duracionSangrado) {
        this.duracionCiclo = duracionCiclo;
        this.duracionSangrado = duracionSangrado;
    }

    public Duracion() {
    }

    /**
     * Este método devuelve la duración del sangrado.
     * @return duración del sangrado.
     */
    public int getDuracionSangrado() {
        return duracionSangrado;
    }

    /**
     * Este método establece la duración del sangrado.
     * @param duracionSangrado duración del sangrado a establecer.
     */
    public void setDuracionSangrado(int duracionSangrado) {
        this.duracionSangrado = duracionSangrado;
    }

    /**
     * Este método devuelve la duración del ciclo.
     * @return duración del ciclo.
     */
    public int getDuracionCiclo() {
        return duracionCiclo;
    }

    /**
     * Este método establece la duración del ciclo.
     * @param duracionCiclo duración del ciclo a establecer.
     */
    public void setDuracionCiclo(int duracionCiclo) {
        this.duracionCiclo = duracionCiclo;
    }
}