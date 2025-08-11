package com.example.Libreria;

import com.example.Libreria.Main.Main;
import com.example.Libreria.Repository.RepositoryAutor;
import com.example.Libreria.Repository.RepositoryLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibreriaApplication implements CommandLineRunner {

	@Autowired
	private RepositoryLibro repositoryLibro;

	@Autowired
	private RepositoryAutor RepositoryAutor;

	public static void main(String[] args) {
		SpringApplication.run(LibreriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repositoryLibro,RepositoryAutor);
		main.iniciar();
	}

}
