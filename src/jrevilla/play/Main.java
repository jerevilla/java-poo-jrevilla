package jrevilla.play;

import jrevilla.play.contenido.Pelicula;
import jrevilla.play.plataforma.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("¡J PLAY!");

        Pelicula pelicula = new Pelicula();
        pelicula.titulo = "El señor de los anillos";
        pelicula.fechaEstreno = LocalDate.of(2018,10,15);
        pelicula.genero = "Fantasia";
        pelicula.calificar(4.7);

        Usuario usuario = new Usuario();
        usuario.nombre = "Juan";
        usuario.fechaRegistro = LocalDateTime.of(2025, 12, 24, 17, 15,14);

        System.out.println(usuario.fechaRegistro);

        //usuario.ver(pelicula);
        System.out.println(pelicula.obtenerFichaTecnica());

        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Cúal es tu nombre? ");
        String nombre = scanner.nextLine();

        System.out.println("Hola, " + nombre + ", esto es JPlay!");

        System.out.println(nombre + ", ¿Cuántos años tienes?");
        int edad = scanner.nextInt();

        System.out.println(nombre + " puedes ver contenido +" + edad);*/
    }
}
