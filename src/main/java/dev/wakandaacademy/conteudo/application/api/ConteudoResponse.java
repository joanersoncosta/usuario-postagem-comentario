package dev.wakandaacademy.conteudo.application.api;

import java.util.UUID;

import dev.wakandaacademy.conteudo.domian.Conteudo;
import dev.wakandaacademy.conteudo.domian.enuns.ConteudoCategoria;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ConteudoResponse {
	private final UUID idConteudo;
	private final UUID idUsuario;
	private final String autor;
	private final String descricao;
	private final ConteudoCategoria categoria;
	private final int quantidadePostagem;
	
	private ConteudoResponse(Conteudo conteudo) {
		this.idConteudo = conteudo.getIdConteudo();
		this.idUsuario = conteudo.getIdUsuario();
		this.autor = conteudo.getAutor();
		this.descricao = conteudo.getDescricao();
		this.categoria = conteudo.getCategoria();
		this.quantidadePostagem = conteudo.getQuantidadePostagem();
	}
	
	public static ConteudoResponse converte(Conteudo conteudo) {
		return new ConteudoResponse(conteudo);
	}

}
