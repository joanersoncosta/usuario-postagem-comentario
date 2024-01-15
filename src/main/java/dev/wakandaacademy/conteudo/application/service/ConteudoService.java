package dev.wakandaacademy.conteudo.application.service;

import dev.wakandaacademy.conteudo.application.api.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.api.ConteudoRequest;

public interface ConteudoService {

	ConteudoIdResponse criaConteudo(String email, ConteudoRequest conteudoRequest);

}
