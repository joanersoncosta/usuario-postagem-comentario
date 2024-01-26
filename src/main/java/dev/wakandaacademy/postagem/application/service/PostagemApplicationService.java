package dev.wakandaacademy.postagem.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.EditaPostagemRequest;
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
	private final ConteudoRepository  conteudoRepository;

	@Override
	public PostagemIdResponse criaPostagem(String usuarioEmail, UUID idConteudo, PostagemRequest postagemRequest) {
		log.info("[inicia] PostagemApplicationService - criaPostagem");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idConteudo] {}", idConteudo);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Conteudo conteudo = conteudoRepository.buscaConteudoPorId(idConteudo)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Conteúdo não encontrado!"));
		Postagem postagem = postagemRepository.salvaPostagem(new Postagem(usuario, conteudo.getIdConteudo(), postagemRequest));
		conteudo.incrementaQuantidadePostagem();
		conteudoRepository.salvaConteudo(conteudo);
		log.info("[finaliza] PostagemApplicationService - criaPostagem");
		return PostagemIdResponse.builder().idPostagem(postagem.getIdPostagem()).build();
	}

	@Override
	public List<PostagemUsuarioListResponse> buscaPostagens(UUID idConteudo) {
		log.info("[inicia] PostagemApplicationService - buscaPostagens");
		log.info("[idConteudo] {}", idConteudo);
		List<Postagem> postagens = postagemRepository.buscaPostagens(idConteudo);
		log.info("[finaliza] PostagemApplicationService - buscaPostagens");
		return PostagemUsuarioListResponse.converte(postagens);
	}

	@Override
	public PostagemResponse buscaPostagemPorId(UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] PostagemApplicationService - buscaPostagemPorId");
		log.info("[idConteudo] {}, [idPostagem] {}", idConteudo, idPostagem);
		Postagem postagem = detalhaPostagem(idConteudo, idPostagem);
		log.info("[finaliza] PostagemApplicationService - buscaPostagemPorId");
		return PostagemResponse.converte(postagem);
	}
	
	@Override
	public void alteraPostPorId(String usuarioEmail, UUID idConteudo, UUID idPostagem,
			EditaPostagemRequest editaPostagemRequest) {
		log.info("[inicia] PostagemApplicationService - alteraPostPorId");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idConteudo] {}, [idPostagem] {}", idConteudo, idPostagem);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = detalhaPostagem(idConteudo, idPostagem);
		postagem.pertenceUsuario(usuario);
		postagem.alteraPostagem(editaPostagemRequest);
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] PostagemApplicationService - alteraPostPorId");
	}

	@Override
	public void deletaPostPorId(String usuarioEmail, UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] PostagemApplicationService - deletaPostPorId");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idConteudo] {}, [idPostagem] {}", idConteudo, idPostagem);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Conteudo conteudo = conteudoRepository.buscaConteudoPorId(idConteudo)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Conteúdo não encontrado!"));
		Postagem postagem = detalhaPostagem(idConteudo, idPostagem);
		postagem.pertenceUsuario(usuario);
		postagemRepository.deletaPost(conteudo, postagem);
		log.info("[finaliza] PostagemApplicationService - deletaPostPorId");
	}

	@Override
	public void usuarioAtivaPostagem(UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] PostagemApplicationService - usuarioAtivaPostagem");
		log.info("[idConteudo] {}, [idPostagem] {}", idConteudo, idPostagem);
		Postagem postagem = detalhaPostagem(idConteudo, idPostagem);
		postagemRepository.desativaPostagem(idConteudo);
		postagem.ativaPostagem();
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] PostagemApplicationService - usuarioAtivaPostagem");
	}

	@Override
	public void usuarioLikePostagem(String usuarioEmail, UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] PostagemApplicationService - usuarioLikePostagem");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idConteudo] {}, [idPostagem] {}", idConteudo, idPostagem);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = detalhaPostagem(idConteudo, idPostagem);
		postagem.likePostagem(usuario);
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] PostagemApplicationService - usuarioLikePostagem");
	}

	@Override
	public void usuarioDeslikePostagem(String usuarioEmail, UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] PostagemApplicationService - usuarioDeslikePostagem");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idConteudo] {}, [idPostagem] {}", idConteudo, idPostagem);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = detalhaPostagem(idConteudo, idPostagem);
		postagem.deslikePostagem(usuario);
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] PostagemApplicationService - usuarioDeslikePostagem");
	}

	@Override
	public Postagem detalhaPostagem(UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] PostagemApplicationService - detalhaPostagem");
		Conteudo conteudo = conteudoRepository.buscaConteudoPorId(idConteudo)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Conteúdo não encontrado!"));
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagem.pertenceConteudo(conteudo);
		log.info("[finaliza] PostagemApplicationService - detalhaPostagem");
		return postagem;
	}
}