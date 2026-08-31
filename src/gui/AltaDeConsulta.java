package gui;

import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

public class AltaDeConsulta extends JInternalFrame {

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
		springLayout.putConstraint(SpringLayout.WEST, nomDLabel, 10, SpringLayout.WEST, getContentPane());
		getContentPane().add(nomDLabel);
		
		JLabel medLabel = new JLabel("Nickname del Médico:");
		springLayout.putConstraint(SpringLayout.SOUTH, medLabel, -249, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, nomDLabel, -25, SpringLayout.NORTH, medLabel);
		springLayout.putConstraint(SpringLayout.WEST, medLabel, 0, SpringLayout.WEST, nomDLabel);
		getContentPane().add(medLabel);
		
		JLabel lblNewLabel = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 25, SpringLayout.SOUTH, medLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, nomDLabel);
		getContentPane().add(lblNewLabel);
		
   }
}

