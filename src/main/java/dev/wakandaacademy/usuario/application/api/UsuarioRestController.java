package dev.wakandaacademy.usuario.application.api;

import org.springframework.web.bind.annotation.RestController;

import dev.wakandaacademy.usuario.application.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UsuarioRestController implements UsuarioAPI {
	private final UsuarioService usuarioService;
	
	@Override
	public PessoaIdResponse postNovoUsuario(PessoaNovoRequest pessoaRequest) {
		log.info("[inicia] UsuarioRestController - postNovoUsuario");
		PessoaIdResponse pessoaCriada = usuarioService.criaNovoUsuario(pessoaRequest);
		log.info("[finaliza] UsuarioRestController - postNovoUsuario");
		return pessoaCriada;
	}

}
