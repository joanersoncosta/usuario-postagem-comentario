package dev.wakandaacademy.comentario.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import dev.wakandaacademy.comentario.application.api.ComentarioAlteracaoRequest;
import dev.wakandaacademy.comentario.application.api.ComentarioRequest;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.usuario.domain.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Document(collection = "Comentario")
@EqualsAndHashCode(of = "idComentario")
public class Comentario {
	
	@Id
	private UUID idComentario;
	@Indexed
	private UUID idConteudo;
	@Indexed
	private UUID idPostagem;
	@Indexed
	private UUID idUsuario;
	private String usuario;
	private LocalDateTime dataCriacaoComentario;
	@NotBlank
	@Size(message = "Campo descrição comentario não pode estar vazio!", min = 3, max = 250)
	private String descricao;
	private int like;
	private Set<ComentarioUsuarioLike> likeUsuarios;

	public Comentario(Usuario usuario, Postagem postagem, ComentarioRequest comentarioRequest) {
		this.idComentario = UUID.randomUUID();
		this.idConteudo = postagem.getIdConteudo();
		this.idPostagem = postagem.getIdPostagem();
		this.idUsuario = usuario.getIdUsuario();
		this.usuario = usuario.getNome();
		this.dataCriacaoComentario = LocalDateTime.now();
		this.descricao = comentarioRequest.getDescricao();
		this.like = 0;
		likeUsuarios = new HashSet<>();
	}

	public void usuarioLikeComentario(Usuario usuarioLike) {
		var likeComentario = ComentarioUsuarioLike.builder().idUsuario(usuarioLike.getIdUsuario()).build();
		if (!likeUsuarios.contains(likeComentario)) {
			likeUsuarios.add(likeComentario);
			like(likeComentario);
		} else {
			likeUsuarios.remove(likeComentario);
			deslike(likeComentario);
		}
	}

	public void like(ComentarioUsuarioLike usuarioLike) {
		this.likeUsuarios.add(usuarioLike);
		this.like += 1;
	}

	public void deslike(ComentarioUsuarioLike usuarioDeslike) {
		this.likeUsuarios.remove(usuarioDeslike);
		this.like -= 1;
	}

	public void alteraComentario(ComentarioAlteracaoRequest comentarioRequest) {
		this.descricao = comentarioRequest.getDescricao();
	}

	public void pertenceUsuario(Usuario usuario, Postagem postagem) {
		if (!idUsuario.equals(usuario.getIdUsuario())
				&& idPostagem.equals(postagem.getIdPostagem()))
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não é o dono do Comentário!");
	}
}