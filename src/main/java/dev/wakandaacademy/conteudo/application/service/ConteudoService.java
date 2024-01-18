package dev.wakandaacademy.conteudo.application.service;

import java.util.List;
import java.util.UUID;

import dev.wakandaacademy.conteudo.application.api.EditaConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.api.ConteudoListResponse;
import dev.wakandaacademy.conteudo.application.api.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.ConteudoResponse;
import dev.wakandaacademy.conteudo.application.api.ConteudoUsuarioListResponse;
import dev.wakandaacademy.conteudo.domian.Conteudo;

public interface ConteudoService {

	ConteudoIdResponse criaConteudo(String email, ConteudoRequest conteudoRequest);

	List<ConteudoListResponse> buscaConteudos();

	ConteudoResponse buscaConteudoPorId(UUID idConteudo);

	Conteudo detalhaConteudoPorId(UUID idConteudo);

	List<ConteudoUsuarioListResponse> buscaConteudosDoUsuario(UUID idUsuario);

	void deletaConteudoPorId(String email, UUID idConteudo);

	void editaConteudoPorId(String email, UUID idConteudo, EditaConteudoRequest editaConteudoRequest);
}
