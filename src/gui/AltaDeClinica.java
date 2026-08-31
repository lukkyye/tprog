package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logica.controladores.ControladorClinica;

public class AltaDeClinica extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    
    private JTextField nombreField;
    private JTextField descField;
    private JTextField dirField;
    private JTextField costoTField;
    private JTextField costoCField;
    private JTextField linkField;
    
    private JButton btnAceptar;
    private JButton btnCancelar;

    public AltaDeClinica() {
        super(
            "Alta de Clínica", // Título
            true,               // Redimensionable
            true,               // Cerrable
            true,               // Maximizable
            true                // Minimizable
        );

        setBounds(100, 100, 450, 360);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblNombre = new JLabel("Nombre *:");
        lblNombre.setBounds(30, 25, 150, 20);
        getContentPane().add(lblNombre);

        nombreField = new JTextField();
        nombreField.setBounds(190, 25, 200, 22);
        getContentPane().add(nombreField);

        JLabel lblDesc = new JLabel("Descripción *:");
        lblDesc.setBounds(30, 60, 150, 20);
        getContentPane().add(lblDesc);

        descField = new JTextField();
        descField.setBounds(190, 60, 200, 22);
        getContentPane().add(descField);

        JLabel lblDir = new JLabel("Dirección *:");
        lblDir.setBounds(30, 95, 150, 20);
        getContentPane().add(lblDir);

        dirField = new JTextField();
        dirField.setBounds(190, 95, 200, 22);
        getContentPane().add(dirField);

        JLabel lblCostoT = new JLabel("Costo Ticket Medicamento *:");
        lblCostoT.setBounds(30, 130, 160, 20);
        getContentPane().add(lblCostoT);

        costoTField = new JTextField();
        costoTField.setBounds(190, 130, 200, 22);
        getContentPane().add(costoTField);

        JLabel lblCostoC = new JLabel("Costo Orden Consulta *:");
        lblCostoC.setBounds(30, 165, 160, 20);
        getContentPane().add(lblCostoC);

        costoCField = new JTextField();
        costoCField.setBounds(190, 165, 200, 22);
        getContentPane().add(costoCField);

        JLabel lblLink = new JLabel("Sitio Web (opcional):");
        lblLink.setBounds(30, 200, 150, 20);
        getContentPane().add(lblLink);

        linkField = new JTextField();
        linkField.setBounds(190, 200, 200, 22);
        getContentPane().add(linkField);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(190, 250, 95, 30);
        getContentPane().add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(295, 250, 95, 30);
        getContentPane().add(btnCancelar);

        btnCancelar.addActionListener(e -> dispose());

        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText().trim();
                String desc = descField.getText().trim();
                String dir = dirField.getText().trim();
                String link = linkField.getText().trim();
                String strCostoT = costoTField.getText().trim();
                String strCostoC = costoCField.getText().trim();

                if (nombre.isEmpty() || desc.isEmpty() || dir.isEmpty() || strCostoT.isEmpty() || strCostoC.isEmpty()) {
                    JOptionPane.showMessageDialog(AltaDeClinica.this,
                        "No se pudo realizar la operación:\nVerifique que los campos obligatorios (*) no estén vacíos.",
                        "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                float costoT;
                float costoC;
                try {
                    costoT = Float.parseFloat(strCostoT);
                    costoC = Float.parseFloat(strCostoC);
                    
                    if (costoT < 0 || costoC < 0) {
                        JOptionPane.showMessageDialog(AltaDeClinica.this,
                            "Los costos deben ser valores positivos.",
                            "Error de Formato", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AltaDeClinica.this,
                        "Los campos de costo deben ser números válidos.",
                        "Error de Formato", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ControladorClinica cc = new ControladorClinica();
                boolean exito = cc.ingresarClinica(nombre, desc, dir, costoT, costoC, link);

                if (exito) {
                    JOptionPane.showMessageDialog(AltaDeClinica.this, 
                        "Clínica ingresada con éxito.", 
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AltaDeClinica.this,
                        "Ya existe una clínica registrada con el nombre: '" + nombre + "'.\nPor favor, ingrese un nombre diferente.",
                        "Nombre Duplicado", JOptionPane.ERROR_MESSAGE);
                    nombreField.requestFocus();
                }
            }
        });
    }
}