package dev.wakandaacademy.comentario.application.service;

import java.util.UUID;

import dev.wakandaacademy.comentario.application.api.ComentarioRequest;

public interface ComentarioService {
	void adicionarComentario(String email, UUID idPostagem, ComentarioRequest comentarioRequest);
	void removeComentario(String email, UUID idComentario, UUID idPostagem);

}
