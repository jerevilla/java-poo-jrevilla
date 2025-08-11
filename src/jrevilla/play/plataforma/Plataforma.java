package jrevilla.play.plataforma;

import jrevilla.play.contenido.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido; //Tipo de relación: agregación

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
    }

    public void agregar(Pelicula elemento) {
        this.contenido.add(elemento);
    }

    public void mostrarTitulos() {

        for (Pelicula pelicula : contenido) {
            System.out.println(pelicula.getTitulo());
        }

//        for (int i = 0; i < contenido.size(); i++) {
//            System.out.println(contenido.get(i).getTitulo());
//        }
    }

    public void eliminar(Pelicula elemento){
        this.contenido.remove(elemento);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }
}
