package dev.wakandaacademy.conteudo.application.api;

import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.conteudo.application.service.ConteudoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ConteudoRestController implements ConteudoAPI {
	private final ConteudoService conteudoService;

	@Override
	public ConteudoIdResponse criaConteudo(String email, ConteudoRequest ConteudoRequest) {
		log.info("[inicia] ConteudoRestController - criaConteudo");
		ConteudoIdResponse conteudoIdResponse = conteudoService.criaConteudo(email, ConteudoRequest);
		log.info("[finaliza] ConteudoRestController - criaConteudo");
		return conteudoIdResponse;
	}

}
