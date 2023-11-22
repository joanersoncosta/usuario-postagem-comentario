package dev.wakandaacademy.postagem.domain;

import java.util.UUID;

import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.Value;

@Value
public class UsuarioPostagem {
	private final UUID idUsuario;
	private final String autor;

	public UsuarioPostagem(Usuario usuario) {
		this.idUsuario = usuario.getIdUsuario();
		this.autor = usuario.getNome();
	}
}
