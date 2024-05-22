package com.funciones.deporte;

import java.util.List;

public class Deporte {
    private String nombre;
    private String intensidad;
    private List<String> fasesRecomendadas;

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
