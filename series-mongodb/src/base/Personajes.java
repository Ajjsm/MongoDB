package base;

import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by Juanaj on 26/02/2016.
 */
public class Personajes {
    private ObjectId id;
    private String nombre;
    private String personalidad;
    private int nivel;
    private Date nacimiento;
    public static final String COLECCION = "personajes";

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
    public String getPersonalidad() {
        return personalidad;
    }
    public void setPersonalidad(String personalidad) {
        this.personalidad = personalidad;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public Date getNacimiento() {
        return nacimiento;
    }
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
    public static String getCOLECCION() {
        return COLECCION;
    }

    public String toString(){
        return "Nombre: "+nombre+ " Personalidad: "+personalidad+ " Nivel: "+nivel;
    }
}
