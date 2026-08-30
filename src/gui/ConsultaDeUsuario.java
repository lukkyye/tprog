package gui;

import javax.swing.JInternalFrame;

public class ConsultaDeUsuario extends JInternalFrame {

    public ConsultaDeUsuario() {

        super(
            "Consulta de Usuario", // título
            true,              // se puede redimensionar
            true,              // se puede cerrar
            true,              // se puede maximizar
            true               // se puede minimizar
        );

        setSize(600, 500);
    }
}