package dev.wakandaacademy.comentario.application.api;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/v1/{idUsuario}/usuario/{idPostagem}/postagem")
public interface ComentarioAPI {

	@PostMapping(value ="/comentario")
	@ResponseStatus(code = HttpStatus.CREATED)
	ComentarioIdResponse adicionaComentario(@PathParam(value = "email") String email, @PathVariable(value = "idUsuario") UUID idUsuario, @PathVariable(value = "idPostagem") UUID idPostagem,
			@RequestBody @Valid ComentarioRequest comentarioRequest);

	@DeleteMapping(value = "/{idComentario}/comentario/remove-comentario")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void removeComentario(@PathParam(value = "email") String email, @PathVariable(value = "idUsuario") UUID idUsuario, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idComentario") UUID idComentario);

	@PatchMapping(value = "/{idComentario}/comentario/like")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void usuarioLike(@PathParam(value = "email") String email, @PathVariable(value = "idUsuario") UUID idUsuario, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idComentario") UUID idComentario);

	@PatchMapping(value = "/{idComentario}/comentario/altera")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void alteraComentario(@PathParam(value = "email") String email, @PathVariable(value = "idUsuario") UUID idUsuario, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idComentario") UUID idComentario, @RequestBody @Valid ComentarioAlteracaoRequest comentarioRequest);

	@GetMapping(value = "/{idComentario}/comentario/busca-comentario")
	@ResponseStatus(code = HttpStatus.OK)
	ComentarioResponse buscaComentarioPorId(@PathParam(value = "email") String email, @PathVariable(value = "idUsuario") UUID idUsuario, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idComentario") UUID idComentario);
	
}
