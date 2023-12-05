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
@RequestMapping("/v1/{idPostagem}/postagem")
public interface ComentarioAPI {

	@PatchMapping(value ="/comentario")
	@ResponseStatus(code = HttpStatus.OK)
	ComentarioIdResponse adicionarComentario(@PathVariable(value = "idPostagem") UUID idPostagem,
			@RequestBody ComentarioRequest comentarioRequest);

	@PatchMapping(value = "/{idComentario}/comentario/remove-comentario")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void removeComentario(@PathParam(value = "email") String email, @PathVariable(value = "idComentario") UUID idComentario, @PathVariable(value = "idPostagem") UUID idPostagem);

	@PatchMapping(value = "/{idComentario}/comentario/like")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void usuarioLike(@PathParam(value = "emailUsuarioComentario") String emailUsuarioComentario, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idComentario") UUID idComentario, @PathParam(value = "emailUsuarioLike") String emailUsuarioLike);

	@PatchMapping(value = "/{idComentario}/comentario/altera")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void alteraComentario(@PathParam(value = "emailUsuario") String emailUsuario, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idComentario") UUID idComentario, @RequestBody @Valid ComentarioAlteracaoRequest comentarioRequest);
}
