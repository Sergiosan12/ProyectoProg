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

public class CuestionarioFInal extends Component {
    public JPanel panelPrincipal;
    private JLabel lblCiclo;
    private JLabel lblSangrado;
    private JButton continuarButton;
    private JSpinner numerCiclo;
    private JSpinner numerSangrado;
    int duracionCiclo;
    int duracionSangrado;
    Duracion duracion = new Duracion();
    String user; // Añade una variable de instancia para el usuario

    private void insertDataIntoDatabase() {
        int duracionCiclo = (Integer) numerCiclo.getValue();
        int duracionSangrado = (Integer) numerSangrado.getValue();

        String sql = "UPDATE menstruacion SET mediaciclo = ?, mediasangrado = ? WHERE usuario = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, duracionCiclo);
            pstmt.setInt(2, duracionSangrado);
            pstmt.setString(3, user);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CuestionarioFInal(String user) { // Modifica el constructor para recibir el nombre de usuario
        this.user = user; // Guarda el nombre de usuario

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
                    mostrarError("Por favor, introduce números válidos.");
                } catch (IllegalArgumentException ex) {
                    mostrarError(ex.getMessage());
                }
                insertDataIntoDatabase();
                JOptionPane.showMessageDialog(null, "Datos de menstruación registrados exitosamente");
                UsoProg usoProg = new UsoProg();
                usoProg.setVisible(true);
            }
        });

    }

    private void mostrarError (String mensaje){
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String user = "tony";
            CuestionarioFInal cuestionarioFInal = new CuestionarioFInal(user);
            JFrame frame = new JFrame("Cuestionario Final");
            frame.setContentPane(cuestionarioFInal.panelPrincipal);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}