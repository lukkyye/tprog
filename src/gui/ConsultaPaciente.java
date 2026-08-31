package gui;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import logica.controladores.ControladorUsuario;
import logica.datatypes.*;
import javax.swing.JList;
import javax.swing.JScrollPane;


public class ConsultaPaciente extends JInternalFrame {

    private String nickname;

    public ConsultaPaciente(String nickname) {
        super(
            "Consulta de Paciente",
            true,
            true,
            true,
            true
        );

        this.nickname = nickname;
        
        ControladorUsuario controlador = new ControladorUsuario();

        DTPaciente paciente =
                controlador.listarDatosPaciente(nickname);
        

        setSize(600, 692);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 109, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 22, 102, 41, 209, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblNewLabel = new JLabel("Nickname:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        JLabel lblNickname = new JLabel("lblNickname");
        GridBagConstraints gbc_lblNickname = new GridBagConstraints();
        gbc_lblNickname.insets = new Insets(0, 0, 5, 0);
        gbc_lblNickname.gridx = 3;
        gbc_lblNickname.gridy = 1;
        getContentPane().add(lblNickname, gbc_lblNickname);
        
        JLabel lblNewLabel_1 = new JLabel("Fecha Nacimiento:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 2;
        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        JLabel lblFechaNacimiento = new JLabel("lblFechaNacimiento");
        GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
        gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 0);
        gbc_lblFechaNacimiento.gridx = 3;
        gbc_lblFechaNacimiento.gridy = 2;
        getContentPane().add(lblFechaNacimiento, gbc_lblFechaNacimiento);
        
        JLabel lblNewLabel_2 = new JLabel("Documento:");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 3;
        getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        JLabel lblDocumento = new JLabel("lblDocumento");
        GridBagConstraints gbc_lblDocumento = new GridBagConstraints();
        gbc_lblDocumento.insets = new Insets(0, 0, 5, 0);
        gbc_lblDocumento.gridx = 3;
        gbc_lblDocumento.gridy = 3;
        getContentPane().add(lblDocumento, gbc_lblDocumento);
        
        JLabel lblNewLabel_3 = new JLabel("Fecha de Ingreso:");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 1;
        gbc_lblNewLabel_3.gridy = 4;
        getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
        
        JLabel lblFechaIngreso = new JLabel("lblFechaIngreso");
        GridBagConstraints gbc_lblFechaIngreso = new GridBagConstraints();
        gbc_lblFechaIngreso.insets = new Insets(0, 0, 5, 0);
        gbc_lblFechaIngreso.gridx = 3;
        gbc_lblFechaIngreso.gridy = 4;
        getContentPane().add(lblFechaIngreso, gbc_lblFechaIngreso);
        
        JLabel lblNewLabel_4 = new JLabel("Ordenes Acumuladas:");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 1;
        gbc_lblNewLabel_4.gridy = 5;
        getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
        
        JLabel lblOrdenes = new JLabel("lblOrdenes");
        GridBagConstraints gbc_lblOrdenes = new GridBagConstraints();
        gbc_lblOrdenes.insets = new Insets(0, 0, 5, 0);
        gbc_lblOrdenes.gridx = 3;
        gbc_lblOrdenes.gridy = 5;
        getContentPane().add(lblOrdenes, gbc_lblOrdenes);
        
        JLabel lblNewLabel_5 = new JLabel("Tickets Acumulados:");
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_5.gridx = 1;
        gbc_lblNewLabel_5.gridy = 6;
        getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
        
        JLabel lblTickets = new JLabel("lblTickets");
        GridBagConstraints gbc_lblTickets = new GridBagConstraints();
        gbc_lblTickets.insets = new Insets(0, 0, 5, 0);
        gbc_lblTickets.gridx = 3;
        gbc_lblTickets.gridy = 6;
        getContentPane().add(lblTickets, gbc_lblTickets);
        
        JLabel lblNewLabel_6 = new JLabel("Antecedentes de Salud:");
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_6.gridx = 1;
        gbc_lblNewLabel_6.gridy = 8;
        getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
        
        JList list = new JList();
        GridBagConstraints gbc_list = new GridBagConstraints();
        gbc_list.insets = new Insets(0, 0, 5, 0);
        gbc_list.fill = GridBagConstraints.BOTH;
        gbc_list.gridx = 3;
        gbc_list.gridy = 8;
        getContentPane().add(list, gbc_list);
        
        JLabel lblNewLabel_6_1 = new JLabel("Reservas de Consultas:");
        GridBagConstraints gbc_lblNewLabel_6_1 = new GridBagConstraints();
        gbc_lblNewLabel_6_1.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_6_1.gridx = 1;
        gbc_lblNewLabel_6_1.gridy = 10;
        getContentPane().add(lblNewLabel_6_1, gbc_lblNewLabel_6_1);
        
        lblNickname.setText(paciente.nickname());

        lblFechaNacimiento.setText(
                paciente.fechaNac().toString()
        );

        lblDocumento.setText(
                String.valueOf(paciente.doc())
        );

        lblFechaIngreso.setText(
                paciente.fechaIngreso().toString()
        );

        lblOrdenes.setText(
                String.valueOf(paciente.cantOrdenesAcum())
        );

        lblTickets.setText(
                String.valueOf(paciente.cantTicketAcum())
        );
        
        JList list_1 = new JList();
        GridBagConstraints gbc_list_1 = new GridBagConstraints();
        gbc_list_1.fill = GridBagConstraints.BOTH;
        gbc_list_1.gridx = 3;
        gbc_list_1.gridy = 10;
        getContentPane().add(list_1, gbc_list_1);
    }
}