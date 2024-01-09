package dev.wakandaacademy.comentario.application.service;

import java.util.List;
import java.util.UUID;

import dev.wakandaacademy.comentario.application.api.ComentarioAlteracaoRequest;
import dev.wakandaacademy.comentario.application.api.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.api.ComentarioListResponse;
import dev.wakandaacademy.comentario.application.api.ComentarioRequest;
import dev.wakandaacademy.comentario.application.api.ComentarioResponse;
import dev.wakandaacademy.comentario.domain.Comentario;

public interface ComentarioService {
	ComentarioIdResponse adicionaComentario(String usuarioEmail, UUID idUsuario, UUID idPostagem, ComentarioRequest comentarioRequest);
	void removeComentario(String usuarioEmail, UUID idUsuario, UUID idPostagem, UUID idComentario);
	void usuarioLike(String email, UUID idUsuario, UUID idPostagem, UUID idComentario);
	Comentario detalhaComentario(UUID idComentario);
	ComentarioResponse buscaComentarioPorId(String email, UUID idUsuario, UUID idPostagem, UUID idComentario);
	void alteraComentario(String email, UUID idUsuario, UUID idPostagem, UUID idComentario,
			ComentarioAlteracaoRequest comentarioRequest);
	List<ComentarioListResponse> buscaComentarios(String usuarioEmail, UUID idUsuario, UUID idPostagem);
}
