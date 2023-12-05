package dev.wakandaacademy.postagem.application.api;

import java.util.UUID;

import dev.wakandaacademy.postagem.domain.UsuarioPostagem;
import lombok.Value;

@Value
public class UsuarioPostagemResponse {
	private final UUID idUsuario;
	private final String autor;

	public UsuarioPostagemResponse(UsuarioPostagem usuario) {
		this.idUsuario = usuario.getIdUsuario();
		this.autor = usuario.getAutor();
	}

}
