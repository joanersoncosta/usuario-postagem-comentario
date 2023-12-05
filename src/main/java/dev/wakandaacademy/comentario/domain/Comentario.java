package dev.wakandaacademy.comentario.domain;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import dev.wakandaacademy.comentario.application.api.ComentarioAlteracaoRequest;
import dev.wakandaacademy.comentario.application.api.ComentarioRequest;
import dev.wakandaacademy.handler.APIException;
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
public class Comentario {

	private UUID idComentario;
	private UUID idPostagem;
	private UUID idUsuario;
	private String usuario;
	private Date data;
	@Setter
	private String descricao;
	@Builder.Default
	private int like = 0;
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

	public void pertenceUsuario(Comentario verificaComentario) {
		if (!idUsuario.equals(verificaComentario.getIdUsuario())
				&& idComentario.equals(verificaComentario.getIdComentario()))
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não é o dono do Comentário!");
	}

	public void alteraComentario(ComentarioAlteracaoRequest comentarioRequest) {
		setDescricao(comentarioRequest.getDescricao());
	}
}