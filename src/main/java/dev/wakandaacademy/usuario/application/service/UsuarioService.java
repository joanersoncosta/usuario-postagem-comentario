package dev.wakandaacademy.usuario.application.service;

import dev.wakandaacademy.usuario.application.api.UsuarioIdResponse;
import dev.wakandaacademy.usuario.application.api.UsuarioNovoRequest;
import jakarta.validation.Valid;

public interface UsuarioService {
	UsuarioIdResponse criaNovoUsuario(@Valid UsuarioNovoRequest pessoaRequest);

}
