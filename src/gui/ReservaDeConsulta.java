package gui;

import javax.swing.JInternalFrame;

public class ReservaDeConsulta extends JInternalFrame {

    public ReservaDeConsulta() {

        super(
            "Reserva de Consulta", // título
            true,              // se puede redimensionar
            true,              // se puede cerrar
            true,              // se puede maximizar
            true               // se puede minimizar
        );

        setSize(600, 500);
   }
}
