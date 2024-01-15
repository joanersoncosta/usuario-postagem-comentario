package dev.wakandaacademy.conteudo.domian;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import dev.wakandaacademy.conteudo.application.api.ConteudoRequest;
import dev.wakandaacademy.conteudo.domian.enuns.ConteudoCategoria;
import dev.wakandaacademy.handler.APIException;
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
@Document(collection = "Conteudo")
public class Conteudo {

	@Id
	private UUID idConteudo;
	@Indexed
	private UUID idUsuario;
	private String autor;
	@NotBlank(message = "Campo descricao não pode estar vazio.")
	@Size(min = 3, max = 250)
	private String descricao;
	@NotBlank(message = "Campo categoria não pode estar vazio.")
	private ConteudoCategoria categoria;
	private int quantidadePostagem;
	private LocalDateTime momentoCricaoCategoria;
	private LocalDateTime momentoAlteracaoCategoria;

	public Conteudo(ConteudoRequest conteudoRequest, Usuario usuario) {
		this.idConteudo = UUID.randomUUID();
		this.idUsuario = usuario.getIdUsuario();
		this.autor = usuario.getNome();
		this.descricao = conteudoRequest.getDescricao();
		this.categoria = retornaCategoria(conteudoRequest.getCategoria());
		this.quantidadePostagem = 0;
		this.momentoCricaoCategoria = LocalDateTime.now();
	}

	private ConteudoCategoria retornaCategoria(String categoria) {
		return ConteudoCategoria.validaCategoria(categoria)
	            .orElseThrow(() -> APIException.build(HttpStatus.BAD_REQUEST, "Categória não encontrada"));
	}

}
