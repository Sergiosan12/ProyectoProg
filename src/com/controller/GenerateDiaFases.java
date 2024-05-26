package com.controller;

import com.model.fases.FaseFolicular;
import com.model.fases.FaseLutea;
import com.model.fases.FaseMenstrual;
import com.model.fases.FaseOvulacion;
import com.model.funciones.Menstruacion;

import java.util.Calendar;
import java.util.Date;

/**
 * La clase GenerateDiaFases calcula las diferentes fases del ciclo menstrual
 * basándose en la duración media del ciclo y la duración del sangrado.
 */
public class GenerateDiaFases {
    private final FaseOvulacion faseOvulacion = new FaseOvulacion();
    private final Menstruacion menstruacion;

    /**
     * Constructor para inicializar la clase con una instancia de Menstruacion.
     *
     * @param menstruacion instancia de Menstruacion con los datos del ciclo.
     */
    public GenerateDiaFases(Menstruacion menstruacion) {
        this.menstruacion = menstruacion;
    }

    /**
     * Calcula la duración media de la fase folicular.
     *
     * @return la duración media de la fase folicular en días.
     */
    public int CalculoMediaFaseFolicular() {
        int mediaFaseFolicular = (menstruacion.getMediaCiclo() - menstruacion.getMediaSangrado()) / 2;
        menstruacion.setDuracionFaseFolicular(mediaFaseFolicular);
        return mediaFaseFolicular;
    }

    /**
     * Calcula la duración de la fase lútea.
     *
     * @param mediaDiaFaseFolicular duración media de la fase folicular en días.
     * @return la duración de la fase lútea en días.
     */
    public int CalculoFaseLutea(int mediaDiaFaseFolicular) {
        int mediaDiaFaseLutea = menstruacion.getMediaCiclo() - menstruacion.getMediaSangrado() - mediaDiaFaseFolicular;
        menstruacion.setDuracionFaseLutea(mediaDiaFaseLutea);
        return mediaDiaFaseLutea;
    }

    /**
     * Calcula la fecha de inicio de la fase folicular.
     *
     * @return la fecha de inicio de la fase folicular.
     */
    public Date CalculoInicioFaseFolicular() {
        try {
            Date nextPeriod = menstruacion.getNextPeriod();
            int daysToAdd = menstruacion.getMediaSangrado();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nextPeriod);
            calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);

            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Calcula la fecha de inicio de la fase lútea.
     *
     * @param mediaDiasFolicular duración media de la fase folicular en días.
     * @return la fecha de inicio de la fase lútea.
     */
    public Date CalculoInicioFaseLutea(int mediaDiasFolicular) {
        try {
            Date nextPeriod = menstruacion.getNextPeriod();
            int daysToAdd = menstruacion.getMediaSangrado() + mediaDiasFolicular + faseOvulacion.getMediaDiasOvulacion();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nextPeriod);
            calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
            Date nextFaseLutea = calendar.getTime();
            menstruacion.setNextFaseLutea(nextFaseLutea);
            return nextFaseLutea;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Calcula la fecha de inicio de la fase de ovulación.
     *
     * @param mediaDiasFolicular duración media de la fase folicular en días.
     * @return la fecha de inicio de la fase de ovulación.
     */
    public Date CalculoInicioFaseOvulacion(int mediaDiasFolicular) {
        try {
            Date nextPeriod = menstruacion.getNextPeriod();
            int daysToAdd = mediaDiasFolicular + menstruacion.getMediaSangrado();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nextPeriod);
            calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
            Date nextFaseOvulacion = calendar.getTime();
            menstruacion.setNextFaseOvular(nextFaseOvulacion);
            return nextFaseOvulacion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Calcula la fecha de inicio de la siguiente fase menstrual.
     *
     * @return la fecha de inicio de la siguiente fase menstrual.
     */
    public Date CalculoSiguienteFaseMenstrual() {
        try {
            Date nextPeriod = menstruacion.getNextPeriod();
            int daysToAdd = menstruacion.getMediaCiclo();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nextPeriod);
            calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
            Date nextFaseMenstrual = calendar.getTime();
            menstruacion.setNextPeriod(nextFaseMenstrual);
            return nextFaseMenstrual;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Calcula y muestra las fechas de inicio de todas las fases del ciclo menstrual.
     */
    public void calcularTodasLasFases() {
        int mediaFaseFolicular = CalculoMediaFaseFolicular();
        int mediaFaseLutea = CalculoFaseLutea(mediaFaseFolicular);

        Date inicioFaseFolicular = CalculoInicioFaseFolicular();
        Date inicioFaseLutea = CalculoInicioFaseLutea(mediaFaseFolicular);
        Date inicioFaseOvulacion = CalculoInicioFaseOvulacion(mediaFaseFolicular);
        Date siguienteFaseMenstrual = CalculoSiguienteFaseMenstrual();

        System.out.println("Inicio Fase Folicular: " + inicioFaseFolicular);
        System.out.println("Inicio Fase Lutea: " + inicioFaseLutea);
        System.out.println("Inicio Fase Ovulacion: " + inicioFaseOvulacion);
        System.out.println("Siguiente Fase Menstrual: " + siguienteFaseMenstrual);
    }
}
