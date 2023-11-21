package dev.wakandaacademy.usuario.application.api;

import java.util.UUID;

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
	public UsuarioIdResponse postNovoUsuario(UsuarioNovoRequest pessoaRequest) {
		log.info("[inicia] UsuarioRestController - postNovoUsuario");
		UsuarioIdResponse pessoaCriada = usuarioService.criaNovoUsuario(pessoaRequest);
		log.info("[finaliza] UsuarioRestController - postNovoUsuario");
		return pessoaCriada;
	}

	@Override
	public UsuarioCriadoResponse buscaUsuarioPorId(UUID idUsuario) {
		log.info("[inicia] UsuarioRestController - buscaUsuarioPorId");
		log.info("[finaliza] UsuarioRestController - buscaUsuarioPorId");
		return null;
	}

}
