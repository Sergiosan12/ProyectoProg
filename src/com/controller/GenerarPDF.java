package com.controller;

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
import com.model.funciones.Informe;
import com.model.funciones.Menstruacion;

/**
 * Esta clase es responsable de generar un informe en formato PDF.
 * El informe incluye información sobre el ciclo menstrual del usuario.
 */
public class GenerarPDF {
    private final Menstruacion menstruacion;
    private Informe informe;
    private final Document document;

    /**
     * Constructor para la clase GenerarPDF.
     *
     * @param menstruacion Los datos de la menstruación que se incluirán en el informe.
     */
    public GenerarPDF(Menstruacion menstruacion) {
        this.menstruacion = menstruacion;
        this.document = new Document();
    }

    /**
     * Este método genera un informe en formato PDF.
     * El informe incluye información como el nombre del usuario, la fecha de nacimiento,
     * la última menstruación, la duración media del período y del ciclo, entre otros.
     * El nombre del archivo PDF será "Informe_mesActual_añoActual.pdf".
     *
     * @param opcionSeleccionada La opción seleccionada para el informe.
     */
    public void generarInforme(int opcionSeleccionada) {
        // Construir el informe utilizando ambos métodos
        this.informe = new InformeBuilder()
                .fromMenstruacion(menstruacion.getUsuario())
                .withDeportes(menstruacion.getUsuario())
                .build();

        Result result = getVariables();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(result.fileName()));
            document.open();
            WriteDocument(opcionSeleccionada, result);
            document.close();
            System.out.println("Documento PDF generado con éxito.");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Escribe el documento PDF con la información seleccionada.
     *
     * @param opcionSeleccionada La opción seleccionada por el usuario (1 para informe de embarazo, otro valor para deportes).
     * @param result El objeto que contiene las variables formateadas para el informe.
     * @throws DocumentException Si ocurre un error al añadir contenido al documento.
     */
    private void WriteDocument(int opcionSeleccionada, Result result) throws DocumentException {
        document.add(new Paragraph("Nombre: " + informe.getNombre(), result.boldFont()));
        document.add(new Paragraph("Edad: " + informe.getEdad(), result.boldFont()));
        document.add(new Paragraph("\nInformaciones generales:", result.boldFont()));
        document.add(new Paragraph("Última Menstruación: " + result.lastPeriod()));
        document.add(new Paragraph("Media Duración del Periodo: " + informe.getMediaSangrado()));
        document.add(new Paragraph("Media Duración del Ciclo: " + informe.getMediaCiclo()));
        document.add(new Paragraph("Duración Fase Menstruación: " + informe.getMediaSangrado()));
        document.add(new Paragraph("Duración Fase Folicular: " + informe.getDuracionFaseFolicular()));
        document.add(new Paragraph("Duración Fase Ovulación: " + informe.getDuracionFaseOvulacion()));
        document.add(new Paragraph("Duración Fase Lútea: " + informe.getDuracionFaseLutea()));
        document.add(new Paragraph("\nPrevisión siguiente mes:", result.boldFont()));
        document.add(new Paragraph("Inicio Siguiente Periodo: " + result.siguientePeriodo()));
        document.add(new Paragraph("Inicio Fase Folicular: " + result.inicioFaseFolicular()));
        document.add(new Paragraph("Inicio Fase Ovulación: " + result.inicioFaseOvulacion(), new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC)));
        document.add(new Paragraph("Inicio Fase Lútea: " + result.inicioFaseLutea()));

        SeleccionOpcion(opcionSeleccionada);

        document.add(new Paragraph("\nFecha del informe: " + result.fechaInforme().format(result.dtf())));
        document.add(new Paragraph("\"Los juegos de Sangre\"", result.boldFont()));
    }
    /**
     * Genera un objeto Result que contiene las variables necesarias para el informe, formateadas adecuadamente.
     *
     * @return Un objeto Result con las variables formateadas.
     */
    private Result getVariables() {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter monthYearFormat = DateTimeFormatter.ofPattern("MM_yyyy");
        LocalDate fechaInforme = LocalDate.now();
        String fileName = "Informe_" + fechaInforme.format(monthYearFormat) + ".pdf";

        String lastPeriod = informe.getLastperiod() != null ? sdf.format(informe.getLastperiod()) : "N/A";
        String inicioFaseMenstrual = informe.getInicioFaseMenstrual() != null ? sdf.format(informe.getInicioFaseMenstrual()) : "N/A";
        String inicioFaseFolicular = informe.getInicioFaseFolicular() != null ? sdf.format(informe.getInicioFaseFolicular()) : "N/A";
        String inicioFaseOvulacion = informe.getInicioFaseOvulacion() != null ? sdf.format(informe.getInicioFaseOvulacion()) : "N/A";
        String inicioFaseLutea = informe.getInicioFaseLutea() != null ? sdf.format(informe.getInicioFaseLutea()) : "N/A";
        String siguientePeriodo = informe.getInicioSiguientePeriodo() != null ? sdf.format(informe.getInicioSiguientePeriodo()) : "N/A";
        Result result = new Result(boldFont, dtf, fechaInforme, fileName, lastPeriod, inicioFaseFolicular, inicioFaseOvulacion, inicioFaseLutea, siguientePeriodo);
        return result;
    }
/**
     * Clase interna que encapsula las variables necesarias para generar el informe.
     */
    private record Result(Font boldFont, DateTimeFormatter dtf, LocalDate fechaInforme, String fileName, String lastPeriod, String inicioFaseFolicular, String inicioFaseOvulacion, String inicioFaseLutea, String siguientePeriodo) {
    }
 /**
     * Selecciona la opción de informe según la opción proporcionada por el usuario.
     *
     * @param opcionSeleccionada La opción seleccionada (1 para informe de embarazo, otro valor para deportes).
     * @throws DocumentException Si ocurre un error al añadir contenido al documento.
     */
    public void SeleccionOpcion(int opcionSeleccionada) throws DocumentException {
        if (opcionSeleccionada == 1) {
            InformeEmbarazo();
        } else {
            InformeDeportes();
        }
    }
 /**
     * Añade al documento las recomendaciones de deportes para cada fase del ciclo menstrual.
     *
     * @throws DocumentException Si ocurre un error al añadir contenido al documento.
     */
    public void InformeDeportes() throws DocumentException {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        document.add(new Paragraph("\nDeportes Preferidos para cada fase:", boldFont));
        document.add(new Paragraph("Deporte Fase Menstrual: " + informe.getDeporteFaseMenstrual()));
        document.add(new Paragraph("Deporte Fase Folicular: " + informe.getDeporteFaseFolicular()));
        document.add(new Paragraph("Deporte Fase Ovulación: " + informe.getDeporteFaseOvulacion()));
        document.add(new Paragraph("Deporte Fase Lútea: " + informe.getDeporteFaseLutea()));
    }
    /**
     * This method adds pregnancy information to the report.
     *
     * @throws DocumentException If there is an error while writing to the document.
     */
    public void InformeEmbarazo() throws DocumentException {
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        document.add(new Paragraph("\nEmbarazo:", boldFont));
        document.add(new Paragraph("Tu periodo fertil comprende de " + informe.getInicioFaseOvulacion() + " a " + informe.getInicioFaseLutea()));
    }
}
