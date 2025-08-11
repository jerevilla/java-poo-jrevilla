package jrevilla.play;

import jrevilla.play.contenido.Pelicula;
import jrevilla.play.plataforma.Plataforma;
import jrevilla.play.plataforma.Usuario;
import jrevilla.play.util.ScannerUtils;


public class Main {

    public static final String NOMBRE_PLATAFORMA = "J PLAY ";
    public static final String VERSION = "1.0.0";

    public static void main(String[] args) {


        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
        String genero = ScannerUtils.capturarTexto("Género del contenido");
        int duracion = ScannerUtils.capturarNumero("Duración del contenido");
        double calificacion = ScannerUtils.capturarDecimal("Calificación del contenido");

        Pelicula pelicula = new Pelicula(nombre, duracion,genero, calificacion);
        Pelicula pelicula1 = new Pelicula("F1 the Movie", 220, "Acción");

        plataforma.agregar(pelicula);
        plataforma.agregar(pelicula1);
        System.out.println("Número de elementos de la plataforma " + plataforma.getContenido().size());

        plataforma.eliminar(pelicula1);
//        System.out.println(pelicula.obtenerFichaTecnica());
        plataforma.mostrarTitulos();

        Usuario usuario = new Usuario("Juan", "juang@gmail.com");
        usuario.ver(pelicula);

    }
}
