package dev.wakandaacademy.postagem.application.service;

import org.springframework.stereotype.Service;

import dev.wakandaacademy.postagem.application.api.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostagemApplicationService implements PostagemService {

	@Override
	public PostagemIdResponse criarPostagem(PostagemRequest postagemRequest) {
		log.info("[inicia] UsuarioRestController - criarPostagem");
		log.info("[finaliza] UsuarioRestController - criarPostagem");
		return null;
	}

}
