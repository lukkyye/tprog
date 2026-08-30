package gui;

import javax.swing.DefaultListModel;
import javax.swing.JInternalFrame;
import javax.swing.SpringLayout;

import logica.datatypes.DTClinica;
import logica.manejadores.ManejadorClinica;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JList;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;


public class ConsultaDeClinica extends JInternalFrame {
	private JTextField nameTextField;
	private JTextField descTextField;
	private JTextField webTextField;
	private JTextField cticketField;
	private JTextField cconsultaField;
	private JTextField dirTextField;

    public ConsultaDeClinica() {

        super(
            "Consulta de Clinica", // título
            true,              // se puede redimensionar
            true,              // se puede cerrar
            true,              // se puede maximizar
            true               // se puede minimizar
        );

        
        setSize(600, 500);
        SpringLayout springLayout = new SpringLayout();
        getContentPane().setLayout(springLayout);
        
        JPanel panel = new JPanel();
        springLayout.putConstraint(SpringLayout.NORTH, panel, 38, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, panel, 146, SpringLayout.NORTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, panel, 582, SpringLayout.WEST, getContentPane());
        getContentPane().add(panel);
        panel.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane);
        
        
        DefaultListModel<String> clinicasDisp = new DefaultListModel<String>();
        JList<String> list = new JList<String>(clinicasDisp);
        ArrayList<String> listable = ManejadorClinica.get_instance().getClinicas().keySet().stream().collect(Collectors.toCollection(ArrayList::new));
        clinicasDisp.addAll(listable);
        scrollPane.setViewportView(list);
        
        JLabel clinicDispLabel = new JLabel("Clinicas disponibles:");
        springLayout.putConstraint(SpringLayout.WEST, clinicDispLabel, 222, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, clinicDispLabel, -6, SpringLayout.NORTH, panel);
        getContentPane().add(clinicDispLabel);
        
        JPanel panel_1 = new JPanel();
        springLayout.putConstraint(SpringLayout.NORTH, panel_1, -284, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -40, SpringLayout.SOUTH, getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, panel_1, 582, SpringLayout.WEST, getContentPane());
        getContentPane().add(panel_1);
        SpringLayout sl_panel_1 = new SpringLayout();
        panel_1.setLayout(sl_panel_1);
        
        JLabel nameClinLabel = new JLabel("Nombre:");
        sl_panel_1.putConstraint(SpringLayout.NORTH, nameClinLabel, 10, SpringLayout.NORTH, panel_1);
        panel_1.add(nameClinLabel);
        
        nameTextField = new JTextField();
        sl_panel_1.putConstraint(SpringLayout.NORTH, nameTextField, 8, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, nameTextField, 27, SpringLayout.EAST, nameClinLabel);
        nameTextField.setEditable(false);
        panel_1.add(nameTextField);
        nameTextField.setColumns(10);
        
        JLabel lblDescripcion = new JLabel("Descripcion:");
        sl_panel_1.putConstraint(SpringLayout.NORTH, lblDescripcion, 41, SpringLayout.SOUTH, nameClinLabel);
        sl_panel_1.putConstraint(SpringLayout.WEST, lblDescripcion, 10, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.WEST, nameClinLabel, 0, SpringLayout.WEST, lblDescripcion);
        panel_1.add(lblDescripcion);
        
        descTextField = new JTextField();
        sl_panel_1.putConstraint(SpringLayout.NORTH, descTextField, 37, SpringLayout.SOUTH, nameTextField);
        sl_panel_1.putConstraint(SpringLayout.WEST, descTextField, 6, SpringLayout.EAST, lblDescripcion);
        sl_panel_1.putConstraint(SpringLayout.EAST, nameTextField, 0, SpringLayout.EAST, descTextField);
        descTextField.setEditable(false);
        descTextField.setColumns(10);
        panel_1.add(descTextField);
        
        JLabel lblWeb = new JLabel("Link web:");
        sl_panel_1.putConstraint(SpringLayout.NORTH, lblWeb, 0, SpringLayout.NORTH, nameClinLabel);
        panel_1.add(lblWeb);
        
