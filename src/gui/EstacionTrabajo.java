package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EstacionTrabajo extends JFrame { //ventana externa

    private JDesktopPane desktopPane; //zona grande donde después se abren las ventanas internas

    public EstacionTrabajo() {

        setTitle("Estación de Trabajo");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Área donde se van a abrir los casos de uso
        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        // Barra de menú
        JMenuBar menuBar = new JMenuBar();  //menú de arriba

        // Menú Usuarios
        JMenu menuUsuarios = new JMenu("Usuarios"); //menu en menu
        
        // Menú Clinica
        JMenu menuClinicas = new JMenu("Clinicas");

        JMenuItem itemAltaUsuario = new JMenuItem("Alta de Usuario");
        itemAltaUsuario.addActionListener(e->{
        	AltaDeUsuario newwindow = new AltaDeUsuario();
        	desktopPane.add(newwindow);
        	newwindow.setVisible(true);
        });
        JMenuItem itemConsultaUsuario = new JMenuItem("Consulta de Usuario");
        itemConsultaUsuario.addActionListener(e->{
        	ConsultaDeUsuario newwindow = new ConsultaDeUsuario();
        	desktopPane.add(newwindow);
        	newwindow.setVisible(true);
        });
        // aagregar el resto claramente

        menuUsuarios.add(itemAltaUsuario);
        menuUsuarios.add(itemConsultaUsuario);
        //agregar el resto claramente

        menuBar.add(menuUsuarios);
        menuBar.add(menuClinicas);

        setJMenuBar(menuBar);
        
        JMenu consultaMenu = new JMenu("Consultas");
        menuBar.add(consultaMenu);
        
        JMenuItem altaConsultaMenuItem = new JMenuItem("Alta de Consulta");
        altaConsultaMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AltaDeConsulta ventana = new AltaDeConsulta();
                desktopPane.add(ventana);
                ventana.setVisible(true);
                
        	}
        });
        
        JMenuItem reservaConsultaItem = new JMenuItem("Reserva de Consulta");
        reservaConsultaItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ReservaDeConsulta ventana = new ReservaDeConsulta();
                desktopPane.add(ventana);
                ventana.setVisible(true);
                
        	}
        });
        
        JMenuItem consultaConsultaItem = new JMenuItem("Consulta de Consulta");
        consultaConsultaItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ConsultaDeConsulta ventana = new ConsultaDeConsulta();
                desktopPane.add(ventana);
                ventana.setVisible(true);
                
        	}
        });
        JMenuItem altaClinicaItem = new JMenuItem("Alta de Clinica");
        altaClinicaItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AltaDeClinica ventana = new AltaDeClinica();
                desktopPane.add(ventana);
                ventana.setVisible(true);
                
        	}
        });
        
        JMenuItem consultaDeReservaDeConsultaItem = new JMenuItem("Consulta de Reserva de Consulta");
        consultaDeReservaDeConsultaItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ConsultaDeReservaDeConsulta ventana = new ConsultaDeReservaDeConsulta();
                desktopPane.add(ventana);
                ventana.setVisible(true);
                
        	}
        });
        
        JMenuItem consultaClinicaItem = new JMenuItem("Consulta de Clinica");
        consultaClinicaItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ConsultaDeClinica ventana = new ConsultaDeClinica();
                desktopPane.add(ventana);
                ventana.setVisible(true);
                
        	}
        });
        
        
        JMenuItem consultadiaConsultaItem = new JMenuItem("Consulta de Día de Consulta");
        consultadiaConsultaItem.addActionListener(e->{
        	ConsultaDeDiaDeConsulta ventana = new ConsultaDeDiaDeConsulta();
        	desktopPane.add(ventana);
        	ventana.setVisible(true);
        });
        
        
        //Alta dia consulta
        JMenuItem altaDiaConsultaMenuItem = new JMenuItem("Alta dia de Consulta");
        altaDiaConsultaMenuItem.addActionListener(e->{
        	AltaDeDiaDeConsulta ventana = new AltaDeDiaDeConsulta();
        	desktopPane.add(ventana);
        	ventana.setVisible(true);
        });
        consultaMenu.add(altaDiaConsultaMenuItem);
        	
        
        
        consultaMenu.add(altaConsultaMenuItem);
        consultaMenu.add(consultadiaConsultaItem);
        consultaMenu.add(reservaConsultaItem);
        consultaMenu.add(consultaClinicaItem);
        menuClinicas.add(altaClinicaItem);
        consultaMenu.add(consultaConsultaItem);

    	
    	

        consultaMenu.add(consultaDeReservaDeConsultaItem);


    	
    	

        menuClinicas.add(consultaClinicaItem);

        
        JMenu especialidadMenu = new JMenu("Especialidades");
        menuBar.add(especialidadMenu);
        
        JMenuItem altaEspecialidadItem = new JMenuItem("Alta de Especialidad");
        altaEspecialidadItem.addActionListener(e->{
        	AltaDeEspecialidad ventana = new AltaDeEspecialidad();
        	desktopPane.add(ventana);
        	ventana.setVisible(true);
        });
        especialidadMenu.add(altaEspecialidadItem);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            EstacionTrabajo ventana = new EstacionTrabajo();
            ventana.setVisible(true);
        });
    }
}
