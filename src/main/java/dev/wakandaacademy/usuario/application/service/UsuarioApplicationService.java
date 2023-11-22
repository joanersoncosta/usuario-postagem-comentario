package dev.wakandaacademy.usuario.application.service;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.usuario.application.api.UsuarioCriadoResponse;
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

	@Override
	public UsuarioCriadoResponse buscaUsuarioPorId(UUID idUsuario) {
		log.info("[inicia] UsuarioApplicationService - buscaUsuarioPorId");
		UsuarioCriadoResponse usuarioResponse = usuarioRepository.buscaUsuarioPorId(idUsuario)
				.map(UsuarioCriadoResponse::converteResponseParaUsuario)
				.orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Usuario não encontrado!"));
		log.info("[finaliza] UsuarioApplicationService - buscaUsuarioPorId");
		return usuarioResponse;
	}

}
