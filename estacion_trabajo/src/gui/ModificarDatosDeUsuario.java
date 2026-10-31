package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;

import logica.controladores.ControladorClinica;
import logica.controladores.ControladorUsuario;
import logica.datatypes.DtMedico;
import logica.datatypes.DtPaciente;
import logica.datatypes.DtUsuario;
import logica.datatypes.TipoMes;
import logica.excepciones.MedicoSinEspecialidadException;
import logica.excepciones.NumeroDeRegistroAlreadyExistsException;
import logica.excepciones.SpecialityNotFoundException;

public class ModificarDatosDeUsuario extends JInternalFrame {

	private static final long serialVersionUID = 5513308871290045531L;

	private final ControladorUsuario controlador = new ControladorUsuario();
	private final ControladorClinica controladorClinica = new ControladorClinica();

	private JComboBox<String> usuariosBox;
	private JPanel formPanel;
	private JButton aceptarButton;

	private JTextField nicknameField;
	private JTextField correoField;
	private JTextField nombreField;
	private JTextField apellidoField;

	private CardLayout cartas = new CardLayout();
	private JPanel tipoPanel;

	private JDateChooser fechaNacChooser;
	private JDateChooser fechaIngresoChooser;
	private JTextField documentoField;
	private DefaultListModel<String> antecedentesModel;
	private JList<String> antecedentesList;
	private JTextField antecedenteField;

	private JTextField nroRegField;
	private JComboBox<TipoMes> mesLicBox;
	private JTextField porcentajeField;
	private JTextField linkField;
	private DefaultListModel<String> especialidadesModel;
	private JList<String> especialidadesList;

	private String nicknameSeleccionado;
	private boolean esMedico;

	public ModificarDatosDeUsuario() {
		super(
				"Modificar Datos de Usuario",
				true,
				true,
				true,
				true
				);

		setSize(660, 600);
		getContentPane().setLayout(new BorderLayout());

		JPanel seleccionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		seleccionPanel.add(new JLabel("Usuario:"));
		usuariosBox = new JComboBox<String>();
		controlador.listarUsuarios().stream()
				.map(dtu->dtu.getNickname())
				.sorted()
				.forEach(usuariosBox::addItem);
		usuariosBox.setSelectedIndex(-1);
		seleccionPanel.add(usuariosBox);
		JButton seleccionarButton = new JButton("Seleccionar");
		seleccionarButton.addActionListener(e -> onSeleccionar());
		seleccionPanel.add(seleccionarButton);
		getContentPane().add(seleccionPanel, BorderLayout.NORTH);

		formPanel = new JPanel(new GridBagLayout());
		construirFormulario();
		formPanel.setVisible(false);
		getContentPane().add(new JScrollPane(formPanel), BorderLayout.CENTER);

		aceptarButton = new JButton("Aceptar");
		aceptarButton.setEnabled(false);
		aceptarButton.addActionListener(e -> onAceptar());
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.addActionListener(e -> dispose());
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botones.add(aceptarButton);
		botones.add(cancelarButton);
		getContentPane().add(botones, BorderLayout.SOUTH);
	}

	private static void agregarFila(Container panel, int fila, String texto, Component campo) {
		GridBagConstraints etiqueta = new GridBagConstraints();
		etiqueta.gridx = 0;
		etiqueta.gridy = fila;
		etiqueta.anchor = GridBagConstraints.NORTHWEST;
		etiqueta.insets = new Insets(6, 10, 4, 5);
		panel.add(new JLabel(texto), etiqueta);

		GridBagConstraints componente = new GridBagConstraints();
		componente.gridx = 1;
		componente.gridy = fila;
		componente.weightx = 1.0;
		componente.fill = GridBagConstraints.HORIZONTAL;
		componente.insets = new Insets(6, 5, 4, 10);
		panel.add(campo, componente);
	}

	private void construirFormulario() {
		nicknameField = new JTextField(20);
		nicknameField.setEditable(false);
		correoField = new JTextField(20);
		correoField.setEditable(false);
		nombreField = new JTextField(20);
		apellidoField = new JTextField(20);

		agregarFila(formPanel, 0, "Nickname:", nicknameField);
		agregarFila(formPanel, 1, "Correo electrónico:", correoField);
		agregarFila(formPanel, 2, "Nombre:", nombreField);
		agregarFila(formPanel, 3, "Apellido:", apellidoField);

		tipoPanel = new JPanel(cartas);
		tipoPanel.add(construirPanelPaciente(), "paciente");
		tipoPanel.add(construirPanelMedico(), "medico");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 0, 5, 0);
		formPanel.add(tipoPanel, gbc);

