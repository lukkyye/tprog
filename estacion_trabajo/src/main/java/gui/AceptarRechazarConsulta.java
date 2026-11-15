package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import  logica.datatypes.DtDiaConsulta;
import  logica.datatypes.DtConsulta;
import  logica.datatypes.EstadoConsulta;
import  logica.excepciones.ConsultaNotFoundException;
import  logica.manejadores.ManejadorConsulta;
import  logica.controladores.ControladorClinica;
import  logica.controladores.ControladorUsuario;

public class AceptarRechazarConsulta extends JInternalFrame {

    private DefaultListModel<DtDiaConsulta> modelListaDias;
    private JList<DtDiaConsulta> listaDias;
    private JComboBox<String> comboNomD;
    private JButton btnSeleccionarDia;
    private JScrollPane scrollDias;
    private String global_nomD;
    private DefaultListModel<DtConsulta> modelListaConsultas;
    private JList<DtConsulta> listaConsultas;
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
            panel.setBorder(BorderFactory.createTitledBorder("Día: " + value.getNomD()));
            panel.setBackground(isSelected ? new Color(220, 235, 252) : Color.WHITE);
            panel.add(new JLabel(" Nombre: " + value.getNomD()));
            panel.add(new JLabel(" Descripción: " + value.getDescD()));
            panel.add(new JLabel(" Día: " + value.getDia()));
            panel.add(new JLabel(" Hora Inicio: " + value.getHoraIni()));
            panel.add(new JLabel(" Costo: $" + value.getCostoD()));
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
            panel.setBorder(BorderFactory.createTitledBorder("Consulta: " + value.getNomC()));
            panel.setBackground(isSelected ? new Color(220, 235, 252) : Color.WHITE);
            panel.add(new JLabel(" Nombre: " + value.getNomC()));
            panel.add(new JLabel(" Fecha: " + value.getFechaC()));
            panel.add(new JLabel(" Sala: " + value.getSala()));
            panel.add(new JLabel(" Cant. Números: " + value.getCantNum()));
            panel.add(new JLabel(" Costo: $" + value.getCostoC()));
            panel.add(new JLabel(" Estado: " + value.getEstado()));
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
	        			DtConsulta dtconsu = listaConsultas.getSelectedValue();	        		
	        			try {
							ManejadorConsulta.getInstance().getConsulta(dtconsu.getNomC()).setEstado(EstadoConsulta.Aceptada);
						} catch (ConsultaNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        				Set<DtConsulta> consultas = controladorClinica.selecListaConsultaDelDia(global_nomD);
	        			JOptionPane.showMessageDialog(this, "Se aceptó la consulta.", "Aceptada correctamente", JOptionPane.INFORMATION_MESSAGE);
	        			render_listaconsultas(consultas);
        			}
        		}
        );
        btnRechazar.addActionListener(
        		e->{
        			if (listaConsultas.getSelectedValue()!=null) {
	        			DtConsulta dtconsu = listaConsultas.getSelectedValue();	        		
	        			try {
							ManejadorConsulta.getInstance().getConsulta(dtconsu.getNomC()).setEstado(EstadoConsulta.Rechazada);
						} catch (ConsultaNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        				Set<DtConsulta> consultas = controladorClinica.selecListaConsultaDelDia(global_nomD);
	        			JOptionPane.showMessageDialog(this, "Se rechazó la consulta", "Rechazada correctamente", JOptionPane.ERROR_MESSAGE);
	        			render_listaconsultas(consultas);
        			}
        		}
        );
        
        listaConsultas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                DtConsulta seleccionada = listaConsultas.getSelectedValue();
                if (seleccionada != null) {
                    comboNomC.setSelectedItem(seleccionada.getNomC());
                }
            }
        });

        listaDias.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                DtDiaConsulta seleccionado = listaDias.getSelectedValue();
                if (seleccionado != null) {
                    comboNomD.setSelectedItem(seleccionado.getNomD());
                }
            }
        });

        btnSeleccionarDia.addActionListener(e -> {
            String nomD = (String) comboNomD.getSelectedItem();
            if (nomD != null) {
                Set<DtConsulta> consultas = controladorClinica.selecListaConsultaDelDia(nomD);
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

    public void cargarDias(Set<DtDiaConsulta> lista) {
        modelListaDias.clear();
        comboNomD.removeAllItems();
        if (lista != null) {
            for (DtDiaConsulta dia : lista) {
                modelListaDias.addElement(dia);
                comboNomD.addItem(dia.getNomD());
            }
        }
    }
    private void render_listaconsultas(Set<DtConsulta> consultas) {
    	modelListaConsultas.clear();
    	for (DtConsulta c : consultas) {
        	if (c.getEstado().equals(EstadoConsulta.Ingresada)) {
        		modelListaConsultas.addElement(c);	
        	}
        }
    }
    private void mostrarPaso2(Set<DtConsulta> consultas) {
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