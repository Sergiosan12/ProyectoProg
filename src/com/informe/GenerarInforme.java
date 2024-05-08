package com.informe;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GenerarInforme {
    private Informe informe;

    public GenerarInforme(Informe informe) {
        this.informe = informe;
    }

    public void generarInforme() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat monthYearFormat = new SimpleDateFormat("MM_yyyy");
        String fileName = "Informe_" + monthYearFormat.format(informe.getFechaInforme()) + ".txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter buffer = new BufferedWriter(writer);

            buffer.write("Nombre: " + informe.getNombre());
            buffer.newLine();
            buffer.write("Información general");
            buffer.write("Fecha de Nacimiento: " + sdf.format(informe.getFechaNacimiento()));
            buffer.newLine();
            buffer.write("Última Menstruación: " + sdf.format(informe.getUltimaMenstruacion()));
            buffer.newLine();
            buffer.write("Media Duración del Periodo: " + informe.getMediaDuracionPeriodo());
            buffer.newLine();
            buffer.write("Media Duración del Ciclo: " + informe.getMediaDuracionCiclo());
            buffer.newLine();
            buffer.write("Fecha del informe: " + sdf.format(informe.getFechaInforme()));
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Introduce la fecha del informe (dd/MM/yyyy):");
        String fechaInformeStr = scanner.nextLine();
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
            Date fechaInforme = sdf.parse(fechaInformeStr);
            Date fechaNacimiento = sdf.parse(fechaNacimientoStr);
            Date ultimaMenstruacion = sdf.parse(ultimaMenstruacionStr);

            Informe informe = new Informe(nombre, fechaInforme, fechaNacimiento, ultimaMenstruacion, mediaDuracionPeriodo, mediaDuracionCiclo);
            GenerarInforme generarInforme = new GenerarInforme(informe);
            generarInforme.generarInforme();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
