package dev.wakandaacademy.conteudo.domian;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.wakandaacademy.conteudo.domian.enuns.ConteudoCategoria;
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

	public Conteudo(UUID idUsuario, String autor, String descricao, String categoria, int quantidadePostagem) {
		this.idConteudo = UUID.randomUUID();
		this.idUsuario = idUsuario;
		this.autor = autor;
		this.descricao = descricao;
		this.categoria = retornaCategoria(categoria);
		this.quantidadePostagem = 0;
		this.momentoCricaoCategoria = LocalDateTime.now();
	}

	private ConteudoCategoria retornaCategoria(String nicho) {
		return this.categoria = ConteudoCategoria.verificaValor(nicho);
	}
}
