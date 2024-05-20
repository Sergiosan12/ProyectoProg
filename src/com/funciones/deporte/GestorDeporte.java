package com.funciones.deporte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorDeporte {
    private List<Deporte> deportes;

    public GestorDeporte() {
        this.deportes = new ArrayList<>();
        generarDeportes();
    }

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

    public List<Deporte> getDeportes() {
        return deportes;
    }
}