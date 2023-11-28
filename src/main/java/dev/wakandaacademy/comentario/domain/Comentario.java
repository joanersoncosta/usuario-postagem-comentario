package dev.wakandaacademy.comentario.domain;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import dev.wakandaacademy.comentario.application.api.ComentarioRequest;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "idUsuario")
@Getter
public class Comentario {

	private UUID idComentario;
	private UUID idPostagem;
	private UUID idUsuario;
	private String usuario;
	private Date data;
	private String descricao;
	private int like;

	public Comentario(Usuario usuario, UUID idPostagem, ComentarioRequest comentarioRequest) {
		this.idComentario = UUID.randomUUID();
		this.idPostagem = idPostagem;
		this.idUsuario = usuario.getIdUsuario();
		this.usuario = usuario.getNome();
		this.data = Date.from(Instant.now());
		this.descricao = comentarioRequest.getDescricao();
		this.like = 0;
	}

	public void pertencePostagem(Postagem postagem) {
		if (!idUsuario.equals(postagem.getIdUsuario())) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Comentário não ter a este Usuario!");
		}
	}

	public void incrementaLike() {
		this.like += 1;
	}

	public void removeLike() {
		this.like -= 1;
	}
}