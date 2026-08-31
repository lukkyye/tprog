package gui;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AltaDeConsulta extends JInternalFrame {
	private static final long serialVersionUID = -6141709894943702613L;
	private JTextField nomDField;
	private JTextField medField;
	private JTextField nomCField;
	private JTextField salaField;
	private JTextField cantNumField;
	private JTextField ddField;
	private JTextField mmField;
	private JTextField yyyyField;

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
		
		JComboBox DiasConsultasBox = new JComboBox();
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
		
		JComboBox MedicosBox = new JComboBox();
		springLayout.putConstraint(SpringLayout.NORTH, MedicosBox, 37, SpringLayout.SOUTH, DiasConsultasBox);
		springLayout.putConstraint(SpringLayout.WEST, MedicosBox, 245, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, MedicosBox, 0, SpringLayout.EAST, DiasConsultasBox);
		getContentPane().add(MedicosBox);
		
		JLabel MedicosLabel = new JLabel("Médicos:");
		springLayout.putConstraint(SpringLayout.NORTH, MedicosLabel, 4, SpringLayout.NORTH, MedicosBox);
		springLayout.putConstraint(SpringLayout.WEST, MedicosLabel, 0, SpringLayout.WEST, DiaConsultaLabel);
		MedicosLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		getContentPane().add(MedicosLabel);
		
		JLabel nomDLabel = new JLabel("Nombre del Dia de Consulta:");
		springLayout.putConstraint(SpringLayout.WEST, nomDLabel, 0, SpringLayout.WEST, DiaConsultaLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, nomDLabel, -289, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(nomDLabel);
		
		nomDField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, nomDField, -2, SpringLayout.NORTH, nomDLabel);
		springLayout.putConstraint(SpringLayout.WEST, nomDField, 23, SpringLayout.EAST, nomDLabel);
		springLayout.putConstraint(SpringLayout.EAST, nomDField, 0, SpringLayout.EAST, DiasConsultasBox);
		getContentPane().add(nomDField);
		nomDField.setColumns(10);
		
		JLabel medLabel = new JLabel("Nickname del Médico:");
		springLayout.putConstraint(SpringLayout.NORTH, medLabel, 25, SpringLayout.SOUTH, nomDLabel);
		springLayout.putConstraint(SpringLayout.WEST, medLabel, 0, SpringLayout.WEST, DiaConsultaLabel);
		getContentPane().add(medLabel);
		
		medField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, medField, -2, SpringLayout.NORTH, medLabel);
		springLayout.putConstraint(SpringLayout.WEST, medField, 72, SpringLayout.EAST, medLabel);
		springLayout.putConstraint(SpringLayout.EAST, medField, 0, SpringLayout.EAST, DiasConsultasBox);
		medField.setColumns(10);
		getContentPane().add(medField);
		
		JLabel nomCLabel = new JLabel("Nombre de la Consulta:");
		springLayout.putConstraint(SpringLayout.NORTH, nomCLabel, 25, SpringLayout.SOUTH, medLabel);
		springLayout.putConstraint(SpringLayout.WEST, nomCLabel, 0, SpringLayout.WEST, DiaConsultaLabel);
		getContentPane().add(nomCLabel);
		
		nomCField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, nomCField, 23, SpringLayout.SOUTH, medField);
		springLayout.putConstraint(SpringLayout.WEST, nomCField, 59, SpringLayout.EAST, nomCLabel);
		springLayout.putConstraint(SpringLayout.EAST, nomCField, 0, SpringLayout.EAST, DiasConsultasBox);
		getContentPane().add(nomCField);
		nomCField.setColumns(10);
		
		JLabel fechaLabel = new JLabel("Fecha de la Consulta:");
		springLayout.putConstraint(SpringLayout.NORTH, fechaLabel, 25, SpringLayout.SOUTH, nomCLabel);
		springLayout.putConstraint(SpringLayout.WEST, fechaLabel, 0, SpringLayout.WEST, DiaConsultaLabel);
		getContentPane().add(fechaLabel);
		
		ddField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, ddField, 21, SpringLayout.SOUTH, nomCField);
		springLayout.putConstraint(SpringLayout.WEST, ddField, 72, SpringLayout.EAST, fechaLabel);
		springLayout.putConstraint(SpringLayout.EAST, ddField, 102, SpringLayout.EAST, fechaLabel);
		getContentPane().add(ddField);
		ddField.setColumns(10);
		
		mmField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, mmField, 0, SpringLayout.NORTH, fechaLabel);
		springLayout.putConstraint(SpringLayout.WEST, mmField, 45, SpringLayout.EAST, ddField);
		mmField.setColumns(10);
		getContentPane().add(mmField);
		
		yyyyField = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, mmField, -43, SpringLayout.WEST, yyyyField);
		springLayout.putConstraint(SpringLayout.NORTH, yyyyField, 0, SpringLayout.NORTH, fechaLabel);
		springLayout.putConstraint(SpringLayout.WEST, yyyyField, 447, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, yyyyField, -74, SpringLayout.EAST, getContentPane());
		yyyyField.setColumns(10);
		getContentPane().add(yyyyField);
		
		JLabel salaLabel = new JLabel("Número de sala:");
		springLayout.putConstraint(SpringLayout.NORTH, salaLabel, 25, SpringLayout.SOUTH, fechaLabel);
		springLayout.putConstraint(SpringLayout.WEST, salaLabel, 0, SpringLayout.WEST, DiaConsultaLabel);
		getContentPane().add(salaLabel);
		
		salaField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, salaField, 61, SpringLayout.SOUTH, nomCField);
		springLayout.putConstraint(SpringLayout.WEST, salaField, -293, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, salaField, -74, SpringLayout.EAST, getContentPane());
		getContentPane().add(salaField);
		salaField.setColumns(10);
		
		JLabel cantNumLabel = new JLabel("Cantidad de Números:");
		springLayout.putConstraint(SpringLayout.NORTH, cantNumLabel, 25, SpringLayout.SOUTH, salaLabel);
		springLayout.putConstraint(SpringLayout.WEST, cantNumLabel, 0, SpringLayout.WEST, DiaConsultaLabel);
		getContentPane().add(cantNumLabel);
		
		cantNumField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, cantNumField, 17, SpringLayout.SOUTH, salaField);
		springLayout.putConstraint(SpringLayout.WEST, cantNumField, 0, SpringLayout.WEST, nomDField);
		springLayout.putConstraint(SpringLayout.EAST, cantNumField, 0, SpringLayout.EAST, DiasConsultasBox);
		getContentPane().add(cantNumField);
		cantNumField.setColumns(10);
		
		JButton cancelarButton = new JButton("Cancelar");
		springLayout.putConstraint(SpringLayout.SOUTH, cancelarButton, -25, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, cancelarButton, 0, SpringLayout.EAST, DiasConsultasBox);
		getContentPane().add(cancelarButton);
		
		JButton aceptarButton = new JButton("Aceptar");
		springLayout.putConstraint(SpringLayout.SOUTH, aceptarButton, 0, SpringLayout.SOUTH, cancelarButton);
		springLayout.putConstraint(SpringLayout.EAST, aceptarButton, 0, SpringLayout.EAST, mmField);
		getContentPane().add(aceptarButton);
		
   }
}

