package dev.wakandaacademy.postagem.application.api;

import java.util.UUID;

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
		log.info("[inicia] PostagemRestController - criarPostagem");
		PostagemIdResponse postagemIdResponse = postagemService.criarPostagem(postagemRequest);
		log.info("[finaliza] PostagemRestController - criarPostagem");
			return postagemIdResponse;
	}

	@Override
	public PostagemResponse buscaPostagemPorId(UUID idPostagem, String email) {
		log.info("[inicia] PostagemRestController - buscaPostagemPorId");
		PostagemResponse postagemResponse = postagemService.buscaPostagemPorId(idPostagem, email);
		log.info("[finaliza] PostagemRestController - buscaPostagemPorId");
		return postagemResponse;
	}

	@Override
	public void patchAlteraPost(UUID idPostagem, String email,
			PostagemAlteracaoRequest postagemAlteracaoRequest) {
		log.info("[inicia] PostagemRestController - patchAlteraPost");
		postagemService.AlteraPostagemPorId(idPostagem, email, postagemAlteracaoRequest);
		log.info("[finaliza] PostagemRestController - patchAlteraPost");
	}

	@Override
	public void deletaPostPorId(UUID idPostagem, String email) {
		log.info("[inicia] PostagemRestController - deletaPostPorId");
		postagemService.deletaPostPorId(idPostagem, email);
		log.info("[finaliza] PostagemRestController - deletaPostPorId");
	}

	@Override
	public void usuarioLike(UUID idPostagem, String email) {
		log.info("[inicia] PostagemRestController - usuarioLike");
		postagemService.usuarioLike(idPostagem, email);
		log.info("[finaliza] PostagemRestController - usuarioLike");
	}

	@Override
	public PostagemListComentariosResponse buscaPostagemComentarios(UUID idPostagem, String email) {
		log.info("[inicia] PostagemRestController - buscaPostagemComentarios");
		PostagemListComentariosResponse postListComentarios = postagemService.buscaPostagemComentarios(idPostagem, email);
		log.info("[finaliza] PostagemRestController - buscaPostagemComentarios");
		return postListComentarios;
	}
}
