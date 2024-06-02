package com.model.funciones;

import java.util.List;

/**
 * La clase Deporte representa un deporte que puede ser recomendado a una usuaria en función de la fase de su ciclo menstrual.
 * Cada instancia de Deporte tiene un nombre, una intensidad y una lista de fases del ciclo menstrual durante las cuales se recomienda.
 * @author Sergiosan12
 */
public class Deporte {
    private String nombre;
    private String intensidad;
    private List<String> fasesRecomendadas;

    /**
     * Crea una nueva instancia de Deporte.
     * @param nombre El nombre del deporte.
     * @param intensidad La intensidad del deporte, que puede ser baja, media o alta.
     * @param fasesRecomendadas Una lista de las fases del ciclo menstrual durante las cuales se recomienda este deporte.
     */
    public Deporte(String nombre, String intensidad, List<String> fasesRecomendadas) {
        this.nombre = nombre;
        this.intensidad = intensidad;
        this.fasesRecomendadas = fasesRecomendadas;
    }

    // Getters y setters para cada atributo

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(String intensidad) {
        this.intensidad = intensidad;
    }

    public List<String> getFasesRecomendadas() {
        return fasesRecomendadas;
    }

    public void setFasesRecomendadas(List<String> fasesRecomendadas) {
        this.fasesRecomendadas = fasesRecomendadas;
    }
}
