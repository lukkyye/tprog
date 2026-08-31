package gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

public class ConsultaDeUsuario extends JInternalFrame {

    private JTextField txtIngreseUsuario;

    public ConsultaDeUsuario() {

        super(
            "Consulta de Usuario", // título
            true,                  // se puede redimensionar
            true,                  // se puede cerrar
            true,                  // se puede maximizar
            true                   // se puede minimizar
        );

        setSize(600, 500);

        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWidths = new int[]{80, 358, 0, 0, 0};

        gridBagLayout.rowHeights = new int[]{
            0, 39, 284, 20, 0, 0, 0, 0, 0, 0
        };

        gridBagLayout.columnWeights = new double[]{
            0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE
        };

        gridBagLayout.rowWeights = new double[]{
            0.0, 0.0, 1.0, 0.0, 0.0,
            0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE
        };

        getContentPane().setLayout(gridBagLayout);



        JLabel lblUsuarios = new JLabel("Usuarios en el sistema:");

        GridBagConstraints gbc_lblUsuarios = new GridBagConstraints();

        gbc_lblUsuarios.anchor = GridBagConstraints.WEST;
        gbc_lblUsuarios.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsuarios.gridx = 1;
        gbc_lblUsuarios.gridy = 1;

        getContentPane().add(lblUsuarios, gbc_lblUsuarios);



        JScrollPane scrollPane = new JScrollPane();

        GridBagConstraints gbc_scrollPane = new GridBagConstraints();

        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 1;
        gbc_scrollPane.gridy = 2;

        getContentPane().add(scrollPane, gbc_scrollPane);


        JList<String> listaUsuarios = new JList<String>();

        listaUsuarios.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        listaUsuarios.setModel(new AbstractListModel<String>() {

            String[] values = new String[] {
                "juapPerez23",
                "lulitapanizzi",
                "mickeyMouse1890"
            };

            public int getSize() {
                return values.length;
            }

            public String getElementAt(int index) {
                return values[index];
            }
        });

        scrollPane.setViewportView(listaUsuarios);



        JLabel lblNickname = new JLabel("Ingrese Nickname:");

        GridBagConstraints gbc_lblNickname = new GridBagConstraints();

        gbc_lblNickname.anchor = GridBagConstraints.WEST;
        gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
        gbc_lblNickname.gridx = 1;
        gbc_lblNickname.gridy = 4;

        getContentPane().add(lblNickname, gbc_lblNickname);



        txtIngreseUsuario = new JTextField();

        GridBagConstraints gbc_txtIngreseUsuario =
            new GridBagConstraints();

        gbc_txtIngreseUsuario.fill =
            GridBagConstraints.HORIZONTAL;

        gbc_txtIngreseUsuario.insets =
            new Insets(0, 0, 5, 5);

        gbc_txtIngreseUsuario.gridx = 1;
        gbc_txtIngreseUsuario.gridy = 5;

        getContentPane().add(
            txtIngreseUsuario,
            gbc_txtIngreseUsuario
        );

        txtIngreseUsuario.setColumns(10);


        JButton btnSeleccionar =
            new JButton("Seleccionar");

        GridBagConstraints gbc_btnSeleccionar =
            new GridBagConstraints();

        gbc_btnSeleccionar.insets =
            new Insets(0, 0, 5, 5);

        gbc_btnSeleccionar.gridx = 2;
        gbc_btnSeleccionar.gridy = 5;

        getContentPane().add(
            btnSeleccionar,
            gbc_btnSeleccionar
        );


        listaUsuarios.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                String nicknameSeleccionado =
                    listaUsuarios.getSelectedValue();

                if (nicknameSeleccionado != null) {

                    txtIngreseUsuario.setText(
                        nicknameSeleccionado
                    );
                }
            }
        });

    }
}