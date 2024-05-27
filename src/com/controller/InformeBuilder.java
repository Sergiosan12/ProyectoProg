package com.controller;

import com.database.DatabaseHandlerMenstruacion;
import com.database.DatabaseHandlerUsuario;
import com.model.funciones.Informe;
import com.model.funciones.Menstruacion;
import com.model.usuario.Usuario;

public class InformeBuilder {
    private final Informe informe;
    private final DatabaseHandlerUsuario dbHandlerUsuario;
    private final DatabaseHandlerMenstruacion dbHandlerMenstruacion;

    public InformeBuilder() {
        this.informe = new Informe();
        this.dbHandlerUsuario = new DatabaseHandlerUsuario();
        this.dbHandlerMenstruacion = new DatabaseHandlerMenstruacion();
    }

    public InformeBuilder fromUsuario(String usuario) {
        Usuario usuarioData = dbHandlerUsuario.selectData(usuario);
        if (usuarioData != null) {
            informe.setNombre(usuarioData.getNombre());
            informe.setEdad(usuarioData.getEdad());
        }
        return this;
    }

    public InformeBuilder fromMenstruacion(String usuario) {
        Menstruacion menstruacionData = dbHandlerMenstruacion.selectData(usuario);
        if (menstruacionData != null) {
            informe.setUsuario(menstruacionData.getUsuario());
            informe.setMediaCiclo(menstruacionData.getMediaCiclo());
            informe.setMediaSangrado(menstruacionData.getMediaSangrado());
            informe.setLastperiod(menstruacionData.getLastperiod());
            informe.setDuracionFaseFolicular(menstruacionData.getDuracionFaseFolicular());
            informe.setDuracionFaseOvulacion(menstruacionData.getDuracionFaseOvular());
            informe.setDuracionFaseLutea(menstruacionData.getDuracionFaseLutea());
            informe.setInicioFaseFolicular(menstruacionData.getNextFaseFolicular());
            informe.setInicioFaseOvulacion(menstruacionData.getNextFaseOvular());
            informe.setInicioFaseLutea(menstruacionData.getNextFaseLutea());
            informe.setInicioSiguientePeriodo(menstruacionData.getNextPeriod());
        }
        return this;
    }
    public InformeBuilder withDeportes(String deporteFaseMenstrual, String deporteFaseFolicular, String deporteFaseOvulacion, String deporteFaseLutea) {
        informe.setDeporteFaseMenstrual(deporteFaseMenstrual);
        informe.setDeporteFaseFolicular(deporteFaseFolicular);
        informe.setDeporteFaseOvulacion(deporteFaseOvulacion);
        informe.setDeporteFaseLutea(deporteFaseLutea);
        return this;
    }

    public Informe build() {
        return informe;
    }
}
