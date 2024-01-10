package dev.wakandaacademy.postagem.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.wakandaacademy.postagem.domain.Postagem;

public interface PostagemRepository {
	Postagem salvaPostagem(Postagem postagem);
	List<Postagem> buscaPostagens();
	Optional<Postagem> buscaPostagemPorId(UUID idPostagem);
	void deletaPost(Postagem postagem);
	void desativaTarefa();
}
