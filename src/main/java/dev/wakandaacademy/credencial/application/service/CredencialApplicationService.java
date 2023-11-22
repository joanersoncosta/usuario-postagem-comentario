package dev.wakandaacademy.credencial.application.service;

import org.springframework.stereotype.Service;

import dev.wakandaacademy.credencial.domain.Credencial;
import dev.wakandaacademy.usuario.application.api.UsuarioNovoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CredencialApplicationService implements CreadencialService {

	@Override
	public Credencial salvaCredencial(UsuarioNovoRequest usuario) {
		log.info("[inicia] CredencialApplicationService - salvaCredencial");
		
		log.info("[inicia] CredencialApplicationService - salvaCredencial");
		return null;
	}

}
