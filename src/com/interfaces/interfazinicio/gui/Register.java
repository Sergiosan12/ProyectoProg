package com.interfaces.interfazinicio.gui;

import com.interfaces.interfazinicio.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public Register(){
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
    }
}