package dev.wakandaacademy.postagem.application.service;

import java.util.UUID;

import dev.wakandaacademy.postagem.application.api.PostagemIdResponse;
import dev.wakandaacademy.postagem.application.api.PostagemRequest;
import dev.wakandaacademy.postagem.application.api.PostagemResponse;

public interface PostagemService {
	PostagemIdResponse criarPostagem(String email,PostagemRequest postagemRequest);
	PostagemResponse buscaPostagemPorId(UUID idPostagem, String email);

}
