/**
 * La clase FaseLutea modela la fase lútea del ciclo menstrual.
 * Calcula la duración media de la fase lútea y el día de inicio de la misma.
 * La duración media de la fase lútea se calcula restando la duración del ciclo menstrual
 * menos la suma de la duración del sangrado menstrual, la duración media de la fase de ovulación
 * y la duración media de la fase folicular.
 */
package com.model.fases;

import com.model.tiempo.Duracion;

import java.util.Date;

public class FaseLutea {
    int mediaDiasLutea;

    public int getMediaDiasLutea() {
        return mediaDiasLutea;
    }

    public void setMediaDiasLutea(int mediaDiasLutea) {
        this.mediaDiasLutea = mediaDiasLutea;
    }
}