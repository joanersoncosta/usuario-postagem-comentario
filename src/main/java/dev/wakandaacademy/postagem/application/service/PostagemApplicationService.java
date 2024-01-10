package dev.wakandaacademy.postagem.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.PostagemAlteracaoRequest;
import dev.wakandaacademy.postagem.application.api.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.PostagemResponse;
import dev.wakandaacademy.postagem.application.api.PostagemUsuarioListResponse;
import dev.wakandaacademy.postagem.application.repository.PostagemRepository;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostagemApplicationService implements PostagemService {
	private final PostagemRepository postagemRepository;
	private final UsuarioRepository usuarioRepository;
	
	@Override
	public PostagemIdResponse criarPostagem(String usuarioEmail, PostagemRequest postagemRequest) {
		log.info("[inicia] PostagemApplicationService - criarPostagem");
		log.info("[usuarioEmail], ", usuarioEmail);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = postagemRepository.salvaPostagem(new Postagem(postagemRequest, usuario));
		log.info("[finaliza] PostagemApplicationService - criarPostagem");
		return PostagemIdResponse.builder().idPostagem(postagem.getIdPostagem()).build();
	}

	@Override
	public List<PostagemUsuarioListResponse> buscaPostagens() {
		log.info("[inicia] PostagemApplicationService - buscaPostagens");
		List<Postagem> postagens = postagemRepository.buscaPostagens();
		log.info("[finaliza] PostagemApplicationService - buscaPostagens");
		return PostagemUsuarioListResponse.converte(postagens);
	}
	
	@Override
	public PostagemResponse buscaPostagemPorId(UUID idPostagem) {
		log.info("[inicia] PostagemApplicationService - buscaPostagemPorId");
		log.info("[idPostagem], ", idPostagem);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		log.info("[finaliza] PostagemApplicationService - buscaPostagemPorId");
		return new PostagemResponse(postagem);
	}
	
	@Override
	public void AlteraPostagemPorId(String usuarioEmail, UUID idPostagem, PostagemAlteracaoRequest postagemAlteracaoRequest) {
		log.info("[inicia] PostagemApplicationService - AlteraPostagemPorId");
		log.info("[usuarioEmail], ", usuarioEmail);
		log.info("[idPostagem], ", idPostagem);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagem.pertenceUsuario(usuario);		
		postagem.alteraPostagem(postagemAlteracaoRequest);
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] PostagemApplicationService - AlteraPostagemPorId");
	}

	@Override
	public void deletaPostPorId(String usuarioEmail, UUID idPostagem) {
		log.info("[inicia] PostagemApplicationService - deletaPostPorId");
		log.info("[usuarioEmail], ", usuarioEmail);
		log.info("[idPostagem], ", idPostagem);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagem.pertenceUsuario(usuario);
		postagemRepository.deletaPost(postagem);
		log.info("[finaliza] PostagemApplicationService - deletaPostPorId");
	}

	@Override
	public void postagemUsuarioLike(String usuarioEmail, UUID idPostagem) {
		log.info("[inicia] PostagemApplicationService - postagemUsuarioLike");
		log.info("[usuarioEmail], ", usuarioEmail);
		log.info("[idPostagem], ", idPostagem);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagem.usuarioLikePostagem(usuario);
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] PostagemApplicationService - postagemUsuarioLike");
	}

	@Override
	public void usuarioAtivaPostagem(UUID idPostagem) {
		log.info("[inicia] PostagemApplicationService - postagemUsuarioLike");
		log.info("[idPostagem], ", idPostagem);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagemRepository.desativaTarefa();
		postagem.ativaPostagem();
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] PostagemApplicationService - postagemUsuarioLike");
	}

}
