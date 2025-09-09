package jrevilla.play.contenido;

import java.time.LocalDate;

public abstract class Contenido {
    private String titulo;
    private String descripcion;
    private int duracion;
    private Genero genero;
    private LocalDate fechaEstreno;
    //public int anioEstreno;
    private double calificacion;
    private boolean disponible;

    public Contenido(String titulo, int duracion, Genero genero, double calificacion) {
        this(titulo, duracion, genero);
        this.calificar(calificacion);
    }

    public Contenido(String titulo, int duracion, Genero genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = LocalDate.now();
        this.disponible = true;
    }

    public abstract void reproducir();

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

    public boolean isDisponible() {
        return disponible;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public Genero getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }
}
