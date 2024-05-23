package com.controller;

import com.database.DatabaseHandlerMenstruacion;
import com.database.DatabaseHandlerUsuario;

import com.model.funciones.Informe;
import com.model.funciones.Menstruacion;
import com.model.usuario.Usuario;

/**
 * La clase GenerarInforme se encarga de generar un informe en formato PDF.
 * El informe incluye información sobre el ciclo menstrual de la usuaria.
 */
public class GenerarInformePrueba {
    private Informe informe;

    /**
     * Constructor de la clase GenerarInforme.
     *
     * @param informe El informe que se va a generar.
     */
    public GenerarInformePrueba(Informe informe) {
        this.informe = informe;
    }

    /**
     * Este método genera un informe en formato PDF.
     * El informe incluye información como el nombre de la usuaria, la fecha de nacimiento,
     * la última menstruación, la media duración del periodo y del ciclo, entre otros.
     * El nombre del archivo PDF será "Informe_mesActual_añoActual.pdf".
     */
    // En la clase GenerarInforme

    /**
     * Este método genera un informe en formato PDF.
     * El informe incluye información como el nombre de la usuaria, la fecha de nacimiento,
     * la última menstruación, la media duración del periodo y del ciclo, entre otros.
     * El nombre del archivo PDF será "Informe_mesActual_añoActual.pdf".
     */
    public void generarInforme() {
        // Obtener los datos del usuario
        String usuario = informe.getUsuario();

        // Crear una instancia de DatabaseHandler
        DatabaseHandlerUsuario dbHandler = new DatabaseHandlerUsuario();
        Usuario usuarioData = dbHandler.selectData(usuario);
        DatabaseHandlerMenstruacion dbHandler2 = new DatabaseHandlerMenstruacion();
        Menstruacion menstruaciondata = dbHandler2.selectData(usuario);

        if (usuarioData != null) {
            usuarioData.getNombre();

            // Resto del código para generar el informe
        } else {
            System.out.println("No se encontró ningún usuario con el nombre proporcionado: " + usuario);
        }
    }
}
