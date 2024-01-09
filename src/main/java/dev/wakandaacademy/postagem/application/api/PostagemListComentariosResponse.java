package dev.wakandaacademy.postagem.application.api;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import dev.wakandaacademy.postagem.domain.Postagem;
import dev.wakandaacademy.postagem.domain.UsuarioPostagem;
import dev.wakandaacademy.usuario.domain.Usuario;
import lombok.Getter;

@Getter
public class PostagemListComentariosResponse {
	private final UUID idPostagem;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT")
	private final Date data;
	private final String titlo;
	private final String descricao;
	private final int like;
	private final UsuarioPostagem autor;
//	private List<ComentarioListResponse> comentarios = new ArrayList<>();

	public PostagemListComentariosResponse(Postagem postagem, Usuario usuarioEmail) {
		this.idPostagem = postagem.getIdPostagem();
		this.data = postagem.getData();
		this.titlo = postagem.getTitlo();
		this.descricao = postagem.getDescricao();
		this.like = postagem.getLike();
		this.autor = new UsuarioPostagem(usuarioEmail);
//		this.comentarios = getComentarios(postagem);
	}
	
//	public List<ComentarioListResponse> getComentarios(Postagem postagem) {
//			List<Comentario> comentarios = postagem.getComentarios();
//			return ComentarioListResponse.converte(comentarios);
//	}
}
