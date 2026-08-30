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

import logica.datatypes.DTDiaConsulta;
import logica.datatypes.DTConsulta;

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
        gridBagLayout.columnWidths = new int[]{140, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblDiaConsulta = new JLabel("Día de Consulta:");
        GridBagConstraints gbc_lblDiaConsulta = new GridBagConstraints();
        gbc_lblDiaConsulta.anchor = GridBagConstraints.WEST;
        gbc_lblDiaConsulta.insets = new Insets(0, 0, 5, 5);
        gbc_lblDiaConsulta.gridx = 0;
        gbc_lblDiaConsulta.gridy = 0;
        getContentPane().add(lblDiaConsulta, gbc_lblDiaConsulta);
        
        JComboBox comboBoxDiaConsulta = new JComboBox();
        GridBagConstraints gbc_comboBoxDiaConsulta = new GridBagConstraints();
        gbc_comboBoxDiaConsulta.insets = new Insets(0, 0, 5, 5);
        gbc_comboBoxDiaConsulta.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxDiaConsulta.gridx = 1;
        gbc_comboBoxDiaConsulta.gridy = 0;
        getContentPane().add(comboBoxDiaConsulta, gbc_comboBoxDiaConsulta);
        for (DTDiaConsulta d : controlador.listarDatosDiaConsulta()) {
            comboBoxDiaConsulta.addItem(d.nomD());
        }
        
        JLabel lablenombredia = new JLabel("Nombre:");
        lablenombredia.setVisible(false);
        GridBagConstraints gbc_lablenombredia = new GridBagConstraints();
        gbc_lablenombredia.insets = new Insets(0, 0, 5, 5);
        gbc_lablenombredia.gridx = 0;
        gbc_lablenombredia.gridy = 1;
        getContentPane().add(lablenombredia, gbc_lablenombredia);
        
        JLabel descripciondia = new JLabel("Descripción:");
        descripciondia.setVisible(false);
        
        JLabel camponombredia = new JLabel("New label");
        GridBagConstraints gbc_camponombredia = new GridBagConstraints();
        gbc_camponombredia.insets = new Insets(0, 0, 5, 5);
        gbc_camponombredia.gridx = 1;
        gbc_camponombredia.gridy = 1;
        getContentPane().add(camponombredia, gbc_camponombredia);
        GridBagConstraints gbc_descripciondia = new GridBagConstraints();
        gbc_descripciondia.insets = new Insets(0, 0, 5, 5);
        gbc_descripciondia.gridx = 0;
        gbc_descripciondia.gridy = 2;
        getContentPane().add(descripciondia, gbc_descripciondia);
        
        JLabel Diadia = new JLabel("Día:");
        Diadia.setVisible(false);
        
        JLabel campodescripciondia = new JLabel("New label");
        GridBagConstraints gbc_campodescripciondia = new GridBagConstraints();
        gbc_campodescripciondia.insets = new Insets(0, 0, 5, 5);
        gbc_campodescripciondia.gridx = 1;
        gbc_campodescripciondia.gridy = 2;
        getContentPane().add(campodescripciondia, gbc_campodescripciondia);
        GridBagConstraints gbc_Diadia = new GridBagConstraints();
        gbc_Diadia.insets = new Insets(0, 0, 5, 5);
        gbc_Diadia.gridx = 0;
        gbc_Diadia.gridy = 3;
        getContentPane().add(Diadia, gbc_Diadia);
        
        JLabel horainidia = new JLabel("Hora de inicio:");
        horainidia.setVisible(false);
        
        JLabel campodiadia = new JLabel("New label");
        GridBagConstraints gbc_campodiadia = new GridBagConstraints();
        gbc_campodiadia.insets = new Insets(0, 0, 5, 5);
        gbc_campodiadia.gridx = 1;
        gbc_campodiadia.gridy = 3;
        getContentPane().add(campodiadia, gbc_campodiadia);
        GridBagConstraints gbc_horainidia = new GridBagConstraints();
        gbc_horainidia.insets = new Insets(0, 0, 5, 5);
        gbc_horainidia.anchor = GridBagConstraints.EAST;
        gbc_horainidia.gridx = 0;
        gbc_horainidia.gridy = 4;
        getContentPane().add(horainidia, gbc_horainidia);
        
        JLabel costodia = new JLabel("Costo:");
        costodia.setVisible(false);
        
        JLabel campohorainidia = new JLabel("New label");
        GridBagConstraints gbc_campohorainidia = new GridBagConstraints();
        gbc_campohorainidia.insets = new Insets(0, 0, 5, 5);
        gbc_campohorainidia.gridx = 1;
        gbc_campohorainidia.gridy = 4;
        getContentPane().add(campohorainidia, gbc_campohorainidia);
        GridBagConstraints gbc_costodia = new GridBagConstraints();
        gbc_costodia.insets = new Insets(0, 0, 5, 5);
        gbc_costodia.gridx = 0;
        gbc_costodia.gridy = 5;
        getContentPane().add(costodia, gbc_costodia);
        
        JLabel cosnsulta = new JLabel("Consulta:");
        cosnsulta.setVisible(false);
        
        JLabel campocostodia = new JLabel("New label");
        GridBagConstraints gbc_campocostodia = new GridBagConstraints();
        gbc_campocostodia.insets = new Insets(0, 0, 5, 5);
        gbc_campocostodia.gridx = 1;
        gbc_campocostodia.gridy = 5;
        getContentPane().add(campocostodia, gbc_campocostodia);
        GridBagConstraints gbc_cosnsulta = new GridBagConstraints();
        gbc_cosnsulta.anchor = GridBagConstraints.EAST;
        gbc_cosnsulta.insets = new Insets(0, 0, 5, 5);
        gbc_cosnsulta.gridx = 0;
        gbc_cosnsulta.gridy = 6;
        getContentPane().add(cosnsulta, gbc_cosnsulta);
        
        JComboBox comboBoxConsultas = new JComboBox();
        comboBoxConsultas.setVisible(false);
       
        GridBagConstraints gbc_comboBoxConsultas = new GridBagConstraints();
        gbc_comboBoxConsultas.insets = new Insets(0, 0, 5, 5);
        gbc_comboBoxConsultas.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxConsultas.gridx = 1;
        gbc_comboBoxConsultas.gridy = 6;
        getContentPane().add(comboBoxConsultas, gbc_comboBoxConsultas);
        
        JLabel nombreconsulta = new JLabel("Nombre:");
        nombreconsulta.setVisible(false);
        GridBagConstraints gbc_nombreconsulta = new GridBagConstraints();
        gbc_nombreconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_nombreconsulta.gridx = 0;
        gbc_nombreconsulta.gridy = 7;
        getContentPane().add(nombreconsulta, gbc_nombreconsulta);
        
        JLabel medicoconsulta = new JLabel("Medico:");
        medicoconsulta.setVisible(false);
        
        JLabel camponombreconsulta = new JLabel("New label");
        GridBagConstraints gbc_camponombreconsulta = new GridBagConstraints();
        gbc_camponombreconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_camponombreconsulta.gridx = 1;
        gbc_camponombreconsulta.gridy = 7;
        getContentPane().add(camponombreconsulta, gbc_camponombreconsulta);
        GridBagConstraints gbc_medicoconsulta = new GridBagConstraints();
        gbc_medicoconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_medicoconsulta.gridx = 0;
        gbc_medicoconsulta.gridy = 8;
        getContentPane().add(medicoconsulta, gbc_medicoconsulta);
        
        JLabel fechaconsulta = new JLabel("Fecha:");
        fechaconsulta.setVisible(false);
        
        JLabel campomedicoconsulta = new JLabel("New label");
        GridBagConstraints gbc_campomedicoconsulta = new GridBagConstraints();
        gbc_campomedicoconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_campomedicoconsulta.gridx = 1;
        gbc_campomedicoconsulta.gridy = 8;
        getContentPane().add(campomedicoconsulta, gbc_campomedicoconsulta);
        GridBagConstraints gbc_fechaconsulta = new GridBagConstraints();
        gbc_fechaconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_fechaconsulta.gridx = 0;
        gbc_fechaconsulta.gridy = 9;
        getContentPane().add(fechaconsulta, gbc_fechaconsulta);
        
        JLabel salaconsulta = new JLabel("Sala:");
        salaconsulta.setVisible(false);
        
        JLabel campofechaconsulta = new JLabel("New label");
        GridBagConstraints gbc_campofechaconsulta = new GridBagConstraints();
        gbc_campofechaconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_campofechaconsulta.gridx = 1;
        gbc_campofechaconsulta.gridy = 9;
        getContentPane().add(campofechaconsulta, gbc_campofechaconsulta);
        GridBagConstraints gbc_salaconsulta = new GridBagConstraints();
        gbc_salaconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_salaconsulta.gridx = 0;
        gbc_salaconsulta.gridy = 10;
        getContentPane().add(salaconsulta, gbc_salaconsulta);
        
        JLabel cantidadnumconsulta = new JLabel("Cantidad De Números:");
        cantidadnumconsulta.setVisible(false);
        
        JLabel camposalaconsulta = new JLabel("New label");
        GridBagConstraints gbc_camposalaconsulta = new GridBagConstraints();
        gbc_camposalaconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_camposalaconsulta.gridx = 1;
        gbc_camposalaconsulta.gridy = 10;
        getContentPane().add(camposalaconsulta, gbc_camposalaconsulta);
        GridBagConstraints gbc_cantidadnumconsulta = new GridBagConstraints();
        gbc_cantidadnumconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_cantidadnumconsulta.gridx = 0;
        gbc_cantidadnumconsulta.gridy = 11;
        getContentPane().add(cantidadnumconsulta, gbc_cantidadnumconsulta);
        
        JLabel costoconsulta = new JLabel("Costo:");
        costoconsulta.setVisible(false);
        
        JLabel campocantnumconsulta = new JLabel("New label");
        GridBagConstraints gbc_campocantnumconsulta = new GridBagConstraints();
        gbc_campocantnumconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_campocantnumconsulta.gridx = 1;
        gbc_campocantnumconsulta.gridy = 11;
        getContentPane().add(campocantnumconsulta, gbc_campocantnumconsulta);
        GridBagConstraints gbc_costoconsulta = new GridBagConstraints();
        gbc_costoconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_costoconsulta.gridx = 0;
        gbc_costoconsulta.gridy = 12;
        getContentPane().add(costoconsulta, gbc_costoconsulta);
        
        JLabel campocostoconsulta = new JLabel("New label");
        GridBagConstraints gbc_campocostoconsulta = new GridBagConstraints();
        gbc_campocostoconsulta.insets = new Insets(0, 0, 5, 5);
        gbc_campocostoconsulta.gridx = 1;
        gbc_campocostoconsulta.gridy = 12;
        getContentPane().add(campocostoconsulta, gbc_campocostoconsulta);
        
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
        
   }
    
}
