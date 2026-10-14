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
        setBounds(100, 100, 540, 420);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        controladorClinica = new ControladorClinica();
        controladorUsuario = new ControladorUsuario();

        lblTituloPaso = new JLabel("Seleccione un Día de Consulta");
        lblTituloPaso.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTituloPaso.setBounds(30, 10, 450, 20);
        getContentPane().add(lblTituloPaso);

        modelListaDias = new DefaultListModel<>();
        listaDias = new JList<>(modelListaDias);
        listaDias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        listaConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaConsultas.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JPanel panel = new JPanel(new GridLayout(5, 1, 2, 2));
            panel.setBorder(BorderFactory.createTitledBorder("Consulta: " + value.nomC()));
            panel.setBackground(isSelected ? new Color(220, 235, 252) : Color.WHITE);
            panel.add(new JLabel(" Nombre: " + value.nomC()));
            panel.add(new JLabel(" Fecha: " + value.fechaC()));
            panel.add(new JLabel(" Sala: " + value.sala()));
            panel.add(new JLabel(" Cant. Números: " + value.cantNum()));
            panel.add(new JLabel(" Costo: $" + value.costoC()));
            panel.add(new JLabel(" Estado: " + value.estado()));
            return panel;
        });

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

        listaConsultas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                DTConsulta seleccionada = listaConsultas.getSelectedValue();
                if (seleccionada != null) {
                    comboNomC.setSelectedItem(seleccionada.nomC());
                }
            }
        });

        listaDias.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                DTDiaConsulta seleccionado = listaDias.getSelectedValue();
                if (seleccionado != null) {
                    comboNomD.setSelectedItem(seleccionado.nomD());
                }
            }
        });

        btnSeleccionarDia.addActionListener(e -> {
            String nomD = (String) comboNomD.getSelectedItem();
            if (nomD != null) {
                Set<DTConsulta> consultas = controladorClinica.selecListaConsultaDelDia(nomD);
                if (consultas != null && !consultas.isEmpty()) {
                    mostrarPaso2(consultas);
                } else {
                    JOptionPane.showMessageDialog(this, "El día seleccionado no tiene consultas asociadas.", "Sin Consultas", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un día de la lista.", "Atención", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnSeleccionarConsulta.addActionListener(e -> {
            String nomC = (String) comboNomC.getSelectedItem();
            if (nomC != null) {
                DTConsultaDetalle detalle = controladorClinica.listarDatosConsulta(nomC);
                if (detalle != null) {
                    mostrarPopUpDetalle(nomC, detalle);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontraron detalles para la consulta seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una consulta de la lista.", "Atención", JOptionPane.WARNING_MESSAGE);
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

    private void mostrarPopUpDetalle(String nomC, DTConsultaDetalle detalle) {
        JPanel panelPopUp = new JPanel(new BorderLayout(10, 10));
        panelPopUp.setPreferredSize(new Dimension(460, 340));

        JTextArea areaDetalle = new JTextArea();
        areaDetalle.setEditable(false);
        areaDetalle.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaDetalle.setText(
            "Consulta: " + detalle.nomC() + "\n" +
            "Médico: " + detalle.medico() + "\n" +
            "Fecha: " + detalle.fechaC() + "\n" +
            "Sala: " + detalle.sala() + "\n" +
            "Cantidad de números: " + detalle.cantNum() + "\n" +
            "Costo: $" + detalle.costoC()
        );
        panelPopUp.add(new JScrollPane(areaDetalle), BorderLayout.NORTH);

        DefaultListModel<DTResConsulta> modelReservasPopUp = new DefaultListModel<>();
        if (detalle.reservas() != null) {
            for (DTResConsulta r : detalle.reservas()) {
                modelReservasPopUp.addElement(r);
            }
        }
        
        JList<DTResConsulta> listaReservasPopUp = new JList<>(modelReservasPopUp);
        listaReservasPopUp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        listaReservasPopUp.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JPanel panel = new JPanel(new GridLayout(3, 1, 1, 1));
            panel.setBorder(BorderFactory.createTitledBorder("Reserva #" + value.numReserva()));
            panel.setBackground(isSelected ? new Color(220, 235, 252) : Color.WHITE);
            panel.add(new JLabel(" N° Reserva: " + value.numReserva()));
            panel.add(new JLabel(" Fecha: " + value.fechaRes()));
            panel.add(new JLabel(" Beneficio: " + (value.aplicaBenef() ? "Sí" : "No")));
            return panel;
        });

        JScrollPane scrollReservasPopUp = new JScrollPane(listaReservasPopUp);
        scrollReservasPopUp.setBorder(BorderFactory.createTitledBorder("Reservas asociadas (Seleccione una):"));
        panelPopUp.add(scrollReservasPopUp, BorderLayout.CENTER);

        JButton btnVerReservaPopUp = new JButton("Ver Detalle de Reserva");
        btnVerReservaPopUp.addActionListener(e -> {
            DTResConsulta reservaSel = listaReservasPopUp.getSelectedValue();
            if (reservaSel != null) {
                DTResConsultaDetalle resDetalle = controladorClinica.listarDatosResConsultaCC(nomC, reservaSel.numReserva());
                if (resDetalle != null) {
                    JOptionPane.showMessageDialog(this,
                        "=== DATOS DE LA RESERVA ===\n" +
                        "Número de Reserva: " + resDetalle.numReserva() + "\n" +
                        "Fecha Reserva: " + resDetalle.fechaRes() + "\n" +
                        "Aplica Beneficio: " + (resDetalle.aplicaBenef() ? "Sí" : "No") + "\n" +
                        "Costo Reserva: $" + resDetalle.costoR() + "\n\n" +
                        "=== DATOS DE LA CONSULTA ===\n" +
                        "Consulta: " + nomC + "\n" +
                        "Médico: " + resDetalle.medicoC() + "\n" +
                        "Fecha Consulta: " + resDetalle.fechaC() + "\n" +
                        "Sala: " + resDetalle.salaC(),
                        "Detalle de Reserva #" + resDetalle.numReserva(),
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontraron detalles para la reserva indicada.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una reserva de la lista.", "Atención", JOptionPane.WARNING_MESSAGE);
            }
        });
        panelPopUp.add(btnVerReservaPopUp, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this, panelPopUp, "Detalle de Consulta: " + nomC, JOptionPane.PLAIN_MESSAGE);
    }
}