package dev.wakandaacademy.usuario.application.service;

import java.util.UUID;

import dev.wakandaacademy.usuario.application.api.UsuarioCriadoResponse;
import dev.wakandaacademy.usuario.application.api.UsuarioIdResponse;
import dev.wakandaacademy.usuario.application.api.UsuarioNovoRequest;
import jakarta.validation.Valid;

public interface UsuarioService {
	UsuarioIdResponse criaNovoUsuario(@Valid UsuarioNovoRequest pessoaRequest);
	UsuarioCriadoResponse buscaUsuarioPorId(UUID idUsuario);

}
