package dev.wakandaacademy.comentario.application.api;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.comentario.application.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ComentarioRestController implements ComentarioAPI {
	private final ComentarioService comentarioService;

	@Override
	public ComentarioIdResponse adicionaComentario(String email, UUID idConteudo, UUID idPostagem,
			ComentarioRequest comentarioRequest) {
		log.info("[inicia] ComentarioRestController - adicionaComentario");
		ComentarioIdResponse comentarioResponse = comentarioService.adicionaComentario(email, idConteudo, idPostagem,
				comentarioRequest);
		log.info("[finaliza] ComentarioRestController - adicionaComentario");
		return comentarioResponse;
	}

	@Override
	public void removeComentario(String email, UUID idConteudo, UUID idPostagem, UUID idComentario) {
		log.info("[inicia] ComentarioRestController - removeComentario");
		comentarioService.removeComentario(email, idConteudo, idPostagem, idComentario);
		log.info("[finaliza] ComentarioRestController - removeComentario");

	}

	@Override
	public void alteraComentario(String email, UUID idConteudo, UUID idPostagem, UUID idComentario,
			EditaComentarioRequest comentarioRequest) {
		log.info("[inicia] ComentarioRestController - alteraComentario");
		comentarioService.alteraComentario(email, idConteudo, idPostagem, idComentario, comentarioRequest);
		log.info("[finaliza] ComentarioRestController - alteraComentario");
	}

	@Override
	public ComentarioResponse buscaComentarioPorId(String email, UUID idConteudo, UUID idPostagem, UUID idComentario) {
		log.info("[inicia] ComentarioRestController - buscaComentarioPorId");
		ComentarioResponse comentario = comentarioService.buscaComentarioPorId(email, idConteudo, idPostagem,
				idComentario);
		log.info("[finaliza] ComentarioRestController - buscaComentarioPorId");
		return comentario;
	}

	@Override
	public List<ComentarioListResponse> buscaComentarios(String email, UUID idConteudo, UUID idPostagem) {
		log.info("[inicia] ComentarioRestController - buscaPostagemComComentarios");
		List<ComentarioListResponse> comentarios = comentarioService.buscaComentarios(email, idConteudo, idPostagem);
		log.info("[finaliza] ComentarioRestController - buscaPostagemComComentarios");
		return comentarios;
	}
	
	@Override
	public void usuarioLike(String email, UUID idConteudo, UUID idPostagem, UUID idComentario) {
		log.info("[inicia] ComentarioRestController - usuarioLike");
		comentarioService.usuarioLike(email, idConteudo, idPostagem, idComentario);
		log.info("[finaliza] ComentarioRestController - usuarioLike");
	}
	
	@Override
	public void usuarioDeslike(String email, UUID idConteudo, UUID idPostagem, UUID idComentario) {
		log.info("[inicia] ComentarioRestController - usuarioDeslike");
		comentarioService.usuarioDeslike(email, idConteudo, idPostagem, idComentario);
		log.info("[finaliza] ComentarioRestController - usuarioDeslike");
	}
}
