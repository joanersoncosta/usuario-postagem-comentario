package dev.wakandaacademy.postagem.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.wakandaacademy.comentario.domain.Comentario;
import dev.wakandaacademy.postagem.domain.Postagem;

public interface PostagemRepository {
	Postagem salvaPostagem(Postagem postagem);
	List<Postagem> buscaPostagens(UUID idConteudo);
	Optional<Postagem> buscaPostagemPorId(UUID idPostagem);
	void deletaPost(Postagem postagem);
	void desativaPostagem(UUID idConteudo);
	List<Comentario> buscaComentarios(UUID idPostagem);
}
