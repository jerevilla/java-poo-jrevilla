package jrevilla.play;

import jrevilla.play.contenido.Genero;
import jrevilla.play.contenido.Pelicula;
import jrevilla.play.contenido.ResumenContenido;
import jrevilla.play.excepcion.PeliculaExistenteException;
import jrevilla.play.plataforma.Plataforma;
import jrevilla.play.plataforma.Usuario;
import jrevilla.play.util.FileUtils;
import jrevilla.play.util.ScannerUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class Main {

    public static final String NOMBRE_PLATAFORMA = "J PLAY ";
    public static final String VERSION = "1.0.0";

    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int REPRODUCIR = 6;
    public static final int MAYOR_DURACION = 7;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;



    public static void main(String[] args) {


        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        cargarPeliculas(plataforma);

        System.out.println("Más de " + plataforma.getDuracionTotal() + " minutos de contenido");

        while(true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    ============MENU==================
                        1. Agregar contenido          
                        2. Mostrar todo               
                        3. Buscar titulo 
                        4. Buscar por Genero  
                        5. Ver populares
                        6. Reproducir       
                        7. Pelicula con mayor duración
                        8. Eliminar                   
                        9. Salir                      
                    ===================================
                    """);

            switch (opcionElegida) {
                case AGREGAR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duración del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificación del contenido");

                    try {
                        plataforma.agregar( new Pelicula(nombre, duracion,genero, calificacion));
                    } catch (PeliculaExistenteException e) {
                        System.out.println(e.getMessage());
                    }


                }
                case MOSTRAR_TODO -> {
                    List<ResumenContenido> contenidoResumidos = plataforma.getResumenes();
                    contenidoResumidos.forEach(resumen -> System.out.println(resumen.toString()));
                }
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturarTexto("Nombre del contenido a buscar: ");
                    Pelicula pelicula = plataforma.buscarPorTitulo(nombreBuscado);
                    if (pelicula != null ) {
                        System.out.println(pelicula.obtenerFichaTecnica());
                    } else {
                        System.out.println(nombreBuscado + " no existe dentro de " + plataforma.getNombre());
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoBuscado = ScannerUtils.capturarGenero("Género del contenido a buscar");

                    List<Pelicula> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    System.out.println(contenidoPorGenero.size() + " encontrados para el género " + generoBuscado);
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica()));
                }
                case VER_POPULARES -> {

                    int cantidad = ScannerUtils.capturarNumero("Cantidad de resultados a mostrar ");

                    List<Pelicula> contenidoPopulares = plataforma.getPopulares(cantidad);
                    contenidoPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica()));

                }
                case REPRODUCIR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido a reproducir ");
                    Pelicula contenido = plataforma.buscarPorTitulo(nombre);

                    if (contenido != null) {
                        plataforma.reproducir(contenido);
                    } else {
                        System.out.println(nombre + " no existe");
                    }

                }
                case MAYOR_DURACION -> {
                    Optional<Pelicula> mayorDuracion = plataforma.getMayorDuracion();
                    System.out.println(mayorDuracion);
                }
                case ELIMINAR -> {
                    String nombreAEliminar = ScannerUtils.capturarTexto("Nombre de la pelicula a eliminar: ");
                    Pelicula contenido = plataforma.buscarPorTitulo(nombreAEliminar);
                    if (contenido != null) {
                        plataforma.eliminar(contenido);
                        System.out.println(nombreAEliminar + " eliminado! ");
                    } else {
                        System.out.println(nombreAEliminar + " no existe en " + plataforma.getNombre());
                    }

                }
                case SALIR -> System.exit(0);
            }


        }
    }
    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.getContenido().addAll(FileUtils.leerContenido());
    }
}
