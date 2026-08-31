package gui;

import javax.swing.JInternalFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AltaDeDiaDeConsulta extends JInternalFrame {

    public AltaDeDiaDeConsulta() {

        super(
            "Alta de Dia de Consulta", // título
            true,              // se puede redimensionar
            true,              // se puede cerrar
            true,              // se puede maximizar
            true               // se puede minimizar
        );

        setSize(600, 500);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 77, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        
        JLabel lblNewLabel = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 3;
        gbc_lblNewLabel.gridy = 1;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_1.gridx = 3;
        gbc_lblNewLabel_1.gridy = 2;
        getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_1_1.gridx = 3;
        gbc_lblNewLabel_1_1.gridy = 3;
        getContentPane().add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_1_1_1.gridx = 3;
        gbc_lblNewLabel_1_1_1.gridy = 4;
        getContentPane().add(lblNewLabel_1_1_1, gbc_lblNewLabel_1_1_1);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel_1_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_1_1.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel_1_1_1_1.gridx = 3;
        gbc_lblNewLabel_1_1_1_1.gridy = 5;
        getContentPane().add(lblNewLabel_1_1_1_1, gbc_lblNewLabel_1_1_1_1);
        
        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel_1_1_1_1_1 = new GridBagConstraints();
        gbc_lblNewLabel_1_1_1_1_1.gridx = 3;
        gbc_lblNewLabel_1_1_1_1_1.gridy = 6;
        getContentPane().add(lblNewLabel_1_1_1_1_1, gbc_lblNewLabel_1_1_1_1_1);
   }
}

