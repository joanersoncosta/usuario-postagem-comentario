package dev.wakandaacademy.postagem.application.api;

import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.postagem.application.service.PostagemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class PostagemRestController implements PostagemAPI {
	private final PostagemService postagemService;

	
	@Override
	public PostagemIdResponse criarPostagem(String email, PostagemRequest postagemRequest) {
		log.info("[inicia] UsuarioRestController - criarPostagem");
		PostagemIdResponse postagemIdResponse = postagemService.criarPostagem(email, postagemRequest);
		log.info("[finaliza] UsuarioRestController - criarPostagem");
			return postagemIdResponse;
	}


	@Override
	public PostagemResponse buscaPostagemPorId(UUID idPostagem, String email) {
		log.info("[inicia] UsuarioRestController - buscaPostagemPorId");
		PostagemResponse postagemResponse = postagemService.buscaPostagemPorId(idPostagem, email);
		log.info("[finaliza] UsuarioRestController - buscaPostagemPorId");
		return postagemResponse;
	}


	@Override
	public void patchAlteraPost(UUID idPostagem, String email,
			PostagemAlteracaoRequest postagemAlteracaoRequest) {
		log.info("[inicia] UsuarioRestController - patchAlteraPost");
		postagemService.AlteraPostagemPorId(idPostagem, email, postagemAlteracaoRequest);
		log.info("[finaliza] UsuarioRestController - patchAlteraPost");
	}


	@Override
	public void deletaPostPorId(UUID idPostagem, String email) {
		log.info("[inicia] UsuarioRestController - deletaPostPorId");
		postagemService.deletaPostPorId(idPostagem, email);
		log.info("[finaliza] UsuarioRestController - deletaPostPorId");
		
	}

}
