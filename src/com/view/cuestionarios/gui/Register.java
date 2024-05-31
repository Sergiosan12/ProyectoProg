package com.view.cuestionarios.gui;

import com.controller.InformeBuilder;
import com.database.InsertDatabaseUsuario;
import com.model.decoracion.RoundedBorder;
import com.model.funciones.Informe;
import com.model.usuario.Usuario;
import com.view.cuestionarios.sangrado.CuestionarioFinal;

import javax.swing.*;
import java.awt.*;

/**
 * La clase Register proporciona una interfaz gráfica para registrar un nuevo usuario.
 * Incluye validaciones de campos y la inserción de datos en la base de datos.
 */
public class Register extends JFrame {
    // Constantes de mensajes de error
    private static final String ERROR_MSG_ALL_FIELDS_REQUIRED = "Todos los campos son obligatorios";
    private static final String ERROR_MSG_USERNAME_LENGTH = "El usuario debe tener al menos 8 caracteres";
    private static final String ERROR_MSG_PASSWORD_LENGTH = "La contraseña debe tener al menos 8 caracteres";
    private static final String ERROR_MSG_PASSWORD_MISMATCH = "Las contraseñas no coinciden";
    private static final String ERROR_MSG_INVALID_EMAIL = "El correo electrónico debe contener '@' y terminar con '.com'";
    private static final String ERROR_MSG_USER_EXISTS = "El usuario ya existe";

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
    private final InsertDatabaseUsuario insertDatabaseUsuario = new InsertDatabaseUsuario();

    /**
     * Constructor de la clase Register.
     * Configura el contenido y los componentes de la ventana de registro.
     */
    public Register() {
        super("Registro");
        setContentPane(panelMainR);
        initializeComponents();
        addEventListeners();
        getRootPane().setDefaultButton(continuarButton);
    }

    /**
     * Inicializa los componentes de la ventana de registro.
     * Configura el spinner de edad y los estilos de los botones.
     */
    private void initializeComponents() {
        // Inicializar spinnerAge con valor inicial 10
        spinnerAge.setModel(new SpinnerNumberModel(10, 0, 100, 1));

        // Establecer la fuente de los botones a Times New Roman
        Font font = new Font("Times New Roman", Font.PLAIN, 12);
        buttonVolver.setFont(font);
        continuarButton.setFont(font);

        // Establecer los bordes de los botones a redondos
        buttonVolver.setBorder(new RoundedBorder(10)); // 10 is the radius
        continuarButton.setBorder(new RoundedBorder(10)); // 10 is the radius

    }

    /**
     * Añade los event listeners a los botones.
     */
    private void addEventListeners() {
        buttonVolver.addActionListener(e -> navigateToSignIn());
        continuarButton.addActionListener(e -> handleContinueAction());
    }

    /**
     * Navega a la ventana de inicio de sesión.
     * Cierra la ventana de registro actual.
     */
    private void navigateToSignIn() {
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

    /**
     * Maneja la acción del botón continuar.
     * Valida los campos y, si son válidos, inserta los datos en la base de datos y navega al cuestionario final.
     */
    private void handleContinueAction() {
        try {
            if (validateFields()) {
                insertDataIntoDatabase();
                navigateToCuestionarioFinal();
                informe.setUsuario(usuario.getUsuario());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Inserta los datos del usuario en la base de datos.
     */
    private void insertDataIntoDatabase() {
        usuario.setNombre(fieldName.getText());
        usuario.setEdad((Integer) spinnerAge.getValue());
        usuario.setUsuario(fieldUser.getText());
        usuario.setEmail(fieldMail.getText());
        usuario.setContrasena(new String(fieldPassword.getPassword()));

        informe.setNombre(usuario.getNombre());
        informe.setEdad(usuario.getEdad());

        InformeBuilder informeBuilder = new InformeBuilder();
        informeBuilder.fromUsuario(usuario.getUsuario());

        insertDatabaseUsuario.insertDataIntoDatabase(usuario);
    }

    /**
     * Navega a la ventana del cuestionario final.
     * Cierra la ventana de registro actual.
     */
    private void navigateToCuestionarioFinal() {
        SwingUtilities.invokeLater(() -> {
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
        });
    }

    /**
     * Valida los campos del formulario de registro.
     *
     * @return true si todos los campos son válidos, false en caso contrario.
     */
    private boolean validateFields() {
        if (isAnyFieldEmpty()) {
            showErrorMessage(ERROR_MSG_ALL_FIELDS_REQUIRED);
            return false;
        }

        if (!isUsernameValid()) {
            showErrorMessage(ERROR_MSG_USERNAME_LENGTH);
            return false;
        }

        if (!isPasswordValid()) {
            showErrorMessage(ERROR_MSG_PASSWORD_LENGTH);
            return false;
        }

        if (!doPasswordsMatch()) {
            showErrorMessage(ERROR_MSG_PASSWORD_MISMATCH);
            return false;
        }

        if (!isEmailValid()) {
            showErrorMessage(ERROR_MSG_INVALID_EMAIL);
            return false;
        }

        if (isUserExisting()) {
            showErrorMessage(ERROR_MSG_USER_EXISTS);
            return false;
        }

        return true;
    }

    /**
     * Verifica si algún campo del formulario está vacío.
     *
     * @return true si algún campo está vacío, false en caso contrario.
     */
    private boolean isAnyFieldEmpty() {
        return fieldName.getText().isEmpty() ||
                fieldUser.getText().isEmpty() ||
                fieldMail.getText().isEmpty() ||
                new String(fieldPassword.getPassword()).isEmpty() ||
                new String(fieldPasswordConfirm.getPassword()).isEmpty();
    }

    /**
     * Verifica si el nombre de usuario es válido (al menos 8 caracteres).
     *
     * @return true si el nombre de usuario es válido, false en caso contrario.
     */
    private boolean isUsernameValid() {
        return fieldUser.getText().length() >= 8;
    }

    /**
     * Verifica si la contraseña es válida (al menos 8 caracteres).
     *
     * @return true si la contraseña es válida, false en caso contrario.
     */
    private boolean isPasswordValid() {
        return new String(fieldPassword.getPassword()).length() >= 8;
    }

    /**
     * Verifica si las contraseñas coinciden.
     *
     * @return true si las contraseñas coinciden, false en caso contrario.
     */
    private boolean doPasswordsMatch() {
        return new String(fieldPassword.getPassword()).equals(new String(fieldPasswordConfirm.getPassword()));
    }

    /**
     * Verifica si el correo electrónico es válido (contiene '@' y termina con '.com').
     *
     * @return true si el correo electrónico es válido, false en caso contrario.
     */
    private boolean isEmailValid() {
        String email = fieldMail.getText();
        return email.contains("@") && email.endsWith(".com");
    }

    /**
     * Verifica si el usuario ya existe en la base de datos.
     *
     * @return true si el usuario ya existe, false en caso contrario.
     */
    private boolean isUserExisting() {
        return insertDatabaseUsuario.isUserExisting(fieldUser.getText());
    }

    /**
     * Muestra un mensaje de error en un cuadro de diálogo.
     *
     * @param message el mensaje de error a mostrar.
     */
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
