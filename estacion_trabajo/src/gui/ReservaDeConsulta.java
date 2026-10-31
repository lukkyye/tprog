package gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.datatypes.DtPaciente;
import logica.clases.Consulta;
import logica.controladores.ControladorClinica;
import logica.datatypes.DtConsulta;
import logica.datatypes.DtDiaConsulta;

import logica.excepciones.ReservaAlreadyExistsException;
import logica.excepciones.UserNotFoundException;
import logica.manejadores.ManejadorConsulta;
import logica.excepciones.BeneficioSinOrdenesException;
import logica.excepciones.ConsultaNotFoundException;
import logica.excepciones.NoNumberAvailableException;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;

import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JRadioButton;

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
        gridBagLayout.columnWidths = new int[]{0, 197, 244, 197, 0};
        gridBagLayout.rowHeights = new int[]{0, 60, 60, 60, 41, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblNewLabel = new JLabel("Día de consulta:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        JComboBox comboBox_Dia_de_Consulta = new JComboBox();
        GridBagConstraints gbc_comboBox_Dia_de_Consulta = new GridBagConstraints();
        gbc_comboBox_Dia_de_Consulta.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_Dia_de_Consulta.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_Dia_de_Consulta.gridx = 2;
        gbc_comboBox_Dia_de_Consulta.gridy = 1;
        getContentPane().add(comboBox_Dia_de_Consulta, gbc_comboBox_Dia_de_Consulta);
        for (DtDiaConsulta d : controlador.listarDatosDiaConsulta()) {
            comboBox_Dia_de_Consulta.addItem(d.getNomD());
        }
        
        JLabel lblNewLabel_1 = new JLabel("Consulta:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 2;
        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        JComboBox comboBox_Consulta = new JComboBox();
        GridBagConstraints gbc_comboBox_Consulta = new GridBagConstraints();
        gbc_comboBox_Consulta.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_Consulta.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_Consulta.gridx = 2;
        gbc_comboBox_Consulta.gridy = 2;
        getContentPane().add(comboBox_Consulta, gbc_comboBox_Consulta);
        comboBox_Dia_de_Consulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                comboBox_Consulta.removeAllItems();
                String dia = comboBox_Dia_de_Consulta.getSelectedItem().toString();
                for (DtConsulta c : controlador.selecListaConsultaDelDia(dia)) {
                    comboBox_Consulta.addItem(c.getNomC());
                }
            }
        });
        
        JLabel lblNewLabel_2 = new JLabel("Paciente:");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 3;
        getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        JComboBox comboBox_Paciente = new JComboBox();
        	for (DtPaciente p : controlador.listarPacientes()) {//datos
        		comboBox_Paciente.addItem(p.getNickname());
        	}
        GridBagConstraints gbc_comboBox_Paciente = new GridBagConstraints();
        gbc_comboBox_Paciente.insets = new Insets(0, 0, 5, 5);
        gbc_comboBox_Paciente.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox_Paciente.gridx = 2;
        gbc_comboBox_Paciente.gridy = 3;
        getContentPane().add(comboBox_Paciente, gbc_comboBox_Paciente);
        
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.getCalendarButton().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        
      
        
        JLabel lblNewLabel_3 = new JLabel("Fecha de reservacion:");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 1;
        gbc_lblNewLabel_3.gridy = 4;
        getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
        
      
        GridBagConstraints gbc_dateChooser = new GridBagConstraints();
        gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
        gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
        gbc_dateChooser.gridx = 2;
        gbc_dateChooser.gridy = 4;
        getContentPane().add(dateChooser, gbc_dateChooser);
        
        JRadioButton rdbtnBeneficioAnual = new JRadioButton("Beneficio anual");
        
        JButton btnReserva = new JButton("Reservar");
        btnReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (comboBox_Consulta.getSelectedItem() == null || comboBox_Paciente.getSelectedItem() == null) {
            		    JOptionPane.showMessageDialog(
            		        null,
            		        "Debe seleccionar una consulta y un paciente.",
            		        "Datos incompletos",
            		        JOptionPane.WARNING_MESSAGE
            		    );
            		    return;
            		}
            		String consulta = comboBox_Consulta.getSelectedItem().toString();
            		String paciente = comboBox_Paciente.getSelectedItem().toString();
            		
            		Consulta a=null;
					try {
						a = ManejadorConsulta.getInstance().getConsulta(comboBox_Consulta.getSelectedItem().toString());
					} catch (ConsultaNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		if (a.getFechaC().isBefore(LocalDate.ofInstant(dateChooser.getDate().toInstant(), ZoneId.systemDefault()))) {
            			JOptionPane.showMessageDialog(
                		        null,
                		        "La fecha de la reserva debe de ser anterior a la fecha de la consulta.",
                		        "Error en fecha",
                		        JOptionPane.WARNING_MESSAGE
                		    );
            			return;
            		}
            		
            		if (dateChooser.getDate() == null) {
            		    JOptionPane.showMessageDialog(
            		        null,
            		        "Debe seleccionar una fecha de reserva.",
            		        "Datos incompletos",
            		        JOptionPane.WARNING_MESSAGE
            		    );
            		    return;
            		}

            		LocalDate fechaReserva = dateChooser.getDate()
            		        .toInstant()
            		        .atZone(ZoneId.systemDefault())
            		        .toLocalDate();
            		
            		
            		boolean usaBeneficio = rdbtnBeneficioAnual.isSelected(); //acaaaaa
            		
                try {
                	controlador.reservarConsulta(paciente, consulta, fechaReserva, usaBeneficio); // modificar
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
                } catch (BeneficioSinOrdenesException ex) {
                	JOptionPane.showMessageDialog(null, ex.getMessage(), "Sin beneficio disponible", JOptionPane.ERROR_MESSAGE);
                } catch (UserNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ConsultaNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        
       // JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
        GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
        gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnNewRadioButton.gridx = 2;
        gbc_rdbtnNewRadioButton.gridy = 5;
        getContentPane().add(rdbtnBeneficioAnual, gbc_rdbtnNewRadioButton);
        GridBagConstraints gbc_btnReserva = new GridBagConstraints();
        gbc_btnReserva.insets = new Insets(0, 0, 0, 5);
        gbc_btnReserva.gridx = 2;
        gbc_btnReserva.gridy = 7;
        getContentPane().add(btnReserva, gbc_btnReserva);
   }
}
