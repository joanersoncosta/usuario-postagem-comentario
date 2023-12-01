package dev.wakandaacademy.comentario.application.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.comentario.application.api.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.api.ComentarioRequest;
import dev.wakandaacademy.comentario.domain.Comentario;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.repository.PostagemRepository;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ComentarioApplicationService implements ComentarioService {
	private final PostagemRepository postagemRepository;
	private final UsuarioRepository usuarioRepository;
	
	@Override
	public ComentarioIdResponse adicionarComentario(UUID idPostagem,
			ComentarioRequest comentarioRequest) {
		log.info("[inicia] ComentarioApplicationService - adicionarComentario");
		log.info("[idPostagem], ", idPostagem);

		Usuario usuario = usuarioRepository.buscaUsuarioPorId(comentarioRequest.getIdUsuario()).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
		Comentario comentario = new Comentario(usuario, idPostagem, comentarioRequest);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagem.adicionarComentario(comentario);
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] ComentarioApplicationService - adicionarComentario");
		return ComentarioIdResponse.builder().idComentario(comentario.getIdComentario()).build();
	}

	@Override
	public void removeComentario(String usuarioEmail, UUID idComentario, UUID idPostagem) {
		log.info("[inicia] ComentarioApplicationService - removeComentario");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idPostagem] {}, [idComentario] {}", idPostagem, idComentario);

		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagem.removeComentario(usuario, idPostagem, idComentario);
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] ComentarioApplicationService - removeComentario");
	}

	@Override
	public void usuarioLike(String usuarioEmail, UUID idPostagem, UUID idComentario, String emailUsuarioLike) {
		log.info("[inicia] ComentarioApplicationService - usuarioLike");
		log.info("[usuarioEmail] {}, [emailUsuarioLike] {}", usuarioEmail, emailUsuarioLike);
		log.info("[idPostagem] {}, [idComentario] {}", idPostagem, idComentario);

		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Usuario usuarioLike = usuarioRepository.buscaUsuarioPorEmail(emailUsuarioLike);
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		postagem.usuarioLikeComentario(usuario, postagem, idComentario, usuarioLike);
		postagemRepository.salvaPostagem(postagem);
		log.info("[finaliza] ComentarioApplicationService - usuarioLike");
	}
}
