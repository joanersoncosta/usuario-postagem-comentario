package dev.wakandaacademy.comentario.application.repository;

import java.util.Optional;
import java.util.UUID;

import dev.wakandaacademy.comentario.domain.Comentario;

public interface ComentarioRepository {
	Comentario adicionaComentario(Comentario comentario);

	Optional<Comentario> buscaComentario(UUID idComentario);

	void removeComentario(Comentario comentario);

}
