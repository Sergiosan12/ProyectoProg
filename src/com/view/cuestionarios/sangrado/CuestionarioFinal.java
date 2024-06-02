package com.view.cuestionarios.sangrado;

import com.controller.GenerateDiaFases;
import com.model.funciones.Menstruacion;
import com.model.usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase CuestionarioFinal proporciona una interfaz gráfica para que el usuario ingrese la duración de su ciclo menstrual y el número de días de sangrado.
 * Una vez que se ingresan los valores, la información se procesa y se guarda.
 */
public class CuestionarioFinal {
    private static final String ERROR_INIT_QUESTIONNAIRE = "Error al inicializar el cuestionario: ";
    private static final String ERROR_INIT_PANEL = "Error al inicializar el panel: ";
    private static final String ERROR_INVALID_VALUES = "Por favor, introduce valores válidos.";
    private static final String ERROR_INVALID_NUMBERS = "Por favor, introduce números válidos.";
    private static final String ERROR_PROCESS_DATA = "Error al procesar los datos: ";

    public JPanel panelPrincipal;
    private JLabel lblCiclo;
    private JLabel lblSangrado;
    private JButton continuarButton;
    private JSpinner numerCiclo;
    private JSpinner numerSangrado;
    private Usuario usuario;
    private Menstruacion menstruacion;
    private GenerateDiaFases generateDiaFases;

    /**
     * Constructor de la clase CuestionarioFinal.
     *
     * @param usuario el objeto Usuario que contiene la información del usuario.
     */
    public CuestionarioFinal(Usuario usuario) {
        this.usuario = usuario;
        initializeMenstruacion();
        initializePanel();
    }

    /**
     * Inicializa el objeto Menstruacion y configura el objeto GenerateDiaFases.
     */
    private void initializeMenstruacion() {
        menstruacion = new Menstruacion();
        generateDiaFases = new GenerateDiaFases(menstruacion);
        menstruacion.setUsuario(usuario.getUsuario());
    }

    /**
     * Inicializa el panel de la interfaz gráfica.
     */
    private void initializePanel() {
        try {
            setupSpinners();
            setupContinueButton();
        } catch (Exception e) {
            handleException(ERROR_INIT_PANEL, e);
        }
    }

    /**
     * Configura los JSpinners para ingresar la duración del ciclo y la duración del sangrado.
     */
    private void setupSpinners() {
        numerCiclo.setModel(new SpinnerNumberModel(28, 15, 60, 1));
        numerSangrado.setModel(new SpinnerNumberModel(5, 3, 15, 1));
    }

    /**
     * Configura el botón "Continuar" para manejar el evento de clic.
     */
    private void setupContinueButton() {
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleContinueButton();
            }
        });
    }

    /**
     * Maneja la acción del botón "Continuar". Valida los valores ingresados, actualiza la información y abre el selector de fecha.
     */
    private void handleContinueButton() {
        try {
            int duracionCiclo = (Integer) numerCiclo.getValue();
            int duracionSangrado = (Integer) numerSangrado.getValue();
            validateValues(duracionCiclo, duracionSangrado);
            generateDiaFases.CambiarDiasSangrado(duracionSangrado);
            generateDiaFases.CambiarDiasCiclo(duracionCiclo);
            openSelectorFecha();
        } catch (NumberFormatException ex) {
            showMessage(ERROR_INVALID_NUMBERS);
        } catch (IllegalArgumentException ex) {
            showMessage(ex.getMessage());
        } catch (Exception ex) {
            handleException(ERROR_PROCESS_DATA, ex);
        }
    }

    /**
     * Valida los valores ingresados para la duración del ciclo y la duración del sangrado.
     *
     * @param duracionCiclo la duración del ciclo.
     * @param duracionSangrado la duración del sangrado.
     * @throws IllegalArgumentException si los valores no son válidos.
     */
    private void validateValues(int duracionCiclo, int duracionSangrado) {
        if (duracionCiclo <= 0 || duracionSangrado <= 0 || duracionSangrado > duracionCiclo) {
            throw new IllegalArgumentException(ERROR_INVALID_VALUES);
        }
    }

    /**
     * Abre la ventana del selector de fecha.
     */
    private void openSelectorFecha() {
        new SelectorFecha(menstruacion);
    }

    /**
     * Muestra un mensaje emergente con un mensaje de error.
     *
     * @param message el mensaje de error a mostrar.
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Maneja las excepciones mostrando un cuadro de diálogo con el mensaje de error.
     *
     * @param message el mensaje de error a mostrar.
     * @param e la excepción que se ha producido.
     */
    private void handleException(String message, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, message + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
