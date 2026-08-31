package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import logica.datatypes.DTDiaConsulta;
import logica.datatypes.DTConsulta;
import logica.datatypes.DTConsultaDetalle;
import logica.datatypes.DTResConsulta;
import logica.datatypes.DTResConsultaDetalle;

import logica.controladores.ControladorClinica;
import logica.controladores.ControladorUsuario;

public class ConsultaDeConsulta extends JInternalFrame {

    private DefaultListModel<DTDiaConsulta> modelListaDias;
    private JList<DTDiaConsulta> listaDias;
    private JComboBox<String> comboNomD;
    private JButton btnSeleccionarDia;
    private JScrollPane scrollDias;

    private DefaultListModel<DTConsulta> modelListaConsultas;
    private JList<DTConsulta> listaConsultas;
    private JComboBox<String> comboNomC;
    private JButton btnSeleccionarConsulta;
    private JScrollPane scrollConsultas;

    private JTextArea areaDetalleConsulta;
    private JScrollPane scrollDetalleConsulta;
    private DefaultListModel<DTResConsulta> modelListaReservas;
    private JList<DTResConsulta> listaReservas;
    private JScrollPane scrollReservas;
    private JButton btnVerReserva;

    private JLabel lblTituloPaso;

    private ControladorClinica controladorClinica;
    private ControladorUsuario controladorUsuario;

