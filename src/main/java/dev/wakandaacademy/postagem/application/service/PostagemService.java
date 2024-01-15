package dev.wakandaacademy.postagem.application.service;

import java.util.List;
import java.util.UUID;

import dev.wakandaacademy.postagem.application.api.PostagemAlteracaoRequest;
import dev.wakandaacademy.postagem.application.api.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.PostagemResponse;
import dev.wakandaacademy.postagem.application.api.PostagemUsuarioListResponse;
import dev.wakandaacademy.postagem.domain.Postagem;

public interface PostagemService {
	PostagemIdResponse criarPostagem(String email, UUID idConteudo, PostagemRequest postagemRequest);
	List<PostagemUsuarioListResponse> buscaPostagens(UUID idConteudo);
	PostagemResponse buscaPostagemPorId(UUID idConteudo, UUID idPostagem);
	void alteraPostPorId(String email, UUID idConteudo, UUID idPostagem, PostagemAlteracaoRequest postagemAlteracaoRequest);
	void deletaPostPorId(String email, UUID idConteudo, UUID idPostagem);
	void usuarioAtivaPostagem(UUID idConteudo, UUID idPostagem);
	void usuarioLikePostagem(String email, UUID idConteudo, UUID idPostagem);
	void usuarioDeslikePostagem(String email, UUID idConteudo, UUID idPostagem);
	Postagem detalhaPostagem(UUID idConteudo, UUID idPostagem);
}