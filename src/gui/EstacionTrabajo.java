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

        JMenuItem itemAltaUsuario = new JMenuItem("Alta de Usuario");
        itemAltaUsuario.addActionListener(e->{
        	AltaDeUsuario newwindow = new AltaDeUsuario();
        	desktopPane.add(newwindow);
        	newwindow.setVisible(true);
        });
        JMenuItem itemConsultaUsuario = new JMenuItem("Consulta de Usuario");
     
        itemConsultaUsuario.addActionListener(e->{
        	ConsultaDeUsuario ventana = new ConsultaDeUsuario();
        	desktopPane.add(ventana);
        	ventana.setVisible(true);
        });
        menuUsuarios.add(itemAltaUsuario);
        menuUsuarios.add(itemConsultaUsuario);
        
        menuBar.add(menuUsuarios);

        setJMenuBar(menuBar);
        
        JMenu consultaMenu = new JMenu("Consultas");
        menuBar.add(consultaMenu);
        
        JMenuItem reservaConsultaItem = new JMenuItem("Reserva de Consulta");
        reservaConsultaItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ReservaDeConsulta ventana = new ReservaDeConsulta();
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
        
        consultaMenu.add(consultadiaConsultaItem);
        consultaMenu.add(reservaConsultaItem);
        consultaMenu.add(consultaClinicaItem);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            EstacionTrabajo ventana = new EstacionTrabajo();
            ventana.setVisible(true);
        });
    }
}
