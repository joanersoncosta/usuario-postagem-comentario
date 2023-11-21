package dev.wakandaacademy.usuario.application.repository;

import java.util.Optional;
import java.util.UUID;

import dev.wakandaacademy.usuario.domain.Usuario;

public interface UsuarioRepository {
	Usuario salvaUsuario(Usuario usuario);
	Optional<Usuario> buscaUsuarioPorId(UUID idUsuario);
}
