package dev.wakandaacademy.postagem.application.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.PostagemAlteracaoRequest;
import dev.wakandaacademy.postagem.application.api.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.PostagemListComentariosResponse;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.PostagemResponse;
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
	public PostagemIdResponse criarPostagem(PostagemRequest postagemRequest) {
		log.info("[inicia] PostagemApplicationService - criarPostagem");
		usuarioRepository.buscaUsuarioPorId(postagemRequest.getIdUsuario());
		Postagem postagem = postagemRepository.salvaPostagem(new Postagem(postagemRequest));
		log.info("[finaliza] PostagemApplicationService - criarPostagem");
		return PostagemIdResponse.builder().idPostagem(postagem.getIdPostagem()).build();
	}

	@Override
	public PostagemResponse buscaPostagemPorId(UUID idPostagem, String email) {
		log.info("[inicia] PostagemApplicationService - buscaPostagemPorId");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagem.pertenceUsuario(usuarioEmail);
		log.info("[finaliza] PostagemApplicationService - buscaPostagemPorId");
		return new PostagemResponse(postagem, usuarioEmail);
	}
	
	@Override
	public void AlteraPostagemPorId(UUID idPostagem, String email, PostagemAlteracaoRequest postagemAlteracaoRequest) {
		log.info("[inicia] PostagemApplicationService - AlteraPostagemPorId");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		log.info("[idPostagem], ", idPostagem);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagem.pertenceUsuario(usuarioEmail);
		postagem.alteraPostagem(postagemAlteracaoRequest);
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] PostagemApplicationService - AlteraPostagemPorId");
	}

	@Override
	public void deletaPostPorId(UUID idPostagem, String email) {
		log.info("[inicia] PostagemApplicationService - deletaPostPorId");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		log.info("[idPostagem], ", idPostagem);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagem.pertenceUsuario(usuarioEmail);
		postagemRepository.deletaPost(postagem);
		log.info("[finaliza] PostagemApplicationService - deletaPostPorId");
	}

	@Override
	public void usuarioLike(UUID idPostagem, String email) {
		log.info("[inicia] PostagemApplicationService - usuarioLike");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", email);
		log.info("[idPostagem], ", idPostagem);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagem.usuarioLike(usuarioEmail);
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] PostagemApplicationService - usuarioLike");
	}

	@Override
	public PostagemListComentariosResponse buscaPostagemComentarios(UUID idPostagem, String email) {
		log.info("[inicia] PostagemApplicationService - buscaPostagemPorId");
		Usuario usuarioEmail = usuarioRepository.buscaUsuarioPorEmail(email);
		log.info("[usuarioEmail], ", usuarioEmail);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagem.pertenceUsuario(usuarioEmail);
		log.info("[finaliza] PostagemApplicationService - buscaPostagemPorId");
		return new PostagemListComentariosResponse(postagem, usuarioEmail);
	}
}