		GridBagConstraints relleno = new GridBagConstraints();
		relleno.gridx = 0;
		relleno.gridy = 5;
		relleno.gridwidth = 2;
		relleno.weighty = 1.0;
		formPanel.add(new JPanel(), relleno);
	}

	private JPanel construirPanelPaciente() {
		JPanel panel = new JPanel(new GridBagLayout());

		fechaNacChooser = new JDateChooser();
		fechaIngresoChooser = new JDateChooser();
		documentoField = new JTextField(12);

		antecedentesModel = new DefaultListModel<String>();
		antecedentesList = new JList<String>(antecedentesModel);
		antecedentesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane antecedentesScroll = new JScrollPane(antecedentesList);
		antecedentesScroll.setPreferredSize(new Dimension(300, 110));

		antecedenteField = new JTextField(20);
		JButton anadirButton = new JButton("Añadir");
		anadirButton.addActionListener(e -> {
			String texto = antecedenteField.getText().trim();
			if (texto.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Escriba el antecedente a añadir.", "Dato incompleto", JOptionPane.WARNING_MESSAGE);
				return;
			}
			antecedentesModel.addElement(texto);
			antecedenteField.setText("");
			antecedenteField.requestFocus();
		});
		JButton quitarButton = new JButton("Quitar");
		quitarButton.addActionListener(e -> {
			int indice = antecedentesList.getSelectedIndex();
			if (indice == -1) {
				JOptionPane.showMessageDialog(this, "Seleccione el antecedente que desea quitar.", "Dato incompleto", JOptionPane.WARNING_MESSAGE);
				return;
			}
			antecedentesModel.remove(indice);
		});

		JPanel nuevoPanel = new JPanel(new BorderLayout(5, 0));
		nuevoPanel.add(antecedenteField, BorderLayout.CENTER);
		JPanel botonesAnt = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		botonesAnt.add(anadirButton);
		botonesAnt.add(quitarButton);
		nuevoPanel.add(botonesAnt, BorderLayout.EAST);

		agregarFila(panel, 0, "Fecha de nacimiento:", fechaNacChooser);
		agregarFila(panel, 1, "Fecha de ingreso:", fechaIngresoChooser);
		agregarFila(panel, 2, "Documento:", documentoField);
		agregarFila(panel, 3, "Antecedentes de salud:", antecedentesScroll);
		agregarFila(panel, 4, "Nuevo antecedente:", nuevoPanel);
		return panel;
	}

	private JPanel construirPanelMedico() {
		JPanel panel = new JPanel(new GridBagLayout());

		nroRegField = new JTextField(12);
		mesLicBox = new JComboBox<TipoMes>(TipoMes.values());
		porcentajeField = new JTextField(6);
		linkField = new JTextField(20);

		especialidadesModel = new DefaultListModel<String>();
		especialidadesList = new JList<String>(especialidadesModel);
		especialidadesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane especialidadesScroll = new JScrollPane(especialidadesList);
		especialidadesScroll.setPreferredSize(new Dimension(300, 120));

		agregarFila(panel, 0, "Número de registro:", nroRegField);
		agregarFila(panel, 1, "Mes de licencia:", mesLicBox);
		agregarFila(panel, 2, "Porcentaje adicional (%):", porcentajeField);
		agregarFila(panel, 3, "Link sitio web (opcional):", linkField);
		agregarFila(panel, 4, "Especialidades:", especialidadesScroll);
		agregarFila(panel, 5, "", new JLabel("(Ctrl + click para elegir varias; al menos una)"));
		return panel;
	}

	private static Date aDate(LocalDate fecha) {
		return Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	private static LocalDate aLocalDate(Date fecha) {
		return LocalDate.ofInstant(fecha.toInstant(), ZoneId.systemDefault());
	}

	private void onSeleccionar() {
		String nickname = (String) usuariosBox.getSelectedItem();
		if (nickname == null) {
			JOptionPane.showMessageDialog(this, "Seleccione un usuario de la lista.", "Usuario no seleccionado", JOptionPane.WARNING_MESSAGE);
			return;
		}

		DtUsuario usuario = controlador.listarDatosUsuario(nickname);
		nicknameField.setText(usuario.getNickname());
		correoField.setText(usuario.getCorreo());
		nombreField.setText(usuario.getNombre());
		apellidoField.setText(usuario.getApellido());

		esMedico = controlador.listarPacientes().stream().noneMatch(p -> p.getNickname().equals(nickname));
		if (esMedico) {
			cargarMedico(nickname);
		} else {
			cargarPaciente(nickname);
		}
		cartas.show(tipoPanel, esMedico ? "medico" : "paciente");

		nicknameSeleccionado = nickname;
		formPanel.setVisible(true);
		aceptarButton.setEnabled(true);
		revalidate();
		repaint();
	}

	private void cargarPaciente(String nickname) {
		DtPaciente paciente = controlador.listarDatosPaciente(nickname);
		fechaNacChooser.setDate(aDate(paciente.getFechaNac()));
		fechaIngresoChooser.setDate(aDate(paciente.getFechaIngreso()));
		documentoField.setText(String.valueOf(paciente.getDoc()));

		antecedentesModel.clear();
		paciente.getAntSalud().forEach(antecedentesModel::addElement);
		antecedenteField.setText("");
	}

	private void cargarMedico(String nickname) {
		DtMedico medico = controlador.listarDatosMedico(nickname);
		nroRegField.setText(medico.getNroReg());
		mesLicBox.setSelectedItem(medico.getMesLic());
		porcentajeField.setText(String.valueOf(medico.getPorcentajeCost()));
		linkField.setText(medico.getLink() == null ? "" : medico.getLink());

		especialidadesModel.clear();
		List<String> todas = new ArrayList<String>(controladorClinica.listarEspecialidades());
		Collections.sort(todas);
		todas.forEach(especialidadesModel::addElement);

		List<String> delMedico = controlador.listarEspecialidadesMedico(nickname);
		int[] indices = delMedico.stream().mapToInt(especialidadesModel::indexOf).filter(i -> i >= 0).toArray();
		especialidadesList.setSelectedIndices(indices);
	}

	private void onAceptar() {
		String nombre = nombreField.getText().trim();
		String apellido = apellidoField.getText().trim();
		if (nombre.isEmpty() || apellido.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El nombre y el apellido no pueden quedar vacíos.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			if (esMedico) {
				modificarMedico(nombre, apellido);
			} else {
				modificarPaciente(nombre, apellido);
			}
		} catch (NumeroDeRegistroAlreadyExistsException | MedicoSinEspecialidadException | SpecialityNotFoundException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "No se pudo modificar el usuario", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void modificarPaciente(String nombre, String apellido) {
		if (fechaNacChooser.getDate() == null || fechaIngresoChooser.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Debe ingresar la fecha de nacimiento y la fecha de ingreso.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
			return;
		}
		LocalDate fechaNac = aLocalDate(fechaNacChooser.getDate());
		LocalDate fechaIngreso = aLocalDate(fechaIngresoChooser.getDate());

		if (fechaNac.isAfter(LocalDate.now())) {
			JOptionPane.showMessageDialog(this, "La fecha de nacimiento no puede ser posterior a hoy.", "Fecha inválida", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (fechaIngreso.isBefore(fechaNac)) {
			JOptionPane.showMessageDialog(this, "La fecha de ingreso no puede ser anterior a la fecha de nacimiento.", "Fecha inválida", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int doc;
		try {
			doc = Integer.parseInt(documentoField.getText().trim());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El documento debe ser un número.", "Dato inválido", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (String.valueOf(doc).length() != 8) {
			JOptionPane.showMessageDialog(this,
					"El documento debe tener 8 dígitos.",
					"Dato inválido",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		ArrayList<String> antecedentes = Collections.list(antecedentesModel.elements());
		controlador.modificarPaciente(nicknameSeleccionado, nombre, apellido, fechaNac, fechaIngreso, doc, antecedentes);
		exito();
	}

	private void modificarMedico(String nombre, String apellido) {
		String nroReg = nroRegField.getText().trim();
		if (nroReg.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe ingresar el número de registro.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int porcentaje;
		try {
			porcentaje = Integer.parseInt(porcentajeField.getText().trim());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El porcentaje adicional debe ser un número entero.", "Dato inválido", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (porcentaje < 0) {
			JOptionPane.showMessageDialog(this, "El porcentaje adicional no puede ser negativo.", "Dato inválido", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String link = linkField.getText().trim();
		if (link.isEmpty()) {
			link = null;
		}

		ArrayList<String> especialidades = new ArrayList<String>(especialidadesList.getSelectedValuesList());
		controlador.modificarMedico(nicknameSeleccionado, nombre, apellido, nroReg, (TipoMes) mesLicBox.getSelectedItem(), porcentaje, link, especialidades);
		exito();
	}

	private void exito() {
		JOptionPane.showMessageDialog(this, "Datos del usuario modificados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
}