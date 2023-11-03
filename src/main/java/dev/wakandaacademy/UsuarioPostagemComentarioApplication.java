package dev.wakandaacademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/")
public class UsuarioPostagemComentarioApplication {

	@GetMapping
	public String getHomeText() {
		return "Pessoa Postagem - API Home";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UsuarioPostagemComentarioApplication.class, args);
	}

}
