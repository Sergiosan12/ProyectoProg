/**
 * La clase FaseOvulacion modela la fase de ovulación del ciclo menstrual.
 * Calcula la duración media de la fase de ovulación y proporciona el día de inicio de la misma.
 * La duración media de la fase de ovulación se calcula como la mitad de la duración del ciclo menstrual.
 */
package com.model.fases;

import com.model.tiempo.Duracion;

public class FaseOvulacion {
    public int mediaDiasOvulacion = 3; // Por defecto, se establece una duración media de 3 días para la ovulación.

    public int getMediaDiasOvulacion() {
        return mediaDiasOvulacion;
    }
}
