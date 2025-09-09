package jrevilla.play;

import jrevilla.play.contenido.*;
import jrevilla.play.excepcion.PeliculaExistenteException;
import jrevilla.play.plataforma.Plataforma;
import jrevilla.play.util.FileUtils;
import jrevilla.play.util.ScannerUtils;

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
    public static final int BUSCAR_POR_TIPO = 7;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;



    public static void main(String[] args) {


        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        cargarPeliculas(plataforma);

        System.out.println("Más de " + plataforma.getDuracionTotal() + " minutos de contenido");
        plataforma.getContenidoPromocionable().forEach(promocionable -> System.out.println(promocionable.promocionar()));

        while(true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    ============MENU==================
                        1. Agregar contenido          
                        2. Mostrar todo               
                        3. Buscar titulo 
                        4. Buscar por Genero  
                        5. Ver populares
                        6. Reproducir       
                        7. Buscar Por Tipo de Contenido
                        8. Eliminar                   
                        9. Salir                      
                    ===================================
                    """);

            switch (opcionElegida) {
                case AGREGAR -> {
                    int tipoContenido = ScannerUtils.capturarNumero("¿Qué tipo de contenido quieres agregar? \n 1. Pelicula\n 2. Documental");
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duración del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificación del contenido");

                    try {
                        if (tipoContenido == 1 ){
                            plataforma.agregar( new Pelicula(nombre, duracion,genero, calificacion));
                        } else {
                            String narrador = ScannerUtils.capturarTexto("Narrador del Documental ");
                            plataforma.agregar( new Documental(nombre, duracion,genero, calificacion, narrador));
                        }

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
                    Contenido contenido = plataforma.buscarPorTitulo(nombreBuscado);
                    if (contenido != null ) {
                        System.out.println(contenido.obtenerFichaTecnica());
                    } else {
                        System.out.println(nombreBuscado + " no existe dentro de " + plataforma.getNombre());
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoBuscado = ScannerUtils.capturarGenero("Género del contenido a buscar");

                    List<Contenido> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    System.out.println(contenidoPorGenero.size() + " encontrados para el género " + generoBuscado);
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica()));
                }
                case VER_POPULARES -> {

                    int cantidad = ScannerUtils.capturarNumero("Cantidad de resultados a mostrar ");

                    List<Contenido> contenidoPopulares = plataforma.getPopulares(cantidad);
                    contenidoPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica()));

                }
                case REPRODUCIR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido a reproducir ");
                    Contenido contenido = plataforma.buscarPorTitulo(nombre);

                    if (contenido != null) {
                        plataforma.reproducir(contenido);
                    } else {
                        System.out.println(nombre + " no existe");
                    }

                }
                case BUSCAR_POR_TIPO -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero("¿Qué tipo de contenido quieres agregar? \n 1. Pelicula \n 2. Documental");

                    if (tipoDeContenido == 1) {
                        List<Pelicula> peliculas = plataforma.getPeliculas();
                        peliculas.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() + "\n") );
                    } else {
                        List<Documental> documentales = plataforma.getDocumentales();
                        documentales.forEach(documental -> System.out.println(documental.obtenerFichaTecnica() + "\n"));
                    }

                }
                /*case MAYOR_DURACION -> {
                    Optional<Contenido> mayorDuracion = plataforma.getMayorDuracion();
                    System.out.println(mayorDuracion);
                } */
                case ELIMINAR -> {
                    String nombreAEliminar = ScannerUtils.capturarTexto("Nombre de la contenido a eliminar: ");
                    Contenido contenido = plataforma.buscarPorTitulo(nombreAEliminar);
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
