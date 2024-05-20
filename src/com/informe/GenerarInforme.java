package com.informe;

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
import java.util.Scanner;
import com.informe.DatosUsuario;

/**
 * La clase GenerarInforme se encarga de generar un informe en formato PDF.
 * El informe incluye información sobre el ciclo menstrual de la usuaria.
 */
public class GenerarInforme {
    private Informe informe;
    private DatosUsuario datosUsuario;
    /**
     * Constructor de la clase GenerarInforme.
     * @param informe El informe que se va a generar.
     */
    public GenerarInforme(Informe informe) {
        this.informe = informe;
        this.datosUsuario = new DatosUsuario();
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

        // Obtén los datos del usuario
        String nombre = datosUsuario.getNombre();
        String email = datosUsuario.getEmail();
        int mediaCiclo = datosUsuario.getMediaCiclo();
        int mediaMenstruacion = datosUsuario.getMediaMenstruacion();

        // Utiliza estos datos al generar el informe
        informe.setNombre(nombre);
        informe.setMediaDuracionPeriodo(String.valueOf(mediaMenstruacion));
        informe.setMediaDuracionCiclo(String.valueOf(mediaCiclo));

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

            document.add(new Paragraph("Nombre: " + datosUsuario.getNombre(), boldFont));
            document.add(new Paragraph("Fecha de Nacimiento: " + sdf.format(informe.getFechaNacimiento()), boldFont));
            document.add(new Paragraph("\nInformaciones generales:", boldFont));
            document.add(new Paragraph("Última Menstruación: " + sdf.format(informe.getUltimaMenstruacion())));
            document.add(new Paragraph("Media Duración del Periodo: " + datosUsuario.getMediaMenstruacion()));
            document.add(new Paragraph("Media Duración del Ciclo: " + datosUsuario.getMediaCiclo()));
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
            document.add(new Paragraph("\"Los juegos de Sangre\"",boldFont));

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatosUsuario datosUsuario = new DatosUsuario();
        String nombre = datosUsuario.getNombre();
        int mediaMenstruacion = datosUsuario.getMediaMenstruacion();
        int mediaCiclo = datosUsuario.getMediaCiclo();
        System.out.println("Introduce la fecha de nacimiento (dd/MM/yyyy):");
        String fechaNacimientoStr = scanner.nextLine();
        System.out.println("Introduce la última menstruación (dd/MM/yyyy):");
        String ultimaMenstruacionStr = scanner.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty() && ultimaMenstruacionStr != null && !ultimaMenstruacionStr.isEmpty()) {
                Date fechaNacimiento = sdf.parse(fechaNacimientoStr);
                Date ultimaMenstruacion = sdf.parse(ultimaMenstruacionStr);

                Informe informe = new Informe();
                informe.setNombre(nombre); // Asegúrate de que estás estableciendo el nombre del usuario
                informe.setFechaNacimiento(fechaNacimiento);
                informe.setUltimaMenstruacion(ultimaMenstruacion);
                informe.setMediaDuracionPeriodo(String.valueOf(mediaMenstruacion));
                informe.setMediaDuracionCiclo(String.valueOf(mediaCiclo));

                GenerarInforme generarInforme = new GenerarInforme(informe);
                generarInforme.generarInforme();
            } else {
                System.out.println("Both date fields must be filled.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
