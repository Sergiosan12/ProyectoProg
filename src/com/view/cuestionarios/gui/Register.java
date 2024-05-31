package com.view.cuestionarios.gui;

import com.controller.InformeBuilder;
import com.database.InsertDatabaseUsuario;
import com.model.decoracion.RoundedBorder;
import com.model.funciones.Informe;
import com.model.usuario.Usuario;
import com.view.cuestionarios.sangrado.CuestionarioFinal;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Properties;

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
    private Usuario usuario = new Usuario();
    private Informe informe = new Informe();

    public Register() {
        super("Registro");
        setContentPane(panelMainR);

        setUpUI();
        setUpListeners();
    }

    private void setUpUI() {
        Font font = new Font("Times New Roman", Font.PLAIN, 12);
        buttonVolver.setFont(font);
        continuarButton.setFont(font);

        buttonVolver.setBorder(new RoundedBorder(10));
        continuarButton.setBorder(new RoundedBorder(10));
    }

    private void setUpListeners() {
        buttonVolver.addActionListener(e -> openSignInFrame());
        continuarButton.addActionListener(e -> handleRegister());
    }

    private void openSignInFrame() {
        try {
            dispose();
            JFrame frame = new SignIn();
            frame.setSize(1200, 570);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleRegister() {
        try {
            fillUsuarioData();
            new InsertDatabaseUsuario().insertDataIntoDatabase(usuario);
            openCuestionarioFinalFrame();
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void fillUsuarioData() {
        usuario.setNombre(fieldName.getText());
        usuario.setEdad((Integer) spinnerAge.getValue());
        usuario.setUsuario(fieldUser.getText());
        usuario.setEmail(fieldMail.getText());
        usuario.setContrasena(new String(fieldPassword.getPassword()));

        informe.setNombre(usuario.getNombre());
        informe.setEdad(usuario.getEdad());

        InformeBuilder informeBuilder = new InformeBuilder();
        informeBuilder.fromUsuario(usuario.getUsuario());
    }



    private void openCuestionarioFinalFrame() {
        SwingUtilities.invokeLater(() -> {
            try {
                dispose();
                JFrame frame = new JFrame("Cuestionario Final");
                frame.setSize(600, 370);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
                CuestionarioFinal cuestionario = new CuestionarioFinal(usuario);
                frame.getContentPane().add(cuestionario.panelPrincipal);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al abrir la ventana Cuestionario " + ex.getMessage());
            }
        });
    }
}
