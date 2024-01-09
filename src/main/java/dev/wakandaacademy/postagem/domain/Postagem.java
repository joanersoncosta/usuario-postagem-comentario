package dev.wakandaacademy.postagem.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.http.HttpStatus;

import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.PostagemAlteracaoRequest;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import dev.wakandaacademy.postagem.domain.enuns.StatusAtivacaoPostagem;
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
	@MongoId(value = FieldType.STRING)
	private UUID idPostagem;
	@Indexed
	private UUID idUsuario;
	private String autor;
	private LocalDateTime dataPostagem;
	@NotBlank
	@Size
	private String titlo;
	@NotBlank
	@Size
	private String descricao;
	private StatusAtivacaoPostagem statusAtivacao;
	@Builder.Default
	private int like = 0;
	private Set<PostagemUsuarioLike> likeUsuarios;

	public Postagem(PostagemRequest postagemRequest, Usuario usuario) {
		this.idPostagem = UUID.randomUUID();
		this.idUsuario = usuario.getIdUsuario();
		this.autor = usuario.getNome();
		this.dataPostagem = LocalDateTime.now();
		this.titlo = postagemRequest.getTitlo();
		this.descricao = postagemRequest.getDescricao();
		this.statusAtivacao = StatusAtivacaoPostagem.INATIVA;
		this.like = 0;
		likeUsuarios = new HashSet<>();
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

	public void usuarioLikePostagem(Usuario usuarioLike) {
		var likeComentario = PostagemUsuarioLike.builder().idUsuario(usuarioLike.getIdUsuario()).build();
		if (!likeUsuarios.contains(likeComentario)) {
			likeUsuarios.add(likeComentario);
			like(likeComentario);
		} else {
			likeUsuarios.remove(likeComentario);
			deslike(likeComentario);
		}
	}

	public void like(PostagemUsuarioLike usuarioLike) {
		this.likeUsuarios.add(usuarioLike);
		this.like += 1;
	}

	public void deslike(PostagemUsuarioLike usuarioDeslike) {
		this.likeUsuarios.remove(usuarioDeslike);
		this.like -= 1;
	}
}
