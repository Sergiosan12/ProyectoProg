package com.interfaces.interfazinicio.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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

    public SignIn() {
        super("Iniciar Sesion");
        setContentPane(panelMain);

        signInButton.addActionListener(e -> {
            String userName = userfield.getText();
            char[] passwordEcrypted = passwordField.getPassword();
            String password = String.valueOf(passwordEcrypted);
            if (userName.equals("Keyla") && password.equals("1234")) {
                verdictLabel.setText("Sign in successfull!");
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
}