    public ConsultaDeConsulta() {
    	super(
                "Consulta de Consulta", // título
                true,              // se puede redimensionar
                true,              // se puede cerrar
                true,              // se puede maximizar
                true               // se puede minimizar
        );
        setBounds(100, 100, 540, 500);
        getContentPane().setLayout(null);

        controladorClinica = new ControladorClinica();
        controladorUsuario = new ControladorUsuario();

        lblTituloPaso = new JLabel("Seleccione un Día de Consulta");
        lblTituloPaso.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTituloPaso.setBounds(30, 10, 450, 20);
        getContentPane().add(lblTituloPaso);

        modelListaDias = new DefaultListModel<>();
        listaDias = new JList<>(modelListaDias);
        listaDias.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JPanel panel = new JPanel(new GridLayout(5, 1, 2, 2));
            panel.setBorder(BorderFactory.createTitledBorder("Día: " + value.nomD()));
            panel.setBackground(isSelected ? new Color(220, 235, 252) : Color.WHITE);
            panel.add(new JLabel(" Nombre: " + value.nomD()));
            panel.add(new JLabel(" Descripción: " + value.descD()));
            panel.add(new JLabel(" Día: " + value.dia()));
            panel.add(new JLabel(" Hora Inicio: " + value.horaIni()));
            panel.add(new JLabel(" Costo: $" + value.costoD()));
            return panel;
        });

        scrollDias = new JScrollPane(listaDias);
        scrollDias.setBounds(30, 35, 460, 240);
        getContentPane().add(scrollDias);

        comboNomD = new JComboBox<>();
        comboNomD.setBounds(30, 290, 270, 30);
        getContentPane().add(comboNomD);

        btnSeleccionarDia = new JButton("Ver Consultas");
        btnSeleccionarDia.setBounds(310, 290, 180, 30);
        getContentPane().add(btnSeleccionarDia);

        modelListaConsultas = new DefaultListModel<>();
        listaConsultas = new JList<>(modelListaConsultas);
        
        scrollConsultas = new JScrollPane(listaConsultas);
        scrollConsultas.setBounds(30, 35, 460, 240);
        scrollConsultas.setVisible(false);
        getContentPane().add(scrollConsultas);

        comboNomC = new JComboBox<>();
        comboNomC.setBounds(30, 290, 270, 30);
        comboNomC.setVisible(false);
        getContentPane().add(comboNomC);

        btnSeleccionarConsulta = new JButton("Ver Detalle");
        btnSeleccionarConsulta.setBounds(310, 290, 180, 30);
        btnSeleccionarConsulta.setVisible(false);
        getContentPane().add(btnSeleccionarConsulta);

        areaDetalleConsulta = new JTextArea();
        areaDetalleConsulta.setEditable(false);
        scrollDetalleConsulta = new JScrollPane(areaDetalleConsulta);
        scrollDetalleConsulta.setBounds(30, 35, 460, 140);
        scrollDetalleConsulta.setVisible(false);
        getContentPane().add(scrollDetalleConsulta);

        modelListaReservas = new DefaultListModel<>();
        listaReservas = new JList<>(modelListaReservas);
        scrollReservas = new JScrollPane(listaReservas);
        scrollReservas.setBorder(BorderFactory.createTitledBorder("Reservas asociadas:"));
        scrollReservas.setBounds(30, 185, 460, 120);
        scrollReservas.setVisible(false);
        getContentPane().add(scrollReservas);

        btnVerReserva = new JButton("Ver Detalle Reserva");
        btnVerReserva.setBounds(310, 315, 180, 30);
        btnVerReserva.setVisible(false);
        getContentPane().add(btnVerReserva);

        btnSeleccionarDia.addActionListener(e -> {
            String nomD = (String) comboNomD.getSelectedItem();
            if (nomD != null) {
                Set<DTConsulta> consultas = controladorClinica.selecListaConsultaDelDia(nomD);
                if (consultas != null && !consultas.isEmpty()) {
                    mostrarPaso2(consultas);
                } else {
                    JOptionPane.showMessageDialog(null, "El día seleccionado no tiene consultas asociadas.");
                }
            }
        });

        btnSeleccionarConsulta.addActionListener(e -> {
            String nomC = (String) comboNomC.getSelectedItem();
            if (nomC != null) {
                DTConsultaDetalle detalle = controladorClinica.listarDatosConsulta(nomC);
                if (detalle != null) {
                    mostrarPaso3(detalle);
                }
            }
        });

        btnVerReserva.addActionListener(e -> {
            DTResConsulta reserva = listaReservas.getSelectedValue();
            String nomC = (String) comboNomC.getSelectedItem();

            if (reserva != null && nomC != null) {
             
                DTResConsultaDetalle resDetalle = controladorClinica.listarDatosResConsultaCC(nomC, reserva.numReserva());
                
                if (resDetalle != null) {
                    JOptionPane.showMessageDialog(this, 
                        "DATOS DE LA RESERVA:\n\n" +
                        "Número de Reserva: " + resDetalle.numReserva() + "\n" +
                        "Fecha Reserva: " + resDetalle.fechaRes() + "\n" +
                        "Aplica Beneficio: " + (resDetalle.aplicaBenef() ? "Sí" : "No") + "\n" +
                        "Costo Reserva: $" + resDetalle.costoR() + "\n\n" +
                        "DATOS DE LA CONSULTA:\n" +
                        "Consulta: " + resDetalle.numReserva() + "\n" +
                        "Médico: " + resDetalle.medicoC() + "\n" +
                        "Fecha Consulta: " + resDetalle.fechaC() + "\n" +
                        "Sala: " + resDetalle.salaC(), 
                        "Detalle de la Reserva", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontraron detalles para la reserva indicada.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una reserva de la lista.");
            }
        });

        cargarDias(controladorClinica.listarDatosDiaConsulta());
    }

    public void cargarDias(Set<DTDiaConsulta> lista) {
        modelListaDias.clear();
        comboNomD.removeAllItems();
        if (lista != null) {
            for (DTDiaConsulta dia : lista) {
                modelListaDias.addElement(dia);
                comboNomD.addItem(dia.nomD());
            }
        }
    }

    private void mostrarPaso2(Set<DTConsulta> consultas) {
        scrollDias.setVisible(false);
        comboNomD.setVisible(false);
        btnSeleccionarDia.setVisible(false);

        modelListaConsultas.clear();
        comboNomC.removeAllItems();
        for (DTConsulta c : consultas) {
            modelListaConsultas.addElement(c);
            comboNomC.addItem(c.nomC());
        }

        lblTituloPaso.setText("Seleccione una Consulta asociada");
        scrollConsultas.setVisible(true);
        comboNomC.setVisible(true);
        btnSeleccionarConsulta.setVisible(true);
    }

    private void mostrarPaso3(DTConsultaDetalle detalle) {
        scrollConsultas.setVisible(false);
        comboNomC.setVisible(false);
        btnSeleccionarConsulta.setVisible(false);

        StringBuilder sb = new StringBuilder();
        sb.append("Consulta: ").append(detalle.nomC()).append("\n");
        sb.append("Médico: ").append(detalle.medico()).append("\n");
        sb.append("Fecha: ").append(detalle.fechaC()).append("\n");
        sb.append("Sala: ").append(detalle.sala()).append("\n");
        sb.append("Cantidad de números: ").append(detalle.cantNum()).append("\n");
        sb.append("Costo: $").append(detalle.costoC());
        areaDetalleConsulta.setText(sb.toString());

        modelListaReservas.clear();
        if (detalle.reservas() != null) {
            for (DTResConsulta r : detalle.reservas()) {
                modelListaReservas.addElement(r);
            }
        }

        lblTituloPaso.setText("Detalle de la Consulta");
        scrollDetalleConsulta.setVisible(true);
        scrollReservas.setVisible(true);
        btnVerReserva.setVisible(true);
    }
}