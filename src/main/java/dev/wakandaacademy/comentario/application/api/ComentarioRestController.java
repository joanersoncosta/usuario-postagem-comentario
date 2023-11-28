package dev.wakandaacademy.comentario.application.api;

import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ComentarioRestController implements ComentarioAPI {

	@Override
	public ComentarioIdResponse adicionarComentario(String email, UUID idPostagem, @Valid ComentarioRequest comentarioRequest) {
		log.info("[inicia] ComentarioRestController - adicionarComentario");
		log.info("[finaliza] ComentarioRestController - adicionarComentario");
		return null;
	}

}
