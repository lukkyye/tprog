package gui;

import javax.swing.JInternalFrame;

public class ConsultaDeDiaDeConsulta extends JInternalFrame {

    public ConsultaDeDiaDeConsulta() {

        super(
            "Consulta de Dia de Consulta", // título
            true,              // se puede redimensionar
            true,              // se puede cerrar
            true,              // se puede maximizar
            true               // se puede minimizar
        );

        setSize(600, 500);
   }
}
