package dev.wakandaacademy.conteudo.application.api;

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
@RequestMapping("/v1/conteudo")
public interface ConteudoAPI {

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	ConteudoIdResponse criaConteudo(@PathParam(value = "email") String email,
			@RequestBody @Valid ConteudoRequest ConteudoRequest);

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	List<ConteudoListResponse> buscaConteudos();

	@GetMapping(value = "/{idConteudo}/busca-conteudo")
	@ResponseStatus(code = HttpStatus.OK)
	ConteudoResponse buscaConteudoPorId(@PathVariable(value = "idConteudo") UUID idConteudo);

	@GetMapping(value = "/{idUsuario}/busca-conteuds-usuario")
	@ResponseStatus(code = HttpStatus.OK)
	List<ConteudoUsuarioListResponse> buscaConteudosDoUsuario(@PathVariable(value = "idUsuario") UUID idUsuario);
	
	@DeleteMapping(value = "/{idConteudo}/deleta-conteudo")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void deletaConteudoPorId(@PathParam(value = "email") String email, @PathVariable(value = "idConteudo") UUID idConteudo);

	@PatchMapping(value = "/{idConteudo}/altera-conteudo")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void editaConteudoPorId(@PathParam(value = "email") String email, @PathVariable(value = "idConteudo") UUID idConteudo,
			@RequestBody @Valid EditaConteudoRequest editaConteudoRequest);

}
