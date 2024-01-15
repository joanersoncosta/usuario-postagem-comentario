package dev.wakandaacademy.conteudo.application.repository;

import java.util.List;

import dev.wakandaacademy.conteudo.domian.Conteudo;

public interface ConteudoRepository {

	Conteudo salvaConteudo(Conteudo conteudo);

	List<Conteudo> buscaConteudos();

}
