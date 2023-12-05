package dev.wakandaacademy.usuario.application.api;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/public/v1/usuario")
public interface UsuarioAPI {

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	UsuarioIdResponse postNovoUsuario(@RequestBody @Valid UsuarioNovoRequest usuarioRequest);

	@GetMapping(value = "/{idUsuario}")
	@ResponseStatus(code = HttpStatus.OK)
	UsuarioCriadoResponse buscaUsuarioPorId(@PathVariable(value = "idUsuario") UUID idUsuario);

}
