package com.view.cuestionarios.gui;

import com.controller.InformeBuilder;
import com.database.Database;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        public Usuario usuario = new Usuario();
        public Informe informe = new Informe();

        private void insertDataIntoDatabase() {
            usuario.setNombre(fieldName.getText());
            usuario.setEdad((Integer) spinnerAge.getValue());
            usuario.setUsuario(fieldUser.getText());
            usuario.setEmail(fieldMail.getText());
            usuario.setContrasena(new String(fieldPassword.getPassword()));

            // Establecer los mismos datos del usuario en el informe
            informe.setNombre(usuario.getNombre());
            informe.setEdad(usuario.getEdad());
            // Añadir el resto de campos de informe que quieras configurar

            InformeBuilder informeBuilder = new InformeBuilder();
            informeBuilder.fromUsuario(usuario.getUsuario());

            String sql = "INSERT INTO usuario (usuario, nombre, contrasenha, email, edad) VALUES (?, ?, ?, ?, ?)";

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

        private boolean isUserExisting(String username) {
            String sql = "SELECT COUNT(*) FROM usuario WHERE usuario = ?";
            try (Connection conn = Database.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        private boolean validateFields() {
            if (fieldName.getText().isEmpty() || fieldUser.getText().isEmpty() ||
                    fieldMail.getText().isEmpty() || new String(fieldPassword.getPassword()).isEmpty() ||
                    new String(fieldPasswordConfirm.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (fieldUser.getText().length() < 8) {
                JOptionPane.showMessageDialog(this, "El usuario debe tener al menos 8 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (new String(fieldPassword.getPassword()).length() < 8) {
                JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 8 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!new String(fieldPassword.getPassword()).equals(new String(fieldPasswordConfirm.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            String email = fieldMail.getText();
            if (!email.contains("@") || !email.endsWith(".com") || email.endsWith(".org")) {
                JOptionPane.showMessageDialog(this, "El correo electrónico debe contener '@' y terminar con '.com'", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (isUserExisting(fieldUser.getText())) {
                JOptionPane.showMessageDialog(this, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return true;
        }

        private void sendEmail(String to, String subject, String content) {
            // Propiedades de la configuración de correo electrónico
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com"); // Cambia esto si usas otro proveedor de correo
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            // Autenticación
            String username = "tucorrreo"; // Cambia esto por tu dirección de correo electrónico
            String password = "tucontraseña"; // Cambia esto por tu contraseña

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject(subject);
                message.setText(content);

                Transport.send(message);
                System.out.println("Correo enviado exitosamente");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        public Register() {
            super("Registro");
            setContentPane(panelMainR);

            // Inicializar spinnerAge con valor inicial 10
            spinnerAge.setModel(new SpinnerNumberModel(10, 10, 100, 1));

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
            });

            continuarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (validateFields()) {
                            insertDataIntoDatabase();
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        dispose();  // Cierra la ventana Register
                                        JFrame frame = new JFrame("Cuestionario Final");
                                        frame.setSize(600, 400);
                                        frame.setLocationRelativeTo(null);
                                        frame.setVisible(true);
                                        CuestionarioFinal cuestionario = new CuestionarioFinal(usuario);
                                        frame.getContentPane().add(cuestionario.panelPrincipal);
                                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                        JOptionPane.showMessageDialog(null, "Error al abrir la ventana Cuestionario " + ex.getMessage());
                                    }
                                }
                            });
                            informe.setUsuario(usuario.getUsuario());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            getRootPane().setDefaultButton(continuarButton);

        }
    }

