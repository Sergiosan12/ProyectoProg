package com.controller;

import com.model.fases.FaseFolicular;
import com.model.fases.FaseLutea;
import com.model.fases.FaseOvulacion;
import com.model.funciones.Menstruacion;

import java.util.Calendar;
import java.util.Date;

public class GenerateDiaFases {
    private FaseFolicular fasefolicular;
    private FaseLutea faseLutea;
    private FaseOvulacion faseOvulacion;
    private Menstruacion menstruacion;



    public Date CalculoInicioFaseFolicular() {
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
        public Date CalculoInicioFaseLutea(){
            Date lastPeriod = menstruacion.getLastperiod();
            int daysToAdd = faseLutea.getInicioDiaLutea();

            // Crear una instancia de Calendar y establecer la fecha a lastPeriod
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lastPeriod);

            // Añadir daysToAdd días a la fecha
            calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);

            // Obtener la fecha actualizada
            Date updatedDate = calendar.getTime();

            return updatedDate;
        }

        public Date CalculoInicioFaseOvulacion(){
            Date lastPeriod = menstruacion.getLastperiod();
            int daysToAdd = faseOvulacion.getDiaInicioOvulacion();

            // Crear una instancia de Calendar y establecer la fecha a lastPeriod
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lastPeriod);

            // Añadir daysToAdd días a la fecha
            calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);

            // Obtener la fecha actualizada
            Date updatedDate = calendar.getTime();

            return updatedDate;
        }


}
