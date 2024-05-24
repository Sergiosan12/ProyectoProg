package com.controller;

import com.model.fases.FaseFolicular;
import com.model.fases.FaseLutea;
import com.model.fases.FaseOvulacion;
import com.model.funciones.Menstruacion;
import com.model.tiempo.Duracion;

import java.util.Calendar;
import java.util.Date;

public class GenerateDiaFases {
    private FaseFolicular faseFolicular = new FaseFolicular();
    private FaseLutea faseLutea = new FaseLutea();
    private FaseOvulacion faseOvulacion = new FaseOvulacion();
    private Menstruacion menstruacion;
    private Duracion duracion = new Duracion();

    public GenerateDiaFases(Menstruacion menstruacion) {
        this.menstruacion = menstruacion;
    }

    public Date CalculoInicioFaseFolicular() {
        Date lastPeriod = menstruacion.getLastperiod();
        int daysToAdd = faseFolicular.getInicioDiaFolicular();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastPeriod);
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);

        return calendar.getTime();
    }

    public Date CalculoInicioFaseLutea() {
        Date lastPeriod = menstruacion.getLastperiod();
        int daysToAdd = faseLutea.getInicioDiaLutea();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastPeriod);
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);

        return calendar.getTime();
    }

    public Date CalculoInicioFaseOvulacion() {
        Date lastPeriod = menstruacion.getLastperiod();
        int daysToAdd = faseOvulacion.getDiaInicioOvulacion();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastPeriod);
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);

        return calendar.getTime();
    }

    public Date CalculoSiguienteFaseMenstrual() {
        Date lastPeriod = menstruacion.getLastperiod();
        int daysToAdd = duracion.getDuracionCiclo();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastPeriod);
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);

        return calendar.getTime();
    }
}
