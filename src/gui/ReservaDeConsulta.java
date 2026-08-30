package gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.datatypes.DTPaciente;
import logica.controladores.ControladorClinica;
import logica.datatypes.DTConsulta;
import logica.datatypes.DTDiaConsulta;

import logica.excepciones.ReservaAlreadyExistsException;
import logica.excepciones.NoNumberAvailableException;
import javax.swing.JOptionPane;

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
        ControladorClinica controlador = new ControladorClinica();
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{197, 197, 197, 0};
        gridBagLayout.rowHeights = new int[]{0, 60, 97, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblNewLabel = new JLabel(" Día de consulta:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 1;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        JComboBox comboBox_Dia_de_Consulta = new JComboBox();
        GridBagConstraints gbc_comboBox_Dia_de_Consulta = new GridBagConstraints();
        gbc_comboBox_Dia_de_Consulta.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_Dia_de_Consulta.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_Dia_de_Consulta.gridx = 1;
        gbc_comboBox_Dia_de_Consulta.gridy = 1;
        getContentPane().add(comboBox_Dia_de_Consulta, gbc_comboBox_Dia_de_Consulta);
        for (DTDiaConsulta d : controlador.listarDatosDiaConsulta()) {
            comboBox_Dia_de_Consulta.addItem(d.nomD());
        }
        
        JLabel lblNewLabel_1 = new JLabel("Consulta:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 2;
        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        JComboBox comboBox_Consulta = new JComboBox();
        GridBagConstraints gbc_comboBox_Consulta = new GridBagConstraints();
        gbc_comboBox_Consulta.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_Consulta.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_Consulta.gridx = 1;
        gbc_comboBox_Consulta.gridy = 2;
        getContentPane().add(comboBox_Consulta, gbc_comboBox_Consulta);
        comboBox_Dia_de_Consulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboBox_Consulta.removeAllItems();
                String dia = comboBox_Dia_de_Consulta.getSelectedItem().toString();
                for (DTConsulta c : controlador.selecListaConsultaDelDia(dia)) {
                    comboBox_Consulta.addItem(c.nomC());
                }
            }
        });
        
        JLabel lblNewLabel_2 = new JLabel("Paciente:");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 3;
        getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        JComboBox comboBox_Paciente = new JComboBox();
        	for (DTPaciente p : controlador.listarPacientes()) {//datos
        		comboBox_Paciente.addItem(p.nickname());
        	}
        GridBagConstraints gbc_comboBox_Paciente = new GridBagConstraints();
        gbc_comboBox_Paciente.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_Paciente.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_Paciente.gridx = 1;
        gbc_comboBox_Paciente.gridy = 3;
        getContentPane().add(comboBox_Paciente, gbc_comboBox_Paciente);
        
        JButton btnReserva = new JButton("Reservar");
        btnReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String consulta = comboBox_Consulta.getSelectedItem().toString();
                String paciente = comboBox_Paciente.getSelectedItem().toString();
                try {
                    controlador.reservarConsulta(paciente, consulta);
                    JOptionPane.showMessageDialog(
                        null,
                        "Reserva realizada correctamente."
                    );
                } catch (ReservaAlreadyExistsException ex) {
                    JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "Ya existe una reserva",
                        JOptionPane.ERROR_MESSAGE
                    );
                } catch (NoNumberAvailableException ex) {
                    JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "No hay números disponibles",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        GridBagConstraints gbc_btnReserva = new GridBagConstraints();
        gbc_btnReserva.insets = new Insets(0, 0, 0, 5);
        gbc_btnReserva.gridx = 1;
        gbc_btnReserva.gridy = 4;
        getContentPane().add(btnReserva, gbc_btnReserva);
   }
}
