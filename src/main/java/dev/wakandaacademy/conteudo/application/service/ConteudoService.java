package dev.wakandaacademy.conteudo.application.service;

import java.util.List;
import java.util.UUID;

import dev.wakandaacademy.conteudo.application.api.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.api.ConteudoListResponse;
import dev.wakandaacademy.conteudo.application.api.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.ConteudoResponse;

public interface ConteudoService {

	ConteudoIdResponse criaConteudo(String email, ConteudoRequest conteudoRequest);

	List<ConteudoListResponse> buscaConteudos();

	ConteudoResponse buscaConteudoPorId(UUID idConteudo);

}
