package mvc;

import com.toedter.calendar.JCalendar;
import sesion.Iniciar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Juanaj on 26/02/2016.
 */
public class View {
    public JTabbedPane tabbedPane1;

    public JTextField tfNombre_personaje;
    public JTextField tfPersonalidad_personaje;
    public JTextField tfNivel_personaje;
    public JButton btNuevo_personaje;
    public JButton btAlta_personaje;
    public JButton btEliminar_personaje;
    public JButton btModificiacion_personaje;
    public JList listaPersonaje;
    public JButton btEliminar_capitulo;
    public JComboBox cbPersonaje;
    public JTextField tfNombre_serie;
    public JTextField tfGenero_serie;
    public JTextField tfTemporadas_series;
    public JCalendar jcEstreno;
    public JButton btNuevo_serie;
    public JButton btAlta_serie;
    public JButton btModificar_serie;
    public JButton btEliminar_serie;

    public JTextField tfTitulo_capitulos;
    public JTextField tfPresupuesto_capitulo;
    public JTextField tfDescripcion_capitulos;

    public JButton btNuevo_capitulo;
    public JButton btAlta_capitulo;
    public JButton btModificar_capitulo;
    public JCalendar jcNacimiento;

    public JList listaCapitulos;
    public JList listaSerie;
    public JComboBox cbSeries;
    public JButton bt_buscarpersonaje;
    public JTextField tf_buscarpersonaje;
    public JButton bt_buscarcapitulo;
    public JTextField tf_buscarcapitulo;
    public JButton bt_vertodo;
    public JButton bt_vertodoscapitulo;
    public JButton bt_salir_inicio;
    public JButton bt_iniciar_inicio;
    JFrame jFrame;

    public JPanel panel1;
    private JPanel jp_inicio;
    private JPanel jp_capitulos;
    private JPanel jp_series;
    private JPanel jp_personajes;

    public DefaultListModel dlm_serie;
    public DefaultListModel dlm_capitulo;
    public DefaultListModel dlm_personaje;

    public View(){
        getFrame();
        initModels();
        enableTabs();

        bt_iniciar_inicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Iniciar iniciar = new Iniciar();
                jFrame.setVisible(false);

            }
        });
        bt_salir_inicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private JFrame getFrame() {
        jFrame= new JFrame();
        jFrame.setContentPane(panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("ALMACEN SERIES");
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        return jFrame;
    }
    public void initModels(){
        dlm_personaje = new DefaultListModel();
        listaPersonaje.setModel(dlm_personaje);

        dlm_capitulo = new DefaultListModel();
        listaCapitulos.setModel(dlm_capitulo);

        dlm_serie = new DefaultListModel();
        listaSerie.setModel(dlm_serie);

    }

    private void enableTabs(){
        tabbedPane1.setEnabledAt(1, false);
        tabbedPane1.setEnabledAt(2, false);
        tabbedPane1.setEnabledAt(3, false);

    }


}
