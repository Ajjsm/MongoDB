package mvc;

import base.Capitulos;
import base.Personajes;
import base.Series;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juanaj on 26/02/2016.
 */
public class Control {

    private Series series;
    private Series serieSeleccionada;
    private Capitulos capitulos;
    private Capitulos capitloSeleccionado;
    private Personajes personajes;
    private Personajes personajeSeleccionado;

    private Model model;
    private View view;

    private boolean nuevoCapitulo;
    private boolean nuevaSerie;
    private boolean nuevoPersonaje;

    public Control(Model model, View view){
        this.model = model;
        this.view = view;

        view.btAlta_personaje.setEnabled(false);
        view.btAlta_capitulo.setEnabled(false);
        view.btAlta_serie.setEnabled(false);

        model.conectar();

        rellenarComboPersonajes();
        rellenarComboSeries();
        listarPersonajes();
        listarCapitulos();
        listarSeries();

        estadoModel();

        view.btNuevo_personaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoPersonaje = true;
                view.btAlta_personaje.setEnabled(true);
                view.tfNombre_personaje.setText("");
                view.tfPersonalidad_personaje.setText("");
                view.tfNivel_personaje.setText("");
            }
        });
        view.btNuevo_capitulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoCapitulo = true;
                view.btAlta_capitulo.setEnabled(true);
                view.tfTitulo_capitulos.setText("");
                view.tfPresupuesto_capitulo.setText("");
                view.tfDescripcion_capitulos.setText("");
            }
        });
        view.btNuevo_serie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevaSerie = true;
                view.btAlta_serie.setEnabled(true);
                view.tfNombre_serie.setText("");
                view.tfGenero_serie.setText("");
                view.tfTemporadas_series.setText("");
            }
        });

        view.btModificiacion_personaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoPersonaje = false;
                view.btAlta_personaje.setEnabled(true);
            }
        });
        view.btModificar_capitulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoCapitulo = false;
                view.btAlta_capitulo.setEnabled(true);
            }
        });
        view.btModificar_serie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevaSerie = false;
                view.btAlta_serie.setEnabled(true);
            }
        });

        view.btAlta_personaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nuevoPersonaje) {
                    personajes = new Personajes();
                } else {
                    personajes = personajeSeleccionado;
                }

                personajes.setNombre(view.tfNombre_personaje.getText());
                personajes.setPersonalidad(view.tfPersonalidad_personaje.getText());
                personajes.setNivel(Integer.parseInt(view.tfNivel_personaje.getText()));
                personajes.setNacimiento(view.jcNacimiento.getDate());

                if (nuevoPersonaje) {
                    model.altaPersonaje(personajes);
                } else {
                    model.modificarPersonaje(personajes);
                }

                view.btAlta_personaje.setEnabled(false);
                listarPersonajes();
                rellenarComboPersonajes();
            }
        });
        view.btAlta_capitulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nuevoCapitulo) {
                    capitulos = new Capitulos();
                } else {
                    capitulos = capitloSeleccionado;
                }

                capitulos.setTitulo(view.tfTitulo_capitulos.getText());
                capitulos.setPresupuesto(Integer.parseInt(view.tfPresupuesto_capitulo.getText()));
                capitulos.setDescripcion(view.tfDescripcion_capitulos.getText());
                capitulos.setPersonajes((Personajes) view.cbPersonaje.getSelectedItem());
                capitulos.setSeries((Series) view.cbSeries.getSelectedItem());

                if (nuevoCapitulo) {
                    model.altaCapitulo(capitulos);
                } else {
                    model.modificarCapitulo(capitulos);
                }

                view.btAlta_capitulo.setEnabled(false);
                listarCapitulos();
            }
        });
        view.btAlta_serie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nuevaSerie) {
                    series = new Series();
                } else {
                    series = serieSeleccionada;
                }

                series.setNombre(view.tfNombre_serie.getText());
                series.setGenero(view.tfGenero_serie.getText());
                series.setTemporadas(Integer.parseInt(view.tfTemporadas_series.getText()));
                series.setFechaEstreno(view.jcEstreno.getDate());

                if (nuevaSerie) {
                    model.altaSerie(series);
                } else {
                    model.modificarSeries(series);
                }

                view.btAlta_serie.setEnabled(false);
                listarSeries();
                rellenarComboSeries();
            }
        });

        view.btEliminar_personaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                personajes = (Personajes) view.listaPersonaje.getSelectedValue();
                JDialog.setDefaultLookAndFeelDecorated(true);
                int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro de querer eliminar?", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.NO_OPTION){
                    System.out.println("Has elejido NO");
                    return;
                } else if (respuesta == JOptionPane.YES_OPTION){
                    model.eliminarPersonaje(personajes.getNombre());
                    listarPersonajes();
                    rellenarComboPersonajes();
                    System.out.println("Has elejido SI");
                } else if (respuesta == JOptionPane.CLOSED_OPTION){
                    System.out.println("Has cerrado la ventana de confirmacion");
                }
            }
        });
        view.btEliminar_capitulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capitulos = (Capitulos) view.listaCapitulos.getSelectedValue();
                JDialog.setDefaultLookAndFeelDecorated(true);
                int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro de querer eliminar?", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.NO_OPTION){
                    System.out.println("Has elejido NO");
                    return;
                } else if (respuesta == JOptionPane.YES_OPTION){
                    capitulos = (Capitulos) view.listaCapitulos.getSelectedValue();
                    model.eliminarCapitulo(capitulos.getTitulo());
                    listarCapitulos();
                } else if (respuesta == JOptionPane.CLOSED_OPTION){
                    System.out.println("Has cerrado la ventana de confirmacion");
                }
            }
        });
        view.btEliminar_serie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                series = (Series) view.listaSerie.getSelectedValue();
                JDialog.setDefaultLookAndFeelDecorated(true);
                int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro de querer eliminar?", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.NO_OPTION){
                    System.out.println("Has elejido NO");
                    return;
                } else if (respuesta == JOptionPane.YES_OPTION) {

                    model.eliminarSerie(series.getNombre());
                    listarSeries();
                    rellenarComboPersonajes();
                } else if (respuesta == JOptionPane.CLOSED_OPTION){
                    System.out.println("Has cerrado la ventana de confirmacion");
                }
            }
        });

        view.listaPersonaje.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                personajeSeleccionado = (Personajes) view.listaPersonaje.getSelectedValue();
                if (personajeSeleccionado!=null){
                    cargarPersonaes();
                }
            }
        });
        view.listaCapitulos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                capitloSeleccionado = (Capitulos) view.listaCapitulos.getSelectedValue();
                if (capitloSeleccionado!=null) {
                    cargarCapitulos();
                }
            }
        });
        view.listaSerie.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                serieSeleccionada = (Series) view.listaSerie.getSelectedValue();
                if (serieSeleccionada != null) {
                    cargarSeries();
                }
            }
        });

        view.bt_buscarcapitulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCapitulo(view.tf_buscarcapitulo.getText());
                if (view.tf_buscarcapitulo.getText().equals("")) {
                    listarCapitulos();
                }
            }
        });
        view.bt_buscarpersonaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarpersonaje(view.tf_buscarpersonaje.getText());
            }
        });

        view.bt_vertodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPersonajes();
            }
        });
        view.bt_vertodoscapitulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCapitulos();
            }
        });
    }

    private void listarPersonajes(){
        List<Personajes> personajesArrayList = new ArrayList<>();
        try {
            personajesArrayList = model.getPersonajes();
            view.dlm_personaje.removeAllElements();
            for (Personajes personajes: personajesArrayList){
                view.dlm_personaje.addElement(personajes);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void listarCapitulos(){
        List<Capitulos> capitulosArrayList = new ArrayList<>();
        try {
            capitulosArrayList = model.getCapitulos();
            view.dlm_capitulo.removeAllElements();
            for (Capitulos capitulos: capitulosArrayList){
                view.dlm_capitulo.addElement(capitulos);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void listarSeries(){
        List<Series> seriesList = new ArrayList<>();
        try {
            seriesList = model.getSeries();
            view.dlm_serie.removeAllElements();
            for (Series series: seriesList){
                view.dlm_serie.addElement(series);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void cargarPersonaes(){
        view.tfNombre_personaje.setText(personajeSeleccionado.getNombre());
        view.tfPersonalidad_personaje.setText(personajeSeleccionado.getPersonalidad());
        view.tfNivel_personaje.setText(String.valueOf(personajeSeleccionado.getNivel()));
    }
    private void cargarSeries(){
        view.tfNombre_serie.setText(serieSeleccionada.getNombre());
        view.tfGenero_serie.setText(serieSeleccionada.getGenero());
        view.tfTemporadas_series.setText(String.valueOf(serieSeleccionada.getTemporadas()));
        view.jcEstreno.setDate(serieSeleccionada.getFechaEstreno());
    }
    private void cargarCapitulos(){
        view.tfTitulo_capitulos.setText(capitloSeleccionado.getTitulo());
        view.tfPresupuesto_capitulo.setText(String.valueOf(capitloSeleccionado.getPresupuesto()));
        view.tfDescripcion_capitulos.setText(String.valueOf(capitloSeleccionado.getDescripcion()));
        view.cbPersonaje.setSelectedItem(capitloSeleccionado.getPersonajes());

    }

    private void rellenarComboPersonajes(){
        List<Personajes> personajesList = new ArrayList<>();
        try {
            personajesList = model.getPersonajes();
            view.cbPersonaje.removeAllItems();
            for (Personajes personajes: personajesList){
                view.cbPersonaje.addItem(personajes);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void rellenarComboSeries(){
        List<Series> seriesList = new ArrayList<>();
        try {
            seriesList = model.getSeries();
            view.cbSeries.removeAllItems();
            for (Series series: seriesList){
                view.cbSeries.addItem(series);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private  void buscarpersonaje(String dato)  {
        List<Personajes> busquedaPersonaje = null;
        try {
            busquedaPersonaje = model.getPersonajes();

        view.dlm_personaje.removeAllElements();
        for(Personajes personajes : busquedaPersonaje){
            String nivel = String.valueOf(personajes.getNivel());

            if (personajes.getNombre().toString().equalsIgnoreCase(dato)) {
                view.dlm_personaje.addElement(personajes);
            }
            if (personajes.getPersonalidad().equalsIgnoreCase(dato)) {
                view.dlm_personaje.addElement(personajes);
            }
            if (nivel.equalsIgnoreCase(dato)) {
                view.dlm_personaje.addElement(personajes);
            }
        }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private  void buscarCapitulo(String dato)  {
        List<Capitulos> busquedaCapitulo = null;
        try {
            busquedaCapitulo = model.getCapitulos();

            view.dlm_capitulo.removeAllElements();
            for(Capitulos capitulos : busquedaCapitulo){
                String presupuesto = String.valueOf(capitulos.getPresupuesto());

                if (capitulos.getTitulo().toString().equalsIgnoreCase(dato)) {
                    view.dlm_capitulo.addElement(capitulos);
                }

                if (capitulos.getDescripcion().toString().equalsIgnoreCase(dato)) {
                    view.dlm_capitulo.addElement(capitulos);
                }

                if (presupuesto.equalsIgnoreCase(dato)) {
                    view.dlm_capitulo.addElement(capitulos);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void estadoModel(){
        if (view.listaCapitulos.getModel().getSize() == 0){
            System.out.println("El model de capitulos esta vacio");
            view.btEliminar_capitulo.setEnabled(false);
        }

        if (view.listaPersonaje.getModel().getSize() == 0){
            System.out.println("El model de personajes esta vacio");
            view.btEliminar_personaje.setEnabled(false);
        }

        if (view.listaSerie.getModel().getSize() == 0){
            System.out.println("El model de series esta vacio");
            view.btEliminar_serie.setEnabled(false);
        }
    }
}
