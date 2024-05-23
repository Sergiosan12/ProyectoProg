/**
 * La clase FaseMenstrual modela la fase menstrual del ciclo menstrual.
 * Calcula la duración media de la fase menstrual y proporciona el día de inicio de la misma.
 * La duración media de la fase menstrual se obtiene de la duración del sangrado menstrual.
 */
package com.model.fases;

import com.model.tiempo.Duracion;

import java.util.Date;

public class FaseMenstrual {
    public Date inicioDiaMenstrual; // Por defecto, el día de inicio de la fase menstrual es el día 1 del ciclo.
    Duracion duracion = Duracion.getInstance();
    int mediaDiasMenstrual = duracion.getDuracionSangrado(); // Duración media de la fase menstrual.

    /**
     * Devuelve la duración media de la fase menstrual.
     *
     * @return La duración media de la fase menstrual.
     */
    public int getMediaDiasMenstrual() {
        return mediaDiasMenstrual;
    }

    /**
     * Establece la duración media de la fase menstrual.
     *
     * @param mediaDiasMenstrual La duración media de la fase menstrual a establecer.
     */
    public void setMediaDiasMenstrual(int mediaDiasMenstrual) {
        this.mediaDiasMenstrual = mediaDiasMenstrual;
    }

    /**
     * Calcula y devuelve la duración media de la fase menstrual.
     * Este método puede ser personalizado para realizar cálculos adicionales si es necesario.
     *
     * @return La duración media de la fase menstrual.
     */
    public int CalculoMediaDiasMenstrual() {
        return mediaDiasMenstrual;
    }

    public Date getInicioDiaMenstrual() {
        return inicioDiaMenstrual;
    }

    public void setInicioDiaMenstrual(Date inicioDiaMenstrual) {
        this.inicioDiaMenstrual = inicioDiaMenstrual;
    }
}
