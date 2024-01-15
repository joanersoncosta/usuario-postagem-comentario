package dev.wakandaacademy.comentario.application.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.wakandaacademy.comentario.domain.Comentario;
import lombok.Getter;

@Getter
public class ComentarioListResponse {
	private UUID idComentario;
	private UUID idPostagem;
	private UUID idUsuario;
	private String publicador;
	private String comentarista;
	private LocalDateTime data;
	private String descricao;
	private int like;

	private ComentarioListResponse(Comentario comentario) {
		this.idComentario = comentario.getIdComentario();
		this.idPostagem = comentario.getIdPostagem();
		this.idUsuario = comentario.getIdUsuario();
		this.publicador = comentario.getPublicador();
		this.comentarista = comentario.getComentarista();		this.data = comentario.getDataCriacaoComentario();
		this.descricao = comentario.getDescricao();
		this.like = comentario.getLike();
	}
	
	public static List<ComentarioListResponse> converte(List<Comentario> comentarios){
		return comentarios.stream()
				.map(ComentarioListResponse::new)
				.collect(Collectors.toList());
	}
}
