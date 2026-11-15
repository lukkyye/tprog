package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import  logica.datatypes.DtPaciente;
import  logica.datatypes.DtResConsulta;
import  logica.datatypes.DtResConsultaDetalle;
import  logica.excepciones.ConsultaNotFoundException;
import  logica.excepciones.UserNotFoundException;
import  logica.controladores.ControladorUsuario;

public class ConsultaDeReservaDeConsulta extends JInternalFrame {

    private DefaultListModel<DtPaciente> modelListaPacientes;
    private JList<DtPaciente> listaPacientes;
    private JComboBox<String> comboNicknames;
    private JButton btnSeleccionarPaciente;
    private JScrollPane scrollPacientes;

    private DefaultListModel<DtResConsulta> modelListaReservas;
    private JList<DtResConsulta> listaReservas;
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
            panel.setBorder(BorderFactory.createTitledBorder("Paciente: " + value.getNickname()));
            panel.setBackground(isSelected ? new Color(220, 235, 252) : Color.WHITE);
            panel.add(new JLabel(" Nickname: " + value.getNickname()));
            panel.add(new JLabel(" Documento: " + value.getDoc()));
            panel.add(new JLabel(" Fecha Nac.: " + value.getFechaNac()));
            panel.add(new JLabel(" Fecha Ingreso: " + value.getFechaIngreso()));
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
            panel.setBorder(BorderFactory.createTitledBorder("Reserva #" + value.getNumReserva()));
            panel.setBackground(isSelected ? new Color(220, 235, 252) : Color.WHITE);
            panel.add(new JLabel(" Consulta: " + value.getNomC()));
            panel.add(new JLabel(" N° Reserva: " + value.getNumReserva()));
            panel.add(new JLabel(" Fecha Reserva: " + value.getFechaRes()));
            panel.add(new JLabel(" Aplica Beneficio: " + (value.getAplicaBenef() ? "Sí" : "No")));
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
                DtPaciente seleccionado = listaPacientes.getSelectedValue();
                if (seleccionado != null) {
                    comboNicknames.setSelectedItem(seleccionado.getNickname());
                }
            }
        });

        listaReservas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                DtResConsulta seleccionada = listaReservas.getSelectedValue();
                if (seleccionada != null) {
                    comboNumReservas.setSelectedItem(seleccionada.getNomC());
                }
            }
        });

        btnSeleccionarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nickname = (String) comboNicknames.getSelectedItem();
                if (nickname != null) {
                    Set<DtResConsulta> reservas=null;
					try {
						reservas = controladorUsuario.listarResConsultas(nickname);
					} catch (UserNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
                    DtResConsultaDetalle detalle=null;
					try {
						detalle = controladorUsuario.listarDatosResConsulta(nomC, nickname);
					} catch (UserNotFoundException | ConsultaNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} //REVISAR ESTO, FUNCIONA RARO ESTA FUNCION EN CONTROLADOR USUARIO. ALGO HAY QUE CAMBIAR.
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

    public void cargarPacientes(Set<DtPaciente> lista) {
        modelListaPacientes.clear();
        comboNicknames.removeAllItems();
        if (lista != null) {
            for (DtPaciente p : lista) {
                modelListaPacientes.addElement(p);
                comboNicknames.addItem(p.getNickname()); 
            }
        }
    }

    private void mostrarPaso2(String nickname, Set<DtResConsulta> reservas) {
        scrollPacientes.setVisible(false);
        comboNicknames.setVisible(false);
        btnSeleccionarPaciente.setVisible(false);

        modelListaReservas.clear();
        comboNumReservas.removeAllItems();
        for (DtResConsulta r : reservas) {
            modelListaReservas.addElement(r);
            comboNumReservas.addItem(r.getNomC());
        }

        lblTituloPaso.setText("Seleccione una Reserva del paciente '" + nickname + "'");
        scrollReservas.setVisible(true);
        comboNumReservas.setVisible(true);
        btnSeleccionarReserva.setVisible(true);
    }

  
    private void mostrarPopUpDetalle(String nomC, DtResConsultaDetalle detalle) {
        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("          DETALLE DE LA RESERVA          \n");
        sb.append("=========================================\n");
        sb.append("Número de Reserva: ").append(detalle.getNumReserva()).append("\n");
        sb.append("Fecha de Reserva:  ").append(detalle.getFechaRes()).append("\n");
        sb.append("Paciente que Reservo:  ").append(detalle.getNicknamePacienteRes()).append("\n");
        sb.append("Aplica Beneficio:  ").append(detalle.getAplicaBenef() ? "Sí" : "No").append("\n");
        sb.append("Costo Reserva:     $").append(detalle.getCostoR()).append("\n\n");
        sb.append("-----------------------------------------\n");
        sb.append("         INFORMACIÓN DE CONSULTA         \n");
        sb.append("-----------------------------------------\n");
        sb.append("Nombre Consulta:   ").append(detalle.getNomC()).append("\n");
        sb.append("Médico a Cargo:    ").append(detalle.getMedicoC()).append("\n");
        sb.append("Fecha Consulta:    ").append(detalle.getFechaC()).append("\n");
        sb.append("Número de Sala:    ").append(detalle.getSalaC()).append("\n");
        sb.append("Cantidad Números:  ").append(detalle.getCantNumC()).append("\n");
        sb.append("Costo Consulta:    $").append(detalle.getCostoC()).append("\n");

        JTextArea areaDetalle = new JTextArea(sb.toString());
        areaDetalle.setEditable(false);
        areaDetalle.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPopUp = new JScrollPane(areaDetalle);
        scrollPopUp.setPreferredSize(new Dimension(420, 280));

        JOptionPane.showMessageDialog(this, scrollPopUp, "Detalle de Reserva #" + detalle.getNumReserva(), JOptionPane.INFORMATION_MESSAGE);
    }
}