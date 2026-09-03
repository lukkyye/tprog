package gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import test.CargadorCSV;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

public class DatosPrueba extends JInternalFrame {
	private static CargadorCSV cargador=null;
	
	public DatosPrueba() {
		
		setTitle("Datos de Prueba");
		setClosable(true);
		setMaximizable(false);
		setResizable(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{260, 0};
		gridBagLayout.rowHeights = new int[]{43, 17, 27, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel cargarLabel = new JLabel("Presione para cargar datos de prueba:");
		cargarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_cargarLabel = new GridBagConstraints();
		gbc_cargarLabel.anchor = GridBagConstraints.NORTH;
		gbc_cargarLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_cargarLabel.insets = new Insets(0, 0, 5, 0);
		gbc_cargarLabel.gridx = 0;
		gbc_cargarLabel.gridy = 1;
		getContentPane().add(cargarLabel, gbc_cargarLabel);
		
		JButton cargarButton = new JButton("Cargar");
		GridBagConstraints gbc_cargarButton = new GridBagConstraints();
		gbc_cargarButton.anchor = GridBagConstraints.NORTH;
		gbc_cargarButton.gridx = 0;
		gbc_cargarButton.gridy = 3;
		getContentPane().add(cargarButton, gbc_cargarButton);
		
		cargarButton.addActionListener(e->{
			if (cargador==null) {
				cargador = new CargadorCSV();
				try {
					cargador.cargarTodo();					
					JOptionPane.showMessageDialog(null, "Datos cargados correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
				} catch (RuntimeException err) {
					JOptionPane.showMessageDialog(null, "No se pudieron cargar los datos: "+err.getMessage(), "Error grave", JOptionPane.ERROR_MESSAGE);
				}
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Los datos ya fueron cargados anteriormente.", "Error", JOptionPane.WARNING_MESSAGE);
			}
		});
		
	}
}
