package dev.wakandaacademy.postagem.application.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import dev.wakandaacademy.comentario.application.api.ComentarioListResponse;
import dev.wakandaacademy.comentario.domain.Comentario;
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
	private List<ComentarioListResponse> comentarios;

	public PostagemResponse(Postagem postagem, List<Comentario> comentarios) {
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
		this.comentarios = ComentarioListResponse.converte(comentarios);

	}

	public static PostagemResponse converte(Postagem postagem, List<Comentario> comentarios) {
		return new PostagemResponse(postagem, comentarios);
	}
}
