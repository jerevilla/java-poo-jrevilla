package jrevilla.play;

import jrevilla.play.contenido.Pelicula;
import jrevilla.play.plataforma.Plataforma;
import jrevilla.play.plataforma.Usuario;
import jrevilla.play.util.ScannerUtils;


public class Main {

    public static final String NOMBRE_PLATAFORMA = "J PLAY ";
    public static final String VERSION = "1.0.0";

    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int ELIMINAR = 4;
    public static final int SALIR = 5;



    public static void main(String[] args) {


        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);


        while(true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar titulo
                    4. Eliminar
                    5. Salir
                    """);
            System.out.println("Opción Elegida " + opcionElegida);

            switch (opcionElegida) {
                case AGREGAR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    String genero = ScannerUtils.capturarTexto("Género del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duración del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificación del contenido");

                    plataforma.agregar( new Pelicula(nombre, duracion,genero, calificacion));
                }
                case MOSTRAR_TODO -> plataforma.mostrarTitulos();
                case BUSCAR_POR_TITULO -> {}
                case ELIMINAR -> {}
                case SALIR -> System.exit(0);
            }


        }

       /*
        Pelicula pelicula1 = new Pelicula("F1 the Movie", 220, "Acción");

        plataforma.agregar(pelicula);
        plataforma.agregar(pelicula1);
        System.out.println("Número de elementos de la plataforma " + plataforma.getContenido().size());

        plataforma.eliminar(pelicula1);
//        System.out.println(pelicula.obtenerFichaTecnica());
        plataforma.mostrarTitulos();

        Usuario usuario = new Usuario("Juan", "juang@gmail.com");
        usuario.ver(pelicula);*/

    }
}
