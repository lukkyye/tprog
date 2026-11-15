package gui;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import  logica.controladores.ControladorUsuario;
import  logica.datatypes.DtBeneficioAnual;
import  logica.datatypes.DtPaciente;
import  logica.excepciones.BeneficioNotFoundException;
import  logica.excepciones.UserNotFoundException;

public class ConsultaDeBeneficioAnual extends JInternalFrame {

	private static final long serialVersionUID = 8271904463318820011L;

	private final ControladorUsuario controlador = new ControladorUsuario();

	private JComboBox<String> pacientesBox;
	private DefaultListModel<Integer> aniosModel;
	private JList<Integer> aniosList;
	private JLabel sinBeneficiosLabel;
	private JLabel anioValor;
	private JLabel ordenesValor;
	private JLabel ticketsValor;

	public ConsultaDeBeneficioAnual() {
		super(
				"Consulta de Beneficio Anual",
				true,
				true,
				true,
				true
				);

		setSize(520, 440);
		getContentPane().setLayout(new GridBagLayout());

		pacientesBox = new JComboBox<String>();
		controlador.listarPacientes().stream()
				.map(dtp->dtp.getNickname())
				.sorted()
				.forEach(pacientesBox::addItem);
		pacientesBox.setSelectedIndex(-1);

		agregar(new JLabel("Paciente:"), 0, 0, 1, GridBagConstraints.WEST, 0.0, 0.0, GridBagConstraints.NONE, 10, 10, 5, 5);
		agregar(pacientesBox, 1, 0, 1, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL, 10, 5, 5, 10);

		agregar(new JLabel("Beneficios anuales del paciente (por año):"), 0, 1, 2, GridBagConstraints.WEST, 0.0, 0.0, GridBagConstraints.NONE, 5, 10, 2, 10);

		aniosModel = new DefaultListModel<Integer>();
		aniosList = new JList<Integer>(aniosModel);
		aniosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		agregar(new JScrollPane(aniosList), 0, 2, 2, GridBagConstraints.CENTER, 1.0, 1.0, GridBagConstraints.BOTH, 2, 10, 2, 10);

		sinBeneficiosLabel = new JLabel(" ");
		agregar(sinBeneficiosLabel, 0, 3, 2, GridBagConstraints.WEST, 0.0, 0.0, GridBagConstraints.NONE, 2, 10, 2, 10);

		anioValor = new JLabel(" ");
		ordenesValor = new JLabel(" ");
		ticketsValor = new JLabel(" ");
		agregarDetalle(4, "Año:", anioValor);
		agregarDetalle(5, "Órdenes de consulta gratuitas:", ordenesValor);
		agregarDetalle(6, "Tickets de medicamento gratuitos:", ticketsValor);

		JButton cerrarButton = new JButton("Cerrar");
		cerrarButton.addActionListener(e -> dispose());
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		botones.add(cerrarButton);
		agregar(botones, 0, 7, 2, GridBagConstraints.SOUTHEAST, 0.0, 0.0, GridBagConstraints.NONE, 10, 10, 10, 10);

		pacientesBox.addActionListener(e -> {
			try {
				cargarAnios();
			} catch (UserNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		aniosList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				try {
					mostrarDetalle();
				} catch (BeneficioNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UserNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void agregar(java.awt.Component c, int x, int y, int ancho, int anchor, double wx, double wy, int fill, int top, int left, int bottom, int right) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = ancho;
		gbc.anchor = anchor;
		gbc.weightx = wx;
		gbc.weighty = wy;
		gbc.fill = fill;
		gbc.insets = new Insets(top, left, bottom, right);
		getContentPane().add(c, gbc);
	}

	private void agregarDetalle(int fila, String texto, JLabel valor) {
		agregar(new JLabel(texto), 0, fila, 1, GridBagConstraints.WEST, 0.0, 0.0, GridBagConstraints.NONE, 4, 10, 4, 5);
		agregar(valor, 1, fila, 1, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL, 4, 5, 4, 10);
	}

	private void cargarAnios() throws UserNotFoundException {
		aniosModel.clear();
		limpiarDetalle();
		sinBeneficiosLabel.setText(" ");

		String nickname = (String) pacientesBox.getSelectedItem();
		if (nickname == null) {
			return;
		}

		controlador.listarBeneficiosAnuales(nickname).stream().sorted(Comparator.comparingInt(dtb->dtb.getAnio())).forEach(b -> aniosModel.addElement(b.getAnio()));

		if (aniosModel.isEmpty()) {
			sinBeneficiosLabel.setText("El paciente no tiene beneficios anuales.");
		}
	}

	private void mostrarDetalle() throws BeneficioNotFoundException, UserNotFoundException {
		String nickname = (String) pacientesBox.getSelectedItem();
		Integer anio = aniosList.getSelectedValue();
		if (nickname == null || anio == null) {
			limpiarDetalle();
			return;
		}

		DtBeneficioAnual beneficio = controlador.listarDatosBeneficioAnual(nickname, anio);
		anioValor.setText(String.valueOf(beneficio.getAnio()));
		ordenesValor.setText(String.valueOf(beneficio.getCantOrdenes()));
		ticketsValor.setText(String.valueOf(beneficio.getCantTicket()));
	}

	private void limpiarDetalle() {
		anioValor.setText(" ");
		ordenesValor.setText(" ");
		ticketsValor.setText(" ");
	}
}
