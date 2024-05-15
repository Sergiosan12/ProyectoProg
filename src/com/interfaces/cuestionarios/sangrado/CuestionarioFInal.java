package com.interfaces.cuestionarios.sangrado;

import com.cuestionario.Duracion;
import com.interfaces.cuestionarios.uso.UsoProg;
import com.interfaces.interfazinicio.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CuestionarioFInal  {
    public JPanel panelPrincipal;
    private JLabel lblCiclo;
    private JLabel lblSangrado;
    private JButton continuarButton;
    private JSpinner numerCiclo;
    private JSpinner numerSangrado;
    int duracionCiclo;
int duracionSangrado;
Duracion duracion = new Duracion();

    private void insertDataIntoDatabase() {
        int duracionCiclo = (Integer) numerCiclo.getValue();
        int duracionSangrado = (Integer) numerSangrado.getValue();
        String user = "maestro";

        String sql = "INSERT INTO menstruacion (usuario, mediaciclo, mediasangrado) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user);
            pstmt.setInt(2, duracionCiclo);
            pstmt.setInt(3, duracionSangrado);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CuestionarioFInal() {
        getJpanel();

    }

    public void getJpanel() {
        numerCiclo.setModel(new SpinnerNumberModel(28, 1, Integer.MAX_VALUE, 1));
        numerSangrado.setModel(new SpinnerNumberModel(5, 1, Integer.MAX_VALUE, 1));
        continuarButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    duracionCiclo = (Integer) numerCiclo.getValue();
                    duracionSangrado = (Integer) numerSangrado.getValue();
                    duracion.setDuracionCiclo(duracionCiclo);
                    duracion.setDuracionSangrado(duracionSangrado);
                    System.out.println("Duración del ciclo: " + duracion.getDuracionCiclo());
                    System.out.println("Duración del sangrado: " + duracion.getDuracionSangrado());
                    if (duracionCiclo <= 0 || duracionSangrado <= 0 || duracionSangrado > duracionCiclo) {
                        throw new IllegalArgumentException("Por favor, introduce valores válidos.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce números válidos.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                insertDataIntoDatabase();
                JOptionPane.showMessageDialog(null, "Datos de menstruación registrados exitosamente");
                UsoProg usoProg = new UsoProg();
                usoProg.setVisible(true);
                    }
        });
    }
}