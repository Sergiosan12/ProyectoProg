package com.controller;

import com.model.fases.FaseFolicular;
import com.model.fases.diaFases;
import com.model.funciones.Menstruacion;

import java.util.Calendar;
import java.util.Date;

public class GenerateDiaFases {
    diaFases diafases;
    private FaseFolicular fasefolicular;
    private Menstruacion menstruacion;


    public Date CalculoInicioFaseFolicular(){
        Date lastPeriod = menstruacion.getLastperiod();
        int daysToAdd = fasefolicular.getInicioDiaFolicular();

        // Crear una instancia de Calendar y establecer la fecha a lastPeriod
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastPeriod);

        // Añadir daysToAdd días a la fecha
        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);

        // Obtener la fecha actualizada
        Date updatedDate = calendar.getTime();

        return updatedDate;
    }
    public void CalculoInicioFaseLutea(){

    }
    public void CalculoInicioFaseOvulacion(){

    }

}
