package dev.wakandaacademy.conteudo.application.api;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.conteudo.application.service.ConteudoService;
import jakarta.validation.Valid;
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
		List<ConteudoListResponse> conteudos = conteudoService.buscaConteudos();
		log.info("[finaliza] ConteudoRestController - buscaConteudos");
		return conteudos;
	}

	@Override
	public ConteudoResponse buscaConteudoPorId(UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - buscaConteudoPorId");
		ConteudoResponse conteudo = conteudoService.buscaConteudoPorId(idConteudo);
		log.info("[finaliza] ConteudoRestController - buscaConteudoPorId");
		return conteudo;
	}

	@Override
	public List<ConteudoUsuarioListResponse> buscaConteudosDoUsuario(UUID idUsuario) {
		log.info("[inicia] ConteudoRestController - buscaConteudosDoUsuario");
		List<ConteudoUsuarioListResponse> conteudosDoUsuario = conteudoService.buscaConteudosDoUsuario(idUsuario);
		log.info("[finaliza] ConteudoRestController - buscaConteudosDoUsuario");
		return conteudosDoUsuario;
	}

	@Override
	public void deletaConteudoPorId(String email, UUID idConteudo) {
		log.info("[inicia] ConteudoRestController - deletaConteudoPorId");
		conteudoService.deletaConteudoPorId(email, idConteudo);
		log.info("[finaliza] ConteudoRestController - deletaConteudoPorId");
	}

}
