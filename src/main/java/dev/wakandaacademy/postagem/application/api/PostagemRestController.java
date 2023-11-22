package dev.wakandaacademy.postagem.application.api;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class PostagemRestController implements PostagemAPI {

	@Override
	public PostagemIdResponse criarPostagem(PostagemRequest postagemRequest) {
		log.info("[inicia] UsuarioRestController - postNovoUsuario");
		log.info("[finaliza] UsuarioRestController - postNovoUsuario");
			return null;
	}

}
