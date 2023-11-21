package dev.wakandaacademy.usuario.application.api;

import org.springframework.http.HttpStatus;
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
	@ResponseStatus(value = HttpStatus.CREATED)
	PessoaIdResponse postNovoUsuario(@RequestBody @Valid PessoaNovoRequest pessoaRequest);

}
