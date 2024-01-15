package dev.wakandaacademy.conteudo.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.wakandaacademy.conteudo.application.api.ConteudoAlteracaoRequest;
import dev.wakandaacademy.conteudo.domian.Conteudo;

public interface ConteudoRepository {

	Conteudo salvaConteudo(Conteudo conteudo);

	List<Conteudo> buscaConteudos();
	
	Optional<Conteudo> buscaConteudoPorId(UUID idConteudo);

	List<Conteudo> buscaConteudosDoUsuario(UUID idUsuario);

	void deletaConteudo(Conteudo conteudo);

	void editarConteudoPorId(Conteudo conteudo, ConteudoAlteracaoRequest conteudoAlteracaoRequest);

}
