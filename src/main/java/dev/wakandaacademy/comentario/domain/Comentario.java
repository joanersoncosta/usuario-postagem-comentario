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
import dev.wakandaacademy.comentario.domain.enuns.StatusLikeComentario;
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
	private String publicador;
	private String comentarista;
	private LocalDateTime dataCriacaoComentario;
	@NotBlank
	@Size(message = "Campo descrição comentario não pode estar vazio!", min = 3, max = 250)
	private String descricao;
	private int like;
	private int deslike;
	private Set<ComentarioUsuarioLike> likes;
	private Set<ComentarioUsuarioLike> deslikes;

	public Comentario(Usuario usuario, Postagem postagem, ComentarioRequest comentarioRequest) {
		this.idComentario = UUID.randomUUID();
		this.idConteudo = postagem.getIdConteudo();
		this.idPostagem = postagem.getIdPostagem();
		this.idUsuario = usuario.getIdUsuario();
		this.publicador = postagem.getPublicador();
		this.comentarista = usuario.getNome();
		this.dataCriacaoComentario = LocalDateTime.now();
		this.descricao = comentarioRequest.getDescricao();
		this.like = 0;
		this.likes = new HashSet<>();
		this.deslikes = new HashSet<>();
	}

	public void like(Usuario usuarioLike) {
		var likePostagem = likeUsuario(usuarioLike);
		var deslikeExistente = deslikeUsuario(usuarioLike);

		if (deslikes.removeIf(deslike -> deslike.equals(deslikeExistente))) {
			this.deslike--;
		}

		if (likes.removeIf(like -> like.equals(likePostagem))) {
			this.like--;
		} else {
			likes.add(likePostagem);
			this.like++;
		}
	}
	
	public void deslike(Usuario usuarioLike) {
		var deslikePostagem = deslikeUsuario(usuarioLike);
		var likeExistente = likeUsuario(usuarioLike);

		if (likes.removeIf(like -> like.equals(likeExistente))) {
			this.like--;
		}

		if (deslikes.removeIf(deslike -> deslike.equals(deslikePostagem))) {
			this.deslike--;
		} else {
			deslikes.add(deslikePostagem);
			this.deslike++;
		}
	}

	private ComentarioUsuarioLike likeUsuario(Usuario usuario) {
		var likeUsuario = ComentarioUsuarioLike.builder().idUsuario(usuario.getIdUsuario())
				.statusComentario(StatusLikeComentario.LIKE).build();
		return likeUsuario;
	}
	
	private ComentarioUsuarioLike deslikeUsuario(Usuario usuario) {
		var likeUsuario = ComentarioUsuarioLike.builder().idUsuario(usuario.getIdUsuario())
				.statusComentario(StatusLikeComentario.DESLIKE).build();
		return likeUsuario;
	}

	public void alteraComentario(ComentarioAlteracaoRequest comentarioRequest) {
		this.descricao = comentarioRequest.getDescricao();
	}

	public void pertenceUsuario(Usuario usuario) {
		if (!idUsuario.equals(usuario.getIdUsuario()))
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não é dono do Comentário!");
	}
	
	public void pertencePost(Postagem postagem) {
		if (!idPostagem.equals(postagem.getIdPostagem()))
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Comentário não pertence a este Post!");
	}
}