package com.controller;

import com.database.DatabaseHandlerMenstruacion;
import com.database.DatabaseHandlerUsuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.model.funciones.Informe;
import com.model.funciones.Menstruacion;
import com.model.usuario.Usuario;

/**
 * La clase GenerarInforme se encarga de generar un informe en formato PDF.
 * El informe incluye información sobre el ciclo menstrual de la usuaria.
 */
public class GenerarInforme {
    private Informe informe;
    String nombre;
    String email;
    String contrasenha;
    int edad;
    int mediaCiclo;
    int mediaSangrado;
    Date lastperiod;

    /**
     * Constructor de la clase GenerarInforme.
     *
     * @param informe El informe que se va a generar.
     */
    public GenerarInforme(Informe informe) {
        this.informe = informe;
    }

    /**
     * Este método genera un informe en formato PDF.
     * El informe incluye información como el nombre de la usuaria, la fecha de nacimiento,
     * la última menstruación, la media duración del periodo y del ciclo, entre otros.
     * El nombre del archivo PDF será "Informe_mesActual_añoActual.pdf".
     */
    public void generarInforme() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter monthYearFormat = DateTimeFormatter.ofPattern("MM_yyyy");
        LocalDate fechaInforme = LocalDate.now();
        String fileName = "Informe_" + fechaInforme.format(monthYearFormat) + ".pdf";

        // Obtener los datos del usuario
        // Obtener los datos del usuario
        // Obtener los datos del usuario
        String usuario = informe.getUsuario();

        // Crear una instancia de DatabaseHandler
        DatabaseHandlerUsuario dbHandler = new DatabaseHandlerUsuario();
        Usuario usuarioData = dbHandler.selectData(usuario);
        DatabaseHandlerMenstruacion dbHandler2 = new DatabaseHandlerMenstruacion();
        Menstruacion menstruaciondata = dbHandler2.selectData(usuario);

        if (usuarioData != null) {
            nombre = usuarioData.getNombre();
            email = usuarioData.getEmail();
            contrasenha = usuarioData.getContrasena();
            edad = usuarioData.getEdad();
            mediaCiclo = menstruaciondata.getMediaCiclo();
            mediaSangrado = menstruaciondata.getMediaSangrado();
            lastperiod = menstruaciondata.getLastperiod();


        } else {
            System.out.println("No se encontró ningún usuario con el nombre proporcionado: " + usuario);
        }

        /**
         * Crea un documento PDF y añade la información del informe.
         */
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

            document.add(new Paragraph("Nombre: " + nombre, boldFont));
            document.add(new Paragraph("Edad " + sdf.format(edad), boldFont));
            document.add(new Paragraph("\nInformaciones generales:", boldFont));
            document.add(new Paragraph("Última Menstruación: " + sdf.format(informe.getUltimaMenstruacion())));
            document.add(new Paragraph("Media Duración del Periodo: " + mediaSangrado));
            document.add(new Paragraph("Media Duración del Ciclo: " + mediaCiclo));
            document.add(new Paragraph("Duración Fase Menstruación: ")); // Add the actual value
            document.add(new Paragraph("Duración Fase Folicular: ")); // Add the actual value
            document.add(new Paragraph("Duración Fase Ovulación: ")); // Add the actual value
            document.add(new Paragraph("Duración Fase Lútea: ")); // Add the actual value
            document.add(new Paragraph("\nPrevisión siguiente mes:", boldFont));
            document.add(new Paragraph("Inicio Siguiente Periodo: ")); // Add the actual value
            document.add(new Paragraph("Inicio Fase Mentruación: ")); // Add the actual value
            document.add(new Paragraph("Inicio Fase Folicular: ")); // Add the actual value
            document.add(new Paragraph("Inicio Fase Ovulación: ")); // Add the actual value
            document.add(new Paragraph("Inicio Fase Lútea: ")); // Add the actual value
            document.add(new Paragraph("\nFecha del informe: " + fechaInforme.format(dtf)));
            document.add(new Paragraph("\"Los juegos de Sangre\"", boldFont));

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}