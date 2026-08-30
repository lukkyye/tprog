package gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import logica.controladores.ControladorClinica;
import logica.datatypes.DTDiaConsulta;
import logica.datatypes.DTConsulta;
import logica.datatypes.DTResConsulta;
import logica.datatypes.DTConsultaDetalle;


public class ConsultaDeDiaDeConsulta extends JInternalFrame {

    public ConsultaDeDiaDeConsulta() {

        super(
            "Consulta de Dia de Consulta", // título
            true,              // se puede redimensionar
            true,              // se puede cerrar
            true,              // se puede maximizar
            true               // se puede minimizar
        );

        setSize(600, 500);
        ControladorClinica controlador = new ControladorClinica();
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 140, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblDiaConsulta = new JLabel("Día de Consulta:");
        GridBagConstraints gbc_lblDiaConsulta = new GridBagConstraints();
        gbc_lblDiaConsulta.anchor = GridBagConstraints.WEST;
        gbc_lblDiaConsulta.insets = new Insets(0, 0, 5, 5);
        gbc_lblDiaConsulta.gridx = 1;
        gbc_lblDiaConsulta.gridy = 0;
        getContentPane().add(lblDiaConsulta, gbc_lblDiaConsulta);
        
        JComboBox comboBoxDiaConsulta = new JComboBox();
        GridBagConstraints gbc_comboBoxDiaConsulta = new GridBagConstraints();
        gbc_comboBoxDiaConsulta.insets = new Insets(0, 0, 5, 5);
        gbc_comboBoxDiaConsulta.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxDiaConsulta.gridx = 2;
        gbc_comboBoxDiaConsulta.gridy = 0;
        getContentPane().add(comboBoxDiaConsulta, gbc_comboBoxDiaConsulta);
        for (DTDiaConsulta d : controlador.listarDatosDiaConsulta()) {
            comboBoxDiaConsulta.addItem(d.nomD());
        }
        
        JLabel lablenombredia = new JLabel("Nombre:");
        lablenombredia.setVisible(false);
        GridBagConstraints gbc_lablenombredia = new GridBagConstraints();
        gbc_lablenombredia.anchor = GridBagConstraints.WEST;
        gbc_lablenombredia.insets = new Insets(0, 0, 5, 5);
        gbc_lablenombredia.gridx = 1;
        gbc_lablenombredia.gridy = 1;
        getContentPane().add(lablenombredia, gbc_lablenombredia);
        
        JLabel descripciondia = new JLabel("Descripción:");
        descripciondia.setVisible(false);
        
        JLabel camponombredia = new JLabel("New label");
        GridBagConstraints gbc_camponombredia = new GridBagConstraints();
        gbc_camponombredia.insets = new Insets(0, 0, 5, 5);
        gbc_camponombredia.gridx = 2;
        gbc_camponombredia.gridy = 1;
        getContentPane().add(camponombredia, gbc_camponombredia);
        GridBagConstraints gbc_descripciondia = new GridBagConstraints();
        gbc_descripciondia.anchor = GridBagConstraints.WEST;
        gbc_descripciondia.insets = new Insets(0, 0, 5, 5);
        gbc_descripciondia.gridx = 1;
        gbc_descripciondia.gridy = 2;
        getContentPane().add(descripciondia, gbc_descripciondia);
        
        JLabel Diadia = new JLabel("Día:");
        Diadia.setVisible(false);
        
        JLabel campodescripciondia = new JLabel("New label");
        GridBagConstraints gbc_campodescripciondia = new GridBagConstraints();
        gbc_campodescripciondia.insets = new Insets(0, 0, 5, 5);
        gbc_campodescripciondia.gridx = 2;
        gbc_campodescripciondia.gridy = 2;
        getContentPane().add(campodescripciondia, gbc_campodescripciondia);
        GridBagConstraints gbc_Diadia = new GridBagConstraints();
        gbc_Diadia.anchor = GridBagConstraints.WEST;
        gbc_Diadia.insets = new Insets(0, 0, 5, 5);
        gbc_Diadia.gridx = 1;
        gbc_Diadia.gridy = 3;
        getContentPane().add(Diadia, gbc_Diadia);
        
        JLabel horainidia = new JLabel("Hora de inicio:");
        horainidia.setVisible(false);
        
        JLabel campodiadia = new JLabel("New label");
        GridBagConstraints gbc_campodiadia = new GridBagConstraints();
        gbc_campodiadia.insets = new Insets(0, 0, 5, 5);
        gbc_campodiadia.gridx = 2;
        gbc_campodiadia.gridy = 3;
        getContentPane().add(campodiadia, gbc_campodiadia);
        GridBagConstraints gbc_horainidia = new GridBagConstraints();
        gbc_horainidia.insets = new Insets(0, 0, 5, 5);
        gbc_horainidia.anchor = GridBagConstraints.WEST;
        gbc_horainidia.gridx = 1;
        gbc_horainidia.gridy = 4;
        getContentPane().add(horainidia, gbc_horainidia);
        
        JLabel costodia = new JLabel("Costo:");
        costodia.setVisible(false);
        
        JLabel campohorainidia = new JLabel("New label");
        GridBagConstraints gbc_campohorainidia = new GridBagConstraints();
        gbc_campohorainidia.insets = new Insets(0, 0, 5, 5);
        gbc_campohorainidia.gridx = 2;
        gbc_campohorainidia.gridy = 4;
        getContentPane().add(campohorainidia, gbc_campohorainidia);
        GridBagConstraints gbc_costodia = new GridBagConstraints();
        gbc_costodia.anchor = GridBagConstraints.WEST;
        gbc_costodia.insets = new Insets(0, 0, 5, 5);
        gbc_costodia.gridx = 1;
        gbc_costodia.gridy = 5;
        getContentPane().add(costodia, gbc_costodia);
        
        JLabel cosnsulta = new JLabel("Consulta:");
        cosnsulta.setVisible(false);
        
        JLabel campocostodia = new JLabel("New label");
        GridBagConstraints gbc_campocostodia = new GridBagConstraints();
        gbc_campocostodia.insets = new Insets(0, 0, 5, 5);
        gbc_campocostodia.gridx = 2;
        gbc_campocostodia.gridy = 5;
        getContentPane().add(campocostodia, gbc_campocostodia);
        GridBagConstraints gbc_cosnsulta = new GridBagConstraints();
        gbc_cosnsulta.anchor = GridBagConstraints.WEST;
        gbc_cosnsulta.insets = new Insets(0, 0, 5, 5);
        gbc_cosnsulta.gridx = 1;
        gbc_cosnsulta.gridy = 6;
        getContentPane().add(cosnsulta, gbc_cosnsulta);
        
        JComboBox comboBoxConsultas = new JComboBox();
        comboBoxConsultas.setVisible(false);
       
        GridBagConstraints gbc_comboBoxConsultas = new GridBagConstraints();
        gbc_comboBoxConsultas.insets = new Insets(0, 0, 5, 5);
        gbc_comboBoxConsultas.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxConsultas.gridx = 2;
        gbc_comboBoxConsultas.gridy = 6;
        getContentPane().add(comboBoxConsultas, gbc_comboBoxConsultas);
        
        JLabel nombreconsulta = new JLabel("Nombre:");
        nombreconsulta.setVisible(false);
        GridBagConstraints gbc_nombreconsulta = new GridBagConstraints();
        gbc_nombreconsulta.anchor = GridBagConstraints.WEST;
        gbc_nombreconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_nombreconsulta.gridx = 1;
        gbc_nombreconsulta.gridy = 7;
        getContentPane().add(nombreconsulta, gbc_nombreconsulta);
        
        JLabel medicoconsulta = new JLabel("Medico:");
        medicoconsulta.setVisible(false);
        
        JLabel camponombreconsulta = new JLabel("New label");
        GridBagConstraints gbc_camponombreconsulta = new GridBagConstraints();
        gbc_camponombreconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_camponombreconsulta.gridx = 2;
        gbc_camponombreconsulta.gridy = 7;
        getContentPane().add(camponombreconsulta, gbc_camponombreconsulta);
        GridBagConstraints gbc_medicoconsulta = new GridBagConstraints();
        gbc_medicoconsulta.anchor = GridBagConstraints.WEST;
        gbc_medicoconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_medicoconsulta.gridx = 1;
        gbc_medicoconsulta.gridy = 8;
        getContentPane().add(medicoconsulta, gbc_medicoconsulta);
        
        JLabel fechaconsulta = new JLabel("Fecha:");
        fechaconsulta.setVisible(false);
        
        JLabel campomedicoconsulta = new JLabel("New label");
        GridBagConstraints gbc_campomedicoconsulta = new GridBagConstraints();
        gbc_campomedicoconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_campomedicoconsulta.gridx = 2;
        gbc_campomedicoconsulta.gridy = 8;
        getContentPane().add(campomedicoconsulta, gbc_campomedicoconsulta);
        GridBagConstraints gbc_fechaconsulta = new GridBagConstraints();
        gbc_fechaconsulta.anchor = GridBagConstraints.WEST;
        gbc_fechaconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_fechaconsulta.gridx = 1;
        gbc_fechaconsulta.gridy = 9;
        getContentPane().add(fechaconsulta, gbc_fechaconsulta);
        
        JLabel salaconsulta = new JLabel("Sala:");
        salaconsulta.setVisible(false);
        
        JLabel campofechaconsulta = new JLabel("New label");
        GridBagConstraints gbc_campofechaconsulta = new GridBagConstraints();
        gbc_campofechaconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_campofechaconsulta.gridx = 2;
        gbc_campofechaconsulta.gridy = 9;
        getContentPane().add(campofechaconsulta, gbc_campofechaconsulta);
        GridBagConstraints gbc_salaconsulta = new GridBagConstraints();
        gbc_salaconsulta.anchor = GridBagConstraints.WEST;
        gbc_salaconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_salaconsulta.gridx = 1;
        gbc_salaconsulta.gridy = 10;
        getContentPane().add(salaconsulta, gbc_salaconsulta);
        
        JLabel cantidadnumconsulta = new JLabel("Cantidad De Números:");
        cantidadnumconsulta.setVisible(false);
        
        JLabel camposalaconsulta = new JLabel("New label");
        GridBagConstraints gbc_camposalaconsulta = new GridBagConstraints();
        gbc_camposalaconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_camposalaconsulta.gridx = 2;
        gbc_camposalaconsulta.gridy = 10;
        getContentPane().add(camposalaconsulta, gbc_camposalaconsulta);
        GridBagConstraints gbc_cantidadnumconsulta = new GridBagConstraints();
        gbc_cantidadnumconsulta.anchor = GridBagConstraints.WEST;
        gbc_cantidadnumconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_cantidadnumconsulta.gridx = 1;
        gbc_cantidadnumconsulta.gridy = 11;
        getContentPane().add(cantidadnumconsulta, gbc_cantidadnumconsulta);
        
        JLabel costoconsulta = new JLabel("Costo:");
        costoconsulta.setVisible(false);
        
        JLabel campocantnumconsulta = new JLabel("New label");
        GridBagConstraints gbc_campocantnumconsulta = new GridBagConstraints();
        gbc_campocantnumconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_campocantnumconsulta.gridx = 2;
        gbc_campocantnumconsulta.gridy = 11;
        getContentPane().add(campocantnumconsulta, gbc_campocantnumconsulta);
        GridBagConstraints gbc_costoconsulta = new GridBagConstraints();
        gbc_costoconsulta.anchor = GridBagConstraints.WEST;
        gbc_costoconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_costoconsulta.gridx = 1;
        gbc_costoconsulta.gridy = 12;
        getContentPane().add(costoconsulta, gbc_costoconsulta);
        
        JLabel campocostoconsulta = new JLabel("New label");
        GridBagConstraints gbc_campocostoconsulta = new GridBagConstraints();
        gbc_campocostoconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_campocostoconsulta.gridx = 2;
        gbc_campocostoconsulta.gridy = 12;
        getContentPane().add(campocostoconsulta, gbc_campocostoconsulta);
        
        JLabel reservaconsulta = new JLabel("Reservas:");
        GridBagConstraints gbc_reservaconsulta = new GridBagConstraints();
        gbc_reservaconsulta.anchor = GridBagConstraints.WEST;
        gbc_reservaconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_reservaconsulta.gridx = 1;
        gbc_reservaconsulta.gridy = 13;
        getContentPane().add(reservaconsulta, gbc_reservaconsulta);
        
        JComboBox comboBoxreservas = new JComboBox();
        GridBagConstraints gbc_comboBoxreservas = new GridBagConstraints();
        gbc_comboBoxreservas.insets = new Insets(0, 0, 5, 5);
        gbc_comboBoxreservas.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxreservas.gridx = 2;
        gbc_comboBoxreservas.gridy = 13;
        getContentPane().add(comboBoxreservas, gbc_comboBoxreservas);
        
        JLabel fechareserva = new JLabel("Fecha:");
        GridBagConstraints gbc_fechareserva = new GridBagConstraints();
        gbc_fechareserva.anchor = GridBagConstraints.WEST;
        gbc_fechareserva.insets = new Insets(0, 0, 5, 5);
        gbc_fechareserva.gridx = 1;
        gbc_fechareserva.gridy = 14;
        getContentPane().add(fechareserva, gbc_fechareserva);
        
        JLabel campofechareserva = new JLabel("New label");
        GridBagConstraints gbc_campofechareserva = new GridBagConstraints();
        gbc_campofechareserva.insets = new Insets(0, 0, 5, 5);
        gbc_campofechareserva.gridx = 2;
        gbc_campofechareserva.gridy = 14;
        getContentPane().add(campofechareserva, gbc_campofechareserva);
        
        JLabel numeroreserva = new JLabel("Número:");
        GridBagConstraints gbc_numeroreserva = new GridBagConstraints();
        gbc_numeroreserva.anchor = GridBagConstraints.WEST;
        gbc_numeroreserva.insets = new Insets(0, 0, 5, 5);
        gbc_numeroreserva.gridx = 1;
        gbc_numeroreserva.gridy = 15;
        getContentPane().add(numeroreserva, gbc_numeroreserva);
        
        JLabel camponumreserva = new JLabel("New label");
        GridBagConstraints gbc_camponumreserva = new GridBagConstraints();
        gbc_camponumreserva.insets = new Insets(0, 0, 5, 5);
        gbc_camponumreserva.gridx = 2;
        gbc_camponumreserva.gridy = 15;
        getContentPane().add(camponumreserva, gbc_camponumreserva);
        
        JLabel beneficioreserva = new JLabel("Beneficio:");
        GridBagConstraints gbc_beneficioreserva = new GridBagConstraints();
        gbc_beneficioreserva.anchor = GridBagConstraints.WEST;
        gbc_beneficioreserva.insets = new Insets(0, 0, 5, 5);
        gbc_beneficioreserva.gridx = 1;
        gbc_beneficioreserva.gridy = 16;
        getContentPane().add(beneficioreserva, gbc_beneficioreserva);
        
        JLabel campobeneficioreserva = new JLabel("New label");
        GridBagConstraints gbc_campobeneficioreserva = new GridBagConstraints();
        gbc_campobeneficioreserva.insets = new Insets(0, 0, 5, 5);
        gbc_campobeneficioreserva.gridx = 2;
        gbc_campobeneficioreserva.gridy = 16;
        getContentPane().add(campobeneficioreserva, gbc_campobeneficioreserva);
        
        JLabel costoreserva = new JLabel("Costo:");
        GridBagConstraints gbc_costoreserva = new GridBagConstraints();
        gbc_costoreserva.anchor = GridBagConstraints.WEST;
        gbc_costoreserva.insets = new Insets(0, 0, 0, 5);
        gbc_costoreserva.gridx = 1;
        gbc_costoreserva.gridy = 17;
        getContentPane().add(costoreserva, gbc_costoreserva);
        
        JLabel campocostoreserva = new JLabel("New label");
        GridBagConstraints gbc_campocostoreserva = new GridBagConstraints();
        gbc_campocostoreserva.insets = new Insets(0, 0, 0, 5);
        gbc_campocostoreserva.gridx = 2;
        gbc_campocostoreserva.gridy = 17;
        getContentPane().add(campocostoreserva, gbc_campocostoreserva);
        
        camponombredia.setVisible(false);
        campodescripciondia.setVisible(false);
        campodiadia.setVisible(false);
        campohorainidia.setVisible(false);
        campocostodia.setVisible(false);
        
        camponombreconsulta.setVisible(false);
        campomedicoconsulta.setVisible(false);
        campofechaconsulta.setVisible(false);
        camposalaconsulta.setVisible(false);
        campocantnumconsulta.setVisible(false);
        campocostoconsulta.setVisible(false);
        
        reservaconsulta.setVisible(false);
        comboBoxreservas.setVisible(false);

        fechareserva.setVisible(false);
        campofechareserva.setVisible(false);

        numeroreserva.setVisible(false);
        camponumreserva.setVisible(false);

        beneficioreserva.setVisible(false);
        campobeneficioreserva.setVisible(false);

        costoreserva.setVisible(false);
        campocostoreserva.setVisible(false);
        
        comboBoxDiaConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (comboBoxDiaConsulta.getSelectedItem() == null) {
                    return;
                }

                String nombreDia = comboBoxDiaConsulta.getSelectedItem().toString();

                for (DTDiaConsulta d : controlador.listarDatosDiaConsulta()) {

                    if (d.nomD().equals(nombreDia)) {

                        camponombredia.setText(d.nomD());
                        campodescripciondia.setText(d.descD());
                        campodiadia.setText(d.dia().toString());
                        campohorainidia.setText(d.horaIni().toString());
                        campocostodia.setText(String.valueOf(d.costoD()));
                        lablenombredia.setVisible(true);
                        descripciondia.setVisible(true);
                        Diadia.setVisible(true);
                        horainidia.setVisible(true);
                        costodia.setVisible(true);
                        camponombredia.setVisible(true);
                        campodescripciondia.setVisible(true);
                        campodiadia.setVisible(true);
                        campohorainidia.setVisible(true);
                        campocostodia.setVisible(true);

                        cosnsulta.setVisible(true);
                        comboBoxConsultas.setVisible(true);

                        comboBoxConsultas.removeAllItems();

                        for (DTConsulta c : controlador.selecListaConsultaDelDia(nombreDia)) {
                            comboBoxConsultas.addItem(c.nomC());
                        }

                        break;
                    }
                }
            }
        });
        comboBoxConsultas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (comboBoxConsultas.getSelectedItem() == null) {
                    return;
                }

                String nombreConsulta =
                        comboBoxConsultas.getSelectedItem().toString();

                DTConsultaDetalle consulta =
                        controlador.listarDatosConsulta(nombreConsulta);
                
                fechareserva.setVisible(false);
                campofechareserva.setVisible(false);

                numeroreserva.setVisible(false);
                camponumreserva.setVisible(false);

                beneficioreserva.setVisible(false);
                campobeneficioreserva.setVisible(false);

                costoreserva.setVisible(false);
                campocostoreserva.setVisible(false);

                camponombreconsulta.setText(consulta.nomC());
                campomedicoconsulta.setText(consulta.medico());
                campofechaconsulta.setText(consulta.fechaC().toString());
                camposalaconsulta.setText(String.valueOf(consulta.sala()));
                campocantnumconsulta.setText(String.valueOf(consulta.cantNum()));
                campocostoconsulta.setText(String.valueOf(consulta.costoC()));

                nombreconsulta.setVisible(true);
                medicoconsulta.setVisible(true);
                fechaconsulta.setVisible(true);
                salaconsulta.setVisible(true);
                cantidadnumconsulta.setVisible(true);
                costoconsulta.setVisible(true);

                camponombreconsulta.setVisible(true);
                campomedicoconsulta.setVisible(true);
                campofechaconsulta.setVisible(true);
                camposalaconsulta.setVisible(true);
                campocantnumconsulta.setVisible(true);
                campocostoconsulta.setVisible(true);
                
                reservaconsulta.setVisible(true);
                comboBoxreservas.setVisible(true);

                comboBoxreservas.removeAllItems();

                for (DTResConsulta r : consulta.reservas()) {
                    comboBoxreservas.addItem(r.numReserva());
                }
                
                
            }
        });
        
        
        comboBoxreservas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (comboBoxreservas.getSelectedItem() == null) {
                    return;
                }

                int numero =
                        (int) comboBoxreservas.getSelectedItem();

                String nombreConsulta =
                        comboBoxConsultas.getSelectedItem().toString();

                DTConsultaDetalle consulta =
                        controlador.listarDatosConsulta(nombreConsulta);

                for (DTResConsulta r : consulta.reservas()) {

                    if (r.numReserva() == numero) {

                        campofechareserva.setText(r.fechaRes().toString());
                        camponumreserva.setText(String.valueOf(r.numReserva()));
                        campobeneficioreserva.setText(String.valueOf(r.aplicaBenef()));
                        campocostoreserva.setText(String.valueOf(r.costoR()));

                        reservaconsulta.setVisible(true);
                        fechareserva.setVisible(true);
                        campofechareserva.setVisible(true);

                        numeroreserva.setVisible(true);
                        camponumreserva.setVisible(true);

                        beneficioreserva.setVisible(true);
                        campobeneficioreserva.setVisible(true);

                        costoreserva.setVisible(true);
                        campocostoreserva.setVisible(true);

                        break;
                    }
                }
            }
        });
        
        
   }
    
}
