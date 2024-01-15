package dev.wakandaacademy.conteudo.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.conteudo.application.api.ConteudoIdResponse;
import dev.wakandaacademy.conteudo.application.api.ConteudoListResponse;
import dev.wakandaacademy.conteudo.application.api.ConteudoRequest;
import dev.wakandaacademy.conteudo.application.api.ConteudoResponse;
import dev.wakandaacademy.conteudo.application.repository.ConteudoRepository;
import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConteudoApplicationService implements ConteudoService {
	private final UsuarioRepository usuarioRepository;
	private final ConteudoRepository conteudoRepository;

	@Override
	public ConteudoIdResponse criaConteudo(String usuarioEmail, ConteudoRequest conteudoRequest) {
		log.info("[inicia] ConteudoApplicationService - criaConteudo");
		log.info("[usuarioEmail], ", usuarioEmail);
		Usuario usuario = usuarioRepository.buscaUsuarioPorEmail(usuarioEmail);
		Conteudo conteudo = conteudoRepository.salvaConteudo(new Conteudo(conteudoRequest, usuario));
		log.info("[finaliza] ConteudoApplicationService - criaConteudo");
		return ConteudoIdResponse.builder().idConteudo(conteudo.getIdConteudo()).build();
	}

	@Override
	public List<ConteudoListResponse> buscaConteudos() {
		log.info("[inicia] ConteudoApplicationService - buscaConteudos");
		List<Conteudo> conteudos = conteudoRepository.buscaConteudos();
		log.info("[finaliza] ConteudoApplicationService - buscaConteudos");
		return ConteudoListResponse.converte(conteudos);
	}

	@Override
	public ConteudoResponse buscaConteudoPorId(UUID idConteudo) {
		log.info("[inicia] ConteudoApplicationService - buscaConteudoPorId");
		Conteudo conteudo = conteudoRepository.buscaConteudoPorId(idConteudo)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Conteúdo não encontrado!"));
		log.info("[finaliza] ConteudoApplicationService - buscaConteudoPorId");
		return ConteudoResponse.converte(conteudo);
	}

}
