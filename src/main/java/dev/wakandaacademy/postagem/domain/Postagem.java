package dev.wakandaacademy.postagem.domain;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@MongoId(value = FieldType.STRING)
	private UUID idPostagem;
	@Indexed
	private UUID idUsuario;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
	private Date data;
	@NotBlank
	@Size(message = "Campo titlo postagem não pode estar vazio!", min = 3, max = 50)
	private String titlo;
	@NotBlank
	@Size(message = "Campo descrição postagem não pode estar vazio!", min = 3, max = 250)
	private String descricao;
	@Builder.Default
	private int like = 0;

	public Postagem(PostagemRequest postagemRequest, Usuario usuario) {
		this.idPostagem = UUID.randomUUID();
		this.idUsuario = postagemRequest.getIdUsuario();
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
}
