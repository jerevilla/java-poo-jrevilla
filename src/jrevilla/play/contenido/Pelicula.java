package jrevilla.play.contenido;

import java.time.LocalDate;

public class Pelicula {
    public String titulo;
    public String descripcion;
    public int duracion;
    public String genero;
    public LocalDate fechaEstreno;
    //public int anioEstreno;
    public double calificacion;
    public boolean disponible;

    public void reproducir() {
        System.out.println("Reproduciendo " + titulo);
    }

    public String obtenerFichaTecnica() {
        return titulo + " (" + fechaEstreno.getYear() + ") \n"+
                "Género: " + genero + "\n" +
                "Calificación: " + calificacion + "/5";
    }

    public void calificar(double calificacion){
        if (calificacion >= 0 && calificacion <=5) {
            this.calificacion = calificacion;
        }
    }

    public boolean esPopular() {
        return calificacion >= 4;
    }
}
