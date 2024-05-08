package com.cuestionario;

public class Cuestionario {
    private static Cuestionario instance = null;
    private CuestionarioSwing cuestionarioGUI;
    private Duracion duracion;

    private Cuestionario() {
        duracion = new Duracion();
        cuestionarioGUI = new CuestionarioSwing(duracion);
    }

    public static Cuestionario getInstance() {
        if (instance == null) {
            instance = new Cuestionario();
        }
        return instance;
    }

    public int getDuracionCiclo() {
        return  duracion.getDuracionCiclo();
    }

    public int getDuracionSangrado() {
        return duracion.getDuracionSangrado();
    }
    public void imprimirDuracionCiclo() {
        System.out.println("Duración del ciclo: " + getDuracionCiclo());
        System.out.println("Duración del sangrado: " + getDuracionSangrado());

    }
}
