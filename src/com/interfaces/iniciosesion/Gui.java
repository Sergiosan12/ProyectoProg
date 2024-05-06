package com.interfaces.iniciosesion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que contiene la interfaz grafica del inicio de sesión
 */
public class Gui extends JFrame {

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

    public Gui(){
        // Set the title of the window
        super("Iniciar Sesion");
        setContentPane(panelMain);
        signInButton.addActionListener(e -> {
            String userName = userfield.getText();
            char[] passwordEcrypted = passwordField.getPassword();
            String password = String.valueOf(passwordEcrypted);
            if(userName.equals("Keyla") && password.equals("1234")){
                verdictLabel.setText("Sign in successfull!");
            } else if (userName.isEmpty() || password.isEmpty()) {
                verdictLabel.setText("Some credentials are empty. Try it again");
            } else{
                verdictLabel.setText("Sign in failed. Wrong credentials. Try it again");
            }
            userfield.setText("");
            passwordField.setText("");
        });
        registerButton.addActionListener(e -> JOptionPane.showMessageDialog(null,"Work in progress..."));
    }

}
