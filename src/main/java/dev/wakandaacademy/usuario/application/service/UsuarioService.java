package dev.wakandaacademy.usuario.application.service;

import dev.wakandaacademy.usuario.application.api.PessoaIdResponse;
import dev.wakandaacademy.usuario.application.api.PessoaNovoRequest;
import jakarta.validation.Valid;

public interface UsuarioService {
	PessoaIdResponse criaNovoUsuario(@Valid PessoaNovoRequest pessoaRequest);

}
