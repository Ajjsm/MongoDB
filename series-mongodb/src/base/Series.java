package base;

import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by Juanaj on 26/02/2016.
 */
public class Series {
    private ObjectId id;
    private String nombre;
    private String genero;
    private int temporadas;
    private Date fechaEstreno;

    public static final String COLECCION = "series";

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public int getTemporadas() {
        return temporadas;
    }
    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }
    public Date getFechaEstreno() {
        return fechaEstreno;
    }
    public void setFechaEstreno(Date fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }
    public static String getCOLECCION() {
        return COLECCION;
    }

    public String toString(){
        return nombre;
    }
}
