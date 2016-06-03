package sesion;

import mvc.View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by JuanAJ on 03/06/2016.
 */
public class Iniciar {
    private boolean isConectado;
    private JLabel jlusuario;
    private JTextField tf_nombre;
    private JLabel jlpassword;
    private JTextField tf_password;
    private JButton btaceptar;
    private JFrame frame;
    public View view;

    public Iniciar(){
        getFrame();
        isConectado = false;

        btaceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((tf_nombre.getText().equalsIgnoreCase("user")) && (tf_password.getText().equalsIgnoreCase("1234"))) {
                    isConectado = true;
                    frame.setVisible(false);
                    view = new View();
                    view.bt_iniciar_inicio.setEnabled(false);

                    view.tabbedPane1.setEnabledAt(1, true);
                    view.tabbedPane1.setEnabledAt(2, true);
                    view.tabbedPane1.setEnabledAt(3, true);

                    JOptionPane.showMessageDialog(null, "Ahora te encuentras conectado");


                } else {
                    JOptionPane.showMessageDialog(null, "Comprueba que has escrito bien tus datos");
                    return;
                }
            }

        });


    }

    public JFrame getFrame(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        jlusuario = new JLabel("(user)Usuario: ");
        tf_nombre = new JTextField(15);
        jlpassword = new JLabel("(1234)Password: ");
        tf_password = new JTextField(15);
        btaceptar = new JButton("Aceptar");

        frame.setLayout(new FlowLayout());
        frame.add(jlusuario);
        frame.add(tf_nombre);

        frame.add(jlpassword);
        frame.add(tf_password);

        frame.add(btaceptar);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        return frame;
    }
}
