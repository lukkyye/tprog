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
        // aagregar el resto claramente

        menuUsuarios.add(itemAltaUsuario);
        menuUsuarios.add(itemConsultaUsuario);
        //agregar el resto claramente

        menuBar.add(menuUsuarios);

        setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("Consultas");
        menuBar.add(mnNewMenu);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Reserva de Consulta");
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ReservaDeConsulta ventana = new ReservaDeConsulta();
                desktopPane.add(ventana);
                ventana.setVisible(true);
                
        	}
        });
        mnNewMenu.add(mntmNewMenuItem);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            EstacionTrabajo ventana = new EstacionTrabajo();
            ventana.setVisible(true);
        });
    }
}
