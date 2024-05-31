package com.view.cuestionarios.gui;

import com.database.DatabaseHandlerMenstruacion;
import com.database.DatabaseHandlerUsuario;
import com.model.funciones.Menstruacion;
import com.view.cuestionarios.InterfazDespuesInicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SignIn extends JFrame {

    // Constantes de mensajes
    private static final String MSG_EMPTY_CREDENTIALS = "Credenciales vacías. Por favor rellene los dos campos";
    private static final String MSG_INVALID_CREDENTIALS = "Credenciales erróneas. Por favor vuelva a intentarlo";
    private static final String MSG_USER_FIELD_PLACEHOLDER = "Ingrese el nombre de usuario";
    private static final String ERROR_MSG_INIT_INTERFACE = "Error al inicializar la interfaz: ";
    private static final String ERROR_MSG_SIGN_IN = "Error al iniciar sesión: ";
    private static final String ERROR_MSG_REGISTER_WINDOW = "Error al abrir la ventana de registro: ";
    private static final String ERROR_MSG_USER_FIELD = "Error en el campo de usuario: ";

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
    private final DatabaseHandlerMenstruacion dbHandlerMenstruacion = new DatabaseHandlerMenstruacion();
    private final DatabaseHandlerUsuario handlerUsuario = new DatabaseHandlerUsuario();

    public SignIn() {
        super("Iniciar Sesion");
        try {
            setContentPane(panelMain);
            initializeComponents();
            initializeListeners();
        } catch (Exception e) {
            handleException(ERROR_MSG_INIT_INTERFACE, e);
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200, 570);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() throws Exception {
        // Inicializa tus componentes aquí
    }

    private void initializeListeners() {
        signInButton.addActionListener(e -> handleSignIn());
        registerButton.addActionListener(e -> handleRegister());
        userfield.addFocusListener(new UserFieldFocusAdapter());
    }

    private void handleSignIn() {
        try {
            String userName = userfield.getText();
            String password = new String(passwordField.getPassword());

            if (handlerUsuario.checkCredentials(userName, password)) {
                Menstruacion menstruacion = dbHandlerMenstruacion.selectData(userName);
                InterfazDespuesInicio interfazDespuesInicio = new InterfazDespuesInicio(menstruacion);
                interfazDespuesInicio.setVisible(true);
                dispose();
            } else if (userName.isEmpty() || password.isEmpty()) {
                verdictLabel.setText(MSG_EMPTY_CREDENTIALS);
            } else {
                verdictLabel.setText(MSG_INVALID_CREDENTIALS);
            }

            clearFields();
        } catch (Exception ex) {
            handleException(ERROR_MSG_SIGN_IN, ex);
        }
    }

    private void handleRegister() {
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
            handleException(ERROR_MSG_REGISTER_WINDOW, ex);
        }
    }

    private void clearFields() {
        userfield.setText("");
        passwordField.setText("");
    }

    private void handleException(String message, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, message + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private class UserFieldFocusAdapter extends FocusAdapter {
        @Override
        public void focusLost(FocusEvent e) {
            try {
                if (userfield.getText().isEmpty()) {
                    userfield.setText(MSG_USER_FIELD_PLACEHOLDER);
                    userfield.setForeground(Color.GRAY);
                }
            } catch (Exception ex) {
                handleException(ERROR_MSG_USER_FIELD, ex);
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            try {
                if (userfield.getText().equals(MSG_USER_FIELD_PLACEHOLDER)) {
                    userfield.setText("");
                    userfield.setForeground(Color.BLACK);
                }
            } catch (Exception ex) {
                handleException(ERROR_MSG_USER_FIELD, ex);
            }
        }
    }
}
