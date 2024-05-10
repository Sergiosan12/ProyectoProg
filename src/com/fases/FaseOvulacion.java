/**
 * La clase FaseOvulacion modela la fase de ovulación del ciclo menstrual.
 * Calcula la duración media de la fase de ovulación y proporciona el día de inicio de la misma.
 * La duración media de la fase de ovulación se calcula como la mitad de la duración del ciclo menstrual.
 */
package com.fases;

import com.cuestionario.Duracion;

public class FaseOvulacion {
    public int mediaDiasOvulacion = 3; // Por defecto, se establece una duración media de 3 días para la ovulación.
    int diaInicioOvulacion; // Día de inicio de la fase de ovulación.
    Duracion duracion = Duracion.getInstance();
    FaseFolicular folicular = new FaseFolicular();

    /**
     * Calcula y devuelve el día de inicio de la fase de ovulación.
     * El inicio de la ovulación se calcula sumando la duración del sangrado menstrual y la duración media
     * de la fase folicular.
     * @return El día de inicio de la fase de ovulación.
     */
    public int getDiaInicioOvulacion() {
        return diaInicioOvulacion = (duracion.getDuracionSangrado() + folicular.getMediaDiasFolicular());
    }

    /**
     * Devuelve la duración media de la fase de ovulación.
     * @return La duración media de la fase de ovulación.
     */
    public int getMediaDiasOvulacion() {
        return mediaDiasOvulacion;
    }

    /**
     * Calcula y devuelve el día de inicio de la fase de ovulación.
     * Este método actualiza la duración media de la ovulación basándose en la mitad de la duración
     * del ciclo menstrual y luego calcula el día de inicio.
     * @return El día de inicio de la fase de ovulación.
     */
    public int InicioDiaOvulacion() {
        mediaDiasOvulacion = (int) Math.ceil(duracion.getDuracionCiclo() / 2.0f);
        return mediaDiasOvulacion;
    }

    /**
     * Establece el día de inicio de la fase de ovulación.
     * Este método no se implementa actualmente.
     * @param diaInicioOvulacion El día de inicio de la fase de ovulación a establecer.
     */
    public void setDiaInicioOvulacion(int diaInicioOvulacion) {
        // Este método no se implementa actualmente.
    }
}