        webTextField = new JTextField();
        sl_panel_1.putConstraint(SpringLayout.WEST, webTextField, 377, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, lblWeb, -6, SpringLayout.WEST, webTextField);
        sl_panel_1.putConstraint(SpringLayout.NORTH, webTextField, 8, SpringLayout.NORTH, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, webTextField, -43, SpringLayout.EAST, panel_1);
        webTextField.setEditable(false);
        webTextField.setColumns(10);
        panel_1.add(webTextField);
        
        cticketField = new JTextField();
        sl_panel_1.putConstraint(SpringLayout.NORTH, cticketField, -2, SpringLayout.NORTH, lblDescripcion);
        sl_panel_1.putConstraint(SpringLayout.WEST, cticketField, 469, SpringLayout.WEST, panel_1);
        sl_panel_1.putConstraint(SpringLayout.EAST, cticketField, -43, SpringLayout.EAST, panel_1);
        cticketField.setEditable(false);
        cticketField.setColumns(10);
        panel_1.add(cticketField);
        
        cconsultaField = new JTextField();
        sl_panel_1.putConstraint(SpringLayout.NORTH, cconsultaField, 51, SpringLayout.SOUTH, cticketField);
        sl_panel_1.putConstraint(SpringLayout.EAST, cconsultaField, -43, SpringLayout.EAST, panel_1);
        cconsultaField.setEditable(false);
        cconsultaField.setColumns(10);
        panel_1.add(cconsultaField);
        
        dirTextField = new JTextField();
        sl_panel_1.putConstraint(SpringLayout.NORTH, dirTextField, 51, SpringLayout.SOUTH, descTextField);
        dirTextField.setEditable(false);
        dirTextField.setColumns(10);
        panel_1.add(dirTextField);
        
        JLabel lblNewLabel_2_1 = new JLabel("Costo de Ticket:");
        sl_panel_1.putConstraint(SpringLayout.EAST, descTextField, -125, SpringLayout.WEST, lblNewLabel_2_1);
        sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_2_1, 0, SpringLayout.NORTH, lblDescripcion);
        sl_panel_1.putConstraint(SpringLayout.EAST, lblNewLabel_2_1, -17, SpringLayout.WEST, cticketField);
        panel_1.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Costo de Consulta:");
        sl_panel_1.putConstraint(SpringLayout.WEST, cconsultaField, 17, SpringLayout.EAST, lblNewLabel_2_1_1);
        sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_2_1_1, 2, SpringLayout.NORTH, cconsultaField);
        sl_panel_1.putConstraint(SpringLayout.EAST, lblNewLabel_2_1_1, 0, SpringLayout.EAST, lblNewLabel_2_1);
        panel_1.add(lblNewLabel_2_1_1);
        
        JLabel lblNewLabel_2_1_2 = new JLabel("Direccion:");
        sl_panel_1.putConstraint(SpringLayout.WEST, dirTextField, 20, SpringLayout.EAST, lblNewLabel_2_1_2);
        sl_panel_1.putConstraint(SpringLayout.EAST, dirTextField, 162, SpringLayout.EAST, lblNewLabel_2_1_2);
        sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_2_1_2, 55, SpringLayout.SOUTH, lblDescripcion);
        sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_2_1_2, 10, SpringLayout.WEST, panel_1);
        panel_1.add(lblNewLabel_2_1_2);
        
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        nameTextField.setFocusable(false);
        dirTextField.setFocusable(false);
        descTextField.setFocusable(false);
        webTextField.setFocusable(false);
        cconsultaField.setFocusable(false);
		cticketField.setFocusable(false);

        
        list.addListSelectionListener(e -> {
        	if (!e.getValueIsAdjusting()) {
        		DTClinica nclinica = ManejadorClinica.get_instance().findClinica(list.getSelectedValue()).get_dt();
        		
        		nameTextField.setText(nclinica.nombre());
        		dirTextField.setText(nclinica.dir());
        		descTextField.setText(nclinica.desc());
        		webTextField.setText(nclinica.web());
        		cconsultaField.setText(String.valueOf(nclinica.cConsulta()));
        		cticketField.setText(String.valueOf(nclinica.cTicket()));
        		
        		
        		panel_1.revalidate();
        		panel_1.repaint();
        	}
        });
   }
}
