package dev.wakandaacademy.usuario.application.api;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class UsuarioRestController implements UsuarioAPI {

	@Override
	public PessoaIdResponse postNovoUsuario(@Valid PessoaNovoRequest pessoaRequest) {
		log.info("[inicia] UsuarioRestController - postNovoUsuario");
		log.info("[finaliza] UsuarioRestController - postNovoUsuario");
		return null;
	}

}
