package base;

import org.bson.types.ObjectId;

/**
 * Created by Juanaj on 26/02/2016.
 */
public class Capitulos {
    private ObjectId id;
    private String titulo;
    private int presupuesto;
    private String descripcion;
    private Personajes personajes;
    private Series series;

    public static final String COLECCION = "capitulos";

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getPresupuesto() {
        return presupuesto;
    }
    public void setPresupuesto(int presupuesto) {
        this.presupuesto = presupuesto;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Personajes getPersonajes() {
        return personajes;
    }
    public void setPersonajes(Personajes personajes) {
        this.personajes = personajes;
    }
    public Series getSeries() {
        return series;
    }
    public void setSeries(Series series) {
        this.series = series;
    }
    public static String getCOLECCION() {
        return COLECCION;
    }

    public String toString(){
        return "Titulo: "+titulo+ " Descripcion: "+descripcion+ " Serie: "+series+ " Personaje: "+personajes+ " Presupuesto: "+ presupuesto;
    }
}
