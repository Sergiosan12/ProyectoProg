package com.controller;

import com.database.DatabaseHandlerDeportes_usuario;
import com.database.DatabaseHandlerMenstruacion;
import com.database.DatabaseHandlerUsuario;
import com.model.funciones.Informe;
import com.model.funciones.Menstruacion;
import com.model.usuario.DeportesUsuario;
import com.model.usuario.Usuario;

/**
 * La clase InformeBuilder se encarga de construir un objeto Informe con datos
 * de diferentes fuentes como Usuario, Menstruacion y DeportesUsuario.
 */
public class InformeBuilder {
    private final Informe informe;
    private final DatabaseHandlerUsuario dbHandlerUsuario;
    private final DatabaseHandlerMenstruacion dbHandlerMenstruacion;
    private final DatabaseHandlerDeportes_usuario dbHandlerDeportes_usuario;

    /**
     * Constructor de la clase InformeBuilder. Inicializa los manejadores de base de datos
     * y crea una instancia de Informe.
     */
    public InformeBuilder() {
        this.dbHandlerDeportes_usuario = new DatabaseHandlerDeportes_usuario();
        this.informe = new Informe();
        this.dbHandlerUsuario = new DatabaseHandlerUsuario();
        this.dbHandlerMenstruacion = new DatabaseHandlerMenstruacion();
    }

    /**
     * Añade los datos del usuario al informe.
     *
     * @param usuario el identificador del usuario.
     */
    public void fromUsuario(String usuario) {
        Usuario usuarioData = dbHandlerUsuario.selectData(usuario);
        if (usuarioData != null) {
            informe.setNombre(usuarioData.getNombre());
            informe.setEdad(usuarioData.getEdad());
        }
    }

    /**
     * Añade los datos de menstruación al informe.
     *
     * @param usuario el identificador del usuario.
     * @return la instancia actual de InformeBuilder.
     */
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

    /**
     * Añade los datos de deportes del usuario al informe.
     *
     * @param usuario el identificador del usuario.
     * @return la instancia actual de InformeBuilder.
     */
    public InformeBuilder withDeportes(String usuario) {
        DeportesUsuario deportesData = dbHandlerDeportes_usuario.selectData(usuario);
        if (deportesData != null) {
            informe.setDeporteFaseMenstrual(deportesData.getDeporteFaseMenstrual());
            informe.setDeporteFaseFolicular(deportesData.getDeporteFaseFolicular());
            informe.setDeporteFaseOvulacion(deportesData.getDeporteFaseOvular());
            informe.setDeporteFaseLutea(deportesData.getDeporteFaseLutea());
        }
        return this;
    }

    /**
     * Construye y devuelve el objeto Informe.
     *
     * @return el objeto Informe con todos los datos añadidos.
     */
    public Informe build() {
        return informe;
    }
}
