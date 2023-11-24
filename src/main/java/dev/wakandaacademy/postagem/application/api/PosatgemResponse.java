package dev.wakandaacademy.postagem.application.api;

import java.util.Date;
import java.util.UUID;

import dev.wakandaacademy.postagem.domain.UsuarioPostagem;
import lombok.Value;

@Value
public class PosatgemResponse {
	private final UUID idPostagem;
	private final UUID idUsuario;
	private final Date data;
	private final String titlo;
	private final String descricao;
	private final int like;
	private final UsuarioPostagem autor;
}
