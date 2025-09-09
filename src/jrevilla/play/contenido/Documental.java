package jrevilla.play.contenido;

public class Documental extends Contenido implements Promocionable {

    String narrador;

    public Documental(String titulo, int duracion, Genero genero) {
        super(titulo, duracion, genero);
    }

    public Documental(String titulo, int duracion, Genero genero, double calificacion, String narrador) {
        super(titulo, duracion, genero, calificacion);
        this.narrador = narrador;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo el documento " + getTitulo() + " narrado por " + getNarrador());
    }

    @Override
    public String promocionar() {
        return "Descubre el documental " + getTitulo() + " Narrado por " + getNarrador() + ". ¡Ahora en jPlay!";
    }

    public String getNarrador() {
        return narrador;
    }


}
