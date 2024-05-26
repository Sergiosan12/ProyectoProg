package com.controller;

import com.model.funciones.Informe;
import com.model.funciones.Menstruacion;

public class InformeBuilder {
    private Informe informe;

    public InformeBuilder() {
        this.informe = new Informe();
    }

    public InformeBuilder fromMenstruacion(Menstruacion menstruacion) {
        informe.setUsuario(menstruacion.getUsuario());
        informe.setMediaCiclo(menstruacion.getMediaCiclo());
        informe.setMediaSangrado(menstruacion.getMediaSangrado());
        informe.setLastperiod(menstruacion.getLastperiod());
        informe.setDuracionFaseFolicular(menstruacion.getDuracionFaseFolicular());
        informe.setDuracionFaseOvulacion(menstruacion.getDuracionFaseOvular());
        informe.setDuracionFaseLutea(menstruacion.getDuracionFaseLutea());
        informe.setInicioFaseFolicular(menstruacion.getNextFaseFolicular());
        informe.setInicioFaseOvulacion(menstruacion.getNextFaseOvular());
        informe.setInicioFaseLutea(menstruacion.getNextFaseLutea());
        informe.setInicioSiguientePeriodo(menstruacion.getNextPeriod());
        return this;
    }

    public Informe build() {
        return informe;
    }
}
