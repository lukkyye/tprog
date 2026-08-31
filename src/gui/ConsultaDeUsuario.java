package gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.DropMode;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;

public class ConsultaDeUsuario extends JInternalFrame {
	private JTextField txtIngreseUsuario;

    public ConsultaDeUsuario() {

        super(
            "Consulta de Usuario", // título
            true,              // se puede redimensionar
            true,              // se puede cerrar
            true,              // se puede maximizar
            true               // se puede minimizar
        );

        setSize(600, 500);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{80, 358, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 39, 284, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblNewLabel = new JLabel("Usuarios en el sistema:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 2;
        getContentPane().add(scrollPane, gbc_scrollPane);
        
        JList listaUsuarios = new JList();
        listaUsuarios.setModel(new AbstractListModel() {
        	String[] values = new String[] {"juapPerez23", "lulitapanizzi", "mickeyMouse1890"};
        	public int getSize() {
        		return values.length;
        	}
        	public Object getElementAt(int index) {
        		return values[index];
        	}
        });
        scrollPane.setViewportView(listaUsuarios);
        
        txtIngreseUsuario = new JTextField();
        txtIngreseUsuario.setText("Ingrese Nickname:");
        GridBagConstraints gbc_txtIngreseUsuario = new GridBagConstraints();
        gbc_txtIngreseUsuario.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtIngreseUsuario.insets = new Insets(0, 0, 5, 5);
        gbc_txtIngreseUsuario.gridx = 1;
        gbc_txtIngreseUsuario.gridy = 4;
        getContentPane().add(txtIngreseUsuario, gbc_txtIngreseUsuario);
        txtIngreseUsuario.setColumns(10);
        
        JButton btnNewButton = new JButton("Seleccionar");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 2;
        gbc_btnNewButton.gridy = 4;
        getContentPane().add(btnNewButton, gbc_btnNewButton);
    }
}