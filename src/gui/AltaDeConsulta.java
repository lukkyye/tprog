package gui;

import javax.swing.JInternalFrame;

public class AltaDeConsulta extends JInternalFrame {

    public AltaDeConsulta() {
        super(
            "Alta de Consulta", // título
            true,              // se puede redimensionar
            true,              // se puede cerrar
            true,              // se puede maximizar
            true               // se puede minimizar
        );

        setSize(600, 500);
        setMaximizable(true);
		setTitle("Alta de Consulta");
		setResizable(true);
		
   }
}

