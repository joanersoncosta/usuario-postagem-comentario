package dev.wakandaacademy.postagem.application.api;

import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.postagem.application.service.PostagemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class PostagemRestController implements PostagemAPI {
	private final PostagemService postagemService;
	
	
	@Override
	public PostagemIdResponse criarPostagem(PostagemRequest postagemRequest) {
		log.info("[inicia] UsuarioRestController - criarPostagem");
		PostagemIdResponse postagemIdResponse = postagemService.criarPostagem(postagemRequest);
		log.info("[finaliza] UsuarioRestController - criarPostagem");
			return postagemIdResponse;
	}

}
