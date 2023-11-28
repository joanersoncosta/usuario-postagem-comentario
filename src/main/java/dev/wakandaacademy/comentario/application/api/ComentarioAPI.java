package dev.wakandaacademy.comentario.application.api;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/v1/comentario")
public interface ComentarioAPI {

	@PatchMapping(value = "/{idPostagem}/postagem")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void adicionarComentario(@PathParam(value = "email") String email, @PathVariable(value = "idPostagem") UUID idPostagem,
			@RequestBody @Valid ComentarioRequest comentarioRequest);

}
