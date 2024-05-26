/**
 * La clase FaseFolicular modela la fase folicular del ciclo menstrual.
 * Calcula la duración media de la fase folicular y el día de inicio de la misma.
 * La duración media de la fase folicular se calcula como la mitad de la duración
 * del ciclo menstrual menos la duración del sangrado menstrual y la duración media
 * de la fase de ovulación.
 */
package com.model.fases;

import com.model.tiempo.Duracion;

public class FaseFolicular {
    int mediaDiasFolicular;


    public int getMediaDiasFolicular() {
        return mediaDiasFolicular;
    }

    public void setMediaDiasFolicular(int mediaDiasFolicular) {
        this.mediaDiasFolicular = mediaDiasFolicular;
    }
}
