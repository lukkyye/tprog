package gui;
import javax.swing.JInternalFrame;
import logica.datatypes.DTResConsultaDetalle;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ConsultaReserva extends JInternalFrame{
	public ConsultaReserva(DTResConsultaDetalle detalle) {
	    super("Detalle de Reserva de Consulta", true, true, true, true);

	    setSize(600, 500);
	    GridBagLayout gridBagLayout = new GridBagLayout();
	    gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 92, 0};
	    gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	    getContentPane().setLayout(gridBagLayout);
	    
	    JLabel lblNewLabel_1 = new JLabel("Número de Reserva: ");
	    GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
	    gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNewLabel_1.gridx = 5;
	    gbc_lblNewLabel_1.gridy = 1;
	    getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
	    
	    JLabel lblNumeroReserva = new JLabel("New label");
	    GridBagConstraints gbc_lblNumeroReserva = new GridBagConstraints();
	    gbc_lblNumeroReserva.insets = new Insets(0, 0, 5, 0);
	    gbc_lblNumeroReserva.gridx = 7;
	    gbc_lblNumeroReserva.gridy = 1;
	    getContentPane().add(lblNumeroReserva, gbc_lblNumeroReserva);
	    
	    JLabel lblNewLabel_1_2 = new JLabel("Fecha de Reserva: ");
	    GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
	    gbc_lblNewLabel_1_2.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNewLabel_1_2.gridx = 5;
	    gbc_lblNewLabel_1_2.gridy = 2;
	    getContentPane().add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);
	    
	    JLabel lblFechaReserva = new JLabel("New label");
	    GridBagConstraints gbc_lblFechaReserva = new GridBagConstraints();
	    gbc_lblFechaReserva.insets = new Insets(0, 0, 5, 0);
	    gbc_lblFechaReserva.gridx = 7;
	    gbc_lblFechaReserva.gridy = 2;
	    getContentPane().add(lblFechaReserva, gbc_lblFechaReserva);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("Aplica Beneficio:  ");
	    GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
	    gbc_lblNewLabel_1_1.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNewLabel_1_1.gridx = 5;
	    gbc_lblNewLabel_1_1.gridy = 3;
	    getContentPane().add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
	    
	    JLabel lblBeneficio = new JLabel("New label");
	    GridBagConstraints gbc_lblBeneficio = new GridBagConstraints();
	    gbc_lblBeneficio.insets = new Insets(0, 0, 5, 0);
	    gbc_lblBeneficio.gridx = 7;
	    gbc_lblBeneficio.gridy = 3;
	    getContentPane().add(lblBeneficio, gbc_lblBeneficio);
	    
	    JLabel lblNewLabel_1_1_1 = new JLabel("Medico:");
	    GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
	    gbc_lblNewLabel_1_1_1.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNewLabel_1_1_1.gridx = 5;
	    gbc_lblNewLabel_1_1_1.gridy = 4;
	    getContentPane().add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
	    
	    JLabel lblMedico = new JLabel("New label");
	    GridBagConstraints gbc_lblMedico = new GridBagConstraints();
	    gbc_lblMedico.insets = new Insets(0, 0, 5, 0);
	    gbc_lblMedico.gridx = 7;
	    gbc_lblMedico.gridy = 4;
	    getContentPane().add(lblMedico, gbc_lblMedico);
	    

	    lblNumeroReserva.setText(String.valueOf(detalle.numReserva()));	    
	    
	    lblFechaReserva.setText(detalle.fechaRes().toString());

	    lblBeneficio.setText(
	        detalle.aplicaBenef() ? "Sí" : "No"
	    );

	    
	    lblMedico.setText(detalle.medicoC());
	    
	    JLabel lblNewLabel_1_1_1_1 = new JLabel("Consulta:");
	    GridBagConstraints gbc_lblNewLabel_1_1_1_1 = new GridBagConstraints();
	    gbc_lblNewLabel_1_1_1_1.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_1_1_1_1.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNewLabel_1_1_1_1.gridx = 5;
	    gbc_lblNewLabel_1_1_1_1.gridy = 7;
	    getContentPane().add(lblNewLabel_1_1_1_1, gbc_lblNewLabel_1_1_1_1);
	    
	    JLabel lblConsulta = new JLabel("New label");
	    GridBagConstraints gbc_lblConsulta = new GridBagConstraints();
	    gbc_lblConsulta.insets = new Insets(0, 0, 5, 0);
	    gbc_lblConsulta.gridx = 7;
	    gbc_lblConsulta.gridy = 7;
	    getContentPane().add(lblConsulta, gbc_lblConsulta);
	    lblConsulta.setText(detalle.nomC());
	    
	    JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Fecha de Consulta:");
	    GridBagConstraints gbc_lblNewLabel_1_1_1_1_1 = new GridBagConstraints();
	    gbc_lblNewLabel_1_1_1_1_1.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_1_1_1_1_1.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNewLabel_1_1_1_1_1.gridx = 5;
	    gbc_lblNewLabel_1_1_1_1_1.gridy = 8;
	    getContentPane().add(lblNewLabel_1_1_1_1_1, gbc_lblNewLabel_1_1_1_1_1);
	    
	    JLabel lblFechaConsulta = new JLabel("New label");
	    GridBagConstraints gbc_lblFechaConsulta = new GridBagConstraints();
	    gbc_lblFechaConsulta.insets = new Insets(0, 0, 5, 0);
	    gbc_lblFechaConsulta.gridx = 7;
	    gbc_lblFechaConsulta.gridy = 8;
	    getContentPane().add(lblFechaConsulta, gbc_lblFechaConsulta);
	    lblFechaConsulta.setText(detalle.fechaC().toString());
	    
	    JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Sala:");
	    GridBagConstraints gbc_lblNewLabel_1_1_1_1_1_1 = new GridBagConstraints();
	    gbc_lblNewLabel_1_1_1_1_1_1.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_1_1_1_1_1_1.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNewLabel_1_1_1_1_1_1.gridx = 5;
	    gbc_lblNewLabel_1_1_1_1_1_1.gridy = 9;
	    getContentPane().add(lblNewLabel_1_1_1_1_1_1, gbc_lblNewLabel_1_1_1_1_1_1);
	    
	    JLabel lblSala = new JLabel("New label");
	    GridBagConstraints gbc_lblSala = new GridBagConstraints();
	    gbc_lblSala.insets = new Insets(0, 0, 5, 0);
	    gbc_lblSala.gridx = 7;
	    gbc_lblSala.gridy = 9;
	    getContentPane().add(lblSala, gbc_lblSala);
	    
	    lblSala.setText(String.valueOf(detalle.salaC()));
	    
	    JLabel lblNewLabel_1_1_1_1_1_1_1_1_1 = new JLabel("Cantidad de Números: ");
	    GridBagConstraints gbc_lblNewLabel_1_1_1_1_1_1_1_1_1 = new GridBagConstraints();
	    gbc_lblNewLabel_1_1_1_1_1_1_1_1_1.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_1_1_1_1_1_1_1_1_1.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNewLabel_1_1_1_1_1_1_1_1_1.gridx = 5;
	    gbc_lblNewLabel_1_1_1_1_1_1_1_1_1.gridy = 10;
	    getContentPane().add(lblNewLabel_1_1_1_1_1_1_1_1_1, gbc_lblNewLabel_1_1_1_1_1_1_1_1_1);
	    
	    JLabel lblCantidadNumeros = new JLabel("New label");
	    GridBagConstraints gbc_lblCantidadNumeros = new GridBagConstraints();
	    gbc_lblCantidadNumeros.insets = new Insets(0, 0, 5, 0);
	    gbc_lblCantidadNumeros.gridx = 7;
	    gbc_lblCantidadNumeros.gridy = 10;
	    getContentPane().add(lblCantidadNumeros, gbc_lblCantidadNumeros);
	    lblCantidadNumeros.setText(String.valueOf(detalle.cantNumC()));
	    
	    JLabel lblNewLabel = new JLabel("Costo Consulta:");
	    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
	    gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
	    gbc_lblNewLabel.gridx = 5;
	    gbc_lblNewLabel.gridy = 11;
	    getContentPane().add(lblNewLabel, gbc_lblNewLabel);
	    
	    JLabel lblCostoConsulta = new JLabel("New label");
	    GridBagConstraints gbc_lblCostoConsulta = new GridBagConstraints();
	    gbc_lblCostoConsulta.insets = new Insets(0, 0, 5, 0);
	    gbc_lblCostoConsulta.gridx = 7;
	    gbc_lblCostoConsulta.gridy = 11;
	    getContentPane().add(lblCostoConsulta, gbc_lblCostoConsulta);
	    lblCostoConsulta.setText("$" + detalle.costoC());
	    
	    JLabel lblNewLabel_2_1 = new JLabel("Costo Reserva:");
	    GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
	    gbc_lblNewLabel_2_1.anchor = GridBagConstraints.WEST;
	    gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 0, 5);
	    gbc_lblNewLabel_2_1.gridx = 5;
	    gbc_lblNewLabel_2_1.gridy = 12;
	    getContentPane().add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);
	    
	    JLabel lblCostoReserva = new JLabel("New label");
	    GridBagConstraints gbc_lblCostoReserva = new GridBagConstraints();
	    gbc_lblCostoReserva.gridx = 7;
	    gbc_lblCostoReserva.gridy = 12;
	    getContentPane().add(lblCostoReserva, gbc_lblCostoReserva);
	    lblCostoReserva.setText("$" + detalle.costoR());
	}
}
