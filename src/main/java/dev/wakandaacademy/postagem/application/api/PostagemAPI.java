package dev.wakandaacademy.postagem.application.api;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.usuario.application.api.UsuarioCriadoResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/v1/postagem")
public interface PostagemAPI {

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	PostagemIdResponse criarPostagem(@PathParam(value = "email") String email, @RequestBody @Valid PostagemRequest postagemRequest);

	@GetMapping(value = "/{idPostagem}")
	@ResponseStatus(code = HttpStatus.OK)
	PostagemResponse buscaPostagemPorId(@PathVariable(value = "idPostagem") UUID idPostagem, @PathParam(value = "email")String email);
}
