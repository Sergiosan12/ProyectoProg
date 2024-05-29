package com.view.funciones.deporte;

import com.controller.GenerarPDF;
import com.controller.InformeBuilder;
import com.database.DatabaseHandlerDeporte;
import com.database.InsertaDatabaseDeportes_usuario;
import com.model.funciones.Informe;
import com.model.funciones.Menstruacion;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class InterfazDeporte{
    private JPanel panel1;
    private static final int OPCION_DEPORTE = 2;
    Menstruacion menstruacion;
    InformeBuilder informeBuilder=new InformeBuilder() ;

    public InterfazDeporte(Menstruacion menstruacion) {
        this.menstruacion = menstruacion;
    }

    public void showDeportesGUI(JFrame previousFrame) {
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseHandlerDeporte dbHandler = new DatabaseHandlerDeporte();
                JFrame frame = new JFrame("Fases y Deportes");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.getContentPane().setBackground(Color.decode("#FFF1F1"));

                // Crear un panel para la pregunta y JComboBox
                JPanel leftPanel = new JPanel(new BorderLayout());
                leftPanel.setBackground(Color.decode("#FFF1F1"));

                // Crear un panel superior con la pregunta
                JPanel topPanel = new JPanel();
                topPanel.setBackground(Color.decode("#FFF1F1"));
                JLabel questionLabel = new JLabel("¿Qué deporte prefieres para cada fase?");
                topPanel.add(questionLabel);
                leftPanel.add(topPanel, BorderLayout.NORTH);

                // Crear un panel central para los JComboBox
                JPanel centerPanel = new JPanel();
                centerPanel.setLayout(new GridLayout(4, 1));
                centerPanel.setBackground(Color.decode("#FFF1F1"));

                // Obtener los nombres de las fases
                List<String> fases = dbHandler.getFases();

                // Crear un HashMap para almacenar los JComboBox y sus fases correspondientes
                HashMap<String, JComboBox<String>> comboBoxes = new HashMap<>();

                // Crear y agregar los JComboBox al panel central y al HashMap
                for (String fase : fases) {
                    List<String> deportes = dbHandler.getDeportesPorFase(fase);
                    JComboBox<String> comboBox = new JComboBox<>(deportes.toArray(new String[0]));
                    comboBox.setBorder(BorderFactory.createTitledBorder(fase));
                    comboBox.setBackground(Color.decode("#FFF1F1"));
                    if (comboBox.getItemCount() > 0) {  // Check if the JComboBox is not empty
                        comboBox.setSelectedIndex(0);  // Set the first item as the default selected item
                    }
                    centerPanel.add(comboBox);
                    comboBoxes.put(fase, comboBox);  // Agregar el JComboBox y su fase al HashMap
                }

                leftPanel.add(centerPanel, BorderLayout.CENTER);

                // Agregar el panel izquierdo al frame
                frame.add(leftPanel, BorderLayout.WEST);

                // Crear un panel para la imagen
                JPanel rightPanel = new JPanel(new BorderLayout());
                rightPanel.setBackground(Color.decode("#FFF1F1"));

                // Cargar y mostrar la imagen
                ImageIcon imageIcon = new ImageIcon("src/com/img/deportes2.jpg"); // Cambia la ruta por la ruta de tu imagen
                JLabel imageLabel = new JLabel(imageIcon);
                rightPanel.add(imageLabel, BorderLayout.CENTER);

                // Agregar el panel derecho al frame
                frame.add(rightPanel, BorderLayout.CENTER);

                // Crear un panel inferior con los botones "Volver" y "Generar Informe"
                JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                bottomPanel.setBackground(Color.decode("#FFF1F1"));

                JButton backButton = new JButton("Volver");
                backButton.setBackground(Color.decode("#F6C4F6"));
                backButton.addActionListener(e -> {
                    frame.dispose();  // Cerrar la ventana actual
                    previousFrame.setVisible(true);  // Volver a la ventana anterior
                });
                bottomPanel.add(backButton);

                JButton continueButton = new JButton("Generar Informe");
                continueButton.setBackground(Color.decode("#F6C4F6"));
                continueButton.addActionListener(e -> {
                    // Recoger las respuestas del usuario
                    String deporteFaseMenstrual = null;
                    String deporteFaseFolicular = null;
                    String deporteFaseOvulacion = null;
                    String deporteFaseLutea = null;

                    JComboBox<String> comboBoxMenstrual = comboBoxes.get("Menstrual");
                    JComboBox<String> comboBoxFolicular = comboBoxes.get("Folicular");
                    JComboBox<String> comboBoxOvulacion = comboBoxes.get("Ovulación");
                    JComboBox<String> comboBoxLutea = comboBoxes.get("Lútea");
                    String usuario=menstruacion.getUsuario();

                    if (comboBoxMenstrual != null && comboBoxMenstrual.getSelectedItem() != null) {
                        deporteFaseMenstrual = (String) comboBoxMenstrual.getSelectedItem();
                    }
                    if (comboBoxFolicular != null && comboBoxFolicular.getSelectedItem() != null) {
                        deporteFaseFolicular = (String) comboBoxFolicular.getSelectedItem();
                    }
                    if (comboBoxOvulacion != null && comboBoxOvulacion.getSelectedItem() != null) {
                        deporteFaseOvulacion = (String) comboBoxOvulacion.getSelectedItem();
                    }
                    if (comboBoxLutea != null && comboBoxLutea.getSelectedItem() != null) {
                        deporteFaseLutea = (String) comboBoxLutea.getSelectedItem();
                    }
                    // Insertar los valores en la base de datos
                    InsertaDatabaseDeportes_usuario dbDeportesUsuario = new InsertaDatabaseDeportes_usuario();
                    dbDeportesUsuario.insertDeportesUsuario(usuario, deporteFaseMenstrual, deporteFaseFolicular, deporteFaseOvulacion, deporteFaseLutea);

                   informeBuilder.withDeportes(usuario);
                    GenerarPDF generarPDF = new GenerarPDF(menstruacion);
                    // Llama al método para generar el informe
                    generarPDF.generarInforme(OPCION_DEPORTE);
                    JOptionPane.showMessageDialog(panel1, "Pdf generado correctamente");


                });

                bottomPanel.add(continueButton);

                frame.add(bottomPanel, BorderLayout.SOUTH);

                // Configurar el frame y hacerlo visible
                frame.pack();
                frame.setSize(800, 400);
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);


                // Ocultar la ventana anterior
                previousFrame.setVisible(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
