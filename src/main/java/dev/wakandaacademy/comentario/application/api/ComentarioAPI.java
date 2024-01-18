package dev.wakandaacademy.comentario.application.api;

import java.util.List;
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
@RequestMapping("/v1/conteudo/{idConteudo}/postagem/{idPostagem}/comentario")
public interface ComentarioAPI {

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	ComentarioIdResponse adicionaComentario(@PathParam(value = "email") String email, @PathVariable(value = "idConteudo") UUID idConteudo, @PathVariable(value = "idPostagem") UUID idPostagem,
			@RequestBody @Valid ComentarioRequest comentarioRequest);

	@DeleteMapping(value = "/{idComentario}/remove-comentario")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void removeComentario(@PathParam(value = "email") String email, @PathVariable(value = "idConteudo") UUID idConteudo, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idComentario") UUID idComentario);

	@PatchMapping(value = "/{idComentario}/altera")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void alteraComentario(@PathParam(value = "email") String email, @PathVariable(value = "idConteudo") UUID idConteudo, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idComentario") UUID idComentario, @RequestBody @Valid EditaComentarioRequest comentarioRequest);

	@GetMapping(value = "/{idComentario}/busca-comentario")
	@ResponseStatus(code = HttpStatus.OK)
	ComentarioResponse buscaComentarioPorId(@PathParam(value = "email") String email, @PathVariable(value = "idConteudo") UUID idConteudo, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idComentario") UUID idComentario);
	
	@GetMapping(value = "/busca-comentarios")
	@ResponseStatus(code = HttpStatus.OK)
	List<ComentarioListResponse> buscaComentarios(@PathParam(value = "email") String email, @PathVariable(value = "idConteudo") UUID idConteudo, @PathVariable(value = "idPostagem") UUID idPostagem);

	@PatchMapping(value = "/{idComentario}/like")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void usuarioLike(@PathParam(value = "email") String email, @PathVariable(value = "idConteudo") UUID idConteudo, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idComentario") UUID idComentario);

	@PatchMapping(value = "/{idComentario}/deslike")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void usuarioDeslike(@PathParam(value = "email") String email, @PathVariable(value = "idConteudo") UUID idConteudo, @PathVariable(value = "idPostagem") UUID idPostagem, @PathVariable(value = "idComentario") UUID idComentario);

}
