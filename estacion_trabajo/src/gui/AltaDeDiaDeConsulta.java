package gui;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import logica.datatypes.DtDiaSemana;

import logica.controladores.ControladorClinica;
import logica.excepciones.DiaConsultaAlreadyExistsException;

public class AltaDeDiaDeConsulta extends JInternalFrame {

    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtHoraInicio;

    private JComboBox<DtDiaSemana> comboDiaSemana;
    private JComboBox<String> comboEspecialidad;
    private JComboBox<String> comboClinica;

    private JButton btnAceptar;
    private JButton btnCancelar;
   


    public AltaDeDiaDeConsulta() {

        super(
            "Alta de Dia de Consulta", // título
            true,              // se puede redimensionar
            true,              // se puede cerrar
            true,              // se puede maximizar
            true               // se puede minimizar
        );

        setSize(600, 500);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 77, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblNewLabel = new JLabel("Nombre: ");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 3;
        gbc_lblNewLabel.gridy = 1;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        txtNombre = new JTextField();
        GridBagConstraints gbc_txtNombre = new GridBagConstraints();
        gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
        gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtNombre.gridx = 5;
        gbc_txtNombre.gridy = 1;
        getContentPane().add(txtNombre, gbc_txtNombre);
        txtNombre.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Descripción: ");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 3;
        gbc_lblNewLabel_1.gridy = 2;
        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        txtDescripcion = new JTextField();
        GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
        gbc_txtDescripcion.insets = new Insets(0, 0, 5, 5);
        gbc_txtDescripcion.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtDescripcion.gridx = 5;
        gbc_txtDescripcion.gridy = 2;
        getContentPane().add(txtDescripcion, gbc_txtDescripcion);
        txtDescripcion.setColumns(10);
        
        JLabel lblNewLabel_1_1 = new JLabel("Día de la semana: ");
        GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1.gridx = 3;
        gbc_lblNewLabel_1_1.gridy = 3;
        getContentPane().add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
        
        comboDiaSemana = new JComboBox();
        GridBagConstraints gbc_comboDiaSemana = new GridBagConstraints();
        gbc_comboDiaSemana.insets = new Insets(0, 0, 5, 5);
        gbc_comboDiaSemana.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboDiaSemana.gridx = 5;
        gbc_comboDiaSemana.gridy = 3;
        getContentPane().add(comboDiaSemana, gbc_comboDiaSemana);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Hora de inicio:");
        GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_1.gridx = 3;
        gbc_lblNewLabel_1_1_1.gridy = 4;
        getContentPane().add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
        
        txtHoraInicio = new JTextField();
        txtHoraInicio.setText("HH:mm");
        GridBagConstraints gbc_txtHoraInicio = new GridBagConstraints();
        gbc_txtHoraInicio.insets = new Insets(0, 0, 5, 5);
        gbc_txtHoraInicio.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtHoraInicio.gridx = 5;
        gbc_txtHoraInicio.gridy = 4;
        getContentPane().add(txtHoraInicio, gbc_txtHoraInicio);
        txtHoraInicio.setColumns(10);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Especialidad: ");
        GridBagConstraints gbc_lblNewLabel_1_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_1_1.gridx = 3;
        gbc_lblNewLabel_1_1_1_1.gridy = 5;
        getContentPane().add(lblNewLabel_1_1_1_1, gbc_lblNewLabel_1_1_1_1);
        
        comboEspecialidad = new JComboBox();
        GridBagConstraints gbc_comboEspecialidad = new GridBagConstraints();
        gbc_comboEspecialidad.insets = new Insets(0, 0, 5, 5);
        gbc_comboEspecialidad.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboEspecialidad.gridx = 5;
        gbc_comboEspecialidad.gridy = 5;
        getContentPane().add(comboEspecialidad, gbc_comboEspecialidad);
        
        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Clínica: ");
        GridBagConstraints gbc_lblNewLabel_1_1_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_1_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1_1_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_1_1_1.gridx = 3;
        gbc_lblNewLabel_1_1_1_1_1.gridy = 6;
        getContentPane().add(lblNewLabel_1_1_1_1_1, gbc_lblNewLabel_1_1_1_1_1);
        
        comboClinica = new JComboBox();
        GridBagConstraints gbc_comboClinica = new GridBagConstraints();
        gbc_comboClinica.insets = new Insets(0, 0, 5, 5);
        gbc_comboClinica.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboClinica.gridx = 5;
        gbc_comboClinica.gridy = 6;
        getContentPane().add(comboClinica, gbc_comboClinica);
        
        btnAceptar = new JButton("Aceptar");
        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
        gbc_btnAceptar.gridx = 4;
        gbc_btnAceptar.gridy = 10;
        getContentPane().add(btnAceptar, gbc_btnAceptar);
        
        btnCancelar = new JButton("Cancelar");
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.anchor = GridBagConstraints.WEST;
        gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
        gbc_btnCancelar.gridx = 5;
        gbc_btnCancelar.gridy = 10;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
        
        btnCancelar.addActionListener(e -> dispose());
        
        comboDiaSemana.setModel(
        	    new DefaultComboBoxModel<DtDiaSemana>(
        	        DtDiaSemana.values()
        	    )
        	);
        
        ControladorClinica controlador =
                new ControladorClinica();


        // Cargar días de la semana
        comboDiaSemana.setModel(
                new DefaultComboBoxModel<DtDiaSemana>(
                        DtDiaSemana.values()
                )
        );


        // Cargar especialidades
        comboEspecialidad.removeAllItems();

        for (String especialidad : controlador.listarEspecialidades()) {
            comboEspecialidad.addItem(especialidad);
        }


        // Cargar clínicas
        comboClinica.removeAllItems();

        for (String clinica : controlador.listarClinicas()) {
            comboClinica.addItem(clinica);
        }
        
        btnAceptar.addActionListener(e -> {

            String nombre =
                    txtNombre.getText().trim();

            String descripcion =
                    txtDescripcion.getText().trim();

            String horaTexto =
                    txtHoraInicio.getText().trim();

            DtDiaSemana dia =
                    (DtDiaSemana) comboDiaSemana.getSelectedItem();

            String especialidad =
                    (String) comboEspecialidad.getSelectedItem();

            String clinica =
                    (String) comboClinica.getSelectedItem();


            // Validar campos
            if (nombre.isEmpty()
                    || descripcion.isEmpty()
                    || horaTexto.isEmpty()
                    || horaTexto.equals("HH:mm")
                    || dia == null
                    || especialidad == null
                    || clinica == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Complete todos los campos.",
                        "Datos incompletos",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // Convertir la hora
            LocalTime horaInicio;

            try {

                horaInicio =
                        LocalTime.parse(horaTexto);

            } catch (DateTimeParseException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "La hora debe ingresarse con formato HH:mm.\nEjemplo: 14:30",
                        "Hora inválida",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            // Crear Día de Consulta
            try {

                controlador.ingresarDiaConsulta(
                        nombre,
                        descripcion,
                        dia,
                        horaInicio,
                        especialidad,
                        clinica
                );


                JOptionPane.showMessageDialog(
                        this,
                        "Día de consulta creado correctamente.",
                        "Alta exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );


                dispose();


            } catch (DiaConsultaAlreadyExistsException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Nombre ya existente",
                        JOptionPane.ERROR_MESSAGE
                );

            }

            }); // cierra el listener del botón Aceptar


            } // cierra constructor


            } // cierra clase

