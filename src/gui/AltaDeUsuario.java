package gui;

import javax.swing.JInternalFrame;

public class AltaDeUsuario extends JInternalFrame {

    public AltaDeUsuario() {

        super(
            "Alta de Usuario", // título
            true,              // se puede redimensionar
            true,              // se puede cerrar
            true,              // se puede maximizar
            true               // se puede minimizar
        );

        setSize(600, 500);
    }
}
