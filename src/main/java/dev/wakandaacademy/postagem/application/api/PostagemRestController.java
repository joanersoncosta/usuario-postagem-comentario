package dev.wakandaacademy.postagem.application.api;

import java.util.List;
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
	public PostagemIdResponse criarPostagem(String email, PostagemRequest postagemRequest) {
		log.info("[inicia] PostagemRestController - criarPostagem");
		PostagemIdResponse postagemIdResponse = postagemService.criarPostagem(email, postagemRequest);
		log.info("[finaliza] PostagemRestController - criarPostagem");
			return postagemIdResponse;
	}

	@Override
	public List<PostagemUsuarioListResponse> buscaPostagens() {
		log.info("[inicia] PostagemRestController - buscaPostagens");
		List<PostagemUsuarioListResponse> postagens = postagemService.buscaPostagens();
		log.info("[finaliza] PostagemRestController - buscaPostagens");
		return postagens;
	}
	
	@Override
	public PostagemResponse buscaPostagemPorId(UUID idPostagem) {
		log.info("[inicia] PostagemRestController - buscaPostagemPorId");
		PostagemResponse postagemResponse = postagemService.buscaPostagemPorId(idPostagem);
		log.info("[finaliza] PostagemRestController - buscaPostagemPorId");
		return postagemResponse;
	}

	@Override
	public void patchAlteraPost(String email, UUID idPostagem,
			PostagemAlteracaoRequest postagemAlteracaoRequest) {
		log.info("[inicia] PostagemRestController - patchAlteraPost");
		postagemService.AlteraPostagemPorId(email, idPostagem, postagemAlteracaoRequest);
		log.info("[finaliza] PostagemRestController - patchAlteraPost");
	}

	@Override
	public void deletaPostPorId(String email, UUID idPostagem) {
		log.info("[inicia] PostagemRestController - deletaPostPorId");
		postagemService.deletaPostPorId(email, idPostagem);
		log.info("[finaliza] PostagemRestController - deletaPostPorId");
	}

	@Override
	public void postagemUsuarioLike(String email, UUID idPostagem) {
		log.info("[inicia] PostagemRestController - postagemUsuarioLike");
		postagemService.postagemUsuarioLike(email, idPostagem);
		log.info("[finaliza] PostagemRestController - postagemUsuarioLike");
	}

	@Override
	public void usuarioAtivaPostagem(String email, UUID idPostagem) {
		log.info("[inicia] usuarioAtivaPostagem - postagemUsuarioLike");
		postagemService.usuarioAtivaPostagem(email, idPostagem);
		log.info("[finaliza] usuarioAtivaPostagem - postagemUsuarioLike");
	}

}
