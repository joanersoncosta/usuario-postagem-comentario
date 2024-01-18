package dev.wakandaacademy.comentario.application.service;

import java.util.List;
import java.util.UUID;

import dev.wakandaacademy.comentario.application.api.EditaComentarioRequest;
import dev.wakandaacademy.comentario.application.api.ComentarioIdResponse;
import dev.wakandaacademy.comentario.application.api.ComentarioListResponse;
import dev.wakandaacademy.comentario.application.api.ComentarioRequest;
import dev.wakandaacademy.comentario.application.api.ComentarioResponse;
import dev.wakandaacademy.comentario.domain.Comentario;

public interface ComentarioService {
	ComentarioIdResponse adicionaComentario(String usuarioEmail, UUID idConteudo, UUID idPostagem, ComentarioRequest comentarioRequest);
	void removeComentario(String usuarioEmail, UUID idConteudo, UUID idPostagem, UUID idComentario);
	Comentario detalhaComentario(UUID idComentario);
	ComentarioResponse buscaComentarioPorId(String email, UUID idConteudo, UUID idPostagem, UUID idComentario);
	void alteraComentario(String email, UUID idConteudo, UUID idPostagem, UUID idComentario,
			EditaComentarioRequest comentarioRequest);
	List<ComentarioListResponse> buscaComentarios(String usuarioEmail, UUID idConteudo, UUID idPostagem);
	void usuarioLike(String email, UUID idConteudo, UUID idPostagem, UUID idComentario);
	void usuarioDeslike(String email, UUID idConteudo, UUID idPostagem, UUID idComentario);

}
