package gui;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;

import logica.manejadores.ManejadorEspecialidad;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AltaDeEspecialidad extends JInternalFrame {
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
		ManejadorEspecialidad.get_instance().test_add_basics();
		
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
		getContentPane().add(aceptarButton);
		
		JButton cancelarButton = new JButton("Cancelar");
		springLayout.putConstraint(SpringLayout.SOUTH, cancelarButton, -93, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, aceptarButton, 0, SpringLayout.NORTH, cancelarButton);
		springLayout.putConstraint(SpringLayout.EAST, aceptarButton, -6, SpringLayout.WEST, cancelarButton);
		springLayout.putConstraint(SpringLayout.EAST, cancelarButton, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(cancelarButton);
		// TODO Auto-generated constructor stub
	}

	public AltaDeEspecialidad(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public AltaDeEspecialidad(String title, boolean resizable) {
		super(title, resizable);
		// TODO Auto-generated constructor stub
	}

	public AltaDeEspecialidad(String title, boolean resizable, boolean closable) {
		super(title, resizable, closable);
		// TODO Auto-generated constructor stub
	}

	public AltaDeEspecialidad(String title, boolean resizable, boolean closable, boolean maximizable) {
		super(title, resizable, closable, maximizable);
		// TODO Auto-generated constructor stub
	}

	public AltaDeEspecialidad(String title, boolean resizable, boolean closable, boolean maximizable,
			boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		// TODO Auto-generated constructor stub
	}
}
