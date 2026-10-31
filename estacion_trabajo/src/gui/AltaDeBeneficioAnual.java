package gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.controladores.ControladorUsuario;
import logica.datatypes.DtPaciente;
import logica.excepciones.BeneficioAlreadyExistsException;
import logica.excepciones.UserNotFoundException;

public class AltaDeBeneficioAnual extends JInternalFrame {
	
	private static final long serialVersionUID = 4620873104551234567L;
	
	private final ControladorUsuario controlador = new ControladorUsuario();
	
	private JComboBox<String> pacientesBox;
	private JTextField anioField;
	private JTextField ordenesField;
	private JTextField ticketField;
	
	public AltaDeBeneficioAnual() {
		super(
				"Alta de Beneficio Anual",
				true,
				true,
				true,
				true
				);
		setSize(520,280);
		getContentPane().setLayout(new GridBagLayout());
		
		pacientesBox = new JComboBox<String>();
		controlador.listarPacientes().stream()
				.map(dtp->dtp.getNickname())
				.sorted()
				.forEach(pacientesBox::addItem);
		
		anioField = new JTextField(String.valueOf(LocalDate.now().getYear()), 10);
		ordenesField = new JTextField(10);
		ticketField = new JTextField(10);
		
		agregarFila(0, "Paciente:", pacientesBox);
		agregarFila(1, "Año:", anioField);
		agregarFila(2, "Órdenes de consulta gratuitas:", ordenesField);
		agregarFila(3, "Tickets de medicamento gratuitos:", ticketField);
		
		JButton aceptarButton = new JButton("Aceptar");
		aceptarButton.addActionListener(e -> {
			try {
				onAceptar();
			} catch (UserNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(e -> dispose());
		
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botones.add(aceptarButton);
		botones.add(cancelarButton);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.insets = new Insets(10, 10, 10, 10);
		getContentPane().add(botones, gbc);
	}
	
	private void agregarFila(int fila, String texto, Component campo) {
		GridBagConstraints etiqueta = new GridBagConstraints();
		etiqueta.gridx = 0;
		etiqueta.gridy = fila;
		etiqueta.anchor = GridBagConstraints.WEST;
		etiqueta.insets = new Insets(8, 10, 4, 5);
		getContentPane().add(new JLabel(texto), etiqueta);
		
		GridBagConstraints componente = new GridBagConstraints();
		componente.gridx = 1;
		componente.gridy = fila;
		componente.weightx = 1.0;
		componente.fill = GridBagConstraints.HORIZONTAL;
		componente.insets = new Insets(8, 5, 4, 10);
		getContentPane().add(campo, componente);
	}
	
	private void onAceptar() throws UserNotFoundException {
		String nickname = (String) pacientesBox.getSelectedItem();
		if (nickname == null) {
			JOptionPane.showMessageDialog(this, "Debe existir al menos un paciente dado de alta.", "Datos insuficientes", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (anioField.getText().trim().isEmpty() || ordenesField.getText().trim().isEmpty() || ticketField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int anio, cantOrdenes, cantTicket;
		try {
			anio = Integer.parseInt(anioField.getText().trim());
			cantOrdenes = Integer.parseInt(ordenesField.getText().trim());
			cantTicket = Integer.parseInt(ticketField.getText().trim());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El año y las cantidades deben ser números enteros.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if (anio < 1 || cantOrdenes < 0 || cantTicket < 0) {
			JOptionPane.showMessageDialog(this, "El año debe ser positivo y las cantidades no pueden ser negativas.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			controlador.altaBeneficioAnual(nickname, anio, cantOrdenes, cantTicket);
			JOptionPane.showMessageDialog(this, "Beneficio anual dado de alta correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} catch (BeneficioAlreadyExistsException ex){
			JOptionPane.showMessageDialog(this, ex.getMessage(), "El paciente ya tiene beneficio para " + anio, JOptionPane.ERROR_MESSAGE);
		}
	}
}