package com.controller;

import com.model.Deporte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * La clase GestorDeporte se encarga de gestionar los deportes en la aplicación.
 * Contiene una lista de deportes y un método para generar y añadir deportes a esta lista.
 * Cada deporte tiene un nombre, una intensidad y una lista de las fases del ciclo menstrual durante las cuales se recomienda.
 *
 * @author Sergiosan12
 */
public class GestorDeporte {
    private List<Deporte> deportes;

    /**
     * Crea una nueva instancia de GestorDeporte.
     * Inicializa la lista de deportes y llama al método para generar y añadir deportes a esta lista.
     */
    public GestorDeporte() {
        this.deportes = new ArrayList<>();
        generarDeportes();
    }

    /**
     * Añade deportes al array
     */
    private void generarDeportes() {
        deportes.add(new Deporte("Fútbol", "Alta", Arrays.asList("FaseFolicular", "FaseOvulacion")));
        deportes.add(new Deporte("Baloncesto", "Alta", Arrays.asList("FaseFolicular", "FaseOvulacion")));
        deportes.add(new Deporte("Tenis", "Media", Arrays.asList("FaseFolicular", "FaseOvulacion", "FaseLutea")));
        deportes.add(new Deporte("Natación", "Baja", Arrays.asList("FaseFolicular", "FaseOvulacion", "FaseLutea", "FaseMenstrual")));
        deportes.add(new Deporte("Atletismo", "Alta", Arrays.asList("FaseFolicular", "FaseOvulacion")));
        deportes.add(new Deporte("Ciclismo", "Alta", Arrays.asList("FaseFolicular", "FaseOvulacion")));
        deportes.add(new Deporte("Pilates", "Baja", Arrays.asList("FaseFolicular", "FaseOvulacion", "FaseLutea", "FaseMenstrual")));
        deportes.add(new Deporte("Yoga", "Baja", Arrays.asList("FaseFolicular", "FaseOvulacion", "FaseLutea", "FaseMenstrual")));
        deportes.add(new Deporte("Crossfit", "Alta", Arrays.asList("FaseFolicular", "FaseOvulacion")));
        deportes.add(new Deporte("Pádel", "Media", Arrays.asList("FaseFolicular", "FaseOvulacion", "FaseLutea")));
    }

    /**
     * Devuelve la lista de deportes.
     * @return La lista de deportes.
     */
    public List<Deporte> getDeportes() {
        return deportes;
    }
}