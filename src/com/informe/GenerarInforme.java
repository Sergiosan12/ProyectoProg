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


public class GenerarInforme {
    private Informe informe;

    public GenerarInforme(Informe informe) {
        this.informe = informe;
    }

    public void generarInforme() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter monthYearFormat = DateTimeFormatter.ofPattern("MM_yyyy");
        LocalDate fechaInforme = LocalDate.now();
        String fileName = "Informe_" + fechaInforme.format(monthYearFormat) + ".pdf";

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

            document.add(new Paragraph("Nombre: " + informe.getNombre(), boldFont));
            document.add(new Paragraph("Fecha de Nacimiento: " + sdf.format(informe.getFechaNacimiento()), boldFont));
            document.add(new Paragraph("\nInformaciones generales:", boldFont));
            document.add(new Paragraph("Última Menstruación: " + sdf.format(informe.getUltimaMenstruacion())));
            document.add(new Paragraph("Media Duración del Periodo: " + informe.getMediaDuracionPeriodo()));
            document.add(new Paragraph("Media Duración del Ciclo: " + informe.getMediaDuracionCiclo()));
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
        System.out.println("Introduce el nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Introduce la fecha de nacimiento (dd/MM/yyyy):");
        String fechaNacimientoStr = scanner.nextLine();
        System.out.println("Introduce la última menstruación (dd/MM/yyyy):");
        String ultimaMenstruacionStr = scanner.nextLine();
        System.out.println("Introduce la media duración del periodo:");
        String mediaDuracionPeriodo = scanner.nextLine();
        System.out.println("Introduce la media duración del ciclo:");
        String mediaDuracionCiclo = scanner.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fechaNacimiento = sdf.parse(fechaNacimientoStr);
            Date ultimaMenstruacion = sdf.parse(ultimaMenstruacionStr);

            Informe informe = new Informe(nombre, null, fechaNacimiento, ultimaMenstruacion, mediaDuracionPeriodo, mediaDuracionCiclo);
            GenerarInforme generarInforme = new GenerarInforme(informe);
            generarInforme.generarInforme();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
