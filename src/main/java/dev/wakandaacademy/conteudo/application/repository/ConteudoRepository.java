package dev.wakandaacademy.conteudo.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.wakandaacademy.conteudo.domian.Conteudo;

public interface ConteudoRepository {

	Conteudo salvaConteudo(Conteudo conteudo);

	List<Conteudo> buscaConteudos();
	
	Optional<Conteudo> buscaConteudoPorId(UUID idConteudo);

}