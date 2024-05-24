package com.view.interfazinicio.gui;

import com.model.funciones.Menstruacion;
import com.view.cuestionarios.uso.UsoProg;
import com.database.Database;

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

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al comprobar las credenciales: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    public SignIn() {
        super("Iniciar Sesion");
        try {
            setContentPane(panelMain);
            initializeComponents();
            initializeListeners();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al inicializar la interfaz: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() throws Exception {
        // Initialize your components here
        // This method can be filled with the component initialization logic if needed.
    }

    private void initializeListeners() {
        signInButton.addActionListener(e -> {
            try {
                String userName = userfield.getText();
                char[] passwordEcrypted = passwordField.getPassword();
                String password = String.valueOf(passwordEcrypted);

                if (checkCredentials(userName, password)) {
                    // Código a ejecutar en caso de credenciales correctas
                    UsoProg usoProg = new UsoProg(new Menstruacion());  // Asegúrate de pasar cualquier parámetro necesario
                    usoProg.setVisible(true);
                    dispose();
                } else if (userName.isEmpty() || password.isEmpty()) {
                    verdictLabel.setText("Some credentials are empty. Try it again");
                } else {
                    verdictLabel.setText("Sign in failed. Wrong credentials. Try it again");
                }

                userfield.setText("");
                passwordField.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al iniciar sesión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            try {
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new Register();
                    frame.setSize(600, 400);
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    dispose();
                });
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al abrir la ventana de registro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        userfield.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (userfield.getText().isEmpty()) {
                        userfield.setText("Ingrese el nombre de usuario");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SignIn.this, "Error en el campo de usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        userfield.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                try {
                    if (userfield.getText().equals("Ingrese el nombre de usuario")) {
                        userfield.setText("");
                        userfield.setForeground(Color.BLACK);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SignIn.this, "Error en el campo de usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignIn::new);
    }
}
