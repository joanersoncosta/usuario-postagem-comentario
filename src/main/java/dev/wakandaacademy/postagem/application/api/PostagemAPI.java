package dev.wakandaacademy.postagem.application.api;

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

import dev.wakandaacademy.comentario.application.api.ComentarioListResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/v1/conteudo/{idConteudo}/postagem")
public interface PostagemAPI {

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	PostagemIdResponse criaPostagem(@PathParam(value = "email") String email,
			@PathVariable(value = "idConteudo") UUID idConteudo, @RequestBody @Valid PostagemRequest postagemRequest);

	@GetMapping(value = "/{idPostagem}/busca-postagem")
	@ResponseStatus(code = HttpStatus.OK)
	PostagemResponse buscaPostagemPorId(@PathVariable(value = "idConteudo") UUID idConteudo,
			@PathVariable(value = "idPostagem") UUID idPostagem);

	@GetMapping(value = "/busca-postagens")
	@ResponseStatus(code = HttpStatus.OK)
	List<PostagemUsuarioListResponse> buscaPostagens(@PathVariable(value = "idConteudo") UUID idConteudo);

	@PatchMapping(value = "/{idPostagem}/altera-post")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void alteraPostPorId(@PathParam(value = "email") String email, @PathVariable(value = "idConteudo") UUID idConteudo,
			@PathVariable(value = "idPostagem") UUID idPostagem,
			@RequestBody @Valid EditaPostagemRequest editaPostagemRequest);

	@DeleteMapping(value = "/{idPostagem}/deleta-post")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void deletaPostPorId(@PathParam(value = "email") String email, @PathVariable(value = "idConteudo") UUID idConteudo,
			@PathVariable(value = "idPostagem") UUID idPostagem);

	@PatchMapping(value = "/{idPostagem}/ativa-postagem")
	@ResponseStatus(value = HttpStatus.OK)
	void usuarioAtivaPostagem(@PathVariable(value = "idConteudo") UUID idConteudo,
			@PathVariable(value = "idPostagem") UUID idPostagem);

	@PatchMapping(value = "/{idPostagem}/usuario-like")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void usuarioLikePostagem(@PathParam(value = "email") String email,
			@PathVariable(value = "idConteudo") UUID idConteudo, @PathVariable(value = "idPostagem") UUID idPostagem);

	@PatchMapping(value = "/{idPostagem}/usuario-deslike")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void usuarioDeslikePostagem(@PathParam(value = "email") String email,
			@PathVariable(value = "idConteudo") UUID idConteudo, @PathVariable(value = "idPostagem") UUID idPostagem);

}