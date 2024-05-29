package com.controller;

import com.model.funciones.Menstruacion;

import java.util.Calendar;
import java.util.Date;

/**
 * La clase GenerateDiaFases calcula las diferentes fases del ciclo menstrual
 * basándose en la duración media del ciclo y la duración del sangrado.
 */
public class GenerateDiaFases {
    private static Menstruacion menstruacion = new Menstruacion();

    /**
     * Constructor para inicializar la clase con una instancia de Menstruacion.
     *
     * @param menstruacion instancia de Menstruacion con los datos del ciclo.
     */
    public GenerateDiaFases(Menstruacion menstruacion) {
        GenerateDiaFases.menstruacion = menstruacion;
    }

    public void CambiarDiasSangrado(int diasSangrado) {
        menstruacion.setMediaSangrado(diasSangrado);
    }

    public void CambiarDiasCiclo(int diasCiclo) {
        menstruacion.setMediaCiclo(diasCiclo);
    }

    public static void CambiarDateLastPeriod(Date lastPeriod) {
        menstruacion.setLastperiod(lastPeriod);
    }


    /**
     * Calcula la duración media de la fase folicular.
     *
     * @return la duración media de la fase folicular en días.
     */
    public static int CalculoMediaFaseFolicular() {
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
    public static int CalculoFaseLutea(int mediaDiaFaseFolicular) {
        int mediaDiaFaseLutea = menstruacion.getMediaCiclo() - menstruacion.getMediaSangrado() - mediaDiaFaseFolicular;
        menstruacion.setDuracionFaseLutea(mediaDiaFaseLutea);
        return mediaDiaFaseLutea;
    }

    public static int CalculoFaseOvulacion() {
        int mediaDiaFaseOvulacion = 3;
        menstruacion.setDuracionFaseOvular(mediaDiaFaseOvulacion);
        return mediaDiaFaseOvulacion;
    }

    public static void CalculoFaseMenstrual() {
        menstruacion.setDuracionFaseMenstrual(menstruacion.getMediaSangrado());
    }

    /**
     * Calcula la fecha de inicio de la fase folicular.
     *
     * @return la fecha de inicio de la fase folicular.
     */
    public static Date CalculoInicioFaseFolicular() {
        if (checkIfNullNextPeriod()) return null;
        // Realiza los cálculos y devuelve la fecha calculada
        Calendar cal = Calendar.getInstance();
        cal.setTime(menstruacion.getNextPeriod());
        cal.add(Calendar.DAY_OF_MONTH, menstruacion.getMediaSangrado());
        Date nextFaseFolicular = cal.getTime();
        menstruacion.setNextFaseFolicular(nextFaseFolicular);
        return nextFaseFolicular;
    }

    private static boolean checkIfNullNextPeriod() {
        // Si la fecha del último período es nula, devuelve null
        return menstruacion.getNextPeriod() == null;
    }


    /**
     * Calcula la fecha de inicio de la fase lútea.
     *
     * @param mediaDiasFolicular duración media de la fase folicular en días.
     * @return la fecha de inicio de la fase lútea.
     */
    public static Date CalculoInicioFaseLutea(int mediaDiasFolicular) {
        if (checkIfNullNextPeriod()) return null;
        try {
            Calendar calendar = getCalendarNextPeriod();
            calendar.add(Calendar.DAY_OF_MONTH, getDaysToAddFaseFolicular(mediaDiasFolicular));
            Date nextFaseLutea = calendar.getTime();
            menstruacion.setNextFaseLutea(nextFaseLutea);
            return nextFaseLutea;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Calendar getCalendarNextPeriod() {
        Date nextPeriod = menstruacion.getNextPeriod();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nextPeriod);
        return calendar;
    }

    private static int getDaysToAddFaseFolicular(int mediaDiasFolicular) {
        return menstruacion.getMediaSangrado() + mediaDiasFolicular + menstruacion.getDuracionFaseOvular();
    }


    /**
     * Calcula la fecha de inicio de la fase de ovulación.
     *
     * @return la fecha de inicio de la fase de ovulación.
     */
    public static Date CalculoInicioFaseOvulacion(int mediaDiasFolicular) {
        if (checkIfNullNextPeriod()) return null;
        try {
            Calendar cal = getCalendarNextPeriod();
            cal.add(Calendar.DAY_OF_MONTH, getDaystoaddFaseFolicular(mediaDiasFolicular));
            Date nextOvular = cal.getTime();
            menstruacion.setNextFaseOvular(nextOvular);
            // Aquí deberías calcular la fecha de inicio de la fase de ovulación
            // Usando la duración media de la fase folicular y lútea
            return nextOvular;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static int getDaystoaddFaseFolicular(int mediaDiasFolicular) {

        return mediaDiasFolicular + menstruacion.getMediaSangrado();
    }

    /**
     * Calcula la fecha de inicio de la siguiente fase menstrual.
     *
     * @param ultimoPeriodo la fecha del último periodo menstrual.
     * @return la fecha de inicio de la siguiente fase menstrual.
     */
    public static Date CalculoSiguienteFaseMenstrual(Date ultimoPeriodo) {
        if (ultimoPeriodo != null) {
            // Realiza los cálculos y devuelve la fecha calculada
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, menstruacion.getMediaCiclo());
            // Aquí deberías calcular la fecha de inicio de la siguiente fase menstrual
            Date siguienteFaseMenstrual = cal.getTime();
            menstruacion.setNextPeriod(siguienteFaseMenstrual);
            return siguienteFaseMenstrual;
        } else {
            // Manejo del caso en el que la fecha del último periodo es null
            System.out.println("La fecha del último período no puede ser nula");
            return null;
        }
    }


    /**
     * Calcula y muestra las fechas de inicio de todas las fases del ciclo menstrual.
     */
    public static void calcularTodasLasFases() {
        int mediaFaseFolicular = CalculoMediaFaseFolicular();
        int mediaFaseLutea = CalculoFaseLutea(mediaFaseFolicular);
        CalculoFaseOvulacion();
        CalculoFaseMenstrual();
        CalculoSiguienteFaseMenstrual(menstruacion.getLastperiod());
        CalculoInicioFaseFolicular();
        CalculoInicioFaseLutea(mediaFaseFolicular);
        CalculoInicioFaseOvulacion(mediaFaseFolicular);
    }
}
