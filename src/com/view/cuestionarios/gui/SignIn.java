package com.view.cuestionarios.gui;

import com.database.DatabaseHandlerMenstruacion;
import com.database.DatabaseHandlerUsuario;
import com.model.funciones.Menstruacion;
import com.view.cuestionarios.InterfazDespuesInicio;
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
    DatabaseHandlerMenstruacion dbHandlerMenstruacion=new DatabaseHandlerMenstruacion();
    DatabaseHandlerUsuario dbHandlerUsuario= new DatabaseHandlerUsuario();

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
        setSize(1200, 570);
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

                if (dbHandlerUsuario.checkCredentials(userName, password)) {
                    // Código a ejecutar en caso de credenciales correctas
                    Menstruacion menstruacion = dbHandlerMenstruacion.selectData(userName);
                    InterfazDespuesInicio interfazDespuesInicio = new InterfazDespuesInicio(menstruacion);
                    interfazDespuesInicio.setVisible(true);
                    dispose();
                } else if (userName.isEmpty() || password.isEmpty()) {
                    verdictLabel.setText("Credenciales vacías. Por favor rellene los dos campos");
                } else {
                    verdictLabel.setText("Credenciales erróneas. Por favor vuelva a intentarlo");
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
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setVisible(true);
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

}
