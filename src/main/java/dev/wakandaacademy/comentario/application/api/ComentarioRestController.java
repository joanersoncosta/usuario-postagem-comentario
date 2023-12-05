package dev.wakandaacademy.comentario.application.api;

import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.comentario.application.service.ComentarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ComentarioRestController implements ComentarioAPI {
	private final ComentarioService comentarioService;
	
	@Override
	public ComentarioIdResponse adicionarComentario(UUID idPostagem, ComentarioRequest comentarioRequest) {
		log.info("[inicia] ComentarioRestController - adicionarComentario");
		ComentarioIdResponse comentarioResponse = comentarioService.adicionarComentario(idPostagem, comentarioRequest);
		log.info("[finaliza] ComentarioRestController - adicionarComentario");
		return comentarioResponse;
	}

	@Override
	public void removeComentario(String email, UUID idComentario, UUID idPostagem) {
		log.info("[inicia] ComentarioRestController - removeComentario");
		comentarioService.removeComentario(email, idComentario, idPostagem);
		log.info("[finaliza] ComentarioRestController - removeComentario");
		
	}

	@Override
	public void usuarioLike(String emailUsuarioComentario, UUID idPostagem, UUID idComentario,
			String emailUsuarioLike) {
		log.info("[inicia] ComentarioRestController - usuarioLike");
		comentarioService.usuarioLike(emailUsuarioComentario, idPostagem, idComentario, emailUsuarioLike);
		log.info("[finaliza] ComentarioRestController - usuarioLike");
	}

	@Override
	public void alteraComentario(String emailUsuario, UUID idPostagem, UUID idComentario,
			ComentarioAlteracaoRequest comentarioRequest) {
		log.info("[inicia] ComentarioRestController - alteraComentario");
		comentarioService.alteraComentario(emailUsuario, idPostagem, idComentario, comentarioRequest);
		log.info("[finaliza] ComentarioRestController - alteraComentario");
		
	}
}
