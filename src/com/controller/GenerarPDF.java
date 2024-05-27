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
 * La clase GenerarPDF se encarga de generar un informe en formato PDF.
 * El informe incluye información sobre el ciclo menstrual de la usuaria.
 */
public class GenerarPDF {
    private final Menstruacion menstruacion;

    public GenerarPDF(Menstruacion menstruacion) {
        this.menstruacion = menstruacion;
    }

    /**
     * Este método genera un informe en formato PDF.
     * El informe incluye información como el nombre de la usuaria, la fecha de nacimiento,
     * la última menstruación, la media duración del periodo y del ciclo, entre otros.
     * El nombre del archivo PDF será "Informe_mesActual_añoActual.pdf".
     */
    public void generarInforme() {
        InformeBuilder informeBuilder = new InformeBuilder();
        Informe informe = informeBuilder.fromMenstruacion(menstruacion.getUsuario()).build();

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

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

            document.add(new Paragraph("Nombre: " + informe.getNombre(), boldFont));
            document.add(new Paragraph("Edad: " + informe.getEdad(), boldFont));
            document.add(new Paragraph("\nInformaciones generales:", boldFont));
            document.add(new Paragraph("Última Menstruación: " + lastPeriod));
            document.add(new Paragraph("Media Duración del Periodo: " + informe.getMediaSangrado()));
            document.add(new Paragraph("Media Duración del Ciclo: " + informe.getMediaCiclo()));
            document.add(new Paragraph("Duración Fase Menstruación: " + informe.getMediaSangrado()));
            document.add(new Paragraph("Duración Fase Folicular: " + informe.getDuracionFaseFolicular()));
            document.add(new Paragraph("Duración Fase Ovulación: " + informe.getDuracionFaseOvulacion()));
            document.add(new Paragraph("Duración Fase Lútea: " + informe.getDuracionFaseLutea()));
            document.add(new Paragraph("\nPrevisión siguiente mes:", boldFont));
            document.add(new Paragraph("Inicio Siguiente Periodo: " + siguientePeriodo));
            document.add(new Paragraph("Inicio Fase Folicular: " + inicioFaseFolicular));
            Font boldItalicFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLDITALIC);
            document.add(new Paragraph("Es tu mejor periodo fértil :", boldItalicFont));
            document.add(new Paragraph("Inicio Fase Ovulación: " + inicioFaseOvulacion ));
            document.add(new Paragraph("Inicio Fase Lútea: " + inicioFaseLutea));
            document.add(new Paragraph("\nPrevisión siguiente mes:", boldFont));
            document.add(new Paragraph("\nFecha del informe: " + fechaInforme.format(dtf)));
            document.add(new Paragraph("\"Los juegos de Sangre\"", boldFont));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
