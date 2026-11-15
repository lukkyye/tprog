package gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import  logica.datatypes.*;
import  logica.controladores.*;

import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import  logica.datatypes.DtPaciente;

public class ConsultaDeUsuario extends JInternalFrame {

    private JTextField txtIngreseUsuario;

    public ConsultaDeUsuario() {

        super(
            "Consulta de Usuario", // título
            true,                  // se puede redimensionar
            true,                  // se puede cerrar
            true,                  // se puede maximizar
            true                   // se puede minimizar
        );

        setSize(600, 500);

        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWidths = new int[]{80, 358, 0, 0, 0};

        gridBagLayout.rowHeights = new int[]{
            0, 39, 284, 20, 0, 0, 0, 0, 0, 0
        };

        gridBagLayout.columnWeights = new double[]{
            0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE
        };

        gridBagLayout.rowWeights = new double[]{
            0.0, 0.0, 1.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE
        };

        getContentPane().setLayout(gridBagLayout);



        JLabel lblUsuarios = new JLabel("Usuarios en el sistema:");

        GridBagConstraints gbc_lblUsuarios = new GridBagConstraints();

        gbc_lblUsuarios.anchor = GridBagConstraints.WEST;
        gbc_lblUsuarios.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsuarios.gridx = 1;
        gbc_lblUsuarios.gridy = 1;

        getContentPane().add(lblUsuarios, gbc_lblUsuarios);



        JScrollPane scrollPane = new JScrollPane();

        GridBagConstraints gbc_scrollPane = new GridBagConstraints();

        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 2;

        getContentPane().add(scrollPane, gbc_scrollPane);


        JList<String> listaUsuarios = new JList<String>();

        listaUsuarios.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        ControladorUsuario controlador = new ControladorUsuario();

        Set<DtUsuario> usuarios = controlador.listarUsuarios();

        String[] nicknames = usuarios.stream()
                .map(dtu->dtu.getNickname())
                .toArray(String[]::new);

        listaUsuarios.setListData(nicknames);

        scrollPane.setViewportView(listaUsuarios);


        
        
        JLabel lblNickname = new JLabel("Ingrese Nickname:");

        GridBagConstraints gbc_lblNickname = new GridBagConstraints();

        gbc_lblNickname.anchor = GridBagConstraints.WEST;
        gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
        gbc_lblNickname.gridx = 1;
        gbc_lblNickname.gridy = 4;

        getContentPane().add(lblNickname, gbc_lblNickname);



        txtIngreseUsuario = new JTextField();

        GridBagConstraints gbc_txtIngreseUsuario =
            new GridBagConstraints();

        gbc_txtIngreseUsuario.fill =
            GridBagConstraints.HORIZONTAL;

        gbc_txtIngreseUsuario.insets =
            new Insets(0, 0, 5, 5);

        gbc_txtIngreseUsuario.gridx = 1;
        gbc_txtIngreseUsuario.gridy = 5;

        getContentPane().add(
            txtIngreseUsuario,
            gbc_txtIngreseUsuario
        );

        txtIngreseUsuario.setColumns(10);


        JButton btnSeleccionar =
            new JButton("Seleccionar");
        
        btnSeleccionar.addActionListener(e -> {

            String nickname = listaUsuarios.getSelectedValue();

            if (nickname == null) {
                JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un usuario de la lista.",
                    "Usuario no seleccionado",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            boolean esPaciente = controlador.listarPacientes()
                    .stream()
                    .anyMatch(paciente ->
                        paciente.getNickname().equals(nickname)
                    );

            JDesktopPane desktopPane = getDesktopPane();

            if (esPaciente) {
            	//solo tiene un elemento y es el DTUsuario que tiene unickname=nickname
            	DtUsuario pseudolista = usuarios
            			.stream()
            			.filter(u->u.getNickname().equals(nickname))
            			.collect(Collectors.toList()).getFirst();
                ConsultaPaciente ventanaPaciente =
                        new ConsultaPaciente(nickname, pseudolista.getNombre(), pseudolista.getApellido(),pseudolista.getCorreo());

                desktopPane.add(ventanaPaciente);
                ventanaPaciente.setVisible(true);

            } else {

                ConsultaMedico ventanaMedico =
                        new ConsultaMedico(nickname);

                desktopPane.add(ventanaMedico);
                ventanaMedico.setVisible(true);
            }

            dispose();
        });
        

        GridBagConstraints gbc_btnSeleccionar =
            new GridBagConstraints();

        gbc_btnSeleccionar.insets =
            new Insets(0, 0, 5, 5);

        gbc_btnSeleccionar.gridx = 2;
        gbc_btnSeleccionar.gridy = 5;

        getContentPane().add(
            btnSeleccionar,
            gbc_btnSeleccionar
        );


        listaUsuarios.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                String nicknameSeleccionado =
                    listaUsuarios.getSelectedValue();

                if (nicknameSeleccionado != null) {

                    txtIngreseUsuario.setText(
                        nicknameSeleccionado
                    );
                }
            }
        });

    }
}