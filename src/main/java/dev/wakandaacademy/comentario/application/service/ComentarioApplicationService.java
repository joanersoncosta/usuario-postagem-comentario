package dev.wakandaacademy.comentario.application.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.comentario.application.api.ComentarioAlteracaoRequest;
import dev.wakandaacademy.comentario.application.api.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.api.ComentarioRequest;
import dev.wakandaacademy.comentario.application.repository.ComentarioRepository;
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
	private final ComentarioRepository comentarioRepository;
	
	@Override
	public ComentarioIdResponse adicionaComentario(String usuarioEmail, UUID idUsuario, UUID idPostagem,
			ComentarioRequest comentarioRequest) {
		log.info("[inicia] ComentarioApplicationService - adicionaComentario");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idUsuario] {}, [idPostagem] {}", idUsuario, idPostagem);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		validaPostagem(idUsuario, idPostagem);
		Comentario comentario = comentarioRepository.adicionaComentario(new Comentario(usuario, idPostagem, comentarioRequest));
		log.info("[finaliza] ComentarioApplicationService - adicionaComentario");
		return ComentarioIdResponse.builder().idComentario(comentario.getIdComentario()).build();
	}

	@Override
	public void removeComentario(String usuarioEmail, UUID idUsuario, UUID idPostagem, UUID idComentario) {
		log.info("[inicia] ComentarioApplicationService - removeComentario");
		log.info("[usuarioEmail] {}", usuarioEmail);
		log.info("[idComentario] {}", idComentario);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Postagem postagem = validaPostagem(idUsuario, idPostagem);
		Comentario comentario = detalhaComentario(idComentario);
		comentario.pertenceUsuario(usuario, postagem);
		comentarioRepository.removeComentario(comentario);
		log.info("[finaliza] ComentarioApplicationService - removeComentario");
	}

	@Override
	public void usuarioLike(String usuarioEmail, UUID idPostagem, UUID idComentario, String emailUsuarioLike) {
		log.info("[inicia] ComentarioApplicationService - usuarioLike");
		log.info("[usuarioEmail] {}, [emailUsuarioLike] {}", usuarioEmail, emailUsuarioLike);
		log.info("[idPostagem] {}, [idComentario] {}", idPostagem, idComentario);

		usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		usuarioRepository.buscaUsuarioPorEmail(emailUsuarioLike);
		postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		log.info("[finaliza] ComentarioApplicationService - usuarioLike");
	}

	@Override
	public void alteraComentario(String emailUsuario, UUID idPostagem, UUID idComentario,
			ComentarioAlteracaoRequest comentarioRequest) {
		log.info("[inicia] ComentarioApplicationService - usuarioLike");
		log.info("[emailUsuario] {}", emailUsuario);
		log.info("[idPostagem] {}, [idComentario] {}", idPostagem, idComentario);

		usuarioRepository.buscaUsuarioPorEmail(emailUsuario);
		postagemRepository.buscaPostagemPorId(idPostagem).orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Post não encontrado!"));
		log.info("[finaliza] ComentarioApplicationService - usuarioLike");
	}

	@Override
	public Comentario detalhaComentario(UUID idComentario) {
		log.info("[inicia] ComentarioApplicationService - detalhaComentario");
		Comentario comentario = comentarioRepository.buscaComentario(idComentario)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Comentário não encontrado"));
		log.info("[finaliza] ComentarioApplicationService - detalhaComentario");
		return comentario;
	}
	
	private Postagem validaPostagem(UUID idUsuario, UUID idPostagem) {
		log.info("[inicia] ComentarioApplicationService - validaPostagem");
		Usuario usuario = usuarioRepository.buscaUsuarioPorId(idUsuario)
			.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
		Postagem postagem = postagemRepository.buscaPostagemPorId(idPostagem)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Comentário não encontrado"));
		postagem.pertenceUsuario(usuario);
		log.info("[finaliza] ComentarioApplicationService - validaPostagem");
		return postagem;
	}

}
