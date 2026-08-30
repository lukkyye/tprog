package gui;

import javax.swing.JInternalFrame;

public class ConsultaDeClinica extends JInternalFrame {

    public ConsultaDeClinica() {

        super(
            "Consulta de Clinica", // título
            true,              // se puede redimensionar
            true,              // se puede cerrar
            true,              // se puede maximizar
            true               // se puede minimizar
        );

        setSize(600, 500);
   }
}
