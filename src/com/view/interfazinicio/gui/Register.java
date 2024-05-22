package com.view.interfazinicio.gui;

import com.model.RoundedBorder;
import com.view.cuestionarios.sangrado.CuestionarioFInal;
import com.database.Database;
import com.model.Usuario;

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
   public Usuario usuario= new Usuario();


    private void insertDataIntoDatabase() {
     usuario.setNombre(fieldName.getText());
        usuario.setEdad((Integer) spinnerAge.getValue());
        usuario.setUsuario(fieldUser.getText());
        usuario.setEmail(fieldMail.getText());
        usuario.setContrasena(new String(fieldPassword.getPassword()));

        String sql = "INSERT INTO usuario (usuario, nombre, contrasenha, email,edad ) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getUsuario());
            pstmt.setString(2, usuario.getNombre());
            pstmt.setString(3, usuario.getContrasena());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setInt(5, usuario.getEdad());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                            CuestionarioFInal cuestionario = new CuestionarioFInal(usuario);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Register register = new Register();
            register.setSize(600, 400);  // Establece el tamaño de la ventana
            register.setVisible(true);
            register.setLocationRelativeTo(null);
            register.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        });
    }
}
