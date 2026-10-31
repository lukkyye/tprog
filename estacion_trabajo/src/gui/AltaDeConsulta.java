package gui;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.DateTimeException;

import logica.controladores.*;
import logica.datatypes.DTDiaConsulta;
import logica.datatypes.DTMedico;
import logica.excepciones.ConsultaNombreAlreadyExistsException;
import logica.excepciones.MedicoSinEspecialidadException;
import logica.excepciones.FechaDiaSemanaInvalidaException;
import com.toedter.calendar.JDateChooser;

public class AltaDeConsulta extends JInternalFrame {
	private static final long serialVersionUID = -6141709894943702613L;
	JComboBox<String> DiasConsultasBox;
	JComboBox<String> MedicosBox;
	private JTextField nomCField;
	private JTextField salaField;
	private JTextField cantNumField;
	
	private ControladorClinica controladorClinica = new ControladorClinica();
	private ControladorUsuario controladorUsuario = new ControladorUsuario();

    public AltaDeConsulta() {
        super(
            "Alta de Consulta", // título
            true,              // se puede redimensionar
            true,              // se puede cerrar
            true,              // se puede maximizar
            true               // se puede minimizar
        );

        setSize(600, 500);
        setMaximizable(true);
		setTitle("Alta de Consulta");
		setResizable(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		DiasConsultasBox = new JComboBox<String>();
		springLayout.putConstraint(SpringLayout.NORTH, DiasConsultasBox, 40, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, DiasConsultasBox, 245, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, DiasConsultasBox, -74, SpringLayout.EAST, getContentPane());
		getContentPane().add(DiasConsultasBox);
		
		JLabel DiaConsultaLabel = new JLabel("Dias de Consulta:");
		springLayout.putConstraint(SpringLayout.NORTH, DiaConsultaLabel, 45, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, DiaConsultaLabel, -171, SpringLayout.WEST, DiasConsultasBox);
		springLayout.putConstraint(SpringLayout.EAST, DiaConsultaLabel, -21, SpringLayout.WEST, DiasConsultasBox);
		DiaConsultaLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().add(DiaConsultaLabel);
		
		MedicosBox = new JComboBox<String>();
		springLayout.putConstraint(SpringLayout.NORTH, MedicosBox, 37, SpringLayout.SOUTH, DiasConsultasBox);
		springLayout.putConstraint(SpringLayout.WEST, MedicosBox, 245, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, MedicosBox, 0, SpringLayout.EAST, DiasConsultasBox);
		getContentPane().add(MedicosBox);
		
		JLabel MedicosLabel = new JLabel("Médicos:");
		springLayout.putConstraint(SpringLayout.NORTH, MedicosLabel, 4, SpringLayout.NORTH, MedicosBox);
		springLayout.putConstraint(SpringLayout.WEST, MedicosLabel, 0, SpringLayout.WEST, DiaConsultaLabel);
		MedicosLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().add(MedicosLabel);
		
		JLabel nomCLabel = new JLabel("Nombre de la Consulta:");
		springLayout.putConstraint(SpringLayout.NORTH, nomCLabel, 246, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, nomCLabel, 0, SpringLayout.WEST, DiaConsultaLabel);
		getContentPane().add(nomCLabel);
		
		nomCField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, nomCField, 246, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, nomCField, 59, SpringLayout.EAST, nomCLabel);
		springLayout.putConstraint(SpringLayout.EAST, nomCField, 0, SpringLayout.EAST, DiasConsultasBox);
		getContentPane().add(nomCField);
		nomCField.setColumns(10);
		
		JLabel fechaLabel = new JLabel("Fecha de la Consulta:");
		springLayout.putConstraint(SpringLayout.NORTH, fechaLabel, 25, SpringLayout.SOUTH, nomCLabel);
		springLayout.putConstraint(SpringLayout.WEST, fechaLabel, 0, SpringLayout.WEST, DiaConsultaLabel);
		getContentPane().add(fechaLabel);
		
		JLabel salaLabel = new JLabel("Número de sala:");
		springLayout.putConstraint(SpringLayout.NORTH, salaLabel, 25, SpringLayout.SOUTH, fechaLabel);
		springLayout.putConstraint(SpringLayout.WEST, salaLabel, 0, SpringLayout.WEST, DiaConsultaLabel);
		getContentPane().add(salaLabel);
		
		salaField = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, salaField, 0, SpringLayout.WEST, nomCField);
		springLayout.putConstraint(SpringLayout.EAST, salaField, 0, SpringLayout.EAST, DiasConsultasBox);
		getContentPane().add(salaField);
		salaField.setColumns(10);
		
