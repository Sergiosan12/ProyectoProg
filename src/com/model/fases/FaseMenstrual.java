/**
 * La clase FaseMenstrual modela la fase menstrual del ciclo menstrual.
 * Calcula la duración media de la fase menstrual y proporciona el día de inicio de la misma.
 * La duración media de la fase menstrual se obtiene de la duración del sangrado menstrual.
 */
package com.model.fases;

import com.model.tiempo.Duracion;

import java.util.Date;

public class FaseMenstrual {

   private int mediaDiasMenstrual ;

    public int getMediaDiasMenstrual() {
        return mediaDiasMenstrual;
    }

    public void setMediaDiasMenstrual(int mediaDiasMenstrual) {
        this.mediaDiasMenstrual = mediaDiasMenstrual;
    }
}
