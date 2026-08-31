package gui;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;

import logica.excepciones.EspecialidadAlreadyExistsException;
import logica.manejadores.ManejadorEspecialidad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AltaDeEspecialidad extends JInternalFrame {
	private static final long serialVersionUID = -6141709894943702613L;
	private JTextField espNomField;
	private JTextField espPorcField;

	public AltaDeEspecialidad() {
		super(
				"Alta de Especialidad", // título
				true,              // se puede redimensionar
				true,              // se puede cerrar
				true,              // se puede maximizar
				true               // se puede minimizar
				);
		
		setSize(600, 500);
	
		setMaximizable(true);
		setTitle("Alta de Especialidad");
		setResizable(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel espNomLabel = new JLabel("Nombre de la Especialidad:");
		springLayout.putConstraint(SpringLayout.NORTH, espNomLabel, 57, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, espNomLabel, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(espNomLabel);
		
		JLabel espPorcLabel = new JLabel("Porcentaje de la Especialidad:");
		springLayout.putConstraint(SpringLayout.NORTH, espPorcLabel, 23, SpringLayout.SOUTH, espNomLabel);
		springLayout.putConstraint(SpringLayout.WEST, espPorcLabel, 0, SpringLayout.WEST, espNomLabel);
		getContentPane().add(espPorcLabel);
		
		espNomField = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, espNomField, 0, SpringLayout.SOUTH, espNomLabel);
		springLayout.putConstraint(SpringLayout.EAST, espNomField, -50, SpringLayout.EAST, getContentPane());
		getContentPane().add(espNomField);
		espNomField.setColumns(10);
		
		espPorcField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, espPorcField, -2, SpringLayout.NORTH, espPorcLabel);
		springLayout.putConstraint(SpringLayout.EAST, espPorcField, 0, SpringLayout.EAST, espNomField);
		getContentPane().add(espPorcField);
		espPorcField.setColumns(10);
		
		JButton aceptarButton = new JButton("Aceptar");
		aceptarButton.addActionListener(e -> onAceptar());
		getContentPane().add(aceptarButton);
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(e -> dispose());
		springLayout.putConstraint(SpringLayout.SOUTH, cancelarButton, -93, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, aceptarButton, 0, SpringLayout.NORTH, cancelarButton);
		springLayout.putConstraint(SpringLayout.EAST, aceptarButton, -6, SpringLayout.WEST, cancelarButton);
		springLayout.putConstraint(SpringLayout.EAST, cancelarButton, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(cancelarButton);
	}

	private void onAceptar() {
		String nombre = espNomField.getText().trim();
		if (nombre.isEmpty() || espPorcField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe completar todos los campos", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int porcentaje;
		try {
			porcentaje = Integer.parseInt(espPorcField.getText().trim());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El porcentaje debe ser un número entero", "Dato inválido", JOptionPane.WARNING_MESSAGE);
			return;
		}
		try {
			ManejadorEspecialidad.get_instance().altaEspecialidad(nombre, porcentaje);
			JOptionPane.showMessageDialog(this, "Especialidad dada de alta correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} catch (EspecialidadAlreadyExistsException ex){
			JOptionPane.showMessageDialog(this, ex.getMessage(), "La especialidad ya existe", JOptionPane.ERROR_MESSAGE);
		}
	}
}
