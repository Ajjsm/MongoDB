package mvc;

import base.Capitulos;
import base.Personajes;
import base.Series;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Juanaj on 26/02/2016.
 */
public class Model {

    MongoClient mongoClient;
    MongoDatabase mongoDatabase;

    public void conectar(){
        mongoClient = new MongoClient();
        mongoDatabase = mongoClient.getDatabase("series");
    }

    public void altaPersonaje(Personajes personajes){
        Document document = new Document()
                .append("nombre", personajes.getNombre())
                .append("personalidad", personajes.getPersonalidad())
                .append("nivel", personajes.getNivel())
                .append("nacimiento", personajes.getNacimiento());
        mongoDatabase.getCollection(Personajes.COLECCION).insertOne(document);

    }
    public void altaCapitulo(Capitulos capitulos){
        Document document = new Document()
                .append("titulo", capitulos.getTitulo())
                .append("presupuesto", capitulos.getPresupuesto())
                .append("descripcion", capitulos.getDescripcion())
                .append("personaje", capitulos.getPersonajes().getNombre())
                .append("serie", capitulos.getSeries().getNombre());
        mongoDatabase.getCollection(Capitulos.COLECCION).insertOne(document);

    }
    public void altaSerie(Series series){
        Document document = new Document()
                .append("nombre", series.getNombre())
                .append("genero", series.getGenero())
                .append("temporadas", series.getTemporadas())
                .append("estreno", series.getFechaEstreno());
        mongoDatabase.getCollection(Series.COLECCION).insertOne(document);

    }

    public void eliminarPersonaje(String nombre){
        mongoDatabase.getCollection(Personajes.COLECCION).deleteOne(new Document("nombre", nombre));
    }
    public void eliminarCapitulo(String titulo){
        mongoDatabase.getCollection(Capitulos.COLECCION).deleteOne(new Document("titulo", titulo));
    }
    public void eliminarSerie(String nombre){
        mongoDatabase.getCollection(Series.COLECCION).deleteOne(new Document("nombre", nombre));
    }

    public void modificarPersonaje(Personajes personajes) {
        mongoDatabase.getCollection(Personajes.COLECCION).replaceOne(new Document("_id", personajes.getId()),
                new Document()
                        .append("nombre", personajes.getNombre())
                        .append("personalidad", personajes.getPersonalidad())
                        .append("nivel", personajes.getNivel())
                        .append("nacimiento", personajes.getNacimiento()));

    }
    public void modificarCapitulo(Capitulos capitulos) {
        mongoDatabase.getCollection(Capitulos.COLECCION).replaceOne(new Document("_id", capitulos.getId()),
                new Document()
                        .append("titulo", capitulos.getTitulo())
                        .append("presupuesto", capitulos.getPresupuesto())
                        .append("descripcion", capitulos.getDescripcion()));

    }
    public void modificarSeries(Series series) {
        mongoDatabase.getCollection(Series.COLECCION).replaceOne(new Document("_id", series.getId()),
                new Document()
                        .append("nombre", series.getNombre())
                        .append("genero", series.getGenero())
                        .append("temporadas", series.getTemporadas())
                        .append("estreno", series.getFechaEstreno()));

    }


    public List<Personajes> getListaPersonajes(FindIterable<Document> findIterable) {
        List<Personajes>personajesList = new ArrayList<>();
        Personajes personajes = null;
        Iterator<Document> iterator = findIterable.iterator();

        while (iterator.hasNext()){
            Document document = iterator.next();
            personajes = new Personajes();
            personajes.setId(document.getObjectId("_id"));
            personajes.setNombre(document.getString("nombre"));
            personajes.setPersonalidad(document.getString("personalidad"));
            personajes.setNivel(document.getInteger("nivel"));
            personajes.setNacimiento(document.getDate("nacimiento"));
            personajesList.add(personajes);
        }
        return personajesList;

    }
    public List<Capitulos> getListaCapitulos(FindIterable<Document> findIterable) {
        List<Capitulos>capitulosArrayList = new ArrayList<>();
        Capitulos capitulos = null;
        Iterator<Document> iterator = findIterable.iterator();

        while (iterator.hasNext()){
            Document document = iterator.next();
            capitulos = new Capitulos();
            capitulos.setId(document.getObjectId("_id"));
            capitulos.setTitulo(document.getString("titulo"));
            capitulos.setDescripcion(document.getString("descripcion"));
            capitulos.setPresupuesto(document.getInteger("presupuesto"));
            capitulosArrayList.add(capitulos);
        }
        return capitulosArrayList;

    }
    public List<Series> getListaSeries(FindIterable<Document> findIterable) {
        List<Series>seriesList = new ArrayList<>();
        Series series = null;
        Iterator<Document> iterator = findIterable.iterator();

        while (iterator.hasNext()){
            Document document = iterator.next();
            series = new Series();
            series.setId(document.getObjectId("_id"));
            series.setNombre(document.getString("nombre"));
            series.setGenero(document.getString("genero"));
            series.setTemporadas(document.getInteger("temporadas"));
            series.setFechaEstreno(document.getDate("estreno"));
            seriesList.add(series);
        }
        return seriesList;

    }

    public List<Personajes> getPersonajes() throws ParseException {
        FindIterable<Document> findIterable = mongoDatabase.getCollection(Personajes.COLECCION).find();
        return getListaPersonajes(findIterable);

    }
    public List<Capitulos> getCapitulos() throws ParseException {
        FindIterable<Document> findIterable = mongoDatabase.getCollection(Capitulos.COLECCION).find();
        return getListaCapitulos(findIterable);

    }
    public List<Series> getSeries() throws ParseException {
        FindIterable<Document> findIterable = mongoDatabase.getCollection(Series.COLECCION).find();
        return getListaSeries(findIterable);

    }


}
