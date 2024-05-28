package com.controller;

import com.database.DatabaseHandlerDeportes_usuario;
import com.database.DatabaseHandlerMenstruacion;
import com.database.DatabaseHandlerUsuario;
import com.model.funciones.Informe;
import com.model.funciones.Menstruacion;
import com.model.usuario.DeportesUsuario;
import com.model.usuario.Usuario;

public class InformeBuilder {
    private final Informe informe;
    private final DatabaseHandlerUsuario dbHandlerUsuario;
    private final DatabaseHandlerMenstruacion dbHandlerMenstruacion;
    private final DatabaseHandlerDeportes_usuario dbHandlerDeportes_usuario;

    public InformeBuilder() {
        this.dbHandlerDeportes_usuario = new DatabaseHandlerDeportes_usuario();
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
    public InformeBuilder withDeportes(String usuario) {

                DeportesUsuario menstruacionData = dbHandlerDeportes_usuario.selectData(usuario);
        if (menstruacionData != null) {
        informe.setDeporteFaseMenstrual(dbHandlerDeportes_usuario.selectData(usuario).getDeporteFaseMenstrual());
        informe.setDeporteFaseFolicular(dbHandlerDeportes_usuario.selectData(usuario).getDeporteFaseFolicular());
        informe.setDeporteFaseOvulacion(dbHandlerDeportes_usuario.selectData(usuario).getDeporteFaseOvular());
        informe.setDeporteFaseLutea(dbHandlerDeportes_usuario.selectData(usuario).getDeporteFaseLutea());
        }
        return this;
    }

    public Informe build() {
        return informe;
    }
}