		JLabel cantNumLabel = new JLabel("Cantidad de Números:");
		springLayout.putConstraint(SpringLayout.NORTH, cantNumLabel, 25, SpringLayout.SOUTH, salaLabel);
		springLayout.putConstraint(SpringLayout.WEST, cantNumLabel, 0, SpringLayout.WEST, DiaConsultaLabel);
		getContentPane().add(cantNumLabel);
		
		cantNumField = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, salaField, -17, SpringLayout.NORTH, cantNumField);
		springLayout.putConstraint(SpringLayout.NORTH, cantNumField, 366, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, cantNumField, 67, SpringLayout.EAST, cantNumLabel);
		springLayout.putConstraint(SpringLayout.EAST, cantNumField, -74, SpringLayout.EAST, getContentPane());
		getContentPane().add(cantNumField);
		cantNumField.setColumns(10);
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(e -> dispose());
		springLayout.putConstraint(SpringLayout.SOUTH, cancelarButton, -25, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, cancelarButton, 0, SpringLayout.EAST, DiasConsultasBox);
		getContentPane().add(cancelarButton);
		
		JDateChooser dateChooser = new JDateChooser();
		springLayout.putConstraint(SpringLayout.WEST, dateChooser, 74, SpringLayout.EAST, fechaLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, dateChooser, -19, SpringLayout.NORTH, salaField);
		getContentPane().add(dateChooser);
		
		JButton aceptarButton = new JButton("Aceptar");
		springLayout.putConstraint(SpringLayout.EAST, aceptarButton, -28, SpringLayout.WEST, cancelarButton);
		springLayout.putConstraint(SpringLayout.EAST, dateChooser, 0, SpringLayout.EAST, aceptarButton);
		aceptarButton.addActionListener(e -> {
			
				   String nomD = (String) DiasConsultasBox.getSelectedItem();
				   String nicknameM = (String) MedicosBox.getSelectedItem();
				   if (nomD == null || nicknameM == null) {
					   JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un día de consulta y un médico dado de alta", "Datos insuficientes", JOptionPane.WARNING_MESSAGE);
					   return;
				   }
				   String nomC = nomCField.getText().trim();
				   LocalDate fecha=null;
				   try {
					   fecha = LocalDate.ofInstant(dateChooser.getDate().toInstant(), ZoneId.systemDefault());
				   } catch (java.lang.NullPointerException | DateTimeException ex) {
					   JOptionPane.showMessageDialog(this, "La fecha ingresada no es válida", "Fecha inválida", JOptionPane.ERROR_MESSAGE);
					   return;
				   }
				   if (nomC.isEmpty() || salaField.getText().trim().isEmpty() || cantNumField.getText().trim().isEmpty()) {
					   JOptionPane.showMessageDialog(this, "Debes completar todos los campos", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
					   return;
				   }
				   int sala, cantNum;
				   try {
					   sala = Integer.parseInt(salaField.getText().trim());
					   cantNum = Integer.parseInt(cantNumField.getText().trim());
				   } catch (NumberFormatException ex) {
					   JOptionPane.showMessageDialog(this, "Sala, Cantidad de números y Fecha deben ser valores numéricos", "Dato inválido", JOptionPane.ERROR_MESSAGE);
					   return;
				   }
				   
				   try {
					   controladorClinica.ingresarConsulta(nomD, nicknameM, nomC, fecha, sala, cantNum);
					   JOptionPane.showMessageDialog(this, "Consulta dada de alta correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				   } catch (ConsultaNombreAlreadyExistsException ex) {
					   JOptionPane.showMessageDialog(this, ex.getMessage(), "Nombre en uso", JOptionPane.ERROR_MESSAGE);
				   } catch (MedicoSinEspecialidadException ex) {
					   JOptionPane.showMessageDialog(this, ex.getMessage(), "Especialidad no coincide", JOptionPane.ERROR_MESSAGE);
				   } catch (FechaDiaSemanaInvalidaException ex) {
					   JOptionPane.showMessageDialog(this, ex.getMessage(), "Fecha inválida", JOptionPane.ERROR_MESSAGE);
				   }
				   
			   
		});
		springLayout.putConstraint(SpringLayout.SOUTH, aceptarButton, 0, SpringLayout.SOUTH, cancelarButton);
		getContentPane().add(aceptarButton);
		
		
		cargarCombos();
   
   
    }
    
   private void cargarCombos() {
	   for (DTDiaConsulta d : controladorClinica.listarDatosDiaConsulta()) {
		   DiasConsultasBox.addItem(d.getNomD());
	   }
	   for (DTMedico m : controladorUsuario.listarMedicos()) {
		   MedicosBox.addItem(m.getNickname());
	   }
   }
}

