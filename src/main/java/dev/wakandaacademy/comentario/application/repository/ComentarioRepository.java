package dev.wakandaacademy.comentario.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.wakandaacademy.comentario.domain.Comentario;
import dev.wakandaacademy.postagem.domain.Postagem;

public interface ComentarioRepository {
	Comentario salvaComentario(Comentario comentario);

	Optional<Comentario> buscaComentario(UUID idComentario);

	void removeComentario(Postagem postagem, Comentario comentario);

	List<Comentario> buscaComentarios(UUID idPostagem);

}
