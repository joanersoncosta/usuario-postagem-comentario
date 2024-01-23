package dev.wakandaacademy.postagem.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.handler.APIException;
import dev.wakandaacademy.postagem.application.api.EditaPostagemRequest;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import dev.wakandaacademy.postagem.domain.enuns.StatusAtivacaoPostagem;
import dev.wakandaacademy.postagem.domain.enuns.StatusLikePostagem;
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
@Document(collection = "Postagem")
@EqualsAndHashCode(of = "idPostagem")
public class Postagem {

	@Id
	private UUID idPostagem;
	private UUID idConteudo;
	@Indexed
	private UUID idUsuario;
	private String publicador;
	private LocalDateTime dataPostagem;
	@NotBlank
	@Size
	private String titlo;
	@NotBlank
	@Size
	private String descricao;
	private StatusAtivacaoPostagem statusAtivacao;
	private int quantidadeComentarios;
	private int like;
	private int deslike;
	private Set<PostagemUsuarioLike> likes;
	private Set<PostagemUsuarioLike> deslikes;

	public Postagem(Usuario usuario, UUID idConteudo, PostagemRequest postagemRequest) {
		this.idPostagem = UUID.randomUUID();
		this.idConteudo = idConteudo;
		this.idUsuario = usuario.getIdUsuario();
		this.publicador = getUserName(usuario.getEmail());
		this.dataPostagem = LocalDateTime.now();
		this.titlo = postagemRequest.getTitlo();
		this.descricao = postagemRequest.getDescricao();
		this.statusAtivacao = StatusAtivacaoPostagem.INATIVA;
		this.quantidadeComentarios = 0;
		this.like = 0;
		this.deslike = 0;
		this.likes = new HashSet<>();
		this.deslikes = new HashSet<>();
	}

	private String getUserName(String email) {
		String[] nome = email.split("@");
		return this.publicador = nome[0];
	}

	public void pertenceUsuario(Usuario usuarioEmail) {
		if (!idUsuario.equals(usuarioEmail.getIdUsuario())) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Usuário não é dono do Post!");
		}
	}
	
	public void pertenceConteudo(Conteudo conteudo) {
		if (!idConteudo.equals(conteudo.getIdConteudo())) {
			throw APIException.build(HttpStatus.UNAUTHORIZED, "Post não pertence a este Conteudo.");
		}
	}

	public void alteraPostagem(EditaPostagemRequest editaPostagemRequest) {
		this.titlo = editaPostagemRequest.getTitlo();
		this.descricao = editaPostagemRequest.getDescricao();
	}

	public void ativaPostagem() {
		this.statusAtivacao = StatusAtivacaoPostagem.ATIVO;
	}

	public void likePostagem(Usuario usuarioLike) {
		PostagemUsuarioLike likePostagem = likeUsuario(usuarioLike);
		PostagemUsuarioLike deslikeExistente = deslikeUsuario(usuarioLike);

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

	public void deslikePostagem(Usuario usuarioLike) {
		PostagemUsuarioLike deslikePostagem = deslikeUsuario(usuarioLike);
		PostagemUsuarioLike likeExistente = likeUsuario(usuarioLike);

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

	private PostagemUsuarioLike likeUsuario(Usuario usuario) {
		var likeUsuario = PostagemUsuarioLike.builder().idUsuario(usuario.getIdUsuario())
				.statusPostagem(StatusLikePostagem.LIKE).build();
		return likeUsuario;
	}

	private PostagemUsuarioLike deslikeUsuario(Usuario usuario) {
		var likeUsuario = PostagemUsuarioLike.builder().idUsuario(usuario.getIdUsuario())
				.statusPostagem(StatusLikePostagem.DESLIKE).build();
		return likeUsuario;
	}
	
	public void incrementaQuantidadeComentarios() {
		this.quantidadeComentarios ++;
	}
	
	public void reduzQuantidadeComentarios() {
		this.quantidadeComentarios --;
	}

}