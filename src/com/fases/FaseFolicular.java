/**
 * La clase FaseFolicular modela la fase folicular del ciclo menstrual.
 * Calcula la duración media de la fase folicular y el día de inicio de la misma.
 * La duración media de la fase folicular se calcula como la mitad de la duración
 * del ciclo menstrual menos la duración del sangrado menstrual y la duración media
 * de la fase de ovulación.
 */
package com.fases;

import com.cuestionario.Duracion;

public class FaseFolicular {
    int mediaDiasFolicular;
    Duracion duracion = new Duracion();
    private final int durationCiclo;
    private final int durationMenstruation;

    /**
     * Inicializa la duración del ciclo menstrual y la duración del sangrado menstrual.
     */
    {
        durationCiclo = duracion.getDuracionCiclo();
        durationMenstruation = duracion.getDuracionSangrado();
    }

    /**
     * Calcula y devuelve la duración media de la fase folicular.
     * @return La duración media de la fase folicular.
     */
    public int getMediaDiasFolicular() {
        return (mediaDiasFolicular = (durationCiclo / 2) - durationMenstruation - 3);
    }

    /**
     * Devuelve el día de inicio de la fase folicular.
     * @return El día de inicio de la fase folicular.
     */
    public int getInicioDiaFolicular() {
        return durationMenstruation;
    }
}
