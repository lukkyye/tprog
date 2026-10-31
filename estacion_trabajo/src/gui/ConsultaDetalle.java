package gui;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;

import logica.controladores.ControladorUsuario;
import logica.datatypes.DTConsultaDetalle;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.util.HashSet;
import java.util.Set;

import logica.datatypes.DTResConsulta;
import logica.datatypes.DTResConsultaDetalle;

public class ConsultaDetalle extends JInternalFrame {
	private String nickname;
    private DTConsultaDetalle detalle;

    public ConsultaDetalle(DTConsultaDetalle detalle, String nickname) {
    	
        super(
            "Detalle de Consulta Asociada",
            true,
            true,
            true,
            true
        );
        this.nickname=nickname;

        this.detalle = detalle;

        setSize(600, 500);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 99, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 216, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblNewLabel = new JLabel("Nombre de Consulta:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 5;
        gbc_lblNewLabel.gridy = 1;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        JLabel lblNombre = new JLabel("New label");
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 7;
        gbc_lblNombre.gridy = 1;
        getContentPane().add(lblNombre, gbc_lblNombre);
        
        JLabel lblMdico = new JLabel("Médico:   ");
        GridBagConstraints gbc_lblMdico = new GridBagConstraints();
        gbc_lblMdico.insets = new Insets(0, 0, 5, 5);
        gbc_lblMdico.gridx = 5;
        gbc_lblMdico.gridy = 2;
        getContentPane().add(lblMdico, gbc_lblMdico);
        
        JLabel lblMedico = new JLabel("New label");
        GridBagConstraints gbc_lblMedico = new GridBagConstraints();
        gbc_lblMedico.insets = new Insets(0, 0, 5, 5);
        gbc_lblMedico.gridx = 7;
        gbc_lblMedico.gridy = 2;
        getContentPane().add(lblMedico, gbc_lblMedico);
        
        JLabel lblNewLabel_1_1 = new JLabel("Fecha: ");
        GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1.gridx = 5;
        gbc_lblNewLabel_1_1.gridy = 3;
        getContentPane().add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
        
        JLabel lblFecha = new JLabel("New label");
        GridBagConstraints gbc_lblFecha = new GridBagConstraints();
        gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
        gbc_lblFecha.gridx = 7;
        gbc_lblFecha.gridy = 3;
        getContentPane().add(lblFecha, gbc_lblFecha);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Número de Sala: ");
        GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_1.gridx = 5;
        gbc_lblNewLabel_1_1_1.gridy = 4;
        getContentPane().add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
        
        JLabel lblSala = new JLabel("New label");
        GridBagConstraints gbc_lblSala = new GridBagConstraints();
        gbc_lblSala.insets = new Insets(0, 0, 5, 5);
        gbc_lblSala.gridx = 7;
        gbc_lblSala.gridy = 4;
        getContentPane().add(lblSala, gbc_lblSala);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Cantidad de Números:");
        GridBagConstraints gbc_lblNewLabel_1_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_1_1.gridx = 5;
        gbc_lblNewLabel_1_1_1_1.gridy = 5;
        getContentPane().add(lblNewLabel_1_1_1_1, gbc_lblNewLabel_1_1_1_1);
        
        JLabel lblCantidadNumeros = new JLabel("New label");
        GridBagConstraints gbc_lblCantidadNumeros = new GridBagConstraints();
        gbc_lblCantidadNumeros.insets = new Insets(0, 0, 5, 5);
        gbc_lblCantidadNumeros.gridx = 7;
        gbc_lblCantidadNumeros.gridy = 5;
        getContentPane().add(lblCantidadNumeros, gbc_lblCantidadNumeros);
        
        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Costo:");
        GridBagConstraints gbc_lblNewLabel_1_1_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_1_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_1_1_1.gridx = 5;
        gbc_lblNewLabel_1_1_1_1_1.gridy = 6;
        getContentPane().add(lblNewLabel_1_1_1_1_1, gbc_lblNewLabel_1_1_1_1_1);
        
        JLabel lblCosto = new JLabel("New label");
        GridBagConstraints gbc_lblCosto = new GridBagConstraints();
        gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
        gbc_lblCosto.gridx = 7;
        gbc_lblCosto.gridy = 6;
        getContentPane().add(lblCosto, gbc_lblCosto);
        
        JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Estado:");
        GridBagConstraints gbc_lblNewLabel_1_1_1_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_1_1_1_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1_1_1_1_1_1.gridx = 5;
        gbc_lblNewLabel_1_1_1_1_1_1.gridy = 7;
        getContentPane().add(lblNewLabel_1_1_1_1_1_1, gbc_lblNewLabel_1_1_1_1_1_1);
        
        JLabel lblEstado = new JLabel("New label");
        GridBagConstraints gbc_lblEstado = new GridBagConstraints();
        gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
        gbc_lblEstado.gridx = 7;
        gbc_lblEstado.gridy = 7;
        getContentPane().add(lblEstado, gbc_lblEstado);
        
        JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Reservas Asociadas:");
        GridBagConstraints gbc_lblNewLabel_1_1_1_1_1_2 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_1_1_1_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_1_1_1_1_1_2.gridx = 5;
        gbc_lblNewLabel_1_1_1_1_1_2.gridy = 8;
        getContentPane().add(lblNewLabel_1_1_1_1_1_2, gbc_lblNewLabel_1_1_1_1_1_2);
        
        JList<String> listaReservas = new JList<String>();
        GridBagConstraints gbc_listaReservas = new GridBagConstraints();
        gbc_listaReservas.insets = new Insets(0, 0, 0, 5);
        gbc_listaReservas.fill = GridBagConstraints.BOTH;
        gbc_listaReservas.gridx = 7;
        gbc_listaReservas.gridy = 8;
        getContentPane().add(listaReservas, gbc_listaReservas);
        
        
        lblNombre.setText(detalle.getNomC());
        lblMedico.setText(detalle.getMedico());
        lblFecha.setText(String.valueOf(detalle.getFechaC()));
        lblSala.setText(String.valueOf(detalle.getSala()));
        lblCantidadNumeros.setText(String.valueOf(detalle.getCantNum()));
        lblCosto.setText("$" + detalle.getCostoC());
        lblEstado.setText(String.valueOf(detalle.getEstado()));
        
        Set<DTResConsulta> reservas = detalle.getReservas();

        if (reservas == null || reservas.isEmpty()) {

            listaReservas.setListData(
                new String[] {"No tiene reservas asociadas"}
            );

            listaReservas.setEnabled(false);

        } else {

            String[] reservasParaMostrar = reservas.stream()
                    .map(reserva ->
                        "Reserva " + reserva.getNumReserva()
                        + " - " + reserva.getFechaRes()
                        + " - $" + reserva.getCostoR()
                    )
                    .toArray(String[]::new);
            
            listaReservas.setListData(reservasParaMostrar);
            listaReservas.setEnabled(true);
        }
    }
}