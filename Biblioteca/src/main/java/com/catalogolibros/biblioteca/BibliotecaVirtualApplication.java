package com.catalogolibros.biblioteca;


import com.catalogolibros.biblioteca.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaVirtualApplication implements CommandLineRunner {

	@Autowired
	private final Principal principal;

	public BibliotecaVirtualApplication(Principal principal){
		this.principal = principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaVirtualApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		principal.muestraMenu();
	}
}
