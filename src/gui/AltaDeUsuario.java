package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import logica.excepciones.*;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import logica.controladores.ControladorUsuario;
import logica.datatypes.TipoMes;
import logica.manejadores.ManejadorEspecialidad;
import javax.swing.AbstractListModel;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import javax.swing.ListSelectionModel;

public class AltaDeUsuario extends JInternalFrame {

    
   

	private static final long serialVersionUID = -6141709894943702613L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField nicknameField;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField emailField;
	private DefaultListModel<String> pacienteListModel = new DefaultListModel<>();
	private JList<String> pacienteList;
	private JTextField documentoField;
	private JTextField ddfield;
	private JTextField antTextfield;
	private JTextField linkField;
	private JTextField nroRegField;
	private JTextField porcCostField;
	private JTextField mmfield;
	private JTextField yyyyfield;

	public AltaDeUsuario() {
		super(
				"Alta de Usuario", // título
				true,              // se puede redimensionar
				true,              // se puede cerrar
				true,              // se puede maximizar
				true               // se puede minimizar
				);
		ManejadorEspecialidad.get_instance().test_add_basics();
		
		setSize(600, 500);
	
		setMaximizable(true);
		setTitle("Alta Usuario");
		setResizable(true);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JLabel selector_label = new JLabel("Seleccione: ");
		getContentPane().add(selector_label);
		
		JRadioButton selectorPaciente = new JRadioButton("Paciente", true);
		springLayout.putConstraint(SpringLayout.NORTH, selectorPaciente, -4, SpringLayout.NORTH, selector_label);
		springLayout.putConstraint(SpringLayout.WEST, selectorPaciente, 5, SpringLayout.EAST, selector_label);
		buttonGroup.add(selectorPaciente);
		getContentPane().add(selectorPaciente);
		
		JRadioButton selectorMedico = new JRadioButton("Medico", false);
		springLayout.putConstraint(SpringLayout.NORTH, selectorMedico, -4, SpringLayout.NORTH, selector_label);
		springLayout.putConstraint(SpringLayout.WEST, selectorMedico, 6, SpringLayout.EAST, selectorPaciente);
		buttonGroup.add(selectorMedico);
		getContentPane().add(selectorMedico);
		
		JLabel nicknameLabel = new JLabel("Ingrese nickname:");
		springLayout.putConstraint(SpringLayout.WEST, nicknameLabel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, selector_label, 0, SpringLayout.WEST, nicknameLabel);
		getContentPane().add(nicknameLabel);
		
		JLabel nameLabel = new JLabel("Nombre:");
		springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 6, SpringLayout.SOUTH, nicknameLabel);
		springLayout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(nameLabel);
		
		JLabel surnameLabel = new JLabel("Apellido:");
		springLayout.putConstraint(SpringLayout.NORTH, surnameLabel, 6, SpringLayout.SOUTH, nameLabel);
		springLayout.putConstraint(SpringLayout.WEST, surnameLabel, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(surnameLabel);
		
		JLabel emailLabel = new JLabel("Correo electrónico: ");
		springLayout.putConstraint(SpringLayout.WEST, emailLabel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, selector_label, 16, SpringLayout.SOUTH, emailLabel);
		springLayout.putConstraint(SpringLayout.NORTH, emailLabel, 6, SpringLayout.SOUTH, surnameLabel);
		getContentPane().add(emailLabel);
		
		nicknameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, nicknameLabel, 2, SpringLayout.NORTH, nicknameField);
		springLayout.putConstraint(SpringLayout.NORTH, nicknameField, 20, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, nicknameField, 147, SpringLayout.WEST, getContentPane());
		getContentPane().add(nicknameField);
		nicknameField.setColumns(10);
		
		nameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, nameField, 2, SpringLayout.SOUTH, nicknameField);
		springLayout.putConstraint(SpringLayout.WEST, nameField, 0, SpringLayout.WEST, nicknameField);
		nameField.setColumns(10);
		getContentPane().add(nameField);
		
		surnameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, surnameField, 2, SpringLayout.SOUTH, nameField);
		springLayout.putConstraint(SpringLayout.EAST, surnameField, 0, SpringLayout.EAST, nicknameField);
		surnameField.setColumns(10);
		getContentPane().add(surnameField);
		
		emailField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, emailField, 2, SpringLayout.SOUTH, surnameField);
		springLayout.putConstraint(SpringLayout.WEST, emailField, 19, SpringLayout.EAST, emailLabel);
		springLayout.putConstraint(SpringLayout.EAST, emailField, 161, SpringLayout.EAST, emailLabel);
		emailField.setColumns(10);
		getContentPane().add(emailField);
		
		JPanel conditionalPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, conditionalPanel, 6, SpringLayout.SOUTH, selector_label);
		springLayout.putConstraint(SpringLayout.WEST, conditionalPanel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, conditionalPanel, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, conditionalPanel, 594, SpringLayout.WEST, getContentPane());
		getContentPane().add(conditionalPanel);
		CardLayout cl = new CardLayout(0,0);
		conditionalPanel.setLayout(cl);
		
		JPanel panelPaciente = new JPanel();
		conditionalPanel.add(panelPaciente, "paciente");

		JPanel panelMedico = new JPanel();
		conditionalPanel.add(panelMedico, "medico");
		SpringLayout sl_panelMedico = new SpringLayout();
		panelMedico.setLayout(sl_panelMedico);
		
		JLabel numRegLabel = new JLabel("Num. de registro: ");
		panelMedico.add(numRegLabel);
		
		JLabel mesLicLabel = new JLabel("Mes de licencia:");
		sl_panelMedico.putConstraint(SpringLayout.NORTH, mesLicLabel, 24, SpringLayout.SOUTH, numRegLabel);
		sl_panelMedico.putConstraint(SpringLayout.WEST, mesLicLabel, 0, SpringLayout.WEST, panelMedico);
		sl_panelMedico.putConstraint(SpringLayout.WEST, numRegLabel, 0, SpringLayout.WEST, mesLicLabel);
		panelMedico.add(mesLicLabel);
		
		JLabel linkLabel = new JLabel("Link sitio web:");
		sl_panelMedico.putConstraint(SpringLayout.WEST, linkLabel, 0, SpringLayout.WEST, numRegLabel);
		panelMedico.add(linkLabel);
		
		JLabel porcCostLabel = new JLabel("Porcentaje costo de consulta:");
		sl_panelMedico.putConstraint(SpringLayout.WEST, porcCostLabel, 0, SpringLayout.WEST, numRegLabel);
		panelMedico.add(porcCostLabel);
		
		linkField = new JTextField();
		sl_panelMedico.putConstraint(SpringLayout.NORTH, porcCostLabel, 19, SpringLayout.SOUTH, linkField);
		sl_panelMedico.putConstraint(SpringLayout.NORTH, linkField, 20, SpringLayout.SOUTH, mesLicLabel);
		sl_panelMedico.putConstraint(SpringLayout.WEST, linkField, 6, SpringLayout.EAST, linkLabel);
		sl_panelMedico.putConstraint(SpringLayout.NORTH, linkLabel, 2, SpringLayout.NORTH, linkField);
		linkField.setColumns(10);
		panelMedico.add(linkField);
		
		nroRegField = new JTextField();
		sl_panelMedico.putConstraint(SpringLayout.NORTH, nroRegField, 28, SpringLayout.NORTH, panelMedico);
		sl_panelMedico.putConstraint(SpringLayout.WEST, nroRegField, 3, SpringLayout.EAST, numRegLabel);
		sl_panelMedico.putConstraint(SpringLayout.NORTH, numRegLabel, 2, SpringLayout.NORTH, nroRegField);
		nroRegField.setColumns(10);
		panelMedico.add(nroRegField);
		
		JComboBox mesLicField = new JComboBox();
		sl_panelMedico.putConstraint(SpringLayout.NORTH, mesLicField, -2, SpringLayout.NORTH, mesLicLabel);
		sl_panelMedico.putConstraint(SpringLayout.WEST, mesLicField, 14, SpringLayout.EAST, mesLicLabel);
		sl_panelMedico.putConstraint(SpringLayout.SOUTH, mesLicField, -18, SpringLayout.NORTH, linkField);
		sl_panelMedico.putConstraint(SpringLayout.EAST, mesLicField, 0, SpringLayout.EAST, nroRegField);
		mesLicField.setModel(new DefaultComboBoxModel(TipoMes.values()));
		panelMedico.add(mesLicField);
		
		porcCostField = new JTextField();
		sl_panelMedico.putConstraint(SpringLayout.NORTH, porcCostField, -2, SpringLayout.NORTH, porcCostLabel);
		sl_panelMedico.putConstraint(SpringLayout.WEST, porcCostField, 6, SpringLayout.EAST, porcCostLabel);
		sl_panelMedico.putConstraint(SpringLayout.EAST, porcCostField, -354, SpringLayout.EAST, panelMedico);
		porcCostField.setColumns(10);
		panelMedico.add(porcCostField);
		
		JLabel percentLabel = new JLabel("%");
		sl_panelMedico.putConstraint(SpringLayout.NORTH, percentLabel, 0, SpringLayout.NORTH, porcCostLabel);
		sl_panelMedico.putConstraint(SpringLayout.WEST, percentLabel, 6, SpringLayout.EAST, porcCostField);
		panelMedico.add(percentLabel);
		
		JLabel espLabel = new JLabel("Especialidades: ");
		sl_panelMedico.putConstraint(SpringLayout.NORTH, espLabel, 0, SpringLayout.NORTH, numRegLabel);
		panelMedico.add(espLabel);
		
		JPanel panel = new JPanel();
		sl_panelMedico.putConstraint(SpringLayout.NORTH, panel, 12, SpringLayout.SOUTH, espLabel);
		sl_panelMedico.putConstraint(SpringLayout.WEST, panel, 133, SpringLayout.EAST, mesLicField);
		sl_panelMedico.putConstraint(SpringLayout.EAST, panel, -37, SpringLayout.EAST, panelMedico);
		panelMedico.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane_1, BorderLayout.CENTER);
		
		JList<String> list = new JList<String>();
		
		list.setModel(new AbstractListModel<String>() {
			
			private static final long serialVersionUID = 7289709041320631359L;
			ArrayList<String> values = ManejadorEspecialidad.get_instance().get_especialidades();
			
			@Override
			public int getSize() {
				return values.size();
			}
			@Override
			public String getElementAt(int index) {
				return values.get(index);
			}
		});
		scrollPane_1.setViewportView(list);
	
		
		JPanel panelEspSelected = new JPanel();
		sl_panelMedico.putConstraint(SpringLayout.WEST, panelEspSelected, 0, SpringLayout.WEST, panel);
		sl_panelMedico.putConstraint(SpringLayout.SOUTH, panelEspSelected, -57, SpringLayout.SOUTH, panelMedico);
		sl_panelMedico.putConstraint(SpringLayout.EAST, panelEspSelected, 0, SpringLayout.EAST, panel);
		panelMedico.add(panelEspSelected);
		panelEspSelected.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel selectedLabel = new JLabel("Seleccionadas:");
		sl_panelMedico.putConstraint(SpringLayout.EAST, selectedLabel, -89, SpringLayout.EAST, panelMedico);
		sl_panelMedico.putConstraint(SpringLayout.SOUTH, panel, -6, SpringLayout.NORTH, selectedLabel);
		sl_panelMedico.putConstraint(SpringLayout.WEST, espLabel, 0, SpringLayout.WEST, selectedLabel);
		sl_panelMedico.putConstraint(SpringLayout.NORTH, panelEspSelected, 6, SpringLayout.SOUTH, selectedLabel);
		sl_panelMedico.putConstraint(SpringLayout.NORTH, selectedLabel, 0, SpringLayout.NORTH, porcCostLabel);
		panelMedico.add(selectedLabel);
		
		JButton cancelMedButton = new JButton("Cancelar");
		sl_panelMedico.putConstraint(SpringLayout.SOUTH, cancelMedButton, 0, SpringLayout.SOUTH, panelMedico);
		panelMedico.add(cancelMedButton);
		
		JButton okMedButton = new JButton("Ok");
		sl_panelMedico.putConstraint(SpringLayout.WEST, okMedButton, 0, SpringLayout.WEST, panelMedico);
		sl_panelMedico.putConstraint(SpringLayout.EAST, okMedButton, -505, SpringLayout.EAST, panelMedico);
		sl_panelMedico.putConstraint(SpringLayout.NORTH, cancelMedButton, 0, SpringLayout.NORTH, okMedButton);
		sl_panelMedico.putConstraint(SpringLayout.WEST, cancelMedButton, 6, SpringLayout.EAST, okMedButton);
		sl_panelMedico.putConstraint(SpringLayout.NORTH, okMedButton, 119, SpringLayout.SOUTH, porcCostLabel);
		panelMedico.add(okMedButton);
		cancelMedButton.addActionListener(e->{
				this.dispose();
			});
		
		ArrayList<String> selections = new ArrayList<String>();
		list.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				
				for (String elemento : list.getSelectedValuesList()) {
					JLabel label = new JLabel(elemento);
					panelEspSelected.add(label);
					selections.add(elemento);
				}
				
				panelEspSelected.revalidate();
				panelEspSelected.repaint();
			}
		});
		ActionListener cardSwitch = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectorMedico.isSelected()) {
					cl.show(conditionalPanel, "medico");
				} else {
					cl.show(conditionalPanel, "paciente");
				}
			}
		};
		
		selectorMedico.addActionListener(cardSwitch);
		selectorPaciente.addActionListener(cardSwitch);
		
		JLabel selector_label_1 = new JLabel("Fecha de nacimiento:");
		
		JLabel selector_label_1_1 = new JLabel("Documento de identidad:");
		
		documentoField = new JTextField();
		documentoField.setColumns(10);
		
		ddfield = new JTextField();
		ddfield.setColumns(10);
		
		JLabel selector_label_1_1_1 = new JLabel("Antecedentes de salud:");
		
		antTextfield = new JTextField();
		antTextfield.setColumns(10);
		
		JButton btnNewButton = new JButton("Añadir");
		
		mmfield = new JTextField();
		mmfield.setColumns(10);
		
		yyyyfield = new JTextField();
		yyyyfield.setColumns(10);
		SpringLayout sl_panelPaciente = new SpringLayout();
		sl_panelPaciente.putConstraint(SpringLayout.NORTH, selector_label_1_1_1, 31, SpringLayout.NORTH, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.WEST, selector_label_1_1_1, 389, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.EAST, selector_label_1_1_1, 551, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.NORTH, antTextfield, 54, SpringLayout.NORTH, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.WEST, antTextfield, 413, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.EAST, antTextfield, 539, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.NORTH, btnNewButton, 54, SpringLayout.NORTH, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.WEST, btnNewButton, 313, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.SOUTH, btnNewButton, 75, SpringLayout.NORTH, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.EAST, btnNewButton, 407, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.NORTH, yyyyfield, 54, SpringLayout.NORTH, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.WEST, yyyyfield, 244, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.EAST, yyyyfield, 294, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.NORTH, mmfield, 54, SpringLayout.NORTH, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.WEST, mmfield, 209, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.EAST, mmfield, 232, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.NORTH, ddfield, 54, SpringLayout.NORTH, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.WEST, ddfield, 180, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.EAST, ddfield, 203, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.NORTH, documentoField, 106, SpringLayout.NORTH, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.WEST, documentoField, 180, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.NORTH, selector_label_1, 56, SpringLayout.NORTH, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.WEST, selector_label_1, 0, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.NORTH, selector_label_1_1, 108, SpringLayout.NORTH, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.WEST, selector_label_1_1, 0, SpringLayout.WEST, panelPaciente);
		sl_panelPaciente.putConstraint(SpringLayout.EAST, selector_label_1_1, 162, SpringLayout.WEST, panelPaciente);
		panelPaciente.setLayout(sl_panelPaciente);
		panelPaciente.add(selector_label_1_1);
		panelPaciente.add(selector_label_1);
		panelPaciente.add(documentoField);
		panelPaciente.add(ddfield);
		panelPaciente.add(mmfield);
		panelPaciente.add(yyyyfield);
		panelPaciente.add(btnNewButton);
		panelPaciente.add(antTextfield);
		panelPaciente.add(selector_label_1_1_1);
		
		JPanel panel_1 = new JPanel();
		sl_panelPaciente.putConstraint(SpringLayout.NORTH, panel_1, 18, SpringLayout.SOUTH, btnNewButton);
		sl_panelPaciente.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, btnNewButton);
		sl_panelPaciente.putConstraint(SpringLayout.SOUTH, panel_1, 87, SpringLayout.SOUTH, btnNewButton);
		sl_panelPaciente.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, antTextfield);
		panelPaciente.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		DefaultListModel<String> modeloAntSalud = new DefaultListModel<>();
		JList<String> antSaludlist = new JList<>(modeloAntSalud);
		
		antSaludlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(antSaludlist);
		
		JButton okPacButton = new JButton("Ok");
		sl_panelPaciente.putConstraint(SpringLayout.WEST, okPacButton, 0, SpringLayout.WEST, selector_label_1_1);
		sl_panelPaciente.putConstraint(SpringLayout.SOUTH, okPacButton, 0, SpringLayout.SOUTH, panelPaciente);
		panelPaciente.add(okPacButton);
		
		JButton cancelPacButton = new JButton("Cancelar");
		sl_panelPaciente.putConstraint(SpringLayout.NORTH, cancelPacButton, 0, SpringLayout.NORTH, okPacButton);
		sl_panelPaciente.putConstraint(SpringLayout.WEST, cancelPacButton, 6, SpringLayout.EAST, okPacButton);
		panelPaciente.add(cancelPacButton);
	
		DefaultListModel<String> modelo = new DefaultListModel<>();
	
		
		btnNewButton.addActionListener(e->{
			String texto = antTextfield.getText().trim();
		    if (texto.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
		        antTextfield.requestFocus();
		        return;
		    }
		    
		    modeloAntSalud.addElement(texto); 
		    
		    antTextfield.setText("");
		    antTextfield.requestFocus();
		});
		
		cancelPacButton.addActionListener(e->{
			this.dispose();
		});
		okPacButton.addActionListener(a -> {
			
			ControladorUsuario cu = new ControladorUsuario();
			ArrayList<String> antssalud = Collections.list(modeloAntSalud.elements());
			try {
			LocalDate fechaNac = LocalDate.of(yyyyfield.getText()==null || yyyyfield.getText().equals("") ? 0:Integer.parseInt(yyyyfield.getText()),mmfield.getText()==null || mmfield.getText().equals("") ? 0:Integer.parseInt(mmfield.getText()), 
					ddfield.getText()==null || ddfield.getText().equals("") ? 0:Integer.parseInt(ddfield.getText()));
			if (validar_paciente(nicknameField.getText(), nameField.getText(), surnameField.getText(), 
					emailField.getText(), fechaNac, Integer.parseInt(documentoField.getText()))){
				try {
					cu.ingresarPaciente(nicknameField.getText(), nameField.getText(),  surnameField.getText(), emailField.getText() , fechaNac, LocalDate.now(),Integer.parseInt(documentoField.getText()),antssalud);
					JOptionPane.showMessageDialog(this, "Operacion realizada con exito");
				} catch (NicknameAlreadyExistsException | EmailAlreadyExistsException e) {
					JOptionPane.showMessageDialog(this, "No se pudo realizar la operacion:\n"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}  else {
				JOptionPane.showMessageDialog(this, "No se pudo realizar la operacion:\nVerifica que no queden campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
			}
			} catch (DateTimeException e) {
		        JOptionPane.showMessageDialog(this, "No se pudo realizar la operacion:\nVerifica que no queden campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		okMedButton.addActionListener(a -> {
			
			ControladorUsuario cu = new ControladorUsuario();
			
			try {
				if (validar_medico(nicknameField.getText(), nameField.getText(), surnameField.getText(), 
						emailField.getText(), Integer.parseInt(nroRegField.getText()), Integer.parseInt(porcCostField.getText()), linkField.getText(), selections)){
					try {
						cu.ingresarMedico(nicknameField.getText(), nameField.getText(),  surnameField.getText(), emailField.getText() , Integer.parseInt(nroRegField.getText()), 
								TipoMes.values()[mesLicField.getSelectedIndex()], Integer.parseInt(porcCostField.getText()), linkField.getText(), selections);
						JOptionPane.showMessageDialog(null, "Operacion realizada con exito");
					} catch (NicknameAlreadyExistsException | EmailAlreadyExistsException e) {
						JOptionPane.showMessageDialog(null, "No se pudo realizar la operacion:\n"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "No se pudo realizar la operacion:\nVerifica que no queden campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
					
				}
			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog(this, "No se pudo realizar la operacion:\nVerifica que no queden campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
			}
		});
		
	}
	public boolean validar_usuario(String nickField,String nameField, String surnameField, String emailField) {
		if (nickField.length()!=0 && surnameField.length()!=0 && emailField.length()!=0 && nameField.length()!=0) {
			return true;
		}
		return false;
	}
	
	public boolean validar_paciente(String nickname, String nombre, String apellido, String correo, LocalDate _fechaNac, int _doc){
		if (validar_usuario(nickname,nombre, apellido,correo)) {
			return true && (_fechaNac!=null);
		}
		return false;
	}
	
	public boolean validar_medico(String _nickname, String _nombre, String _apellido, String _correo, int _nroReg, int _porcentajeCosto, String _link, ArrayList<String> espnom) {
		return validar_usuario(_nickname, _nombre, _apellido, _correo) && _nroReg!=0 && (!espnom.isEmpty());
	}
}
