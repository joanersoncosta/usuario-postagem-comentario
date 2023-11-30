package dev.wakandaacademy.postagem.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import dev.wakandaacademy.comentario.domain.Comentario;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.PostagemAlteracaoRequest;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import dev.wakandaacademy.usuario.domain.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Document(collection = "Postagem")
public class Postagem {

	@Id
	private UUID idPostagem;
	private UUID idUsuario;
	private Date data;
	@NotBlank
	@Size(min = 3, max = 50)
	private String titlo;
	@NotBlank
	@Size(min = 3, max = 250)
	private String descricao;
	@Builder.Default
	private int like = 0;
	private Set<UsuarioLikePostagem> likeUsuarios = new HashSet<>();
	private List<Comentario> comentarios = new ArrayList<>();

	public Postagem(PostagemRequest postagemRequest, Usuario usuario) {
		this.idPostagem = UUID.randomUUID();
		this.idUsuario = usuario.getIdUsuario();
		this.data = Date.from(Instant.now());
		this.titlo = postagemRequest.getTitlo();
		this.descricao = postagemRequest.getDescricao();
		this.like = 0;
	}

	public void pertenceUsuario(Usuario usuarioEmail) {
		if (!idUsuario.equals(usuarioEmail.getIdUsuario())) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não é dono do Post!");
		}
	}

	public void alteraPostagem(PostagemAlteracaoRequest postagemAlteracaoRequest) {
		this.titlo = postagemAlteracaoRequest.getTitlo();
		this.descricao = postagemAlteracaoRequest.getDescricao();
	}

	public void usuarioLike(Usuario usuarioPost) {
		var usuarioLikePost = UsuarioLikePostagem.builder().idUsuario(usuarioPost.getIdUsuario()).build();
		if (!likeUsuarios.contains(usuarioLikePost)) {
			usuarioLikePost.setLikeUsuario(true);
			like(usuarioLikePost);
		} else {
			deslike(usuarioLikePost);
		}
	}

	public void like(UsuarioLikePostagem usuarioLike) {
		this.likeUsuarios.add(usuarioLike);
		this.like += 1;
	}

	public void deslike(UsuarioLikePostagem usuarioDeslike) {
		this.likeUsuarios.remove(usuarioDeslike);
		this.like -= 1;
	}

	public void adicionarComentario(Comentario comentario) {
		this.comentarios.add(comentario);
	}

	public void usuarioLikeComentario(Usuario usuario, Postagem postagem, UUID idComentario) {
		var comentario = Comentario.builder().idUsuario(usuario.getIdUsuario()).idPostagem(idPostagem)
				.idComentario(idComentario).build();
		if (!comentarios.contains(comentario))
			throw APIException.build(HttpStatus.NOT_FOUND, "Comentário não encontrado para este Usuário!");
		comentario.incrementaLike(usuario);
	}
}
