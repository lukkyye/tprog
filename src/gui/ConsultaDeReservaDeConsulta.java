package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import logica.datatypes.DTPaciente;
import logica.datatypes.DTResConsulta;
import logica.datatypes.DTResConsultaDetalle;
import logica.controladores.ControladorUsuario;

public class ConsultaDeReservaDeConsulta extends JInternalFrame {

    private DefaultListModel<DTPaciente> modelListaPacientes;
    private JList<DTPaciente> listaPacientes;
    private JComboBox<String> comboNicknames;
    private JButton btnSeleccionarPaciente;
    private JScrollPane scrollPacientes;

    private DefaultListModel<DTResConsulta> modelListaReservas;
    private JList<DTResConsulta> listaReservas;
    private JComboBox<Integer> comboNumReservas;
    private JButton btnSeleccionarReserva;
    private JScrollPane scrollReservas;

    private JTextArea areaDetalleReserva;
    private JScrollPane scrollDetalle;

    private JLabel lblTituloPaso;

    private ControladorUsuario controladorUsuario;

    public ConsultaDeReservaDeConsulta() {
        super(
            "Consulta de Reserva de Consulta", // Título
            true,                              // Redimensionable
            true,                              // Cerrable
            true,                              // Maximizable
            true                               // Minimizable
        );
        
        setBounds(100, 100, 520, 480);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        controladorUsuario = new ControladorUsuario();

        lblTituloPaso = new JLabel("Seleccione un Paciente");
        lblTituloPaso.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTituloPaso.setBounds(30, 10, 440, 20);
        getContentPane().add(lblTituloPaso);

        modelListaPacientes = new DefaultListModel<>();
        listaPacientes = new JList<>(modelListaPacientes);
        
        listaPacientes.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JPanel panel = new JPanel(new GridLayout(4, 1, 2, 2));
            panel.setBorder(BorderFactory.createTitledBorder("Paciente: " + value.nickname()));
            panel.setBackground(isSelected ? new Color(220, 235, 252) : Color.WHITE);
            panel.add(new JLabel(" Nickname: " + value.nickname()));
            panel.add(new JLabel(" Documento: " + value.doc()));
            panel.add(new JLabel(" Fecha Nac.: " + value.fechaNac()));
            panel.add(new JLabel(" Fecha Ingreso: " + value.fechaIngreso()));
            return panel;
        });

        scrollPacientes = new JScrollPane(listaPacientes);
        scrollPacientes.setBounds(30, 35, 440, 240);
        getContentPane().add(scrollPacientes);

        comboNicknames = new JComboBox<>();
        comboNicknames.setBounds(30, 290, 260, 30);
        getContentPane().add(comboNicknames);

        btnSeleccionarPaciente = new JButton("Ver Reservas");
        btnSeleccionarPaciente.setBounds(300, 290, 170, 30);
        getContentPane().add(btnSeleccionarPaciente);

        modelListaReservas = new DefaultListModel<>();
        listaReservas = new JList<>(modelListaReservas);

        scrollReservas = new JScrollPane(listaReservas);
        scrollReservas.setBounds(30, 35, 440, 240);
        scrollReservas.setVisible(false);
        getContentPane().add(scrollReservas);

        comboNumReservas = new JComboBox<>();
        comboNumReservas.setBounds(30, 290, 260, 30);
        comboNumReservas.setVisible(false);
        getContentPane().add(comboNumReservas);

        btnSeleccionarReserva = new JButton("Ver Detalle");
        btnSeleccionarReserva.setBounds(300, 290, 170, 30);
        btnSeleccionarReserva.setVisible(false);
        getContentPane().add(btnSeleccionarReserva);

        areaDetalleReserva = new JTextArea();
        areaDetalleReserva.setEditable(false);
        areaDetalleReserva.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        scrollDetalle = new JScrollPane(areaDetalleReserva);
        scrollDetalle.setBounds(30, 35, 440, 380);
        scrollDetalle.setVisible(false);
        getContentPane().add(scrollDetalle);

        listaPacientes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                DTPaciente seleccionado = listaPacientes.getSelectedValue();
                if (seleccionado != null) {
                    comboNicknames.setSelectedItem(seleccionado.nickname());
                }
            }
        });

        btnSeleccionarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nickname = (String) comboNicknames.getSelectedItem();
                if (nickname != null) {
                    HashSet<DTResConsulta> reservas = controladorUsuario.listarResConsultas(nickname);
                    if (reservas != null && !reservas.isEmpty()) {
                        mostrarPaso2(nickname, reservas);
                    } else {
                        JOptionPane.showMessageDialog(null, "El paciente seleccionado no posee reservas asociadas.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un paciente.");
                }
            }
        });

        btnSeleccionarReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nickname = (String) comboNicknames.getSelectedItem();
                Integer numReserva = (Integer) comboNumReservas.getSelectedItem();

                if (nickname != null && numReserva != null) {
                    DTResConsultaDetalle detalle = controladorUsuario.listarDatosResConsulta(nickname, numReserva);
                    if (detalle != null) {
                        mostrarPaso3(detalle);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontraron detalles para la reserva indicada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una reserva de la lista.");
                }
            }
        });

        cargarPacientes(controladorUsuario.listarPacientes());
    }

    public void cargarPacientes(HashSet<DTPaciente> lista) {
        modelListaPacientes.clear();
        comboNicknames.removeAllItems();
        if (lista != null) {
            for (DTPaciente p : lista) {
                modelListaPacientes.addElement(p);
                comboNicknames.addItem(p.nickname()); 
            }
        }
    }

    private void mostrarPaso2(String nickname, HashSet<DTResConsulta> reservas) {
        scrollPacientes.setVisible(false);
        comboNicknames.setVisible(false);
        btnSeleccionarPaciente.setVisible(false);

        modelListaReservas.clear();
        comboNumReservas.removeAllItems();
        for (DTResConsulta r : reservas) {
            modelListaReservas.addElement(r);
            comboNumReservas.addItem(r.numReserva());
        }

        lblTituloPaso.setText("Seleccione una Reserva del paciente '" + nickname + "'");
        scrollReservas.setVisible(true);
        comboNumReservas.setVisible(true);
        btnSeleccionarReserva.setVisible(true);
    }

    private void mostrarPaso3(DTResConsultaDetalle detalle) {
        scrollReservas.setVisible(false);
        comboNumReservas.setVisible(false);
        btnSeleccionarReserva.setVisible(false);

        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("          DETALLE DE LA RESERVA          \n");
        sb.append("=========================================\n\n");
        sb.append("Número de Reserva: ").append(detalle.numReserva()).append("\n");
        sb.append("Fecha de Reserva:  ").append(detalle.fechaRes()).append("\n");
        sb.append("Aplica Beneficio:  ").append(detalle.aplicaBenef() ? "Sí" : "No").append("\n");
        sb.append("Costo Final:       $").append(detalle.costoR()).append("\n\n");
        sb.append("-----------------------------------------\n");
        sb.append("         INFORMACIÓN DE CONSULTA         \n");
        sb.append("-----------------------------------------\n");
        sb.append("Nombre Consulta:   ").append(detalle.nomC()).append("\n");
        sb.append("Médico a Cargo:    ").append(detalle.medicoC()).append("\n");
        sb.append("Fecha Consulta:    ").append(detalle.fechaC()).append("\n");
        sb.append("Número de Sala:    ").append(detalle.salaC()).append("\n");
        sb.append("Cantidad Números:  ").append(detalle.cantNumC()).append("\n");
        sb.append("Costo Consulta:    $").append(detalle.costoC()).append("\n");

        areaDetalleReserva.setText(sb.toString());

        lblTituloPaso.setText("Datos Detallados de la Reserva");
        scrollDetalle.setVisible(true);
    }
}