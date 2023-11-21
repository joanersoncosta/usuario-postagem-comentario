package dev.wakandaacademy.usuario.application.service;

import org.springframework.stereotype.Service;

import dev.wakandaacademy.usuario.application.api.UsuarioIdResponse;
import dev.wakandaacademy.usuario.application.api.UsuarioNovoRequest;
import dev.wakandaacademy.usuario.application.repository.UsuarioRepository;
import dev.wakandaacademy.usuario.domain.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class UsuarioApplicationService implements UsuarioService {
	private final UsuarioRepository usuarioRepository;
	
	@Override
	public UsuarioIdResponse criaNovoUsuario(@Valid UsuarioNovoRequest pessoaRequest) {
		log.info("[inicia] UsuarioApplicationService - criaNovoUsuario");
		Usuario usuario = usuarioRepository.salvaUsuario(new Usuario(pessoaRequest));
		log.info("[finaliza] UsuarioApplicationService - criaNovoUsuario");
		return UsuarioIdResponse.builder()
				.idUsuario(usuario.getIdUsuario())
				.build();
	}

}
