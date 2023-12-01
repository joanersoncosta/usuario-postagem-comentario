package dev.wakandaacademy.comentario.domain;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import dev.wakandaacademy.comentario.application.api.ComentarioRequest;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.postagem.domain.UsuarioLikePostagem;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "idUsuario")
@Getter
@Setter
public class Comentario {

	private UUID idComentario;
	private UUID idPostagem;
	private UUID idUsuario;
	private String usuario;
	private Date data;
	private String descricao;
	private int like;
	private Set<UsuarioLikeComentario> likeUsuarios = new HashSet<>();

	public Comentario(Usuario usuario, UUID idPostagem, ComentarioRequest comentarioRequest) {
		this.idComentario = UUID.randomUUID();
		this.idPostagem = idPostagem;
		this.idUsuario = usuario.getIdUsuario();
		this.usuario = usuario.getNome();
		this.data = Date.from(Instant.now());
		this.descricao = comentarioRequest.getDescricao();
		this.like = 0;
	}

	public void usuarioLikeComentario(Usuario usuarioLike) {
		var likeComentario = UsuarioLikeComentario.builder().idUsuario(usuarioLike.getIdUsuario()).build();

		if (!likeUsuarios.contains(likeComentario)) {
			likeUsuarios.add(likeComentario);
			like(likeComentario);
		} else {
			likeUsuarios.remove(likeComentario);
			deslike(likeComentario);
		}
	}

	public void like(UsuarioLikeComentario usuarioLike) {
		this.likeUsuarios.add(usuarioLike);
		this.like += 1;
	}

	public void deslike(UsuarioLikeComentario usuarioDeslike) {
		this.likeUsuarios.remove(usuarioDeslike);
		this.like -= 1;
	}
}