package com.interfaces.interfazinicio.gui;

import com.interfaces.cuestionarios.sangrado.CuestionarioFInal;
import com.interfaces.interfazinicio.Database;
import com.interfaces.interfazinicio.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends JFrame {
    private JPanel panelMainR;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JLabel labelName;
    private JTextField fieldName;
    private JLabel labelAge;
    private JSpinner spinnerAge;
    private JLabel labelUser;
    private JTextField fieldUser;
    private JLabel labelMail;
    private JTextField fieldMail;
    private JLabel labelPassword;
    private JPasswordField fieldPassword;
    private JLabel labelPasswordConfirm;
    private JPasswordField fieldPasswordConfirm;
    private JLabel labelRegister;
    private JPanel paneTitle;
    private JButton buttonVolver;
    private JButton continuarButton;


    private String insertDataIntoDatabase() {
        String name = fieldName.getText();
        int age = (Integer) spinnerAge.getValue();
        String user = fieldUser.getText();
        String mail = fieldMail.getText();
        String password = new String(fieldPassword.getPassword());

        String sqlUsuario = "INSERT INTO usuario (usuario, nombre, contrasenha, email,edad ) VALUES (?, ?, ?, ?, ?)";
        String sqlMenstruacion = "INSERT INTO menstruacion (usuario, mediaciclo, mediasangrado) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmtUsuario = conn.prepareStatement(sqlUsuario);
             PreparedStatement pstmtMenstruacion = conn.prepareStatement(sqlMenstruacion)) {

            // Inserta el nuevo usuario en la tabla usuario
            pstmtUsuario.setString(1, user);
            pstmtUsuario.setString(2, name);
            pstmtUsuario.setString(3, password);
            pstmtUsuario.setString(4, mail);
            pstmtUsuario.setInt(5, age);
            pstmtUsuario.executeUpdate();

            // Inserta una nueva entrada en la tabla menstruacion para el nuevo usuario con valores nulos en mediaciclo y mediasangrado
            pstmtMenstruacion.setString(1, user);
            pstmtMenstruacion.setNull(2, java.sql.Types.INTEGER); // Inserta un valor nulo en mediaciclo
            pstmtMenstruacion.setNull(3, java.sql.Types.INTEGER); // Inserta un valor nulo en mediasangrado
            pstmtMenstruacion.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Register() {
        super("Registro");
        setContentPane(panelMainR);

        // Establecer la fuente de los botones a Times New Roman
        Font font = new Font("Times New Roman", Font.PLAIN, 12);
        buttonVolver.setFont(font);
        continuarButton.setFont(font);

        // Establecer los bordes de los botones a redondos
        buttonVolver.setBorder(new RoundedBorder(10)); // 10 is the radius
        continuarButton.setBorder(new RoundedBorder(10)); // 10 is the radius

        // Agregar una acción al botón "Volver"
        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame frame = new SignIn();
                frame.setSize(1200, 570);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertDataIntoDatabase();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            dispose();  // Cierra la ventana Register
                            JFrame frame = new JFrame("Cuestionario Final");
                            frame.setSize(600, 370);
                            frame.setVisible(true);
                            frame.setLocationRelativeTo(null);
                            CuestionarioFInal cuestionario = new CuestionarioFInal();
                            frame.getContentPane().add(cuestionario.panelPrincipal);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al abrir la ventana CuestionarioFInal: " + ex.getMessage());
                        }
                    }
                });
                JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
            }
        });
    }
}