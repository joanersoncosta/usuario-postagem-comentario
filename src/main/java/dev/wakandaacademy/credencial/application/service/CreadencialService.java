package dev.wakandaacademy.credencial.application.service;

import dev.wakandaacademy.credencial.domain.Credencial;
import dev.wakandaacademy.usuario.application.api.UsuarioNovoRequest;

public interface CreadencialService {
	Credencial salvaCredencial(UsuarioNovoRequest usuario);
}
