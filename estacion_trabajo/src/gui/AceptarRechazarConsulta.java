package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import logica.datatypes.DTDiaConsulta;
import logica.datatypes.DTConsulta;
import logica.datatypes.EstadoConsulta;
import logica.manejadores.ManejadorConsulta;
import logica.controladores.ControladorClinica;
import logica.controladores.ControladorUsuario;

public class AceptarRechazarConsulta extends JInternalFrame {

    private DefaultListModel<DTDiaConsulta> modelListaDias;
    private JList<DTDiaConsulta> listaDias;
    private JComboBox<String> comboNomD;
    private JButton btnSeleccionarDia;
    private JScrollPane scrollDias;
    private String global_nomD;
    private DefaultListModel<DTConsulta> modelListaConsultas;
    private JList<DTConsulta> listaConsultas;
    private JComboBox<String> comboNomC;
    private JButton btnSeleccionarConsulta;
    private JScrollPane scrollConsultas;
    private JButton btnRechazar;
    private JButton btnAceptar;
    private JLabel lblTituloPaso;
    private ControladorClinica controladorClinica;
    private ControladorUsuario controladorUsuario;
    private JButton btnVolver;

    public AceptarRechazarConsulta() {
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
        
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(30, 332, 180, 30);
        getContentPane().add(btnAceptar);
        
        btnRechazar = new JButton("Rechazar");
        btnRechazar.setBounds(222, 332, 180, 30);
        getContentPane().add(btnRechazar);
        
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e->{
        	volver();
        });
        btnVolver.setBounds(414, 332, 76, 30);
        getContentPane().add(btnVolver);
        
        btnAceptar.setVisible(false);
        btnVolver.setVisible(false);
        btnRechazar.setVisible(false);
        
        btnAceptar.addActionListener(
        		e->{
        			if (listaConsultas.getSelectedValue()!=null) {
	        			DTConsulta dtconsu = listaConsultas.getSelectedValue();	        		
	        			ManejadorConsulta.getInstance().getConsulta(dtconsu.nomC()).setEstado(EstadoConsulta.Aceptada);
        				Set<DTConsulta> consultas = controladorClinica.selecListaConsultaDelDia(global_nomD);
	        			JOptionPane.showMessageDialog(this, "Se aceptó la consulta.", "Aceptada correctamente", JOptionPane.INFORMATION_MESSAGE);
	        			render_listaconsultas(consultas);
        			}
        		}
        );
        btnRechazar.addActionListener(
        		e->{
        			if (listaConsultas.getSelectedValue()!=null) {
	        			DTConsulta dtconsu = listaConsultas.getSelectedValue();	        		
	        			ManejadorConsulta.getInstance().getConsulta(dtconsu.nomC()).setEstado(EstadoConsulta.Rechazada);
        				Set<DTConsulta> consultas = controladorClinica.selecListaConsultaDelDia(global_nomD);
	        			JOptionPane.showMessageDialog(this, "Se rechazó la consulta", "Rechazada correctamente", JOptionPane.ERROR_MESSAGE);
	        			render_listaconsultas(consultas);
        			}
        		}
        );
        
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
                global_nomD=nomD;
                if (consultas != null && !consultas.isEmpty()) {
                    mostrarPaso2(consultas);
                } else {
                    JOptionPane.showMessageDialog(this, "El día seleccionado no tiene consultas asociadas.", "Sin Consultas", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un día de la lista.", "Atención", JOptionPane.WARNING_MESSAGE);
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
    private void render_listaconsultas(Set<DTConsulta> consultas) {
    	modelListaConsultas.clear();
    	for (DTConsulta c : consultas) {
        	if (c.estado().equals(EstadoConsulta.Ingresada)) {
        		modelListaConsultas.addElement(c);	
        	}
        }
    }
    private void mostrarPaso2(Set<DTConsulta> consultas) {
        scrollDias.setVisible(false);
        comboNomD.setVisible(false);
        btnSeleccionarDia.setVisible(false);

        comboNomC.removeAllItems();
        comboNomC.setVisible(false);
        render_listaconsultas(consultas);

        lblTituloPaso.setText("Seleccione una Consulta asociada");
        scrollConsultas.setVisible(true);
        btnSeleccionarConsulta.setVisible(false);
        btnAceptar.setVisible(true);
        btnRechazar.setVisible(true);
        btnVolver.setVisible(true);
    }
  private void volver() {
	  scrollDias.setVisible(true);
      comboNomD.setVisible(true);
      btnSeleccionarDia.setVisible(true);

      comboNomC.removeAllItems();
      comboNomC.setVisible(true);
      cargarDias(controladorClinica.listarDatosDiaConsulta());
      lblTituloPaso.setText("Seleccione una Consulta asociada");
      scrollConsultas.setVisible(false);
      btnSeleccionarConsulta.setVisible(true);
      btnAceptar.setVisible(false);
      btnRechazar.setVisible(false);
      btnVolver.setVisible(false);
  }
}