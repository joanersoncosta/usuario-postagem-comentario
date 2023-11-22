package dev.wakandaacademy.credencial.application.service;

import org.springframework.stereotype.Service;

import dev.wakandaacademy.credencial.application.repository.CredencialRepository;
import dev.wakandaacademy.credencial.domain.Credencial;
import dev.wakandaacademy.usuario.application.api.UsuarioNovoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CredencialApplicationService implements CreadencialService {
	private final CredencialRepository credencialRepository;
	@Override
	public Credencial salvaCredencial(UsuarioNovoRequest usuarioRequest) {
		log.info("[inicia] CredencialApplicationService - salvaCredencial");
		Credencial credencial = credencialRepository.salvaCredencial(new Credencial(usuarioRequest.getEmail(), usuarioRequest.getSenha()));
		log.info("[finaliza] CredencialApplicationService - salvaCredencial");
		return credencial;
	}

}
