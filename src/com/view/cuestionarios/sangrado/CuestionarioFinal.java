package com.view.cuestionarios.sangrado;

import com.controller.GenerateDiaFases;
import com.model.funciones.Menstruacion;
import com.model.usuario.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public CuestionarioFinal(Usuario usuario) {
        this.usuario = usuario;
        initializeMenstruacion();
        initializePanel();
    }

    private void initializeMenstruacion() {
        menstruacion = new Menstruacion();
        generateDiaFases = new GenerateDiaFases(menstruacion);
        menstruacion.setUsuario(usuario.getUsuario());
    }

    private void initializePanel() {
        try {
            setupSpinners();
            setupContinueButton();
        } catch (Exception e) {
            handleException(ERROR_INIT_PANEL, e);
        }
    }

    private void setupSpinners() {
        numerCiclo.setModel(new SpinnerNumberModel(28, 15, 60, 1));
        numerSangrado.setModel(new SpinnerNumberModel(5, 3, 15, 1));
    }

    private void setupContinueButton() {
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleContinueButton();
            }
        });
    }

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

    private void validateValues(int duracionCiclo, int duracionSangrado) {
        if (duracionCiclo <= 0 || duracionSangrado <= 0 || duracionSangrado > duracionCiclo) {
            throw new IllegalArgumentException(ERROR_INVALID_VALUES);
        }
    }

    private void openSelectorFecha() {
        new SelectorFecha(menstruacion);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void handleException(String message, Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, message + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
