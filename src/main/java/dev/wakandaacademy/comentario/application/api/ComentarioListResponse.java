package dev.wakandaacademy.comentario.application.api;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import dev.wakandaacademy.comentario.domain.Comentario;
import lombok.Value;

@Value
public class ComentarioListResponse {
	private UUID idComentario;
	private final String usuario;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	private final Date data;
	private final String descricao;
	private final int like;

	public ComentarioListResponse(Comentario comentario) {
		this.idComentario = comentario.getIdComentario();
		this.usuario = comentario.getUsuario();
		this.data = comentario.getData();
		this.descricao = comentario.getDescricao();
		this.like = comentario.getLike();
	}
	
	public static List<ComentarioListResponse> converte(List<Comentario> comentario){
		return comentario.stream().map(ComentarioListResponse::new)
				.collect(Collectors.toList());
	}
}
