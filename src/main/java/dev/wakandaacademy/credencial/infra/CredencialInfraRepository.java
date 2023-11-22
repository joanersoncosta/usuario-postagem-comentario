package dev.wakandaacademy.credencial.infra;

import org.springframework.stereotype.Repository;

import dev.wakandaacademy.credencial.application.repository.CredencialRepository;
import dev.wakandaacademy.credencial.domain.Credencial;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@RequiredArgsConstructor
@Log4j2
public class CredencialInfraRepository implements CredencialRepository {

	@Override
	public Credencial salvaCredencial(Credencial credencial) {
		log.info("[inicia] CredencialInfraRepository - salvaCredencial");
		log.info("[finaliza] CredencialInfraRepository - salvaCredencial");
		return null;
	}

}
