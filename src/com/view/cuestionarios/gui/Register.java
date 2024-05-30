package com.view.cuestionarios.gui;

import com.database.DatabaseHandlerUsuario;
import com.database.InsertDatabaseUsuario;
import com.model.decoracion.RoundedBorder;
import com.model.funciones.Informe;
import com.model.usuario.Usuario;
import com.view.cuestionarios.sangrado.CuestionarioFinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * La clase {@code Register} representa una ventana de registro de usuario.
 * Permite al usuario ingresar sus datos personales y crear una cuenta.
 */
public class Register extends JFrame {
    private static final String ERROR_MSG = "Error";
    private static final String FIELD_EMPTY_MSG = "Todos los campos son obligatorios";
    private static final String USER_MIN_LENGTH_MSG = "El usuario debe tener al menos 8 caracteres";
    private static final String PASSWORD_MIN_LENGTH_MSG = "La contraseña debe tener al menos 8 caracteres";
    private static final String PASSWORD_MISMATCH_MSG = "Las contraseñas no coinciden";
    private static final String EMAIL_FORMAT_MSG = "El correo electrónico debe contener '@' y terminar con '.com'";
    private static final String USER_EXISTS_MSG = "El usuario ya existe";

    private JPanel panelMainR;
    private JTextField fieldName;
    private JSpinner spinnerAge;
    private JTextField fieldUser;
    private JTextField fieldMail;
    private JPasswordField fieldPassword;
    private JPasswordField fieldPasswordConfirm;
    private JButton buttonVolver;
    private JButton continuarButton;
    private Usuario usuario = new Usuario();
    private Informe informe = new Informe();

    private InsertDatabaseUsuario insertDatabaseUsuario = new InsertDatabaseUsuario();
    private DatabaseHandlerUsuario databaseHandlerUsuario = new DatabaseHandlerUsuario();

    /**
     * Constructor de la clase {@code Register}.
     * Configura la interfaz gráfica y añade los listeners a los botones.
     */
    public Register() {
        super("Registro");
        setContentPane(panelMainR);
        initUI();
        addListeners();
    }

    /**
     * Inicializa los componentes de la interfaz de usuario.
     */
    private void initUI() {
        spinnerAge.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        Font font = new Font("Times New Roman", Font.PLAIN, 12);
        buttonVolver.setFont(font);
        continuarButton.setFont(font);
        buttonVolver.setBorder(new RoundedBorder(10));
        continuarButton.setBorder(new RoundedBorder(10));
    }

    /**
     * Añade los listeners a los botones de la interfaz.
     */
    private void addListeners() {
        buttonVolver.addActionListener(e -> goBack());
        continuarButton.addActionListener(e -> proceedRegistration());
    }

    /**
     * Regresa a la pantalla de inicio de sesión.
     */
    private void goBack() {
        dispose();
        JFrame frame = new SignIn();
        frame.setSize(1200, 570);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Procede con el registro del usuario si los campos son válidos.
     */
    private void proceedRegistration() {
        try {
            if (validateFields()) {
                insertDatabaseUsuario.insertDataIntoDatabase(usuario);
                SwingUtilities.invokeLater(this::openCuestionarioFinal);
                informe.setUsuario(usuario.getUsuario());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Abre la ventana del cuestionario final.
     */
    private void openCuestionarioFinal() {
        try {
            dispose();
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

    /**
     * Valida los campos de entrada en el formulario de registro.
     *
     * @return true si todos los campos son válidos, de lo contrario false.
     */
    private boolean validateFields() {
        if (isFieldEmpty(fieldName) || isFieldEmpty(fieldUser) || isFieldEmpty(fieldMail) ||
                isPasswordEmpty(fieldPassword) || isPasswordEmpty(fieldPasswordConfirm)) {
            showError(FIELD_EMPTY_MSG);
            return false;
        }

        if (fieldUser.getText().length() < 8) {
            showError(USER_MIN_LENGTH_MSG);
            return false;
        }

        if (new String(fieldPassword.getPassword()).length() < 8) {
            showError(PASSWORD_MIN_LENGTH_MSG);
            return false;
        }

        if (!new String(fieldPassword.getPassword()).equals(new String(fieldPasswordConfirm.getPassword()))) {
            showError(PASSWORD_MISMATCH_MSG);
            return false;
        }

        String email = fieldMail.getText();
        if (!email.contains("@") || !email.endsWith(".com")) {
            showError(EMAIL_FORMAT_MSG);
            return false;
        }

        if (databaseHandlerUsuario.isUserExisting(fieldUser.getText())) {
            showError(USER_EXISTS_MSG);
            return false;
        }
        return true;
    }

    /**
     * Verifica si un campo de texto está vacío.
     *
     * @param field El campo de texto a verificar.
     * @return true si el campo está vacío, de lo contrario false.
     */
    private boolean isFieldEmpty(JTextField field) {
        return field.getText().isEmpty();
    }

    /**
     * Verifica si un campo de contraseña está vacío.
     *
     * @param field El campo de contraseña a verificar.
     * @return true si el campo está vacío, de lo contrario false.
     */
    private boolean isPasswordEmpty(JPasswordField field) {
        return new String(field.getPassword()).isEmpty();
    }

    /**
     * Muestra un mensaje de error en un cuadro de diálogo.
     *
     * @param message El mensaje de error a mostrar.
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, ERROR_MSG, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Método principal para iniciar la aplicación de registro.
     *
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new Register();
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        });
    }
}
