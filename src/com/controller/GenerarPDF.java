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
import java.util.Locale;

import com.model.funciones.Informe;
import com.model.fases.FaseFolicular;
import com.model.fases.FaseLutea;
import com.model.fases.FaseMenstrual;
import com.model.fases.FaseOvulacion;
import com.controller.GenerateDiaFases;

/**
 * La clase GenerarPDF se encarga de generar un informe en formato PDF.
 * El informe incluye información sobre el ciclo menstrual de la usuaria.
 */
public class GenerarPDF {
    private Informe configuracion;
    private FaseMenstrual faseMenstrual;
    private FaseFolicular faseFolicular;
    private FaseOvulacion faseOvulacion;
    private FaseLutea faseLutea;
    private GenerateDiaFases generateDiaFases;

    public GenerarPDF() {
    }

    /**
     * Constructor de la clase GenerarPDF.
     *
     * @param informe El informe que se va a generar.
     */
    public GenerarPDF(Informe informe, FaseMenstrual faseMenstrual, FaseFolicular faseFolicular,
                      FaseOvulacion faseOvulacion, FaseLutea faseLutea, GenerateDiaFases generateDiaFases) {
        this.configuracion = informe;
        this.faseMenstrual = faseMenstrual;
        this.faseFolicular = faseFolicular;
        this.faseOvulacion = faseOvulacion;
        this.faseLutea = faseLutea;
        this.generateDiaFases = generateDiaFases;
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

        String usuario = "prueba";
        if (configuracion.getUsuario() != null) {
            usuario = configuracion.getUsuario();
        }
        String lastPeriod = "N/A"; // Default value
        if (configuracion.getLastperiod() != null) {
            lastPeriod = sdf.format(configuracion.getLastperiod());
        }

        String inicioFaseMenstrual = "N/A"; // Valor por defecto
        if (configuracion.getInicioFaseMenstrual() != null) {
            inicioFaseMenstrual = sdf.format(configuracion.getInicioFaseMenstrual());
        }

        String inicioFaseFolicular = "N/A"; // Valor por defecto
        if (configuracion.getInicioFaseFolicular() != null) {
            inicioFaseFolicular = sdf.format(configuracion.getInicioFaseFolicular());
        }

        String inicioFaseOvulacion = "N/A"; // Valor por defecto
        if (configuracion.getInicioFaseOvulacion() != null) {
            inicioFaseOvulacion = sdf.format(configuracion.getInicioFaseOvulacion());
        }

        String inicioFaseLutea = "N/A"; // Valor por defecto
        if (configuracion.getInicioFaseLutea() != null) {
            inicioFaseLutea = sdf.format(configuracion.getInicioFaseLutea());
        }
        String siguientePeriodo = "N/A"; // Valor por defecto
        if (configuracion.getInicioSiguientePeriodo() != null) {
            siguientePeriodo = sdf.format(configuracion.getInicioSiguientePeriodo());
        }
        // Imprimir valores para depuración
        System.out.println("Nombre: " + configuracion.getNombre());
        System.out.println("Edad: " + configuracion.getEdad());
        System.out.println("Última Menstruación: " + lastPeriod);
        System.out.println("Media Duración del Periodo: " + configuracion.getMediaSangrado());
        System.out.println("Media Duración del Ciclo: " + configuracion.getMediaCiclo());

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            System.out.println("Documento PDF abierto correctamente.");

            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            System.out.println("Generando informe...");
            document.add(new Paragraph("Nombre: " + configuracion.getNombre(), boldFont));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Edad: " + configuracion.getEdad(), boldFont));
            document.add(new Paragraph("\nInformaciones generales:", boldFont));
            document.add(new Paragraph("Última Menstruación: " + lastPeriod));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Media Duración del Periodo: " + configuracion.getMediaSangrado()));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Media Duración del Ciclo: " + configuracion.getMediaCiclo()));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Duración Fase Menstruación: " + configuracion.getDuracionFaseMenstrual()));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Duración Fase Folicular: " + configuracion.getDuracionFaseFolicular()));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Duración Fase Ovulación: " + configuracion.getDuracionFaseOvulacion()));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Duración Fase Lútea: " + configuracion.getDuracionFaseLutea()));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Inicio Fase Menstrual: " + inicioFaseMenstrual));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Inicio Fase Folicular: " + inicioFaseFolicular));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Inicio Fase Ovulación: " + inicioFaseOvulacion));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Inicio Fase Lútea: " + inicioFaseLutea));
            document.add(new Paragraph("\nPrevisión siguiente mes:", boldFont));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Inicio Siguiente Periodo: " + siguientePeriodo));
            document.add(new Paragraph("\nFecha del informe: " + fechaInforme.format(dtf)));
            System.out.println("Generando informe...");
            document.add(new Paragraph("\"Los juegos de Sangre\"", boldFont));
            System.out.println("Generando informe...");

            document.close();
            System.out.println("Documento PDF cerrado correctamente.");
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println("Error al generar el documento PDF: " + e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al encontrar el archivo para el documento PDF: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
