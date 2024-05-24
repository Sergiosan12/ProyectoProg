package com.controller;

import com.controller.GenerateDiaFases;
import com.model.fases.FaseFolicular;
import com.model.fases.FaseLutea;
import com.model.fases.FaseMenstrual;
import com.model.fases.FaseOvulacion;
import com.model.funciones.Informe;
import com.model.funciones.Menstruacion;
import com.model.usuario.Usuario;

public class InformeBuilder {
    private Informe informe;

    public InformeBuilder(Informe informe) {
        this.informe = informe;
    }

    public InformeBuilder() {
        this.informe = new Informe();
    }

    public  InformeBuilder fromUsuario(Usuario usuario) {
        informe.setUsuario(usuario.getUsuario());
        informe.setNombre(usuario.getNombre());
        informe.setEmail(usuario.getEmail());
        informe.setContrasenha(usuario.getContrasena());
        informe.setEdad(usuario.getEdad());
        return this;
    }

    public InformeBuilder fromMenstruacion(Menstruacion menstruacion) {
        informe.setMediaCiclo(menstruacion.getMediaCiclo());
        informe.setMediaSangrado(menstruacion.getMediaSangrado());
        informe.setLastperiod(menstruacion.getLastperiod());
        return this;
    }

    public InformeBuilder fromFases(FaseMenstrual faseMenstrual, FaseFolicular faseFolicular,
                                    FaseOvulacion faseOvulacion, FaseLutea faseLutea) {
        informe.setDuracionFaseMenstrual(faseMenstrual.getMediaDiasMenstrual());
        informe.setDuracionFaseFolicular(faseFolicular.getMediaDiasFolicular());
        informe.setDuracionFaseOvulacion(faseOvulacion.getMediaDiasOvulacion());
        informe.setDuracionFaseLutea(faseLutea.getMediaDiasLutea());
        informe.setInicioFaseMenstrual(faseMenstrual.getInicioDiaMenstrual());
        return this;
    }

    public InformeBuilder fromGenerateDiaFases(GenerateDiaFases generateDiaFases) {
        informe.setInicioFaseFolicular(generateDiaFases.CalculoInicioFaseFolicular());
        informe.setInicioFaseOvulacion(generateDiaFases.CalculoInicioFaseOvulacion());
        informe.setInicioFaseLutea(generateDiaFases.CalculoInicioFaseLutea());
        informe.setInicioSiguientePeriodo(generateDiaFases.CalculoSiguienteFaseMenstrual());
        return this;
    }

    public Informe build() {
        return informe;
    }
}
