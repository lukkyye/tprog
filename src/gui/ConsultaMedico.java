package gui;

import javax.swing.JInternalFrame;
import logica.controladores.*;
import logica.datatypes.*;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;

import java.util.ArrayList;
import java.util.HashSet;

import logica.datatypes.DTConsulta;
import logica.datatypes.DTConsultaDetalle;

public class ConsultaMedico extends JInternalFrame {

    private String nickname;
    private ArrayList<DTConsulta> consultasMedico;

    public ConsultaMedico(String nickname) {
        super(
            "Consulta de Médico",
            true,
            true,
            true,
            true
        );

        this.nickname = nickname;

        setSize(600, 500);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 97, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 114, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblNewLabel = new JLabel("Nickname:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        JLabel lblNickname = new JLabel("New label");
        GridBagConstraints gbc_lblNickname = new GridBagConstraints();
        gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
        gbc_lblNickname.gridx = 3;
        gbc_lblNickname.gridy = 1;
        getContentPane().add(lblNickname, gbc_lblNickname);
        
        JLabel lblNewLabel_1_1 = new JLabel("Nombre:");
        GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1.gridx = 1;
        gbc_lblNewLabel_1_1.gridy = 2;
        getContentPane().add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
        
        JLabel lblNombre = new JLabel("New label");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 3;
        gbc_lblNombre.gridy = 2;
        getContentPane().add(lblNombre, gbc_lblNombre);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Apellido:");
        GridBagConstraints gbc_lblNewLabel_1_1_2 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_2.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_2.gridx = 1;
        gbc_lblNewLabel_1_1_2.gridy = 3;
        getContentPane().add(lblNewLabel_1_1_2, gbc_lblNewLabel_1_1_2);
        
        JLabel lblApellido = new JLabel("New label");
        GridBagConstraints gbc_lblApellido = new GridBagConstraints();
        gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
        gbc_lblApellido.gridx = 3;
        gbc_lblApellido.gridy = 3;
        getContentPane().add(lblApellido, gbc_lblApellido);
        
        JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Correo: ");
        GridBagConstraints gbc_lblNewLabel_1_1_2_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_2_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1_2_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_2_1_1.gridx = 1;
        gbc_lblNewLabel_1_1_2_1_1.gridy = 4;
        getContentPane().add(lblNewLabel_1_1_2_1_1, gbc_lblNewLabel_1_1_2_1_1);
        
        JLabel lblCorreo = new JLabel("New label");
        GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
        gbc_lblCorreo.insets = new Insets(0, 0, 5, 5);
        gbc_lblCorreo.gridx = 3;
        gbc_lblCorreo.gridy = 4;
        getContentPane().add(lblCorreo, gbc_lblCorreo);
        
        JLabel lblNewLabel_1_1_2_1_1_1_1 = new JLabel("Número de Registro:");
        GridBagConstraints gbc_lblNewLabel_1_1_2_1_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_2_1_1_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1_2_1_1_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_2_1_1_1_1.gridx = 1;
        gbc_lblNewLabel_1_1_2_1_1_1_1.gridy = 6;
        getContentPane().add(lblNewLabel_1_1_2_1_1_1_1, gbc_lblNewLabel_1_1_2_1_1_1_1);
        
        JLabel lblNumeroDeRegistro = new JLabel("New label");
        GridBagConstraints gbc_lblNumeroDeRegistro = new GridBagConstraints();
        gbc_lblNumeroDeRegistro.insets = new Insets(0, 0, 5, 5);
        gbc_lblNumeroDeRegistro.gridx = 3;
        gbc_lblNumeroDeRegistro.gridy = 6;
        getContentPane().add(lblNumeroDeRegistro, gbc_lblNumeroDeRegistro);
        
        JLabel lblNewLabel_1_1_2_1_1_1_1_1_1 = new JLabel("Mes de Licencia:");
        GridBagConstraints gbc_lblNewLabel_1_1_2_1_1_1_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_2_1_1_1_1_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1_2_1_1_1_1_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_2_1_1_1_1_1_1.gridx = 1;
        gbc_lblNewLabel_1_1_2_1_1_1_1_1_1.gridy = 7;
        getContentPane().add(lblNewLabel_1_1_2_1_1_1_1_1_1, gbc_lblNewLabel_1_1_2_1_1_1_1_1_1);
        
        JLabel lblMesDeLicencia = new JLabel("New label");
        GridBagConstraints gbc_lblMesDeLicencia = new GridBagConstraints();
        gbc_lblMesDeLicencia.insets = new Insets(0, 0, 5, 5);
        gbc_lblMesDeLicencia.gridx = 3;
        gbc_lblMesDeLicencia.gridy = 7;
        getContentPane().add(lblMesDeLicencia, gbc_lblMesDeLicencia);
        
        JLabel lblNewLabel_1_1_2_1_1_1_1_1_1_1_1 = new JLabel("Porcentaje de Costo:");
        GridBagConstraints gbc_lblNewLabel_1_1_2_1_1_1_1_1_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_2_1_1_1_1_1_1_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1_2_1_1_1_1_1_1_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_2_1_1_1_1_1_1_1_1.gridx = 1;
        gbc_lblNewLabel_1_1_2_1_1_1_1_1_1_1_1.gridy = 8;
        getContentPane().add(lblNewLabel_1_1_2_1_1_1_1_1_1_1_1, gbc_lblNewLabel_1_1_2_1_1_1_1_1_1_1_1);
        
        JLabel lblPorcentajeDeCosto = new JLabel("New label");
        GridBagConstraints gbc_lblPorcentajeDeCosto = new GridBagConstraints();
        gbc_lblPorcentajeDeCosto.insets = new Insets(0, 0, 5, 5);
        gbc_lblPorcentajeDeCosto.gridx = 3;
        gbc_lblPorcentajeDeCosto.gridy = 8;
        getContentPane().add(lblPorcentajeDeCosto, gbc_lblPorcentajeDeCosto);
        
        JLabel lblNewLabel_1_1_2_1_1_1_1_1_1_1_1_1_1 = new JLabel("Link:  ");
        GridBagConstraints gbc_lblNewLabel_1_1_2_1_1_1_1_1_1_1_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_2_1_1_1_1_1_1_1_1_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1_2_1_1_1_1_1_1_1_1_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_2_1_1_1_1_1_1_1_1_1_1.gridx = 1;
        gbc_lblNewLabel_1_1_2_1_1_1_1_1_1_1_1_1_1.gridy = 9;
        getContentPane().add(lblNewLabel_1_1_2_1_1_1_1_1_1_1_1_1_1, gbc_lblNewLabel_1_1_2_1_1_1_1_1_1_1_1_1_1);
        
        JLabel lblLink = new JLabel("New label");
        GridBagConstraints gbc_lblLink = new GridBagConstraints();
        gbc_lblLink.insets = new Insets(0, 0, 5, 5);
        gbc_lblLink.gridx = 3;
        gbc_lblLink.gridy = 9;
        getContentPane().add(lblLink, gbc_lblLink);
        
        JLabel lblNewLabel_1 = new JLabel("Consultas Asociadas:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 11;
        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        JList listaConsultas = new JList();
        GridBagConstraints gbc_listaConsultas = new GridBagConstraints();
        gbc_listaConsultas.insets = new Insets(0, 0, 0, 5);
        gbc_listaConsultas.fill = GridBagConstraints.BOTH;
        gbc_listaConsultas.gridx = 3;
        gbc_listaConsultas.gridy = 11;
        getContentPane().add(listaConsultas, gbc_listaConsultas);
 

    
        ControladorUsuario controlador = new ControladorUsuario();

        DTMedico medico =
                controlador.listarDatosMedico(nickname);

        DTUsuario usuario = controlador.listarUsuarios()
                .stream()
                .filter(u -> u.nickname().equals(nickname))
                .findFirst()
                .orElse(null);

        // Datos comunes
        if (usuario != null) {
            lblNickname.setText(usuario.nickname());
            lblNombre.setText(usuario.nombre());
            lblApellido.setText(usuario.apellido());
            lblCorreo.setText(usuario.correo());
        }

        // Datos del médico
        lblNumeroDeRegistro.setText(
                String.valueOf(medico.nroReg())
        );

        lblMesDeLicencia.setText(
                String.valueOf(medico.mesLic())
        );

        lblPorcentajeDeCosto.setText(
                String.valueOf(medico.porcentajeCost())
        );

        lblLink.setText(
                medico.link()
        );
        
        HashSet<DTConsulta> consultas =
                controlador.listarConsultasAsociadas(nickname);

        consultasMedico = new ArrayList<>(consultas);

        if (consultasMedico.isEmpty()) {

            listaConsultas.setListData(
                new String[] {"No tiene consultas asociadas"}
            );

            listaConsultas.setEnabled(false);

        } else {

            String[] consultasParaMostrar = consultasMedico.stream()
                    .map(consulta ->
                        consulta.nomC()
                        + " - " + consulta.fechaC()
                        + " - Sala " + consulta.sala()
                    )
                    .toArray(String[]::new);

            listaConsultas.setListData(consultasParaMostrar);
            listaConsultas.setEnabled(true);
        }
    }
	}