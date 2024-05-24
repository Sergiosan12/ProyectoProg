package com.model.funciones;

import com.model.tiempo.Duracion;

/**
 * La clase Cuestionario es una clase singleton que maneja una instancia de CuestionarioSwing y Duracion.
 * Proporciona métodos para obtener la duración del ciclo y la duración del sangrado, y para imprimir estos valores.
 *
 */
public class Cuestionario {
    private static Cuestionario instance = null; // La única instancia de Cuestionario
    private CuestionarioSwing cuestionarioSwing; // La interfaz gráfica de usuario
    private Duracion duracion; // La duración del ciclo y del sangrado

    /**
     * Constructor privado de la clase Cuestionario.
     * Inicializa la duración y la interfaz gráfica de usuario.
     */
    private Cuestionario() {
        Duracion duracion = Duracion.getInstance();
        cuestionarioSwing = new CuestionarioSwing(duracion);
    }

    /**
     * Método para obtener la única instancia de Cuestionario.
     * Si la instancia no existe, se crea una nueva.
     *
     * @return La única instancia de Cuestionario.
     */
    public static Cuestionario getInstance() {
        if (instance == null) {
            instance = new Cuestionario();
        }
        return instance;
    }

    /**
     * Método para obtener la duración del ciclo.
     *
     * @return La duración del ciclo.
     */
    public int getDuracionCiclo() {
        return  duracion.getDuracionCiclo();
    }

    /**
     * Método para obtener la duración del sangrado.
     *
     * @return La duración del sangrado.
     */
    public int getDuracionSangrado() {
        return duracion.getDuracionSangrado();
    }

}