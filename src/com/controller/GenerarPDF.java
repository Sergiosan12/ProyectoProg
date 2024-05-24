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
import java.util.Locale;

import com.model.funciones.Informe;
import com.model.funciones.Menstruacion;
import com.model.usuario.Usuario;
import com.model.fases.FaseFolicular;
import com.model.fases.FaseLutea;
import com.model.fases.FaseMenstrual;
import com.model.fases.FaseOvulacion;

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
        InformeBuilder builder = new InformeBuilder();
        configuracion=builder.build();
        String usuario = configuracion.getUsuario();

        /**
         * Crea un documento PDF y añade la información del informe.
         */
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

            document.add(new Paragraph("Nombre: " + configuracion.getNombre(), boldFont));
            document.add(new Paragraph("Edad: " + configuracion.getEdad(), boldFont));
            document.add(new Paragraph("\nInformaciones generales:", boldFont));
            document.add(new Paragraph("Última Menstruación: " + sdf.format(configuracion.getLastperiod())));
            document.add(new Paragraph("Media Duración del Periodo: " + configuracion.getMediaSangrado()));
            document.add(new Paragraph("Media Duración del Ciclo: " + configuracion.getMediaCiclo()));
            document.add(new Paragraph("Duración Fase Menstruación: " + configuracion.getDuracionFaseMenstrual()));
            document.add(new Paragraph("Duración Fase Folicular: " + configuracion.getDuracionFaseFolicular()));
            document.add(new Paragraph("Duración Fase Ovulación: " + configuracion.getDuracionFaseOvulacion()));
            document.add(new Paragraph("Duración Fase Lútea: " + configuracion.getDuracionFaseLutea()));
            document.add(new Paragraph("Inicio Fase Menstrual: " + sdf.format(configuracion.getInicioFaseMenstrual())));
            document.add(new Paragraph("Inicio Fase Folicular: " + sdf.format(configuracion.getInicioFaseFolicular())));
            document.add(new Paragraph("Inicio Fase Ovulación: " + sdf.format(configuracion.getInicioFaseOvulacion())));
            document.add(new Paragraph("Inicio Fase Lútea: " + sdf.format(configuracion.getInicioFaseLutea())));
            document.add(new Paragraph("\nPrevisión siguiente mes:", boldFont));
            document.add(new Paragraph("Inicio Siguiente Periodo: " + sdf.format(configuracion.getInicioSiguientePeriodo())));

            document.add(new Paragraph("\nFecha del informe: " + fechaInforme.format(dtf)));
            document.add(new Paragraph("\"Los juegos de Sangre\"", boldFont));

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
