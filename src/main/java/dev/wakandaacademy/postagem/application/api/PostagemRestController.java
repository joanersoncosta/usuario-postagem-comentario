package dev.wakandaacademy.postagem.application.api;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.comentario.application.api.ComentarioListResponse;
import dev.wakandaacademy.postagem.application.service.PostagemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class PostagemRestController implements PostagemAPI {
	private final PostagemService postagemService;

	@Override
	public PostagemIdResponse criarPostagem(String email, UUID idConteudo, PostagemRequest postagemRequest) {
		log.info("[inicia] PostagemRestController - criarPostagem");
		PostagemIdResponse postagemIdResponse = postagemService.criarPostagem(email, idConteudo, postagemRequest);
		log.info("[finaliza] PostagemRestController - criarPostagem");
		return postagemIdResponse;
	}

	@Override
	public List<PostagemUsuarioListResponse> buscaPostagens(UUID idConteudo) {
		log.info("[inicia] PostagemRestController - buscaPostagens");
		List<PostagemUsuarioListResponse> postagens = postagemService.buscaPostagens(idConteudo);
		log.info("[finaliza] PostagemRestController - buscaPostagens");
		return postagens;
	}

	@Override
	public PostagemResponse buscaPostagemPorId(UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] PostagemRestController - buscaPostagemPorId");
		PostagemResponse postagemResponse = postagemService.buscaPostagemPorId(idConteudo, idPostagem);
		log.info("[finaliza] PostagemRestController - buscaPostagemPorId");
		return postagemResponse;
	}

	@Override
	public void alteraPostPorId(String email, UUID idConteudo, UUID idPostagem, PostagemAlteracaoRequest postagemAlteracaoRequest) {
		log.info("[inicia] PostagemRestController - alteraPostPorId");
		postagemService.alteraPostPorId(email, idConteudo, idPostagem, postagemAlteracaoRequest);
		log.info("[finaliza] PostagemRestController - alteraPostPorId");
	}

	@Override
	public void deletaPostPorId(String email, UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] PostagemRestController - deletaPostPorId");
		postagemService.deletaPostPorId(email, idConteudo, idPostagem);
		log.info("[finaliza] PostagemRestController - deletaPostPorId");
	}

	@Override
	public List<ComentarioListResponse> usuarioAtivaPostagem(UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] usuarioAtivaPostagem - postagemUsuarioLike");
		List<ComentarioListResponse> comentariosDoPost = postagemService.usuarioAtivaPostagem(idConteudo, idPostagem);
		log.info("[finaliza] usuarioAtivaPostagem - postagemUsuarioLike");
		return comentariosDoPost;
	}

	@Override
	public void usuarioLikePostagem(String email, UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] PostagemRestController - usuarioLikePostagem");
		postagemService.usuarioLikePostagem(email, idConteudo, idPostagem);
		log.info("[finaliza] PostagemRestController - usuarioLikePostagem");
	}

	@Override
	public void usuarioDeslikePostagem(String email, UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] PostagemRestController - usuarioDeslikePostagem");
		postagemService.usuarioDeslikePostagem(email, idConteudo, idPostagem);
		log.info("[finaliza] PostagemRestController - usuarioDeslikePostagem");
	}

}