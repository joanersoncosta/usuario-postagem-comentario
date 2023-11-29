package dev.wakandaacademy.comentario.application.service;

import java.util.UUID;

import dev.wakandaacademy.comentario.application.api.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.api.ComentarioRequest;

public interface ComentarioService {
	ComentarioIdResponse adicionarComentario(UUID idPostagem, ComentarioRequest comentarioRequest);
	void removeComentario(String email, UUID idComentario, UUID idPostagem);

}
