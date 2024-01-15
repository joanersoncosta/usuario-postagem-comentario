package dev.wakandaacademy.conteudo.application.api;

import java.util.List;

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

	@Override
	public List<ConteudoListResponse> buscaConteudos() {
		log.info("[inicia] ConteudoRestController - buscaConteudos");
		List<ConteudoListResponse>  conteudos = conteudoService.buscaConteudos();
		log.info("[finaliza] ConteudoRestController - buscaConteudos");
		return conteudos;
	}

}
