package gui;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import logica.controladores.ControladorUsuario;
import logica.datatypes.*;
import logica.manejadores.ManejadorUsuario;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import java.util.HashSet;
import java.util.ArrayList;
import javax.swing.SwingConstants;




public class ConsultaPaciente extends JInternalFrame {

    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private ArrayList<DTResConsulta> reservasPaciente;

    public ConsultaPaciente(String nickname, String _nombre, String _apellido, String _correo) {
        super(
            "Consulta de Paciente",
            true,
            true,
            true,
            true
        );
        this.correo=_correo;
        this.apellido=_apellido;
        this.nombre=_nombre;
        this.nickname = nickname;
        
        ControladorUsuario controlador = new ControladorUsuario();

        DTPaciente paciente =
                controlador.listarDatosPaciente(nickname);
        

        setSize(600, 692);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 109, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 22, 0, 41, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
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
        gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
        gbc_lblNickname.gridx = 3;
        gbc_lblNickname.gridy = 1;
        getContentPane().add(lblNickname, gbc_lblNickname);
        
        DTUsuario usuario = ManejadorUsuario.get_instance().get_usuario(paciente.nickname()).get_dt();
        
        lblNickname.setText(usuario.nickname());
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.anchor = GridBagConstraints.WEST;
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 1;
        gbc_lblNombre.gridy = 2;
        getContentPane().add(lblNombre, gbc_lblNombre);
        
        JLabel nameField = new JLabel("New label");
        GridBagConstraints gbc_nameField = new GridBagConstraints();
        gbc_nameField.insets = new Insets(0, 0, 5, 5);
        gbc_nameField.gridx = 3;
        gbc_nameField.gridy = 2;
        getContentPane().add(nameField, gbc_nameField);
        nameField.setText(this.nombre);
        
        JLabel lblNewLabel_1_1 = new JLabel("Apellido:");
        GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1.gridx = 1;
        gbc_lblNewLabel_1_1.gridy = 3;
        getContentPane().add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
        
        JLabel surnameField = new JLabel("New label");
        GridBagConstraints gbc_surnameField = new GridBagConstraints();
        gbc_surnameField.insets = new Insets(0, 0, 5, 5);
        gbc_surnameField.gridx = 3;
        gbc_surnameField.gridy = 3;
        getContentPane().add(surnameField, gbc_surnameField);
        surnameField.setText(this.apellido);
        
        JLabel correoLabel = new JLabel("Correo Electronico:");
        GridBagConstraints gbc_correoLabel = new GridBagConstraints();
        gbc_correoLabel.anchor = GridBagConstraints.WEST;
        gbc_correoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_correoLabel.gridx = 1;
        gbc_correoLabel.gridy = 4;
        getContentPane().add(correoLabel, gbc_correoLabel);
        
        JLabel correoField = new JLabel("New label");
        GridBagConstraints gbc_correoField = new GridBagConstraints();
        gbc_correoField.insets = new Insets(0, 0, 5, 5);
        gbc_correoField.gridx = 3;
        gbc_correoField.gridy = 4;
        getContentPane().add(correoField, gbc_correoField);
        correoField.setText(this.correo);
        
        JLabel lblNewLabel_1 = new JLabel("Fecha Nacimiento:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 5;
        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        JLabel lblFechaNacimiento = new JLabel("lblFechaNacimiento");
        GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
        gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaNacimiento.gridx = 3;
        gbc_lblFechaNacimiento.gridy = 5;
        getContentPane().add(lblFechaNacimiento, gbc_lblFechaNacimiento);
        
        // esto hay que poner lblNombre.setText(usuario.nombre());
        
        // esto hay que poner lblApellido.setText(usuario.apellido());
        
        // esto hay que poner lblCorreo.setText(usuario.correo());

        lblFechaNacimiento.setText(
                paciente.fechaNac().toString()
        );
        
        JLabel lblNewLabel_2 = new JLabel("Documento:");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 6;
        getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        JLabel lblDocumento = new JLabel("lblDocumento");
        GridBagConstraints gbc_lblDocumento = new GridBagConstraints();
        gbc_lblDocumento.insets = new Insets(0, 0, 5, 5);
        gbc_lblDocumento.gridx = 3;
        gbc_lblDocumento.gridy = 6;
        getContentPane().add(lblDocumento, gbc_lblDocumento);
        
                lblDocumento.setText(
                        String.valueOf(paciente.doc())
                );
        
        JLabel lblNewLabel_3 = new JLabel("Fecha de Ingreso:");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 1;
        gbc_lblNewLabel_3.gridy = 7;
        getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
        
        JLabel lblFechaIngreso = new JLabel("lblFechaIngreso");
        GridBagConstraints gbc_lblFechaIngreso = new GridBagConstraints();
        gbc_lblFechaIngreso.insets = new Insets(0, 0, 5, 5);
        gbc_lblFechaIngreso.gridx = 3;
        gbc_lblFechaIngreso.gridy = 7;
        getContentPane().add(lblFechaIngreso, gbc_lblFechaIngreso);
        
                lblFechaIngreso.setText(
                        paciente.fechaIngreso().toString()
                );
        
        JLabel lblNewLabel_4 = new JLabel("Ordenes Acumuladas:");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 1;
        gbc_lblNewLabel_4.gridy = 8;
        getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
        
        JLabel lblOrdenes = new JLabel("lblOrdenes");
        GridBagConstraints gbc_lblOrdenes = new GridBagConstraints();
        gbc_lblOrdenes.insets = new Insets(0, 0, 5, 5);
        gbc_lblOrdenes.gridx = 3;
        gbc_lblOrdenes.gridy = 8;
        getContentPane().add(lblOrdenes, gbc_lblOrdenes);
        
                lblOrdenes.setText(
                        String.valueOf(paciente.cantOrdenesAcum())
                );
        
        JLabel lblNewLabel_5 = new JLabel("Tickets Acumulados:");
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_5.gridx = 1;
        gbc_lblNewLabel_5.gridy = 9;
        getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
        
        JLabel lblTickets = new JLabel("lblTickets");
        GridBagConstraints gbc_lblTickets = new GridBagConstraints();
        gbc_lblTickets.insets = new Insets(0, 0, 5, 5);
        gbc_lblTickets.gridx = 3;
        gbc_lblTickets.gridy = 9;
        getContentPane().add(lblTickets, gbc_lblTickets);
        
                lblTickets.setText(
                        String.valueOf(paciente.cantTicketAcum())
                );
        
        JLabel lblNewLabel_6 = new JLabel("Antecedentes de Salud:");
        GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
        gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_6.gridx = 1;
        gbc_lblNewLabel_6.gridy = 10;
        getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
        
        JList<String> listaAntecedentes = new JList<String>();
        GridBagConstraints gbc_listaAntecedentes = new GridBagConstraints();
        gbc_listaAntecedentes.insets = new Insets(0, 0, 5, 5);
        gbc_listaAntecedentes.fill = GridBagConstraints.BOTH;
        gbc_listaAntecedentes.gridx = 3;
        gbc_listaAntecedentes.gridy = 10;
        getContentPane().add(listaAntecedentes, gbc_listaAntecedentes);
        
        JLabel lblNewLabel_6_1 = new JLabel("Reservas de Consultas:");
        GridBagConstraints gbc_lblNewLabel_6_1 = new GridBagConstraints();
        gbc_lblNewLabel_6_1.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_6_1.gridx = 1;
        gbc_lblNewLabel_6_1.gridy = 11;
        getContentPane().add(lblNewLabel_6_1, gbc_lblNewLabel_6_1);
        
        JList<String> listaReservas = new JList<String>();
        listaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        GridBagConstraints gbc_listaReservas = new GridBagConstraints();
        gbc_listaReservas.insets = new Insets(0, 0, 0, 5);
        gbc_listaReservas.fill = GridBagConstraints.BOTH;
        gbc_listaReservas.gridx = 3;
        gbc_listaReservas.gridy = 11;
        getContentPane().add(listaReservas, gbc_listaReservas);
        
        listaReservas.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int indice = listaReservas.getSelectedIndex();

                if (indice != -1 && !reservasPaciente.isEmpty()) {

                    DTResConsulta reservaSeleccionada =
                            reservasPaciente.get(indice);

                    int numeroReserva =
                            reservaSeleccionada.numReserva();
                    
                    DTResConsultaDetalle detalle =
                            controlador.listarDatosResConsulta(reservaSeleccionada.nomC(), nickname);//cambiosd aca

                    // acá abriremos la ventana con el detalle
                    mostrarPopUpDetalle(numeroReserva, detalle);
                }
            }
        });
        
        if (paciente.antSalud() == null || paciente.antSalud().isEmpty()) {

        	listaAntecedentes.setListData(
                new String[] {"Sin antecedentes de salud"}
            );

        } else {

        	listaAntecedentes.setListData(
                paciente.antSalud().toArray(new String[0])
            );
        }
        
        HashSet<DTResConsulta> reservas =
                controlador.listarResConsultas(nickname);

        // Guardamos las reservas para poder saber después
        // cuál seleccionó el administrador
        reservasPaciente = new ArrayList<>(reservas);

        if (reservasPaciente.isEmpty()) {

            listaReservas.setListData(
                new String[] {"No tiene reservas de consultas"}
            );

            listaReservas.setEnabled(false);

        } else {

            String[] reservasParaMostrar = reservasPaciente.stream()
                    .map(reserva ->
                        "Reserva " + reserva.numReserva()
                        + " - " + reserva.fechaRes()
                        + " - $" + reserva.costoR()
                    )
                    .toArray(String[]::new);

            listaReservas.setListData(reservasParaMostrar);
            listaReservas.setEnabled(true);
        }
    }
    private void mostrarPopUpDetalle(Integer numReserva, DTResConsultaDetalle detalle) {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("          DETALLE DE LA RESERVA          \n");
        sb.append("=========================================\n");
        sb.append("Número de Reserva: ").append(detalle.numReserva()).append("\n");
        sb.append("Fecha de Reserva:  ").append(detalle.fechaRes()).append("\n");
        sb.append("Aplica Beneficio:  ").append(detalle.aplicaBenef() ? "Sí" : "No").append("\n");
        sb.append("Costo Reserva:     $").append(detalle.costoR()).append("\n\n");
        sb.append("-----------------------------------------\n");
        sb.append("         INFORMACIÓN DE CONSULTA         \n");
        sb.append("-----------------------------------------\n");
        sb.append("Nombre Consulta:   ").append(detalle.nomC()).append("\n");
        sb.append("Médico a Cargo:    ").append(detalle.medicoC()).append("\n");
        sb.append("Fecha Consulta:    ").append(detalle.fechaC()).append("\n");
        sb.append("Número de Sala:    ").append(detalle.salaC()).append("\n");
        sb.append("Cantidad Números:  ").append(detalle.cantNumC()).append("\n");
        sb.append("Costo Consulta:    $").append(detalle.costoC()).append("\n");

        JTextArea areaDetalle = new JTextArea(sb.toString());
        areaDetalle.setEditable(false);
        areaDetalle.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPopUp = new JScrollPane(areaDetalle);
        scrollPopUp.setPreferredSize(new Dimension(420, 280));

        JOptionPane.showMessageDialog(this, scrollPopUp, "Detalle de Reserva #" + numReserva, JOptionPane.INFORMATION_MESSAGE);
    }
}