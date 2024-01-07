package dev.wakandaacademy.comentario.application.service;

import java.util.UUID;

import dev.wakandaacademy.comentario.application.api.ComentarioAlteracaoRequest;
import dev.wakandaacademy.comentario.application.api.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.api.ComentarioRequest;

public interface ComentarioService {
	ComentarioIdResponse adicionaComentario(UUID idUsuario, UUID idPostagem, ComentarioRequest comentarioRequest);
	void removeComentario(String email, UUID idComentario, UUID idPostagem);
	void usuarioLike(String email, UUID idPostagem, UUID idComentario, String emailUsuarioLike);
	void alteraComentario(String emailUsuario, UUID idPostagem, UUID idComentario,
			ComentarioAlteracaoRequest comentarioRequest);
}
