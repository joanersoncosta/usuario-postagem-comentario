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
	public void adicionarComentario(String email, UUID idPostagem, ComentarioRequest comentarioRequest) {
		log.info("[inicia] ComentarioRestController - adicionarComentario");
		comentarioService.adicionarComentario(email, idPostagem, comentarioRequest);
		log.info("[finaliza] ComentarioRestController - adicionarComentario");
	}

	@Override
	public void removeComentario(String email, UUID idPostagem) {
		log.info("[inicia] ComentarioRestController - removeComentario");
		comentarioService.removeComentario(email, idPostagem);
		log.info("[finaliza] ComentarioRestController - removeComentario");
		
	}
}
