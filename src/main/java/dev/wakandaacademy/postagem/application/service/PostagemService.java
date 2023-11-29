package dev.wakandaacademy.postagem.application.service;

import java.util.UUID;

import dev.wakandaacademy.postagem.application.api.PostagemAlteracaoRequest;
import dev.wakandaacademy.postagem.application.api.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.PostagemResponse;

public interface PostagemService {
	PostagemIdResponse criarPostagem(PostagemRequest postagemRequest);
	PostagemResponse buscaPostagemPorId(UUID idPostagem, String email);
	void AlteraPostagemPorId(UUID idPostagem, String email, PostagemAlteracaoRequest postagemAlteracaoRequest);
	void deletaPostPorId(UUID idPostagem, String email);
	void incrementaLike(UUID idPostagem, String email);
	void removeLike(UUID idPostagem, String email);
}
