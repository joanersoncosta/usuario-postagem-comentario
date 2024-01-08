package dev.wakandaacademy.comentario.application.api;

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
	public ComentarioIdResponse adicionaComentario(String email, UUID idUsuario, UUID idPostagem, ComentarioRequest comentarioRequest) {
		log.info("[inicia] ComentarioRestController - adicionaComentario");
		ComentarioIdResponse comentarioResponse = comentarioService.adicionaComentario(email, idUsuario, idPostagem, comentarioRequest);
		log.info("[finaliza] ComentarioRestController - adicionaComentario");
		return comentarioResponse;
	}

	@Override
	public void removeComentario(String email, UUID idUsuario, UUID idPostagem, UUID idComentario) {
		log.info("[inicia] ComentarioRestController - removeComentario");
		comentarioService.removeComentario(email, idUsuario, idPostagem, idComentario);
		log.info("[finaliza] ComentarioRestController - removeComentario");
		
	}

	@Override
	public void usuarioLike	(String email, UUID idUsuario, UUID idPostagem, UUID idComentario) {
		log.info("[inicia] ComentarioRestController - usuarioLike");
		comentarioService.usuarioLike(email, idUsuario, idPostagem, idComentario);
		log.info("[finaliza] ComentarioRestController - usuarioLike");
	}

	@Override
	public void alteraComentario(String email, UUID idUsuario, UUID idPostagem, UUID idComentario, ComentarioAlteracaoRequest comentarioRequest) {
		log.info("[inicia] ComentarioRestController - alteraComentario");
		comentarioService.alteraComentario(email, idUsuario, idPostagem, idComentario, comentarioRequest);
		log.info("[finaliza] ComentarioRestController - alteraComentario");
	}

	@Override
	public ComentarioResponse buscaComentarioPorId(String email, UUID idUsuario, UUID idPostagem, UUID idComentario) {
		log.info("[inicia] ComentarioRestController - buscaComentarioPorId");
		ComentarioResponse comentario = comentarioService.buscaComentarioPorId(email, idUsuario, idPostagem, idComentario);
		log.info("[finaliza] ComentarioRestController - buscaComentarioPorId");
		return comentario;
	}
}
