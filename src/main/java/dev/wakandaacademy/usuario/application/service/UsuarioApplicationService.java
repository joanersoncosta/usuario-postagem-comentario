package dev.wakandaacademy.usuario.application.service;

import org.springframework.stereotype.Service;

import dev.wakandaacademy.usuario.application.api.PessoaIdResponse;
import dev.wakandaacademy.usuario.application.api.PessoaNovoRequest;
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
	public PessoaIdResponse criaNovoUsuario(@Valid PessoaNovoRequest pessoaRequest) {
		log.info("[inicia] UsuarioApplicationService - criaNovoUsuario");
		Usuario usuario = usuarioRepository.salvaUsuario(new Usuario(pessoaRequest));
		log.info("[finaliza] UsuarioApplicationService - criaNovoUsuario");
		return PessoaIdResponse.builder()
				.idUsuario(usuario.getIdUsuario())
				.build();
	}

}
