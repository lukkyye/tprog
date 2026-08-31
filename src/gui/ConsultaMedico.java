package gui;

import javax.swing.JInternalFrame;

public class ConsultaMedico extends JInternalFrame {

    private String nickname;

    public ConsultaMedico(String nickname) {
        super(
            "Consulta de Médico",
            true,
            true,
            true,
            true
        );

        this.nickname = nickname;

        setSize(600, 500);
    }
}