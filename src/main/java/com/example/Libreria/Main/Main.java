package com.example.Libreria.Main;

import com.example.Libreria.DTO.LibroDTO;
import com.example.Libreria.DTO.RespuestaJsonDTO;
import com.example.Libreria.Modelo.Libro;
import com.example.Libreria.Modelo.Persona;
import com.example.Libreria.Repository.RepositoryAutor;
import com.example.Libreria.Repository.RepositoryLibro;
import com.example.Libreria.Service.ConsultaAPI;
import com.example.Libreria.Service.ConversorJSON;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private final static Scanner sc = new Scanner(System.in);

    private final String URL_BASE = "http://gutendex.com/books/?";

    private RepositoryLibro repositoryLibro;

    private RepositoryAutor repositoryAutor;

    public Main(RepositoryLibro repositoryLibro,RepositoryAutor repositoryAutor){
        this.repositoryLibro = repositoryLibro;
        this.repositoryAutor = repositoryAutor;
    }
    public void iniciar(){
        var opcion = -1;

        while(opcion != 0){
            mostrarMenu();
            try{
                opcion = sc.nextInt();
                sc.nextLine();
            }catch (Exception e){
                System.out.println("Ingrese solo números.");
                sc.nextLine();
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;

                case 2:
                    listarLibrosRegistrados();
                    break;

                case 3:
                    listarTodosAutores();
                    break;

                case 4:
                    listarAutoresVivosPorAnio();
                    break;

                case 5:

                    listaLibrosPorIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando programa.....");
                    break;

                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                    break;
            }
        }


    }

    public static void mostrarMenu() {
        System.out.println("\n");
        System.out.println("    SISTEMA DE BIBLIOTECA\n");
        System.out.println("Elija la opción a través de su número:");
        System.out.println("1- Buscar libro por título");
        System.out.println("2- Listar libros registrados");
        System.out.println("3- Listar autores registrados");
        System.out.println("4- Listar autores vivos en un determinado año");
        System.out.println("5- Listar libros por idioma");
        System.out.println("0 - Salir");

    }

    private void buscarLibroPorTitulo() {

        System.out.println("Ingrese el título del libro: ");
        String tituloLibro = sc.nextLine();

        String json = ConsultaAPI.consultarAPI(URL_BASE + "search=" + tituloLibro.replace(" ", "%20"));
        RespuestaJsonDTO respuestaMapeada = ConversorJSON.obtenerDatos(json, RespuestaJsonDTO.class);

        List<LibroDTO> librosDTO = respuestaMapeada.results();

        librosDTO.forEach(l -> {

            //verifica que no exista en la bd
            if (repositoryLibro.existsById(l.id())) {
                System.out.println("El libro con Id " + l.id() + " ya existe en la base de datos.");
                return;
            }

            Persona autor = repositoryAutor.buscarAutorPorTodosDatos(
                    l.autores().get(0).nombre(),
                    l.autores().get(0).anioNacimiento(),
                    l.autores().get(0).anioMuerte()
            ).orElseGet(() -> new Persona(
                    l.autores().get(0).nombre(),
                    l.autores().get(0).anioNacimiento(),
                    l.autores().get(0).anioMuerte()
            ));

            Libro libro = new Libro(
                    l.id(),
                    l.titulo(),
                    autor,
                    l.resumenes().isEmpty() ? "" : l.resumenes().get(0),
                    l.temas(),
                    l.generos(),
                    l.idiomas().isEmpty() ? "" : l.idiomas().get(0),
                    l.cantidadDescargas()
            );

            repositoryLibro.save(libro);
            System.out.println("Libro guardado: " + libro.getTitulo());
        });
    }

    private void listarLibrosRegistrados() {
        List<Libro>librosTotales = repositoryLibro.findAll();
        librosTotales.forEach(libro -> System.out.println(libro));
        System.out.println("Presione una tecla para continuar...");
        sc.nextLine();
    }

    private void listarTodosAutores(){
        List<Persona> tooosAutores = repositoryAutor.listarTodosAutores();
        tooosAutores.forEach(System.out::println);
        System.out.println("Presione una tecla para continuar...");
        sc.nextLine();
    }

    private  void listarAutoresVivosPorAnio(){
        Integer anio = null;
        while (anio == null) {
            System.out.println("Ingrese el año a verificar: ");
            try {
                anio = sc.nextInt();
                sc.nextLine(); // limpiar buffer
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
                sc.nextLine();
            }
        }

        Optional<List<Persona>> autoresVivos = repositoryAutor.buscarAutorVivosPorAnio(anio);

        if(autoresVivos.isPresent()){
            autoresVivos.get().forEach(System.out::println);
        }else {
            System.out.println("No hay registro de autores vivos en ese año ");
        }
        System.out.println("Presione una tecla para continuar...");
        sc.nextLine();
    }

    private void listaLibrosPorIdioma(){

        Integer opcion = null;
        mostrarMenuIdiomas();

        while(opcion == null){
            try{
                opcion = sc.nextInt();
                sc.nextLine();
            }catch (Exception e){
                System.out.println("Ingrese un numero valido: ");
                sc.nextLine();
            }
        }

        String codIdioma = obtenerCodigoIdioma(opcion);

        List<Libro> librosEncontrados = repositoryLibro.buscarLibroPorIdioma(codIdioma);

        if(librosEncontrados.isEmpty()){
            System.out.println("No hay registro de libros en ese idioma ");
        }else{
            librosEncontrados.forEach(System.out::println);
        }
        System.out.println("Presione una tecla para continuar...");
        sc.nextLine();
    }

    public void mostrarMenuIdiomas() {
        System.out.println("Elige un idioma para buscar libros:");
        System.out.println("1) Español");
        System.out.println("2) Inglés");
        System.out.println("3) Francés");
        System.out.println("4) Alemán");
        System.out.println("5) Italiano");
        System.out.println("6) Portugués");
        System.out.println("7) Latín");
        System.out.println("8) Ruso");
        System.out.print("Ingrese el número de su elección: ");
    }

    private String obtenerCodigoIdioma(Integer opcion) {
        return switch (opcion) {
            case 1 -> "es";
            case 2 -> "en";
            case 3 -> "fr";
            case 4 -> "de";
            case 5 -> "it";
            case 6 -> "pt";
            case 7 -> "la";
            case 8 -> "ru";
            default -> "";
        };
    }




}
