package jrevilla.play;

import jrevilla.play.contenido.Pelicula;
import jrevilla.play.plataforma.Usuario;
import jrevilla.play.util.ScannerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static final String NOMBRE_PLATAFORMA = "J PLAY ";
    public static final String VERSION = "1.0.0";

    public static void main(String[] args) {



        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
        String genero = ScannerUtils.capturarTexto("Género del contenido");
        int duracion = ScannerUtils.capturarNumero("Duración del contenido");
        double calificacion = ScannerUtils.capturarDecimal("Calificación del contenido");

        Pelicula pelicula = new Pelicula(nombre, duracion,genero, calificacion);

        System.out.println(pelicula.obtenerFichaTecnica());

        Usuario usuario = new Usuario("Juan", "juang@gmail.com");
        usuario.ver(pelicula);

    }
}
