package dev.wakandaacademy.comentario.application.api;

import java.time.LocalDateTime;
import java.util.UUID;

import dev.wakandaacademy.comentario.domain.Comentario;
import lombok.Getter;

@Getter
public class ComentarioResponse {
	private UUID idComentario;
	private UUID idPostagem;
	private UUID idUsuario;
	private String publicador;
	private String comentarista;
	private LocalDateTime data;
	private String descricao;
	private int like;

	public ComentarioResponse(Comentario comentario) {
		this.idComentario = comentario.getIdComentario();
		this.idPostagem = comentario.getIdPostagem();
		this.idUsuario = comentario.getIdUsuario();
		this.publicador = comentario.getPublicador();
		this.comentarista = comentario.getComentarista();
		this.data = comentario.getDataCriacaoComentario();
		this.descricao = comentario.getDescricao();
		this.like = comentario.getLike();
	}

	public static ComentarioResponse converte(Comentario comentario) {
		return new ComentarioResponse(comentario);
	}
}
