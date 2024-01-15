package dev.wakandaacademy.conteudo.application.api;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.conteudo.domian.enuns.ConteudoCategoria;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ConteudoListResponse {
	private final UUID idConteudo;
	private final UUID idUsuario;
	private final String autor;
	private final String descricao;
	private final ConteudoCategoria categoria;
	private final int quantidadePostagem;
	
	private ConteudoListResponse(Conteudo conteudo) {
		this.idConteudo = conteudo.getIdConteudo();
		this.idUsuario = conteudo.getIdUsuario();
		this.autor = conteudo.getAutor();
		this.descricao = conteudo.getDescricao();
		this.categoria = conteudo.getCategoria();
		this.quantidadePostagem = conteudo.getQuantidadePostagem();
	}

	public static List<ConteudoListResponse> converte(List<Conteudo> conteudos) {
		return conteudos.stream()
				.map(ConteudoListResponse::new)
				.collect(Collectors.toList());
	}

}
