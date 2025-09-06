package jrevilla.play.plataforma;

import jrevilla.play.contenido.Contenido;
import jrevilla.play.contenido.Genero;
import jrevilla.play.contenido.ResumenContenido;
import jrevilla.play.excepcion.PeliculaExistenteException;
import jrevilla.play.util.FileUtils;

import java.util.*;

public class Plataforma {
    private String nombre;
    private List<Contenido> contenido; //Tipo de relación: agregación
    private Map<Contenido, Integer> visualizaciones;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();
    }

    public void agregar(Contenido elemento) {
        Contenido contenido = this.buscarPorTitulo(elemento.getTitulo());
        if (contenido != null) {
            throw new PeliculaExistenteException(elemento.getTitulo());
        }
        FileUtils.escribirContenido(elemento);
        this.contenido.add(elemento);
    }

    public void reproducir(Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproducido " + conteoActual + " veces");

        this.contarVisualizacion(contenido);
        contenido.reproducir();
        //visualizaciones.put(contenido, conteoActual + 1);
    }

    private void contarVisualizacion(Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActual + 1);

    }
    public List<String> getTitulos() {

        return contenido.stream()
                .map(Contenido::getTitulo)
                .toList();
    }

    public List<ResumenContenido> getResumenes() {
        return contenido.stream()
                .map(c -> new ResumenContenido(c.getTitulo(), c.getDuracion(), c.getGenero()))
                .toList();
    }

    public void eliminar(Contenido elemento){
        this.contenido.remove(elemento);
    }

    public Contenido buscarPorTitulo(String titulo) {
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public List<Contenido> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public List<Contenido> getPopulares(int cantidad) {

        return contenido.stream()
                .sorted(Comparator.comparingDouble(Contenido::getCalificacion).reversed())
                .filter(pelicula -> pelicula.getCalificacion() >= 4)
                .limit(cantidad)
                .toList();
    }

    public int getDuracionTotal() {
        return contenido.stream()
                .mapToInt(Contenido::getDuracion)
                .sum();
    }

    public Optional<Contenido> getMayorDuracion() {

        Optional<Contenido> max = contenido.stream()
                .max(Comparator.comparingInt(Contenido::getDuracion));
        return max;

    }

    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }
}
