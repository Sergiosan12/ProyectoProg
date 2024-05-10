/**
 * La clase FaseLutea modela la fase lútea del ciclo menstrual.
 * Calcula la duración media de la fase lútea y el día de inicio de la misma.
 * La duración media de la fase lútea se calcula restando la duración del ciclo menstrual
 * menos la suma de la duración del sangrado menstrual, la duración media de la fase de ovulación
 * y la duración media de la fase folicular.
 */
package com.fases;

import com.cuestionario.Duracion;

public class FaseLutea {
    int mediaDiasLutea;
    int duracionCiclo;
    int duracionMenstruacion;
    int inicioDiaLutea;
    Duracion duracion = Duracion.getInstance();    FaseOvulacion ovulacion = new FaseOvulacion();
    FaseFolicular folicular = new FaseFolicular();

    /**
     * Inicializa la duración del ciclo menstrual y la duración del sangrado menstrual.
     */
    {
        duracionCiclo = duracion.getDuracionCiclo();
        duracionMenstruacion = duracion.getDuracionSangrado();
    }

    /**
     * Establece la duración media de la fase lútea.
     * @param mediaDiasLutea La duración media de la fase lútea.
     */
    public void setMediaDiasLutea(int mediaDiasLutea) {
        this.mediaDiasLutea = mediaDiasLutea;
    }

    /**
     * Calcula y devuelve la duración media de la fase lútea.
     * @return La duración media de la fase lútea.
     */
    public int getMediaDiasLutea() {
        return (duracionCiclo - (duracionMenstruacion + ovulacion.mediaDiasOvulacion + folicular.getMediaDiasFolicular()));
    }

    /**
     * Devuelve el día de inicio de la fase lútea.
     * @return El día de inicio de la fase lútea.
     */
    public int getInicioDiaLutea() {
        return duracionCiclo - getMediaDiasLutea();
    }

}
