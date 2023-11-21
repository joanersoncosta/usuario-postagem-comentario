package dev.wakandaacademy.usuario.application.service;

import org.springframework.stereotype.Service;

import dev.wakandaacademy.usuario.application.api.PessoaIdResponse;
import dev.wakandaacademy.usuario.application.api.PessoaNovoRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Service
//@RequiredArgsConstructor
@Log4j2
public class UsuarioApplicationService implements UsuarioService {

	@Override
	public PessoaIdResponse criaNovoUsuario(@Valid PessoaNovoRequest pessoaRequest) {
		log.info("[inicia] UsuarioApplicationService - criaNovoUsuario");
		log.info("[finaliza] UsuarioApplicationService - criaNovoUsuario");
		return null;
	}

}
