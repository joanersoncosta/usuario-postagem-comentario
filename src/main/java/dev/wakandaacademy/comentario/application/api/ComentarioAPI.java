package dev.wakandaacademy.comentario.application.api;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/v1/comentario")
public interface ComentarioAPI {

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	ComentarioIdResponse adicionarComentario(@PathParam(value = "email") String email, @PathParam(value = "idPostagem") UUID idPostagem,
			@RequestBody @Valid ComentarioRequest comentarioRequest);

}
