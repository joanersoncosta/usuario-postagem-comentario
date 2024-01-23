package dev.wakandaacademy.postagem.application.api;

import java.time.LocalDateTime;
import java.util.UUID;

import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.postagem.domain.enuns.StatusAtivacaoPostagem;
import lombok.Value;

@Value
public class PostagemResponse {
	
	private final UUID idConteudo;
	private final UUID idPostagem;
	private final UUID idUsuario;
	private final String publicador;
	private final LocalDateTime dataPostagem;
	private final String titlo;
	private final String descricao;
	private StatusAtivacaoPostagem statusAtivacao;
	private int quantidadeComentarios;
	private final int like;
	private final int deslike;

	public PostagemResponse(Postagem postagem) {
		this.idConteudo = postagem.getIdConteudo();
		this.idPostagem = postagem.getIdPostagem();
		this.idUsuario = postagem.getIdUsuario();
		this.publicador = postagem.getPublicador();
		this.dataPostagem = postagem.getDataPostagem();
		this.titlo = postagem.getTitlo();
		this.descricao = postagem.getDescricao();
        this.statusAtivacao = postagem.getStatusAtivacao();
		this.like = postagem.getLike();
		this.quantidadeComentarios = postagem.getQuantidadeComentarios();
		this.deslike = postagem.getDeslike();
	}

	public static PostagemResponse converte(Postagem postagem) {
		return new PostagemResponse(postagem);
	}
}
