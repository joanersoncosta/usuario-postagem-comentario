package dev.wakandaacademy.postagem.application.api;

import java.util.Date;
import java.util.UUID;

import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.postagem.domain.UsuarioPostagem;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.Value;

@Value
public class PostagemResponse {
	
	private final UUID idPostagem;
	private final Date data;
	private final String titlo;
	private final String descricao;
	private final int like;
	private final UsuarioPostagem autor;
	
	public PostagemResponse(Postagem postagem, Usuario usuarioEmail) {
		this.idPostagem = postagem.getIdPostagem();
		this.data = postagem.getData();
		this.titlo = postagem.getTitlo();
		this.descricao = postagem.getDescricao();
		this.like = postagem.getLike();
		this.autor = new UsuarioPostagem(usuarioEmail);
	}
}
