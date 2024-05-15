package com.interfaces.interfazinicio.gui;

import com.interfaces.cuestionarios.uso.UsoProg;
import com.interfaces.interfazinicio.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignIn extends JFrame {

    private JPanel panelMain;
    private JPanel panelImage;
    private JPanel panelData;
    private JTextField userfield;
    private JPasswordField passwordField;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JButton signInButton;
    private JButton registerButton;
    private JLabel verdictLabel;


    private boolean checkCredentials(String userName, String password) {
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND contrasenha = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userName);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public SignIn() {
        super("Iniciar Sesion");
        setContentPane(panelMain);


        signInButton.addActionListener(e -> {
            String userName = userfield.getText();
            char[] passwordEcrypted = passwordField.getPassword();
            String password = String.valueOf(passwordEcrypted);

            if (checkCredentials(userName, password)) {
                UsoProg usoProg = new UsoProg();
                usoProg.setVisible(true);
            } else if (userName.isEmpty() || password.isEmpty()) {
                verdictLabel.setText("Some credentials are empty. Try it again");
            } else {
                verdictLabel.setText("Sign in failed. Wrong credentials. Try it again");
            }

            userfield.setText("");
            passwordField.setText("");
        });


        registerButton.addActionListener(e -> SwingUtilities.invokeLater(() -> {
            JFrame frame = new Register();
            frame.setSize(600, 400);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dispose();
        }));
        userfield.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (userfield.getText().isEmpty()){
                    userfield.setText("Ingrese el nombre de usuario");
                }
            }
        });
        userfield.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(userfield.getText().equals("Ingrese el nombre de usuario")){
                    userfield.setText("");
                    userfield.setForeground(Color.BLACK);
                }
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignIn signIn = new SignIn();
            signIn.setSize(1200, 560);  // Establece el tamaño de la ventana
            signIn.setVisible(true);
            signIn.setLocationRelativeTo(null);
            signIn.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        });
    }
}


