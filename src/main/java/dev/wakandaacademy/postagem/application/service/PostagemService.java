package dev.wakandaacademy.postagem.application.service;

import java.util.List;
import java.util.UUID;

import dev.wakandaacademy.postagem.application.api.PostagemAlteracaoRequest;
import dev.wakandaacademy.postagem.application.api.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.PostagemResponse;
import dev.wakandaacademy.postagem.application.api.PostagemUsuarioListResponse;

public interface PostagemService {
	PostagemIdResponse criarPostagem(String email,PostagemRequest postagemRequest);
	List<PostagemUsuarioListResponse> buscaPostagens();
	PostagemResponse buscaPostagemPorId(UUID idPostagem);
	void AlteraPostagemPorId(String email, UUID idPostagem, PostagemAlteracaoRequest postagemAlteracaoRequest);
	void deletaPostPorId(String email, UUID idPostagem);
	void postagemUsuarioLike(String email, UUID idPostagem);
	void usuarioAtivaPostagem(String email, UUID idPostagem);
}
