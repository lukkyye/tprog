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
    private JComboBox<String> comboNumReservas;
    private JButton btnSeleccionarReserva;
    private JScrollPane scrollReservas;

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
        
        setBounds(100, 100, 520, 400);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        controladorUsuario = new ControladorUsuario();

        lblTituloPaso = new JLabel("Seleccione un Paciente");
        lblTituloPaso.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTituloPaso.setBounds(30, 10, 440, 20);
        getContentPane().add(lblTituloPaso);

        modelListaPacientes = new DefaultListModel<>();
        listaPacientes = new JList<>(modelListaPacientes);
        listaPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
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
        listaReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaReservas.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JPanel panel = new JPanel(new GridLayout(3, 1, 2, 2));
            panel.setBorder(BorderFactory.createTitledBorder("Reserva #" + value.numReserva()));
            panel.setBackground(isSelected ? new Color(220, 235, 252) : Color.WHITE);
            panel.add(new JLabel(" Consulta: " + value.nomC()));
            panel.add(new JLabel(" N° Reserva: " + value.numReserva()));
            panel.add(new JLabel(" Fecha Reserva: " + value.fechaRes()));
            panel.add(new JLabel(" Aplica Beneficio: " + (value.aplicaBenef() ? "Sí" : "No")));
            return panel;
        });

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

        listaPacientes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                DTPaciente seleccionado = listaPacientes.getSelectedValue();
                if (seleccionado != null) {
                    comboNicknames.setSelectedItem(seleccionado.nickname());
                }
            }
        });

        listaReservas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                DTResConsulta seleccionada = listaReservas.getSelectedValue();
                if (seleccionada != null) {
                    comboNumReservas.setSelectedItem(seleccionada.nomC());
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
                        JOptionPane.showMessageDialog(ConsultaDeReservaDeConsulta.this, "El paciente seleccionado no posee reservas asociadas.", "Sin Reservas", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ConsultaDeReservaDeConsulta.this, "Seleccione un paciente.", "Atención", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnSeleccionarReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String nomC = (String) comboNumReservas.getSelectedItem();
                String nickname = (String) comboNicknames.getSelectedItem();

                if (nomC != null && nickname != null) {
                    DTResConsultaDetalle detalle = controladorUsuario.listarDatosResConsulta(nomC, nickname); //REVISAR ESTO, FUNCIONA RARO ESTA FUNCION EN CONTROLADOR USUARIO. ALGO HAY QUE CAMBIAR.
                    if (detalle != null) {
                        mostrarPopUpDetalle(nomC, detalle);
                    } else {
                        JOptionPane.showMessageDialog(ConsultaDeReservaDeConsulta.this, "No se encontraron detalles para la reserva indicada.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(ConsultaDeReservaDeConsulta.this, "Seleccione una reserva de la lista.", "Atención", JOptionPane.WARNING_MESSAGE);
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
            comboNumReservas.addItem(r.nomC());
        }

        lblTituloPaso.setText("Seleccione una Reserva del paciente '" + nickname + "'");
        scrollReservas.setVisible(true);
        comboNumReservas.setVisible(true);
        btnSeleccionarReserva.setVisible(true);
    }

  
    private void mostrarPopUpDetalle(String nomC, DTResConsultaDetalle detalle) {
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

        JOptionPane.showMessageDialog(this, scrollPopUp, "Detalle de Reserva #" + detalle.numReserva(), JOptionPane.INFORMATION_MESSAGE);
    }
}